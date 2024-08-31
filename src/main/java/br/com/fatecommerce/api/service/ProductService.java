package br.com.fatecommerce.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fatecommerce.api.entity.Product;
import br.com.fatecommerce.api.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product category) {
        return productRepository.saveAndFlush(category);
    }

    public List<Product> listProduct() {
        return productRepository.findAll();
    }

    public HashMap<String, Object> deleteProduct(Long idProduct) {
        Optional<Product> category = Optional.ofNullable(productRepository.findById(idProduct)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado!")));

        productRepository.delete(category.get());
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "Produto: " + category.get().getNameProduct() + " excluído com sucesso!");
        return result;
    }

    public Product findProductById(Long idProduct) {
        return productRepository.findById(idProduct)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado!"));
    }

    public Product updateProduct(Product category) {
        if (findProductById(category.getId()) != null) {
            return productRepository.saveAndFlush(category);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!");
        }
    }

}
