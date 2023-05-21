package com.example.demo.services;

import com.example.demo.entities.categories.Category;
import com.example.demo.entities.categories.CategoryImportDTO;
import com.example.demo.entities.products.Product;
import com.example.demo.entities.products.ProductsImportDTO;
import com.example.demo.entities.users.User;
import com.example.demo.entities.users.UserImportDTO;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

@Service
public class SeedServiceImpl implements SeedService {
    private final String USER_PATH_XML = "src/main/resources/users.xml";
    private final String CATEGORY_PATH_XML = "src/main/resources/categories.xml";
    private final String PRODUCT_PATH_XML = "src/main/resources/products.xml";
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository,
                           ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;

        this.mapper = new ModelMapper();
    }

    @Override
    public void seedUsers() throws FileNotFoundException, JAXBException {
        FileReader fileReader = new FileReader(USER_PATH_XML);

        JAXBContext jaxbContext = JAXBContext.newInstance(UserImportDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        UserImportDTO unmarshal = (UserImportDTO) unmarshaller.unmarshal(fileReader);
        List<User> usersDTO = unmarshal.getUsers().stream()
                .map(u -> mapper.map(u, User.class))
                .toList();
        this.userRepository.saveAll(usersDTO);
    }

    @Override
    public void seedCategories() throws FileNotFoundException, JAXBException {
        FileReader fileReader = new FileReader(CATEGORY_PATH_XML);
        JAXBContext jaxbContext = JAXBContext.newInstance(CategoryImportDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        CategoryImportDTO unmarshal = (CategoryImportDTO) unmarshaller.unmarshal(fileReader);
        List<Category> categoriesDTO = unmarshal.getCategories()
                .stream().map(category -> mapper.map(category, Category.class)).toList();

        this.categoryRepository.saveAll(categoriesDTO);
    }

    @Override
    public void seedProducts() throws FileNotFoundException, JAXBException {
        FileReader fileReader = new FileReader(PRODUCT_PATH_XML);
        JAXBContext jaxbContext = JAXBContext.newInstance(ProductsImportDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ProductsImportDTO unmarshal = (ProductsImportDTO) unmarshaller.unmarshal(fileReader);
        List<Product> productsDTO = unmarshal.getProducts()
                .stream()
                .map(p -> mapper.map(p, Product.class))
                .map(this::setBuyer)
                .map(this::setSeller)
                .map(this::setCategory)
                .toList();
        this.productRepository.saveAll(productsDTO);
    }

    private Product setCategory(Product product) {

            Random random = new Random();
            int categoriesCount = random.nextInt((int) categoryRepository.count()) + 1;
            Set<Category> categorySet = new HashSet<>();
            for (int i = 0; i < categoriesCount; i++) {
                Optional<Category> category = categoryRepository
                        .findById(random.nextInt((int) categoryRepository.count()) + 1);
                categorySet.add(category.get());
            }
            product.setCategories(categorySet);
            return product;


    }

    private Product setSeller(Product product) {

        int id = getRandomUserId();
        User seller = userRepository.findById(id).get();
        product.setSeller(seller);
        return product;
    }

    private Product setBuyer(Product product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(700)) > 0) {
            return product;
        }
        int id = getRandomUserId();

        User buyer = userRepository.findById(id).get();
        product.setBuyer(buyer);
        return product;
    }

    private int getRandomUserId() {
        long count = this.userRepository.count();
        Random random = new Random();
        return random.nextInt((int) count) + 1;
    }
}
