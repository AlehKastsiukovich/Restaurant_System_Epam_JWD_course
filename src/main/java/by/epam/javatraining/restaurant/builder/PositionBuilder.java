package by.epam.javatraining.restaurant.builder;

import by.epam.javatraining.restaurant.entity.PositionItemGroup;
import java.math.BigDecimal;

public class PositionBuilder {
    private int positionId;
    private BigDecimal itemPrice;
    private String itemName;
    private PositionItemGroup group;

    public PositionBuilder buildId(int positionId) {
        this.positionId = positionId;
        return this;
    }

    public PositionBuilder buildItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    public PositionBuilder buildItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public PositionBuilder buildPositionItemGroup(int groupId) {
        group = new PositionItemGroup();
        group.setGroupId(groupId);
        return this;
    }

    public int getPositionId() {
        return positionId;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public PositionItemGroup getGroup() {
        return group;
    }
}
