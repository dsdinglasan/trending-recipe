package com.recipe.trending.controller;

import com.recipe.trending.dto.RecipeDto;
import com.recipe.trending.service.IRecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {
    @Mock
    private IRecipeService service;

    @InjectMocks
    private RecipeController recipeController;

    private RecipeDto recipeDto1;
    private RecipeDto recipeDto2;

    @BeforeEach
    void setUp() {
        recipeDto1 = new RecipeDto();
        recipeDto1.setId(1L);
        recipeDto1.setName("Spaghetti Carbonara");
        recipeDto1.setDifficulty("medium");
        recipeDto1.setPosition("1");
        recipeDto1.setImageUrl("carbonara.jpg");

        recipeDto2 = new RecipeDto();
        recipeDto2.setId(2L);
        recipeDto2.setName("Margherita Pizza");
        recipeDto2.setDifficulty("easy");
        recipeDto2.setPosition("2");
        recipeDto2.setImageUrl("pizza.jpg");
    }

    @Test
    void testGetAllTrendingRecipesSuccess() {
        // Given
        List<RecipeDto> recipeList = Arrays.asList(recipeDto1, recipeDto2);
        when(service.getAllTrendingRecipes()).thenReturn(recipeList);

        // When
        ResponseEntity<?> response = recipeController.getAllTrendingRecipes();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recipeList, response.getBody());
    }

    @Test
    void testGetAllTrendingRecipesException() {
        // Given
        when(service.getAllTrendingRecipes()).thenThrow(new RuntimeException("Service error"));

        // When
        ResponseEntity<?> response = recipeController.getAllTrendingRecipes();

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error is encountered when getting the list of all trending recipes.", response.getBody());
    }

    @Test
    void testGetRecipesByDifficultySuccess() {
        // Given
        String difficulty = "medium";
        List<RecipeDto> recipeList = Collections.singletonList(recipeDto1);
        when(service.getTrendingRecipesGivenDifficulty(difficulty)).thenReturn(recipeList);

        // When
        ResponseEntity<?> response = recipeController.getRecipesByDifficulty(difficulty);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recipeList, response.getBody());
    }

    @Test
    void testGetRecipesByDifficultyMissingParameter() {
        // When
        ResponseEntity<?> response = recipeController.getRecipesByDifficulty(null);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("A difficulty is required for filtering trending recipes", response.getBody());
    }

    @Test
    void testGetRecipesByDifficultyException() {
        // Given
        String difficulty = "easy";
        when(service.getTrendingRecipesGivenDifficulty(difficulty)).thenThrow(new RuntimeException("Service error"));

        // When
        ResponseEntity<?> response = recipeController.getRecipesByDifficulty(difficulty);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error is encountered when getting the list of trending recipes filtered by the difficulty given.", response.getBody());
    }
}