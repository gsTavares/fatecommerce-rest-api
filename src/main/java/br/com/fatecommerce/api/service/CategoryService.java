package br.com.fatecommerce.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fatecommerce.api.entity.Category;
import br.com.fatecommerce.api.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    public List<Category> listCategory() {
        return categoryRepository.findAll();
    }

    public HashMap<String, Object> deleteCategory(Long idCategory) {
        Optional<Category> category = Optional.ofNullable(categoryRepository.findById(idCategory)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoria não encontrada!")));

        categoryRepository.delete(category.get());
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "Categoria: " + category.get().getNameCategory() + " excluída com sucesso!");
        return result;
    }

    public Category findCategoryById(Long idCategory) {
        return categoryRepository.findById(idCategory)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoria não encontrada!"));
    }

    public Category updateCategory(Category category) {
        if (findCategoryById(category.getId()) != null) {
            return categoryRepository.saveAndFlush(category);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!");
        }
    }

}
