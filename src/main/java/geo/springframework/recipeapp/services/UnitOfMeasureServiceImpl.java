package geo.springframework.recipeapp.services;

import geo.springframework.recipeapp.command.UnitOfMeasureCommand;
import geo.springframework.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import geo.springframework.recipeapp.domain.UnitOfMeasure;
import geo.springframework.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
                                    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUnitsOfMeasure() {

        Set<UnitOfMeasureCommand> unitOfMeasureCommandSet = new HashSet<>();

        Iterable<UnitOfMeasure> iterable = unitOfMeasureRepository.findAll();

        for(UnitOfMeasure unitOfMeasure : iterable){
            unitOfMeasureCommandSet.add(unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure));
        }
        return unitOfMeasureCommandSet;
    }
}
