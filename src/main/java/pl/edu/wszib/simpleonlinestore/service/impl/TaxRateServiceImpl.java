package pl.edu.wszib.simpleonlinestore.service.impl;

import org.springframework.stereotype.Service;
import pl.edu.wszib.simpleonlinestore.dao.TaxRateDao;
import pl.edu.wszib.simpleonlinestore.dto.TaxRateDTO;
import pl.edu.wszib.simpleonlinestore.exceptions.NotFound;
import pl.edu.wszib.simpleonlinestore.mapper.TaxRateMapper;
import pl.edu.wszib.simpleonlinestore.model.TaxRate;
import pl.edu.wszib.simpleonlinestore.service.TaxRateService;

import java.util.List;

@Service
public class TaxRateServiceImpl implements TaxRateService {

    private TaxRateDao dao;
    private TaxRateMapper mapper;

    public TaxRateServiceImpl(TaxRateDao dao, TaxRateMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public List<TaxRateDTO> list() {
        return mapper.toDTO(dao.findAll());
    }

    @Override
    public TaxRateDTO get(Integer id) {
        return dao.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFound(id, TaxRate.class));
    }

    @Override
    public TaxRateDTO create(TaxRateDTO dto) {
        dto.setId(null);
        TaxRate toBeCreated = mapper.toDB(dto);
        return mapper.toDTO(dao.save(toBeCreated));
    }

    @Override
    public TaxRateDTO update(TaxRateDTO dto) {
        TaxRate existing = dao.findById(dto.getId())
                .orElseThrow(() -> new NotFound(dto.getId(), TaxRate.class));
        TaxRate incoming = mapper.toDB(dto);
        existing.setRate(incoming.getRate());
        return mapper.toDTO(dao.save(existing));
    }

    @Override
    public void delete(Integer id) {
        TaxRate existing = dao.findById(id)
                .orElseThrow(() -> new NotFound(id, TaxRate.class));

        dao.delete(existing);
    }
}
