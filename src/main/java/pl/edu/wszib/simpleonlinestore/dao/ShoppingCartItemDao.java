package pl.edu.wszib.simpleonlinestore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.simpleonlinestore.model.ProductCategory;
import pl.edu.wszib.simpleonlinestore.model.ShoppingCart;
import pl.edu.wszib.simpleonlinestore.model.ShoppingCartItem;

import java.util.Optional;

@Repository
public interface ShoppingCartItemDao extends JpaRepository<ShoppingCartItem, Integer> {
    ShoppingCartItem findByShoppingCartAndProductId(ShoppingCart shoppingCart, Integer productId);
}
