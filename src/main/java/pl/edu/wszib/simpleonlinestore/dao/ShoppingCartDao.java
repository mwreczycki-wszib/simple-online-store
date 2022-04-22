package pl.edu.wszib.simpleonlinestore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.simpleonlinestore.model.ProductCategory;
import pl.edu.wszib.simpleonlinestore.model.ShoppingCart;
import pl.edu.wszib.simpleonlinestore.model.ShoppingCartStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartDao extends JpaRepository<ShoppingCart, Integer> {
    Optional<ShoppingCart> findByStatus(ShoppingCartStatus status);
    List<ShoppingCart> findAllByStatusOrderByUpdateDateDesc(ShoppingCartStatus status);
}
