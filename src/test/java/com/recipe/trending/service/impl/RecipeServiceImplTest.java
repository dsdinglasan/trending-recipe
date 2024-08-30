package com.recipe.trending.service.impl;

import com.recipe.trending.dto.RecipeDto;
import com.recipe.trending.entity.Recipe;
import com.recipe.trending.mapper.RecipeMapper;
import com.recipe.trending.repository.IRecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {
    @Mock
    private IRecipeRepository repository;

    @Mock
    private RecipeMapper mapper;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    private Recipe recipe1;
    private Recipe recipe2;
    private RecipeDto recipeDto1;
    private RecipeDto recipeDto2;

    @BeforeEach
    void setUp() {
        recipe1 = new Recipe(1L, "Recipe1", "easy", 1, "url1");
        recipe2 = new Recipe(2L, "Recipe2", "medium", 2, "url2");

        recipeDto1 = new RecipeDto();
        recipeDto1.setId(1L);
        recipeDto1.setName("Recipe1");
        recipeDto1.setDifficulty("easy");
        recipeDto1.setPosition("1");
        recipeDto1.setImageUrl("url1");

        recipeDto2 = new RecipeDto();
        recipeDto2.setId(2L);
        recipeDto2.setName("Recipe2");
        recipeDto2.setDifficulty("medium");
        recipeDto2.setPosition("2");
        recipeDto2.setImageUrl("url2");
    }

    @Test
    void testGetAllTrendingRecipes() {
        // Mock repository call
        when(repository.findAll(Sort.by("position"))).thenReturn(Arrays.asList(recipe1, recipe2));

        // Mock mapper call
        when(mapper.toDto(recipe1)).thenReturn(recipeDto1);
        when(mapper.toDto(recipe2)).thenReturn(recipeDto2);

        // Call service method
        List<RecipeDto> result = recipeService.getAllTrendingRecipes();

        // Verify the results
        assertEquals(2, result.size());
        assertEquals(recipeDto1, result.get(0));
        assertEquals(recipeDto2, result.get(1));
    }

    @Test
    void testGetTrendingRecipesGivenDifficulty() {
        // Mock repository call
        when(repository.findRecipesByDifficultyOrderByPosition("easy")).thenReturn(Collections.singletonList(recipe1));

        // Mock mapper call
        when(mapper.toDto(recipe1)).thenReturn(recipeDto1);

        // Call service method
        List<RecipeDto> result = recipeService.getTrendingRecipesGivenDifficulty("easy");

        // Verify the results
        assertEquals(1, result.size());
        assertEquals(recipeDto1, result.get(0));
    }
}