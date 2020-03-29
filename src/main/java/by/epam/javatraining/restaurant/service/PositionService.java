package by.epam.javatraining.restaurant.service;

import by.epam.javatraining.restaurant.entity.Position;
import java.util.List;

public interface PositionService {

    void createPosition(Position position);

    void deletePosition(Position position);

    Position getPositionById(int id);

    List<Position> getAllPositions();
}
