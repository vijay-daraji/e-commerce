package com.vijay.ecommerceapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vijay.ecommerceapp.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

	List<OrderEntity> findByPlacedDateBetween(Date start_date, Date end_date);

	List<OrderEntity> findByPlacedDate(Date placed_date);

	Long countByPlacedDate(Date placed_date);

	Long countByPlacedDateBetween(Date start_date, Date end_date);
	
	@Query("SELECT SUM(totalAmount) FROM order_details where confirmedDate BETWEEN :start_date AND :end_date")
	Long sumOfTotalAmount(Date start_date, Date end_date);


}
