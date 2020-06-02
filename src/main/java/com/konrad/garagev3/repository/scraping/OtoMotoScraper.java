package com.konrad.garagev3.repository.scraping;

import com.konrad.garagev3.model.dao.Car;
import com.konrad.garagev3.model.dao.Statistic;
import com.konrad.garagev3.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.concurrent.Callable;

@Repository
@Slf4j
public class OtoMotoScraper implements Callable<Object> {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Object call() {
        try {
            for (Car car : carRepository.findAll()) {
                List<Double> prices = new ArrayList<>();
                String filterUrl = String.format(
                        "https://www.otomoto.pl/osobowe/%s/%s/od-%s/?search[filter_float_year:to]=%s&search[order]=created_at:desc",
                        car.getBrand(),
                        car.getModel(),
                        car.getProductionDate(),
                        car.getProductionDate());
                Document documentPages = Jsoup.connect(filterUrl)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36")
                        .get();
                String lastPageNumber = documentPages.select("ul[class='om-pager rel'] > li:not([class='next abs']) > a").last().text();
                int lastPage;
                try {
                    lastPage = Integer.parseInt(lastPageNumber);
                } catch (NumberFormatException e) {
                    lastPage = 1;
                }
                for (int i = 1; i <= lastPage; i++) {
                    String test = filterUrl + "&page=" + i;
                    Document document = Jsoup.connect(filterUrl + "&page=" + i)
                            .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36")
                            .get();
                    document.select("span[class='offer-price__number ds-price-number'] > span:not([class='offer-price__currency ds-price-currency']) ")
                            .forEach(element -> {
                                try {
                                    prices.add(Double.valueOf(element.text().replace(" ", "")));
                                } catch (Exception e) {
                                    log.error(e.getMessage(), e);
                                }
                            });
                }
                DoubleSummaryStatistics doubleSummaryStatistics = prices.stream().mapToDouble(t -> t).summaryStatistics();
                car.setHeightPrice(doubleSummaryStatistics.getMax());
                car.setLowerPrice(doubleSummaryStatistics.getMin());
                car.setAvrPrice(doubleSummaryStatistics.getAverage());
                carRepository.save(car);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


}
