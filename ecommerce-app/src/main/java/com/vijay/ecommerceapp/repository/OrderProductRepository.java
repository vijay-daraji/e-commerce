package com.vijay.ecommerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vijay.ecommerceapp.entity.OrderProductEntity;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long>{

	OrderProductEntity findByProductIdAndOrderOrderId(long productId, Long orderId);
	
	@Query("SELECT SUM(total) FROM order_product where order_order_Id = :orderId")
	Long sumOfTotal(Long orderId);

}
