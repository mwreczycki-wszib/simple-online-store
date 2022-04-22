package pl.edu.wszib.simpleonlinestore.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.simpleonlinestore.dto.ProductCategoryDTO;
import pl.edu.wszib.simpleonlinestore.dto.TaxRateDTO;
import pl.edu.wszib.simpleonlinestore.service.ProductCategoryService;
import pl.edu.wszib.simpleonlinestore.service.TaxRateService;

import java.util.List;

@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {

    private ProductCategoryService service;

    public ProductCategoryController(ProductCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductCategoryDTO> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public ProductCategoryDTO get(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping
    public ProductCategoryDTO create(@RequestBody ProductCategoryDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public ProductCategoryDTO update(@RequestBody ProductCategoryDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
