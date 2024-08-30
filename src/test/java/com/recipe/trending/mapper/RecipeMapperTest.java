package com.recipe.trending.mapper;

import com.recipe.trending.dto.RecipeDto;
import com.recipe.trending.entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeMapperTest {
    private RecipeMapper recipeMapper;

    @BeforeEach
    void setUp() {
        recipeMapper = new RecipeMapper();
    }

    @Test
    void testToDtoWithNonNullRecipe() {
        // Given
        Recipe recipe = new Recipe(1L, "Spaghetti Carbonara", "medium", 5, "carbonara.jpg");

        // When
        RecipeDto dto = recipeMapper.toDto(recipe);

        // Then
        assertEquals(1L, dto.getId());
        assertEquals("Spaghetti Carbonara", dto.getName());
        assertEquals("medium", dto.getDifficulty());
        assertEquals("5", dto.getPosition());
        assertEquals("carbonara.jpg", dto.getImageUrl());
    }

    @Test
    void testToDtoWithNullRecipe() {
        // Given
        Recipe recipe = null;

        // When
        RecipeDto dto = recipeMapper.toDto(recipe);

        // Then
        assertNull(dto);
    }
}