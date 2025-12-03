package sv.edu.ues.dam235.minirestaurantmanager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.ProductsDTO;
import sv.edu.ues.dam235.minirestaurantmanager.services.ProductServices;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {
    final private ProductServices productServices;
    private ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }
    @GetMapping
    public ResponseEntity<List<ProductsDTO>> getAllItems() {
        try {
            List<ProductsDTO> items = productServices.getAllProducts();
            if (items.isEmpty()) {
                return ResponseEntity.status(204).build();
            } else {
                return ResponseEntity.ok(items);
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }
    @PostMapping
    public ResponseEntity<ProductsDTO> createProduct(@RequestBody ProductsDTO productsDTO) {
        try {
            ProductsDTO newProduct = productServices.createProduct(productsDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        } catch (Exception e) {
            log.error("Error al crear el producto: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductsDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductsDTO productsDTO) {
        try {
            ProductsDTO updatedProduct = productServices.updateProduct(id, productsDTO);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            log.error("Error al actualizar el producto: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al actualizar el producto: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        try {
            productServices.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            log.error("Error al eliminar el producto: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al eliminar el producto: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}