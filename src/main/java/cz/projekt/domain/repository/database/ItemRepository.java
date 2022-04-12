package cz.projekt.domain.repository.database;

import cz.projekt.domain.entity.database.Item;
import cz.projekt.domain.entity.database.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    boolean existsByShoppingListIdAndProduct(Long listId, Product product);
}
