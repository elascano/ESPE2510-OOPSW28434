package ec.edu.espe.controller;

import ec.edu.espe.model.Product;
import ec.edu.espe.repository.ProductRepository;
import ec.edu.espe.service.TaxService;
import ec.edu.espe.view.ProductView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductController {
    private ProductView view;
    private ProductRepository repository;
    private TaxService taxService;

    public ProductController(ProductView view, ProductRepository repository, TaxService taxService) {
        this.view = view;
        this.repository = repository;
        this.taxService = taxService;

        this.view.addSaveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = view.getProductName();
                    double basePrice = view.getProductPrice();

                    double total = taxService.calculateFinalPrice(basePrice);

                    Product product = new Product(name, basePrice);
                    product.setTotalPrice(total);

                    repository.save(product);

                    view.displayMessage("Save in MongoDB\nTotal with IVA: " + total);
                    
                } catch (Exception ex) {
                    view.displayMessage("Error: invalid dates.");
                }
            }
        });
    }
}