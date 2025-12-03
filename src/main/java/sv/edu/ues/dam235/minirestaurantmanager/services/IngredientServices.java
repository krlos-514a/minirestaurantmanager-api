package sv.edu.ues.dam235.minirestaurantmanager.services;

import sv.edu.ues.dam235.minirestaurantmanager.dtos.IngredientDTO;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.ProductsDTO;

import java.util.List;

public interface IngredientServices {

    List<IngredientDTO> getAllIngredients();

    IngredientDTO getIngredient(String id);

    IngredientDTO createIngredient(IngredientDTO ingredientDTO);

    IngredientDTO updateIngredient(String id, IngredientDTO ingredientDTO);

    void deleteIngredient(String id);
}