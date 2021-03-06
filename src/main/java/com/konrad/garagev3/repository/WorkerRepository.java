package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Worker;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface WorkerRepository extends UserRepository<Worker> {
    List<Worker> findByNameContainingOrSurnameContaining(String name, String surname);

    @Query(value = "select substring_index(substring_index(j.created_date, ' ', 1), '-', 2) as date, sum(cs.price) as price, w.name from job j join car_service cs on j.car_service_id = cs.id join login.user w on w.id = j.worker_id where (j.created_date between ?1 and ?2) group by substring_index(substring_index(j.created_date, ' ', 1), '-', 2), w.id", nativeQuery = true)
    List<ResultStatistic> getStatisticByWorker( LocalDate start, LocalDate end);

    interface ResultStatistic {
        String getDate();

        BigDecimal getPrice();

        String getName();
    }
}
