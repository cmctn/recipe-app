package com.cem.recipes.data;


import com.cem.recipes.model.Ingredient;
import com.cem.recipes.model.Recipe;
import com.cem.recipes.model.request.IngredientRequestDTO;
import com.cem.recipes.model.request.RecipeRequestDTO;
import com.cem.recipes.model.response.IngredientResponseDTO;
import com.cem.recipes.model.response.RecipeResponseDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


@Component
public class RecipeInitializer {

    public RecipeRequestDTO initializeRecipeRequestDTO_1() {
        return RecipeRequestDTO.builder()
                .name("Orange Jam")
                .vegetarian(true)
                .numberOfServings(4)
                .instructions("add orange, add sugar")
                .ingredients(Collections.singletonList(IngredientRequestDTO.builder().name("orange").build()))
                .build();
    }

    public RecipeRequestDTO initializeRecipeRequestDTO_2() {
        return RecipeRequestDTO.builder()
                .name("Orange Jam")
                .vegetarian(true)
                .numberOfServings(6)
                .instructions("add orange, add sugar")
                .ingredients(List.of(IngredientRequestDTO.builder().name("orange").build()))
                .build();
    }

    public Recipe initializeRecipe_1() {
        return Recipe.builder()
                .id(1L)
                .name("Orange Jam")
                .vegetarian(true)
                .numberOfServings(4)
                .instructions("add orange, add sugar")
                .ingredients(List.of(Ingredient.builder().name("orange").build()))
                .build();
    }

    public Recipe initializeRecipe_2() {
        return Recipe.builder()
                .id(1L)
                .name("Orange Jam")
                .vegetarian(true)
                .numberOfServings(6)
                .instructions("add orange, add sugar")
                .ingredients(List.of(Ingredient.builder().name("orange").build()))
                .build();
    }

    public RecipeResponseDTO initializeRecipeResponseDTO_1() {
        return RecipeResponseDTO.builder()
                .id(1L)
                .name("Orange Jam")
                .vegetarian(true)
                .numberOfServings(4)
                .instructions("add orange, add sugar")
                .ingredients(List.of(IngredientResponseDTO.builder().name("orange").build()))
                .build();
    }

    public RecipeResponseDTO initializeRecipeResponseDTO_2() {
        return RecipeResponseDTO.builder()
                .id(1L)
                .name("Orange Jam")
                .vegetarian(true)
                .numberOfServings(6)
                .instructions("add orange, add sugar")
                .ingredients(List.of(IngredientResponseDTO.builder().name("orange").build()))
                .build();
    }

}