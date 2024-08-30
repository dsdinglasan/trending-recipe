package com.recipe.trending;

import com.recipe.trending.entity.Recipe;
import com.recipe.trending.repository.IRecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrendingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrendingApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(IRecipeRepository repository) {
		return args -> {
			repository.save(new Recipe(1L, "Chicken Burger", "Easy", 1, "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg"));
			repository.save(new Recipe(2L, "Beef Burger", "Easy", 2, "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg"));
			repository.save(new Recipe(3L, "Vegan Burger", "Medium", 3, "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg"));
			repository.save(new Recipe(4L, "Breakfast Burger", "Medium", 4, "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg"));
			repository.save(new Recipe(5L, "Tonkatsu Burger", "Hard", 5, "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg"));
			repository.save(new Recipe(6L, "Korean Beef Burger", "Hard", 6, "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg"));
		};
	}
}
