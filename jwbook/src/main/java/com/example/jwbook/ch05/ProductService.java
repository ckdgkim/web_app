package com.example.jwbook.ch05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
  Map<Long, Product> products = new HashMap<>();
  private static long sequence = 0L;

  public ProductService() {
    Product p = new Product(++sequence, "Galaxy", 1000, "Samsung", "2024-10-21");
    products.put(p.getId(), p);
    p = new Product(++sequence, "iPhone", 2000, "Apple", "2024-10-21");
    products.put(p.getId(), p);
  }

  public List<Product> findAll() {
    return new ArrayList<>(products.values());
  }

  public Product findById(long id) {
    return products.get(id);
  }
}