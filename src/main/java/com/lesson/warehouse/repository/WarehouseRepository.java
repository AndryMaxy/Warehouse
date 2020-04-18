package com.lesson.warehouse.repository;

import com.lesson.warehouse.domain.Product;
import com.lesson.warehouse.domain.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface WarehouseRepository extends JpaRepository<Product, Long> {

//    private List<Product> products = new ArrayList<>();
//
//    public boolean save(Product product) {
//        return products.add(product);
//    }
//
//    public long getProductCostSum() {
//        int sum = 0;
//        for (Product product : products) {
//            sum += product.getCost();
//        }
//        return sum;
//    }
//
//    public Product findById(long id) {
//        for (Product product : products) {
//            if (product.getId() == id) {
//                return product;
//            }
//        }
//        return null;
//    }
//
//    public Product removeById(long id) {
//        for (int i = 0; i < products.size(); i++) {
//            if (products.get(i).getId() == id) {
//                return products.remove(i);
//            }
//        }
//        return null;
//    }
//
//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public int generateId() {
//        return products.size() + 2;
//    }
}
