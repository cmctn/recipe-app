package com.cem.recipes.service;

import com.cem.recipes.data.RecipeInitializer;
import com.cem.recipes.exception.ResourceNotFoundException;
import com.cem.recipes.model.Recipe;
import com.cem.recipes.model.request.RecipeRequestDTO;
import com.cem.recipes.model.response.RecipeResponseDTO;
import com.cem.recipes.repo.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;
    @InjectMocks
    private RecipeService recipeService;
    @Spy
    private ModelMapper modelMapper;
    @Spy
    private RecipeInitializer recipeInitializer;


    // MethodName_StateUnderTest_ExpectedBehavior

    @Test
    void createRecipe_ValidRecipe_createRecipe() {
        // given
        RecipeRequestDTO recipeRequestDTO = recipeInitializer.initializeRecipeRequestDTO_1();
        Recipe recipe = recipeInitializer.initializeRecipe_1();
        RecipeResponseDTO recipeResponseDTO = recipeInitializer.initializeRecipeResponseDTO_1();

        // when
        when(modelMapper.map(recipeRequestDTO, Recipe.class)).thenReturn(recipe);
        when(recipeRepository.save(ArgumentMatchers.any())).thenReturn(recipe);
        when(modelMapper.map(recipe, RecipeResponseDTO.class)).thenReturn(recipeResponseDTO);
        RecipeResponseDTO actualRecipe = recipeService.createRecipe(recipeRequestDTO);

        // then
        verify(recipeRepository, times(1)).save(recipe);
        assertEquals(recipe.getName(), actualRecipe.getName());
    }

    @Test
    void updateRecipe_RecipeDoesNotExist_ThrowException() {
        // given
        Long id = 1L;
        String expectedExceptionMessage = "Recipe not found with id:1";
        RecipeRequestDTO recipeRequestDTO = recipeInitializer.initializeRecipeRequestDTO_1();

        // when
        when(recipeRepository.findById(id))
                .thenThrow(new ResourceNotFoundException("Recipe not found with id:" + id));
        Throwable exception = assertThrows(ResourceNotFoundException.class,
                () -> recipeService.updateRecipe(recipeRequestDTO, id));

        // then
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void updateRecipe_RecipeExists_UpdateRecipe() {
        // given
        Long id = 1L;
        RecipeRequestDTO recipeRequestDTO = recipeInitializer.initializeRecipeRequestDTO_2();
        Recipe currentRecipe = recipeInitializer.initializeRecipe_1();
        Recipe newRecipe = recipeInitializer.initializeRecipe_2();
        RecipeResponseDTO recipeResponseDTO = recipeInitializer.initializeRecipeResponseDTO_2();

        // when
        when(recipeRepository.findById(id)).thenReturn(Optional.of(currentRecipe));
        when(modelMapper.map(recipeRequestDTO, Recipe.class)).thenReturn(newRecipe);
        when(recipeRepository.save(ArgumentMatchers.any())).thenReturn(newRecipe);
        when(modelMapper.map(newRecipe, RecipeResponseDTO.class)).thenReturn(recipeResponseDTO);
        RecipeResponseDTO actualRecipeResponseDTO = recipeService.updateRecipe(recipeRequestDTO, id);

        // then
        assertEquals(recipeRequestDTO.getNumberOfServings(), actualRecipeResponseDTO.getNumberOfServings());
    }

    @Test
    void deleteRecipe_RecipeDoesNotExist_ThrowException() {
        // given
        Long id = 1L;
        String expectedExceptionMessage = "Recipe not found with id:1";

        // when
        when(recipeRepository.findById(id))
                .thenThrow(new ResourceNotFoundException("Recipe not found with id:" + id));
        Throwable exception = assertThrows(ResourceNotFoundException.class,
                () -> recipeService.deleteRecipe(id));

        // then
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void deleteRecipe_RecipeExists_DeleteRecipe() {
        // given
        Long id = 1L;
        Recipe recipe = recipeInitializer.initializeRecipe_1();

        // when
        when(recipeRepository.findById(id)).thenReturn(Optional.of(recipe));
        doNothing().when(recipeRepository).delete(recipe);
        recipeService.deleteRecipe(id);

        // then
        verify(recipeRepository, times(1)).delete(ArgumentMatchers.any(Recipe.class));
    }

    @Test
    void getRecipeByName_RecipeDoesNotExist_ThrowException() {
        // given
        String name = "Strawberry Jam";
        String expectedExceptionMessage = "Recipe not found with name:Strawberry Jam";

        // when
        when(recipeRepository.findByName(name))
                .thenThrow(new ResourceNotFoundException("Recipe not found with name:" + name));
        Throwable exception = assertThrows(ResourceNotFoundException.class,
                () -> recipeService.getRecipeByName(name));

        // then
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void getRecipeByName_RecipeExists_GetRecipe() {
        // given
        String name = "Orange Jam";
        Recipe recipe = recipeInitializer.initializeRecipe_1();
        RecipeResponseDTO recipeResponseDTO = recipeInitializer.initializeRecipeResponseDTO_1();

        // when
        when(recipeRepository.findByName(name)).thenReturn(Optional.of(recipe));
        when(modelMapper.map(recipe, RecipeResponseDTO.class)).thenReturn(recipeResponseDTO);
        RecipeResponseDTO actualRecipeResponseDTO = recipeService.getRecipeByName(name);

        // then
        assertEquals(actualRecipeResponseDTO.getName(), name);
    }

}