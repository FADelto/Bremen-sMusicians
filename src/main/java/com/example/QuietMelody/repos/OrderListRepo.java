package com.example.QuietMelody.repos;

        import com.example.QuietMelody.domain.Cart;
        import com.example.QuietMelody.domain.OrderList;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface OrderListRepo extends JpaRepository<OrderList,Long> {
        @Query(value = "SELECT * FROM order_list WHERE user_id=?1",nativeQuery = true)
        List<OrderList> findOrderByUserId(Long user_id);
}