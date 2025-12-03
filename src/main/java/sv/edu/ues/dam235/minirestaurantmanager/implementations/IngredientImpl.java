package sv.edu.ues.dam235.minirestaurantmanager.implementations;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.IngredientDTO;
import sv.edu.ues.dam235.minirestaurantmanager.entities.Ingredient;
import sv.edu.ues.dam235.minirestaurantmanager.repositories.IngredientRepository;
import sv.edu.ues.dam235.minirestaurantmanager.services.IngredientServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class IngredientImpl implements IngredientServices {
    private final IngredientRepository ingredientRepository;
    private IngredientImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    @Override
    public List<IngredientDTO> getAllIngredients() {
        List<IngredientDTO> result = new ArrayList<>();
        List<Ingredient> items = this.ingredientRepository.findAll();
        for (Ingredient item : items) {
            result.add(new IngredientDTO(item.getId(), item.getName(), item.getUnitOfMeasure(), item.getQuantity(), item.getOwnerId(), item.isNeedsSync()));
        }
        return result;
    }

    @Override
    public IngredientDTO getIngredient(String id) {
        IngredientDTO ingredientDTO;
        Optional<Ingredient> resultado = this.ingredientRepository.findById(id);
        Ingredient ingredient = new Ingredient();
        if(resultado.orElse(null) != null) {
            ingredient = resultado.get();
            ingredientDTO = new IngredientDTO(ingredient.getId(), ingredient.getName(), ingredient.getUnitOfMeasure(), ingredient.getQuantity(), ingredient.getOwnerId(), ingredient.isNeedsSync());
            return ingredientDTO;
        }
        return null;
    }

    @Override
    public IngredientDTO createIngredient(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDTO.getId());
        ingredient.setName(ingredientDTO.getName());
        ingredient.setUnitOfMeasure(ingredientDTO.getUnitOfMeasure());
        ingredient.setQuantity(ingredientDTO.getQuantity());
        ingredient.setOwnerId(ingredientDTO.getOwnerId());
        ingredient.setNeedsSync(ingredientDTO.isNeedsSync());
        ingredient = ingredientRepository.save(ingredient);
        return new IngredientDTO(ingredient.getId(), ingredient.getName(), ingredient.getUnitOfMeasure(), ingredient.getQuantity(), ingredient.getOwnerId(), ingredient.isNeedsSync());
    }

    @Override
    public IngredientDTO updateIngredient(String id, IngredientDTO ingredientDTO) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        ingredient.setId(ingredientDTO.getId());
        ingredient.setName(ingredientDTO.getName());
        ingredient.setUnitOfMeasure(ingredientDTO.getUnitOfMeasure());
        ingredient.setOwnerId(ingredientDTO.getOwnerId());
        ingredient.setNeedsSync(ingredientDTO.isNeedsSync());
        ingredient = ingredientRepository.save(ingredient);
        return new IngredientDTO(ingredient.getId(), ingredient.getName(), ingredient.getUnitOfMeasure(), ingredient.getQuantity(), ingredient.getOwnerId(), ingredient.isNeedsSync());
    }

    @Override
    public void deleteIngredient(String id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        ingredientRepository.deleteById(id);
    }
}
