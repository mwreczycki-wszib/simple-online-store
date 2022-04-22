package pl.edu.wszib.simpleonlinestore.service.impl;

import org.springframework.stereotype.Service;
import pl.edu.wszib.simpleonlinestore.dao.ProductCategoryDao;
import pl.edu.wszib.simpleonlinestore.dto.ProductCategoryDTO;
import pl.edu.wszib.simpleonlinestore.dto.ProductCategoryDTO;
import pl.edu.wszib.simpleonlinestore.dto.ProductCategoryDTO;
import pl.edu.wszib.simpleonlinestore.exceptions.NotFound;
import pl.edu.wszib.simpleonlinestore.mapper.ProductCategoryMapper;
import pl.edu.wszib.simpleonlinestore.model.ProductCategory;
import pl.edu.wszib.simpleonlinestore.model.TaxRate;
import pl.edu.wszib.simpleonlinestore.service.ProductCategoryService;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private ProductCategoryDao dao;
    private ProductCategoryMapper mapper;

    public ProductCategoryServiceImpl(ProductCategoryDao dao, ProductCategoryMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public List<ProductCategoryDTO> list() {
        return mapper.toDTO(dao.findAll());
    }

    @Override
    public ProductCategoryDTO get(Integer id) {
        return dao.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFound(id, ProductCategory.class));
    }

    @Override
    public ProductCategoryDTO create(ProductCategoryDTO dto) {
        dto.setId(null);
        ProductCategory toBeCreated = mapper.toDB(dto);
        return mapper.toDTO(dao.save(toBeCreated));
    }

    @Override
    public ProductCategoryDTO update(ProductCategoryDTO dto) {
        ProductCategory existing = dao.findById(dto.getId())
                .orElseThrow(() -> new NotFound(dto.getId(), ProductCategory.class));
        ProductCategory incoming = mapper.toDB(dto);
        existing.setTaxRate(incoming.getTaxRate());
        existing.setName(incoming.getName());
        return mapper.toDTO(dao.save(existing));
    }

    @Override
    public void delete(Integer id) {
        ProductCategory existing = dao.findById(id)
                .orElseThrow(() -> new NotFound(id, TaxRate.class));

        dao.delete(existing);
    }
}
