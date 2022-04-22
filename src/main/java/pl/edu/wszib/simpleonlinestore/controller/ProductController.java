package pl.edu.wszib.simpleonlinestore.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.simpleonlinestore.dto.ProductDTO;
import pl.edu.wszib.simpleonlinestore.service.ProductCategoryService;
import pl.edu.wszib.simpleonlinestore.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductDTO> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public ProductDTO get(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public ProductDTO update(@RequestBody ProductDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
