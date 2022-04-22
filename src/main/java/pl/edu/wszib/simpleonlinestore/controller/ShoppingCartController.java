package pl.edu.wszib.simpleonlinestore.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.simpleonlinestore.dto.ShoppingCartDTO;
import pl.edu.wszib.simpleonlinestore.dto.SimpleShoppingCartDTO;
import pl.edu.wszib.simpleonlinestore.model.ShoppingCart;
import pl.edu.wszib.simpleonlinestore.service.ShoppingCartService;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public ShoppingCartDTO get() {
        return shoppingCartService.get();
    }

    @GetMapping("/{id}")
    public ShoppingCartDTO get(@PathVariable Integer id) {
        return shoppingCartService.get(id);
    }

    @GetMapping("/simple/{id}")
    public SimpleShoppingCartDTO getSimple(@PathVariable Integer id) {
        return shoppingCartService.getSimple(id);
    }

    @GetMapping("/history")
    public List<SimpleShoppingCartDTO> history() {
        return shoppingCartService.history();
    }

    @PutMapping("/add/{productId}")
    public ShoppingCartDTO add(@PathVariable Integer productId, @RequestParam(required = false, defaultValue = "1") int amount) {
        return shoppingCartService.add(productId, amount);
    }

    @PutMapping("/remove/{productId}")
    public ShoppingCartDTO remove(@PathVariable Integer productId, @RequestParam(required = false, defaultValue = "1") int amount) {
        return shoppingCartService.remove(productId, amount);
    }

    @PostMapping("/clear")
    public ShoppingCartDTO clear() {
        return shoppingCartService.clear();
    }

    @PostMapping("/submit")
    public ShoppingCartDTO submit(@RequestParam String email) {
        return shoppingCartService.submit(email);
    }
}
