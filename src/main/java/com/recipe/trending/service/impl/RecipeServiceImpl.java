package com.recipe.trending.service.impl;

import com.recipe.trending.dto.RecipeDto;
import com.recipe.trending.mapper.RecipeMapper;
import com.recipe.trending.repository.IRecipeRepository;
import com.recipe.trending.service.IRecipeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements IRecipeService {

    private final IRecipeRepository repository;
    private final RecipeMapper mapper;

    @Override
    public List<RecipeDto> getAllTrendingRecipes() {
        return repository.findAll(Sort.by("position")).stream().map(mapper::toDto).toList();
    }

    @Override
    public List<RecipeDto> getTrendingRecipesGivenDifficulty(String difficulty) {
        return repository.findRecipesByDifficultyOrderByPosition(difficulty).stream().map(mapper::toDto).toList();
    }
}
