package com.bridgelabz.hospital.repository;

import com.bridgelabz.hospital.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT COUNT(o) FROM Order o WHERE o.ngay_tiep_nhan = :date")
    int countOrdersByNgayTao(@Param("date") LocalDate date);
    @Query("SELECT o FROM Order o WHERE o.trang_thai like :trangThai and o.userCreatedBy.customerId = :customerId")
    List<Order> findByUserCreatedByCustomerId(long customerId ,String trangThai);

    @Query("SELECT o FROM Order o WHERE  o.userCreatedBy.customerId = :customerId")
    List<Order>findAllBycustomerId(long customerId );

    @Query("SELECT COUNT(o) FROM Order o WHERE o.ngay_tiep_nhan >= :startDate AND o.ngay_tiep_nhan <= :endDate")
    int countByTimePeriod(Date startDate, Date endDate);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.userConfirmedBy IS NOT NULL AND o.ngay_tiep_nhan >= :startDate AND o.ngay_tiep_nhan <= :endDate AND o.trang_thai = 'Đã xác nhận'")
    int countConfirmedByTimePeriod(Date startDate, Date endDate);


    @Query("SELECT o FROM Order o WHERE  o.userConfirmedBy.role = :role")
    List<Order> findByUserConfirmedBy_Role(String role);
}

