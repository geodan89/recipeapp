package geo.springframework.recipeapp.controllers;

import geo.springframework.recipeapp.domain.Category;
import geo.springframework.recipeapp.domain.UnitOfMeasure;
import geo.springframework.recipeapp.repositories.CategoryRepository;
import geo.springframework.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index" })
    public String getIndexPage(){
        System.out.println("Some message to say hi! 889787");
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Pinch");

        System.out.println("Category Id is: "+ categoryOptional.get().getId());
        System.out.println("Unit of measure Id is: " + unitOfMeasureOptional.get().getId());

        return "index";
    }


}
