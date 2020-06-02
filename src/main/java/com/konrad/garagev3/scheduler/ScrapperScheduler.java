package com.konrad.garagev3.scheduler;

import com.konrad.garagev3.repository.scraping.OtoMotoScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ScrapperScheduler {
    private ExecutorService executorService;
    @Autowired
    private OtoMotoScraper otoMotoScraper;

    @PostConstruct
    public void init() {
        executorService = Executors.newFixedThreadPool(5);
    }

    @PreDestroy
    public void destroy() {
        executorService.shutdown();
    }

  //  @Scheduled(fixedRate = 6000)
    public void runScrapper() throws InterruptedException {
        List<Callable<Object>> callableList = new ArrayList<>();
        callableList.add(otoMotoScraper);
        executorService.invokeAll(callableList);
    }
}
