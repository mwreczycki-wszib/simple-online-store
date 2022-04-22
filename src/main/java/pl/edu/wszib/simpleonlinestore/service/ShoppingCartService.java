package pl.edu.wszib.simpleonlinestore.service;

import pl.edu.wszib.simpleonlinestore.dto.ShoppingCartDTO;
import pl.edu.wszib.simpleonlinestore.dto.SimpleShoppingCartDTO;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCartDTO get();
    ShoppingCartDTO get(Integer id);
    SimpleShoppingCartDTO getSimple(Integer id);
    List<SimpleShoppingCartDTO> history();

    ShoppingCartDTO add(Integer productId, int amount);
    ShoppingCartDTO remove(Integer productId, int amount);

    ShoppingCartDTO submit(String email);
    ShoppingCartDTO clear();

}
