package pl.edu.wszib.simpleonlinestore.service.impl;

import org.springframework.stereotype.Service;
import pl.edu.wszib.simpleonlinestore.dao.ProductDao;
import pl.edu.wszib.simpleonlinestore.dto.ProductDTO;
import pl.edu.wszib.simpleonlinestore.dto.ProductDTO;
import pl.edu.wszib.simpleonlinestore.exceptions.NotFound;
import pl.edu.wszib.simpleonlinestore.mapper.ProductMapper;
import pl.edu.wszib.simpleonlinestore.model.Product;
import pl.edu.wszib.simpleonlinestore.model.ProductCategory;
import pl.edu.wszib.simpleonlinestore.model.TaxRate;
import pl.edu.wszib.simpleonlinestore.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao dao;
    private ProductMapper mapper;

    public ProductServiceImpl(ProductDao dao, ProductMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public List<ProductDTO> list() {
        return mapper.toDTO(dao.findAll());
    }

    @Override
    public ProductDTO get(Integer id) {
        return dao.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFound(id, Product.class));
    }

    @Override
    public ProductDTO create(ProductDTO dto) {
        dto.setId(null);
        Product toBeCreated = mapper.toDB(dto);
        return mapper.toDTO(dao.save(toBeCreated));
    }

    @Override
    public ProductDTO update(ProductDTO dto) {
        Product existing = dao.findById(dto.getId())
                .orElseThrow(() -> new NotFound(dto.getId(), Product.class));
        Product incoming = mapper.toDB(dto);
        existing.setAmount(incoming.getAmount());
        existing.setCategory(incoming.getCategory());
        existing.setName(incoming.getName());
        existing.setPrice(incoming.getPrice());
        return mapper.toDTO(dao.save(existing));
    }

    @Override
    public void delete(Integer id) {
        Product existing = dao.findById(id)
                .orElseThrow(() -> new NotFound(id, TaxRate.class));

        dao.delete(existing);
    }
}
