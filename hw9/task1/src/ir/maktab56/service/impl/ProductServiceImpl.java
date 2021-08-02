package ir.maktab56.service.impl;

import ir.maktab56.base.service.impl.BaseServiceImpl;
import ir.maktab56.domain.Product;
import ir.maktab56.repository.impl.ProductRepositoryImpl;

public class ProductServiceImpl extends BaseServiceImpl<Product, Long, ProductRepositoryImpl> {

    public ProductServiceImpl(ProductRepositoryImpl repository) {
        super(repository);
    }



    public void showAllProduct() {

        for (Product product : repository.getAll())
            System.out.println(product);

        System.out.println("\n\n");
    }

}
