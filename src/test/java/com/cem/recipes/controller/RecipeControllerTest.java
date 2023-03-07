package com.cem.recipes.controller;

import com.cem.recipes.data.RecipeInitializer;
import com.cem.recipes.model.Recipe;
import com.cem.recipes.model.response.RecipeResponseDTO;
import com.cem.recipes.repo.RecipeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class RecipeControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RecipeInitializer recipeInitializer;
    @Autowired
    private RecipeRepository recipeRepository;


    @AfterEach
    public void tearDown() {
        recipeRepository.deleteAll();
    }


    @Test
    void createRecipe_() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/recipes/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeInitializer.initializeRecipeRequestDTO_1())))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();
        RecipeResponseDTO recipeResponseDTO = new ObjectMapper().readValue(responseString, RecipeResponseDTO.class);
        assertEquals("Orange Jam", recipeResponseDTO.getName());
        assertEquals(true, recipeResponseDTO.getVegetarian());
        assertEquals(4, recipeResponseDTO.getNumberOfServings());
        assertEquals("add orange, add sugar", recipeResponseDTO.getInstructions());
        assertEquals("orange", recipeResponseDTO.getIngredients().get(0).getName());
    }

    @Test
    void updateRecipe_() throws Exception {
        MvcResult mvcCreateResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/recipes/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeInitializer.initializeRecipeRequestDTO_1())))
                .andReturn();

        String responseString = mvcCreateResult.getResponse().getContentAsString();
        RecipeResponseDTO createdRecipe = new ObjectMapper().readValue(responseString, RecipeResponseDTO.class);

        Long id = createdRecipe.getId();
        RecipeResponseDTO expectedRecipeResponseDTO = recipeInitializer.initializeRecipeResponseDTO_2();

        mockMvc.perform(MockMvcRequestBuilders
                .put("/recipes/v1/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(recipeInitializer.initializeRecipeRequestDTO_2())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedRecipeResponseDTO.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vegetarian").value(expectedRecipeResponseDTO.getVegetarian()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfServings").value(expectedRecipeResponseDTO.getNumberOfServings()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.instructions").value(expectedRecipeResponseDTO.getInstructions()))
                .andReturn();
    }

    @Test
    void getRecipeByName_() throws Exception {
        MvcResult mvcCreateResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/recipes/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeInitializer.initializeRecipeRequestDTO_1())))
                .andReturn();

        String responseString = mvcCreateResult.getResponse().getContentAsString();
        RecipeResponseDTO createdRecipe = new ObjectMapper().readValue(responseString, RecipeResponseDTO.class);

        String name = createdRecipe.getName();
        mockMvc.perform(MockMvcRequestBuilders
                .get("/recipes/v1/{name}", name)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void deleteRecipe_() throws Exception {
        MvcResult mvcCreateResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/recipes/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeInitializer.initializeRecipeRequestDTO_1())))
                .andReturn();

        String responseString = mvcCreateResult.getResponse().getContentAsString();
        RecipeResponseDTO createdRecipe = new ObjectMapper().readValue(responseString, RecipeResponseDTO.class);

        Long id = createdRecipe.getId();
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/recipes/v1/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

}