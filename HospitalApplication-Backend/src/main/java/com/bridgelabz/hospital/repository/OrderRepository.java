package com.bridgelabz.hospital.repository;

import com.bridgelabz.hospital.entity.Order;
import com.bridgelabz.hospital.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Phương thức save() của JpaRepository để thêm mới bản ghi
    // Đối tượng order được truyền vào sẽ được thêm vào cơ sở dữ liệu
    Order save(Order order);
    List<Order> findByUserCreatedBy(Users userCreatedBy);
}

