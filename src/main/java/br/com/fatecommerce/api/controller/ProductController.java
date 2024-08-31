package br.com.fatecommerce.api.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatecommerce.api.entity.Product;
import br.com.fatecommerce.api.service.ProductService;

@RestController
@RequestMapping(value = "/api/v1/product")
@CrossOrigin("*")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Object> saveProduct(@RequestBody Product category) {
        Product result = productService.saveProduct(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listProduct() {
        List<Product> result = productService.listProduct();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/delete/{idProduct}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long idProduct) {
        HashMap<String, Object> result = productService.deleteProduct(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/findProduct/{idProduct}")
    public ResponseEntity<Object> getProductById(@PathVariable Long idProduct){
        Product result = productService.findProductById(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateProduct(@RequestBody Product category) {
        Product result = productService.updateProduct(category);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
