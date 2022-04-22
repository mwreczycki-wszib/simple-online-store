package pl.edu.wszib.simpleonlinestore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.simpleonlinestore.model.Product;
import pl.edu.wszib.simpleonlinestore.model.ProductCategory;

@Repository
public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer> {
}
