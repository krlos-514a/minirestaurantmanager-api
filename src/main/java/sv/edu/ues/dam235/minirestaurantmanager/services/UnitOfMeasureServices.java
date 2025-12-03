package sv.edu.ues.dam235.minirestaurantmanager.services;

import sv.edu.ues.dam235.minirestaurantmanager.dtos.UnitOfMeasureDTO;
import java.util.List;

public interface UnitOfMeasureServices {

    List<UnitOfMeasureDTO> getAllUnitsOfMeasure();

    UnitOfMeasureDTO getUnitOfMeasure(String name);

    UnitOfMeasureDTO createUnitOfMeasure(UnitOfMeasureDTO unitOfMeasureDTO);

    UnitOfMeasureDTO updateUnitOfMeasure(String name, UnitOfMeasureDTO unitOfMeasureDTO);

    void deleteUnitOfMeasure(String name);
}
