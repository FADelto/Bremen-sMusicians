package com.example.QuietMelody.repos;

import com.example.QuietMelody.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
    @Query(value = "SELECT * FROM CART WHERE user_id=?1 AND product_id=?2",nativeQuery = true)
    List<Cart> findCartByIds(Long user_id, Long product_id);
    @Query(value = "SELECT * FROM CART WHERE user_id=?1",nativeQuery = true)
    List<Cart> findCartById(Long user_id);
    @Query(value = "DELETE FROM CART WHERE user_id=?1",nativeQuery = true)
    @Transactional
    @Modifying
    void deleteByUser_Id(Long user_id);
}