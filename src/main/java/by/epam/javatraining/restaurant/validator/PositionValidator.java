package by.epam.javatraining.restaurant.validator;

import by.epam.javatraining.restaurant.entity.Position;

public enum PositionValidator {

    INSTANCE;

    private static final String POSITION_NAME_PATTERN = "^[A-Za-z0-9_-]{2,16}$";

    public boolean isPositionValidate(Position position) {
        return position != null
                && position.getItemName().matches(POSITION_NAME_PATTERN)
                && position.getGroup().getGroupId() > 0
                && position.getItemPrice().doubleValue() > 0;
    }
}
