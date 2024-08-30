package com.recipe.trending.service;

import com.recipe.trending.dto.RecipeDto;

import java.util.List;

public interface IRecipeService {
    List<RecipeDto> getAllTrendingRecipes();
    List<RecipeDto> getTrendingRecipesGivenDifficulty(String difficulty);
}
