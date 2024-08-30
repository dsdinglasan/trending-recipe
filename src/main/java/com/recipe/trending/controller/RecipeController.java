package com.recipe.trending.controller;

import com.recipe.trending.dto.RecipeDto;
import com.recipe.trending.service.IRecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trending-recipes")
@AllArgsConstructor
@Slf4j
public class RecipeController {
    private final IRecipeService service;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllTrendingRecipes(){
        try {
            List<RecipeDto> trendingRecipes = service.getAllTrendingRecipes();
            return new ResponseEntity<>(trendingRecipes, HttpStatus.OK);
        } catch (Exception e) {
            String message = "An error is encountered when getting the list of all trending recipes.";
            log.error(message, e);
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/by-difficulty", produces = "application/json")
    public ResponseEntity<?> getRecipesByDifficulty(@RequestParam("difficulty") String difficulty) {
        try {
            if (difficulty == null || difficulty.trim().isEmpty()) {
                String message = "A difficulty is required for filtering trending recipes";
                log.warn(message);
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            List<RecipeDto> recipesByDifficulty = service.getTrendingRecipesGivenDifficulty(difficulty);
            return new ResponseEntity<>(recipesByDifficulty, HttpStatus.OK);
        } catch (Exception e) {
            String message = "An error is encountered when getting the list of trending recipes filtered by the difficulty given.";
            log.error(message, e);
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
