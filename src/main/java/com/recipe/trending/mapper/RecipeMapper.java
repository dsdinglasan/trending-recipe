package com.recipe.trending.mapper;

import com.recipe.trending.dto.RecipeDto;
import com.recipe.trending.entity.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapper {

    public RecipeDto toDto(Recipe recipe) {
        if (recipe == null) {
            return null;
        }

        RecipeDto dto = new RecipeDto();
        dto.setId(recipe.getId());
        dto.setName(recipe.getName());
        dto.setDifficulty(recipe.getDifficulty());
        dto.setPosition(recipe.getPosition().toString());
        dto.setImageUrl(recipe.getImageUrl());

        return dto;
    }
}
