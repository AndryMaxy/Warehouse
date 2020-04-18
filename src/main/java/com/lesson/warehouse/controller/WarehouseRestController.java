package com.lesson.warehouse.controller;

import com.lesson.warehouse.domain.Product;
import com.lesson.warehouse.domain.Subscriber;
import com.lesson.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class WarehouseRestController {

    @Autowired
    private WarehouseRepository repository;

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = repository.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addProduct(@RequestParam String name, @RequestParam Long cost, @RequestParam Long count) {
 //       int id = repository.generateId();
        Product product = new Product(name, cost, count);
        repository.save(product);
        if (isSaved) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam Long cost,
            @RequestParam Long count) {
        Product product = repository.findById(id).get();
//        if (product == null) {
//            return ResponseEntity.badRequest().build();
//        }
        product.setName(name);
        product.setCost(cost);
        product.setCount(count);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeById(@PathVariable Long id) {
        repository.deleteById(id);
//        if (product == null) {
//            return ResponseEntity.badRequest().build();
//        }
        return ResponseEntity.ok("");
    }
}
