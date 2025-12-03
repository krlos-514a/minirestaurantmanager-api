package sv.edu.ues.dam235.minirestaurantmanager.entities;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishIngredientId implements Serializable {
    private String dishId;
    private String ingredientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishIngredientId that = (DishIngredientId) o;
        return Objects.equals(dishId, that.dishId) &&
                Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, ingredientId);
    }
}
