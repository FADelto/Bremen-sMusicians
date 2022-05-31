package com.example.QuietMelody.repos;

        import com.example.QuietMelody.domain.OrderList;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface OrderListRepo extends JpaRepository<OrderList,Long> {

}