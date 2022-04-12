package cz.projekt.domain.repository.database;

import cz.projekt.domain.entity.database.Product;
import cz.projekt.domain.entity.database.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList,Long> {

    boolean existsByIdAndItemList_Product(Long listId, Product product);
}
