package geo.springframework.recipeapp.services;

import geo.springframework.recipeapp.command.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUnitsOfMeasure();
}
