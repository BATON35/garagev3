package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByVehicleId(Long id);

    List<Job> findByVehicleNumberPlate(String numberPlate);

    @Query(value = "select created_date as date,sum(price) as totalPrice, month(created_date) as month from job group by month", nativeQuery = true)
    List<JobStatisticIncome> getStatisticByMonth();

    interface JobStatisticIncome {
        String getDate();

        BigDecimal getTotalPrice();

    }
}

