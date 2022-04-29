package com.vijay.ecommerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vijay.ecommerceapp.entity.CartProductEntity;

@Repository
public interface CartProductRepository extends JpaRepository<CartProductEntity, Long>{

	CartProductEntity findByProductIdAndCartCartId(Long productId, Long cartId);
	
	@Query("SELECT SUM(cp.total) FROM cart_product cp where cp.cart.cartId = :cartId")
	Long selectTotal(Long cartId);

	@Query("SELECT cp FROM cart_product cp where cp.productId= :productId AND cp.cart.cartId= :cartId")
	CartProductEntity findByProductIdAndCartId(Long productId, Long cartId);

}
