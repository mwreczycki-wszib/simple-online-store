package pl.edu.wszib.simpleonlinestore.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.simpleonlinestore.dto.TaxRateDTO;
import pl.edu.wszib.simpleonlinestore.service.TaxRateService;

import java.util.List;

@RestController
@RequestMapping("/taxRate")
public class TexRateController {

    private TaxRateService service;

    public TexRateController(TaxRateService service) {
        this.service = service;
    }

    @GetMapping
    public List<TaxRateDTO> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public TaxRateDTO get(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping
    public TaxRateDTO create(@RequestBody TaxRateDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public TaxRateDTO update(@RequestBody TaxRateDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
