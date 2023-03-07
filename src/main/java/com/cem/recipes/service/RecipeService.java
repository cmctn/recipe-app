package com.cem.recipes.service;

import com.cem.recipes.exception.ResourceNotFoundException;
import com.cem.recipes.model.Recipe;
import com.cem.recipes.model.request.RecipeRequestDTO;
import com.cem.recipes.model.response.RecipeResponseDTO;
import com.cem.recipes.repo.RecipeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import com.querydsl.core.types.Predicate;
import java.util.stream.Collectors;


@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, ModelMapper modelMapper) {
        this.recipeRepository = recipeRepository;
        this.modelMapper = modelMapper;
    }

    public RecipeResponseDTO createRecipe(RecipeRequestDTO recipeRequestDTO) {
        Recipe recipe = modelMapper.map(recipeRequestDTO, Recipe.class);
        return modelMapper.map(recipeRepository.save(recipe), RecipeResponseDTO.class);
    }

    public RecipeResponseDTO updateRecipe(RecipeRequestDTO recipeRequestDTO, Long id) {
        Recipe currentRecipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id:" + id));
        Recipe newRecipe = modelMapper.map(recipeRequestDTO, Recipe.class);
        // avoided updating ingredients entity, it can cause issues with referential integrity
        BeanUtils.copyProperties(newRecipe, currentRecipe, "id","ingredients");
        Recipe updatedRecipe = recipeRepository.save(currentRecipe);
        return modelMapper.map(updatedRecipe, RecipeResponseDTO.class);
    }

    public void deleteRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Recipe not found with id:" + id));
        recipeRepository.delete(recipe);
    }

    public RecipeResponseDTO getRecipeByName(String name) {
        Recipe recipe = recipeRepository.findByName(name).orElseThrow(() ->
                new ResourceNotFoundException("Recipe not found with name:" + name));
        return modelMapper.map(recipe, RecipeResponseDTO.class);
    }

    public List<RecipeResponseDTO> getAll(Predicate predicate, Pageable pageable) {
        Page<Recipe> recipes = recipeRepository.findAll(predicate, pageable);
        List<Recipe> recipeList = recipes.getContent();
        return recipeList.stream()
                .map(recipe -> modelMapper.map(recipe, RecipeResponseDTO.class))
                .collect(Collectors.toList());
    }


}