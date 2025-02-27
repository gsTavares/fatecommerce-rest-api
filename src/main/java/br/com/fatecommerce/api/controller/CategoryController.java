package br.com.fatecommerce.api.controller;

import br.com.fatecommerce.api.entity.Category;

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

import br.com.fatecommerce.api.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(value = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Object> saveCategory(@RequestBody Category category) {
        Category result = categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listCategory() {
        List<Category> result = categoryService.listCategory();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/delete/{idCategory}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long idCategory) {
        HashMap<String, Object> result = categoryService.deleteCategory(idCategory);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/findCategory/{idCategory}")
    public ResponseEntity<Object> getCategoryById(@PathVariable Long idCategory){
        Category result = categoryService.findCategoryById(idCategory);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCategory(@RequestBody Category category) {
        Category result = categoryService.updateCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
