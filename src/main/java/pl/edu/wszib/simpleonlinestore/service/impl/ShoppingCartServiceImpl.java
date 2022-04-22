package pl.edu.wszib.simpleonlinestore.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.wszib.simpleonlinestore.dao.ProductDao;
import pl.edu.wszib.simpleonlinestore.dao.ShoppingCartDao;
import pl.edu.wszib.simpleonlinestore.dao.ShoppingCartItemDao;
import pl.edu.wszib.simpleonlinestore.dto.ShoppingCartDTO;
import pl.edu.wszib.simpleonlinestore.dto.SimpleShoppingCartDTO;
import pl.edu.wszib.simpleonlinestore.exceptions.AlreadyRemoved;
import pl.edu.wszib.simpleonlinestore.exceptions.CanNotSubmitEmpty;
import pl.edu.wszib.simpleonlinestore.exceptions.NotFound;
import pl.edu.wszib.simpleonlinestore.exceptions.OutOfStock;
import pl.edu.wszib.simpleonlinestore.mapper.ShoppingCartMapper;
import pl.edu.wszib.simpleonlinestore.model.Product;
import pl.edu.wszib.simpleonlinestore.model.ShoppingCart;
import pl.edu.wszib.simpleonlinestore.model.ShoppingCartItem;
import pl.edu.wszib.simpleonlinestore.model.ShoppingCartStatus;
import pl.edu.wszib.simpleonlinestore.service.ShoppingCartService;
import pl.edu.wszib.simpleonlinestore.service.ShoppingCartSubmitService;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartDao shoppingCartDao;
    private ShoppingCartItemDao shoppingCartItemDao;
    private ShoppingCartMapper mapper;
    private ShoppingCartSubmitService submitService;
    private ProductDao productDao;

    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, ShoppingCartItemDao shoppingCartItemDao, ShoppingCartMapper mapper, ShoppingCartSubmitService submitService, ProductDao productDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.shoppingCartItemDao = shoppingCartItemDao;
        this.mapper = mapper;
        this.submitService = submitService;
        this.productDao = productDao;
    }


    @Override
    @Transactional
    public ShoppingCartDTO get() {
        return mapper.toDTO(getOrCreate());
    }

    @Override
    public ShoppingCartDTO get(Integer id) {
        return shoppingCartDao
                .findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFound(id, ShoppingCart.class));
    }

    @Override
    public SimpleShoppingCartDTO getSimple(Integer id) {
        return shoppingCartDao
                .findById(id)
                .map(mapper::toSimpleDTO)
                .orElseThrow(() -> new NotFound(id, ShoppingCart.class));
    }

    @Override
    public List<SimpleShoppingCartDTO> history() {
        return mapper.toSimpleDTO(shoppingCartDao
                .findAllByStatusOrderByUpdateDateDesc(ShoppingCartStatus.SUBMITTED));
    }

    @Override
    @Transactional
    public ShoppingCartDTO add(Integer productId, int amount) {
        ShoppingCart shoppingCart = getOrCreate();
        Product product = getProduct(productId);
        ShoppingCartItem shoppingCartItem = shoppingCartItemDao.findByShoppingCartAndProductId(shoppingCart, productId);
        boolean newlyAdded = false;
        if(shoppingCartItem != null) {
            shoppingCartItem.setAmount(shoppingCartItem.getAmount() + amount);
        } else {
            shoppingCartItem = new ShoppingCartItem();
            shoppingCartItem.setAmount(1);
            shoppingCartItem.setProduct(product);
            shoppingCartItem.setShoppingCart(shoppingCart);
            newlyAdded = true;
        }

        if(product.getAmount() < shoppingCartItem.getAmount()) {
            throw new OutOfStock();
        }

        shoppingCartItem = shoppingCartItemDao.save(shoppingCartItem);

        if(newlyAdded) {
            shoppingCart.getItems().add(shoppingCartItem);
        }

        return mapper.toDTO(shoppingCartDao.save(shoppingCart));
    }

    @Override
    @Transactional
    public ShoppingCartDTO remove(Integer productId, int amount) {
        ShoppingCart shoppingCart = getOrCreate();
        Product product = getProduct(productId);
        ShoppingCartItem shoppingCartItem = shoppingCartItemDao.findByShoppingCartAndProductId(shoppingCart, productId);
        if(shoppingCartItem == null) {
            throw new AlreadyRemoved();
        }

        shoppingCartItem.setAmount(shoppingCartItem.getAmount() - amount);

        if(shoppingCartItem.getAmount() <= 0) {
            shoppingCart.getItems().remove(shoppingCartItem);
            shoppingCartItemDao.delete(shoppingCartItem);
        } else {
            shoppingCartItemDao.save(shoppingCartItem);
        }

        return mapper.toDTO(shoppingCartDao.save(shoppingCart));
    }

    @Override
    @Transactional
    public ShoppingCartDTO submit(String email) {
        ShoppingCart shoppingCart = getOrCreate();
        if(shoppingCart.getItems().isEmpty()) {
            throw new CanNotSubmitEmpty();
        }
        submitService.submit(shoppingCart, email);
        shoppingCart.setStatus(ShoppingCartStatus.SUBMITTED);
        return mapper.toDTO(shoppingCartDao.save(shoppingCart));
    }

    @Override
    @Transactional
    public ShoppingCartDTO clear() {
        ShoppingCart shoppingCart = getOrCreate();
        shoppingCartItemDao.deleteAll(shoppingCart.getItems());
        shoppingCart.getItems().clear();
        return mapper.toDTO(shoppingCartDao.save(shoppingCart));
    }

    private Product getProduct(Integer id) {
        return productDao
                .findById(id)
                .orElseThrow(() -> new NotFound(id, Product.class));
    }

    private ShoppingCart getOrCreate() {
        return shoppingCartDao.findByStatus(ShoppingCartStatus.NEW)
                .orElseGet(this::create);
    }

    private ShoppingCart create() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setStatus(ShoppingCartStatus.NEW);
        return shoppingCartDao.save(shoppingCart);
    }
}
