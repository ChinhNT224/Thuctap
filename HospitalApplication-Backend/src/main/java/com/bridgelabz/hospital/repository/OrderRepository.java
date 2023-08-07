package com.bridgelabz.hospital.repository;

import com.bridgelabz.hospital.entity.Customer;
import com.bridgelabz.hospital.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT COUNT(o) FROM Order o WHERE o.ngay_tiep_nhan = :date")
    int countOrdersByNgayTao(@Param("date") LocalDate date);
    @Query("SELECT o FROM Order o WHERE o.trang_thai like :trangThai and o.userCreatedBy.customerId = :customerId")
    List<Order> findByUserCreatedByCustomerId(long customerId ,String trangThai);

}

