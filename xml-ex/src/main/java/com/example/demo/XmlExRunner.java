package com.example.demo;

import com.example.demo.entities.products.ProductsInRangeDTO;
import com.example.demo.entities.users.UsersExportDTO;
import com.example.demo.services.ProductService;
import com.example.demo.services.SeedService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@Component
public class XmlExRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public XmlExRunner(SeedService seedService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedService.seedUsers();
        seedService.seedCategories();
        seedService.seedProducts();
        seedService.seedAll();
        getAllProductsInRange();
        getAllUsersWhoHaveAtLeastOneItemSold();
    }

    private void getAllUsersWhoHaveAtLeastOneItemSold() throws JAXBException {
        UsersExportDTO users = this.userService.getAllUsersWhoHaveAtLeastOneItemSold();
        JAXBContext jaxbContext = JAXBContext.newInstance(UsersExportDTO.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(users,System.out);
    }
//Get all Products in Range (500-1000)
    private void getAllProductsInRange() throws JAXBException {
        ProductsInRangeDTO products = this.productService.getAllProductsInRange(500,1000);
        JAXBContext jaxbContext = JAXBContext.newInstance(ProductsInRangeDTO.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(products,System.out);
    }
}
