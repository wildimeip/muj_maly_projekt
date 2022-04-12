package cz.projekt.domain.repository.database;

import cz.projekt.domain.entity.database.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT name FROM Product name WHERE lower(name.name) = lower(:name)")
    Optional<Product> findByName(String name);

}
