package sv.edu.ues.dam235.minirestaurantmanager.services;

import sv.edu.ues.dam235.minirestaurantmanager.dtos.ProductsDTO;

import java.util.List;

public interface ProductServices {
    public List<ProductsDTO> getAllProducts();
    ProductsDTO createProduct(ProductsDTO productsDTO);
    ProductsDTO updateProduct(Integer id, ProductsDTO productsDTO);
    void deleteProduct(Integer id);
}