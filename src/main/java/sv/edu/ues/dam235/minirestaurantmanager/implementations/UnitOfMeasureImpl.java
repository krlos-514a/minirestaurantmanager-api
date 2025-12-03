package sv.edu.ues.dam235.minirestaurantmanager.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.UnitOfMeasureDTO;
import sv.edu.ues.dam235.minirestaurantmanager.entities.UnitOfMeasure;
import sv.edu.ues.dam235.minirestaurantmanager.repositories.UnitOfMeasureRepository;
import sv.edu.ues.dam235.minirestaurantmanager.services.UnitOfMeasureServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UnitOfMeasureImpl implements UnitOfMeasureServices {
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public List<UnitOfMeasureDTO> getAllUnitsOfMeasure() {
        List<UnitOfMeasureDTO> result = new ArrayList<>();
        List<UnitOfMeasure> items = this.unitOfMeasureRepository.findAll();
        for (UnitOfMeasure item : items) {
            result.add(new UnitOfMeasureDTO(item.getName(), item.isCustom(), item.getOwnerId(), item.isNeedsSync()));
        }
        return result;
    }

    @Override
    public UnitOfMeasureDTO getUnitOfMeasure(String name) {
        Optional<UnitOfMeasure> result = this.unitOfMeasureRepository.findById(name);
        if (result.isPresent()) {
            UnitOfMeasure item = result.get();
            return new UnitOfMeasureDTO(item.getName(), item.isCustom(), item.getOwnerId(), item.isNeedsSync());
        }
        return null;
    }

    @Override
    public UnitOfMeasureDTO createUnitOfMeasure(UnitOfMeasureDTO dto) {
        UnitOfMeasure item = new UnitOfMeasure();
        item.setName(dto.getName());
        item.setCustom(dto.isCustom());
        item.setOwnerId(dto.getOwnerId());
        item.setNeedsSync(dto.isNeedsSync());
        item = unitOfMeasureRepository.save(item);
        return new UnitOfMeasureDTO(item.getName(), item.isCustom(), item.getOwnerId(), item.isNeedsSync());
    }

    @Override
    public UnitOfMeasureDTO updateUnitOfMeasure(String name, UnitOfMeasureDTO dto) {
        UnitOfMeasure item = unitOfMeasureRepository.findById(name)
                .orElseThrow(() -> new RuntimeException("UnitOfMeasure not found"));
        item.setCustom(dto.isCustom());
        item.setOwnerId(dto.getOwnerId());
        item.setNeedsSync(dto.isNeedsSync());
        item = unitOfMeasureRepository.save(item);
        return new UnitOfMeasureDTO(item.getName(), item.isCustom(), item.getOwnerId(), item.isNeedsSync());
    }

    @Override
    public void deleteUnitOfMeasure(String name) {
        if (!unitOfMeasureRepository.existsById(name)) {
            throw new RuntimeException("UnitOfMeasure not found");
        }
        unitOfMeasureRepository.deleteById(name);
    }
}
