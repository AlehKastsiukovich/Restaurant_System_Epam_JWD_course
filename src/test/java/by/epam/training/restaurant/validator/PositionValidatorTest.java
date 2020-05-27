package by.epam.training.restaurant.validator;

import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.entity.PositionItemGroup;
import by.epam.javatraining.restaurant.validator.PositionValidator;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;

public class PositionValidatorTest {
    private PositionValidator validator = PositionValidator.INSTANCE;
    private Position position;

    @Test
    public void testisPositionValidateNullEnterParameterShouldResultFalse() {
        position = null;

        boolean result = validator.isPositionValidate(position);
        boolean excepted = false;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testisPositionValidateWithNotValidPositionNameShouldResultFalse() {
        position = new Position();
        position.setItemName("1");
        position.setPositionId(10);
        PositionItemGroup group = new PositionItemGroup();
        group.setGroupId(1);
        position.setGroup(group);
        position.setItemPrice(new BigDecimal(10));

        boolean result = validator.isPositionValidate(position);
        boolean excepted = false;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testisPositionValidateWithValidPositionNameShouldResultTrue() {
        position = new Position();
        position.setItemName("Pizza");
        position.setPositionId(10);
        PositionItemGroup group = new PositionItemGroup();
        group.setGroupId(1);
        position.setGroup(group);
        position.setItemPrice(new BigDecimal(10));

        boolean result = validator.isPositionValidate(position);
        boolean excepted = true;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testisPositionValidateWithMinusPriceShouldResultFalse() {
        position = new Position();
        position.setItemName("Pizza");
        position.setPositionId(10);
        PositionItemGroup group = new PositionItemGroup();
        group.setGroupId(1);
        position.setGroup(group);
        position.setItemPrice(new BigDecimal(-10));

        boolean result = validator.isPositionValidate(position);
        boolean excepted = false;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testisPositionValidateWithMinusGroupIdShouldResultFalse() {
        position = new Position();
        position.setItemName("Pizza");
        position.setPositionId(10);
        PositionItemGroup group = new PositionItemGroup();
        group.setGroupId(-100);
        position.setGroup(group);
        position.setItemPrice(new BigDecimal(10));

        boolean result = validator.isPositionValidate(position);
        boolean excepted = false;

        Assert.assertEquals(excepted, result);
    }
}
