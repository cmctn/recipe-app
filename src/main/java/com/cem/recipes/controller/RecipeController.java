package com.cem.recipes.controller;

import com.cem.recipes.model.Recipe;
import com.cem.recipes.model.request.RecipeRequestDTO;
import com.cem.recipes.model.response.DeleteResponse;
import com.cem.recipes.model.response.RecipeResponseDTO;
import com.cem.recipes.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import com.querydsl.core.types.Predicate;



@RestController
@RequestMapping("/recipes")
@Tag(name = "RecipeController", description ="Recipes REST API")
public class RecipeController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @Operation(summary = "Create Recipe")
    @PostMapping(path = "/v1", produces = {"application/json", "application/xml"})
    public ResponseEntity<RecipeResponseDTO> createRecipe(@Valid @RequestBody RecipeRequestDTO recipeRequestDTO) {
        RecipeResponseDTO recipeResponseDTO = recipeService.createRecipe(recipeRequestDTO);
        logger.info("Recipe created with id:{}", recipeResponseDTO.getId());
        return new ResponseEntity<>(recipeResponseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Recipe")
    @PutMapping(path = "/v1/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<RecipeResponseDTO> updateRecipe(@Valid @RequestBody RecipeRequestDTO recipeRequestDTO,
                                                          @PathVariable("id") Long id) {
        logger.info("updateRecipe called for:{}", id);
        return ResponseEntity.ok(recipeService.updateRecipe(recipeRequestDTO, id));
    }

    @Operation(summary = "Delete Recipe")
    @DeleteMapping(path = "/v1/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<DeleteResponse> deleteRecipe(@PathVariable("id") Long id) {
        logger.info("deleteRecipe called for:{}", id);
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>(new DeleteResponse(id, "Recipe deleted successfully"), HttpStatus.OK);
    }

    @Operation(summary = "Get Recipe By Name")
    @GetMapping(path = "/v1/{name}", produces = {"application/json", "application/xml"})
    public ResponseEntity<RecipeResponseDTO> getRecipeByName(@PathVariable("name") String name) {
        logger.info("getRecipeByName called for:{}", name);
        return ResponseEntity.ok(recipeService.getRecipeByName(name));
    }

    @Operation(summary = "Get Recipes By Filtering")
    @GetMapping(path = "/v1/all", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<RecipeResponseDTO>> getRecipes(
            @QuerydslPredicate(root = Recipe.class) Predicate predicate,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable) {
        logger.info("getRecipes called");
        return ResponseEntity.ok(recipeService.getAll(predicate, pageable));
    }

}