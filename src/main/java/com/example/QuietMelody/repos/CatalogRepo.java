package com.example.QuietMelody.repos;

import com.example.QuietMelody.domain.Catalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CatalogRepo extends JpaRepository<Catalog,Long> {
    @Query(value = "SELECT * FROM CATALOG ORDER BY id DESC",nativeQuery = true)
    Page<Catalog> findAvailable(PageRequest pageRequest);
    @Query(value = "SELECT * FROM CATALOG ORDER BY price",nativeQuery = true)
    Page<Catalog> sortAscending(PageRequest pageRequest);
    @Query(value = "SELECT * FROM CATALOG ORDER BY price DESC",nativeQuery = true)
    Page<Catalog> sortDescending(PageRequest pageRequest);
}
