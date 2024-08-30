package com.recipe.trending.repository;

import com.recipe.trending.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findRecipesByDifficultyOrderByPosition(String difficulty);
}
