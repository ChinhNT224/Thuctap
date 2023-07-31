package com.bridgelabz.hospital.repository;

import com.bridgelabz.hospital.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT COUNT(o) FROM Order o WHERE o.ngay_tiep_nhan = :date")
    int countOrdersByNgayTao(@Param("date") LocalDate date);
}

