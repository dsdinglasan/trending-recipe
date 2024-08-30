# trending-recipe
Overview

This documentation provides details about the REST APIs available for managing recipes. The APIs allow you to retrieve trending recipes and filter them by difficulty.

Base URL
http://localhost:8080/api/trending-recipes

Endpoints

1. Get All Trending Recipes
   
   GET /trending-recipes  
   Retrieves a list of all trending recipes sorted by their position.  
       Request  
         Method: GET  
         Headers:  
         Accept: application/json  
   Response  
     Status Code: 200 OK  
     Content-Type: application/json  

  Error Responses  
    Status Code: 500 Internal Server Error  

2. Get Recipes by Difficulty  
   
   GET /trending-recipes/by-difficulty  
   Retrieves a list of trending recipes filtered by a specific difficulty level.  
   Request  
     Method: GET  
     URL: /trending-recipes/by-difficulty  
     Query Parameters:  
       difficulty (required): The difficulty level to filter recipes by (e.g., "Easy", "Medium", "Hard").  
     Headers:  
       Accept: application/json  
   Response  
     Status Code: 200 OK  
     Content-Type: application/json  

  Error Responses  
    Status Code: 400 Bad Request  
    Status Code: 500 Internal Server Error  
