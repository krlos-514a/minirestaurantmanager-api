package sv.edu.ues.dam235.minirestaurantmanager.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.ProductsDTO;
import sv.edu.ues.dam235.minirestaurantmanager.entities.Product;
import sv.edu.ues.dam235.minirestaurantmanager.repositories.ProductRepository;
import sv.edu.ues.dam235.minirestaurantmanager.services.ProductServices;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductsImpl implements ProductServices {
    private final ProductRepository productRepository;
    private ProductsImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public List<ProductsDTO> getAllProducts() {
        List<ProductsDTO> result = new ArrayList<>();
        List<Product> items = this.productRepository.findAll();
        for (Product item : items) {
            result.add(new ProductsDTO(item.getCode(), item.getName(),
                    item.isStatus()));
        }
        return result;
    }

    @Override
    public ProductsDTO createProduct(ProductsDTO productsDTO) {
        Product product = new Product();
        product.setName(productsDTO.getName());
        product.setStatus(productsDTO.isStatus());
        product = productRepository.save(product);
        return new ProductsDTO(product.getCode(), product.getName(), product.isStatus());
    }

    @Override
    public ProductsDTO updateProduct(Integer id, ProductsDTO productsDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productsDTO.getName());
        product.setStatus(productsDTO.isStatus());
        product = productRepository.save(product);
        return new ProductsDTO(product.getCode(), product.getName(), product.isStatus());
    }

    @Override
    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }
}