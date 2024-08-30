package com.recipe.trending.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RecipeDto implements Serializable {
    private Long id;
    private String name;
    private String difficulty;
    private String position;
    private String imageUrl;
}
