package by.epam.javatraining.restaurant.entity;

public enum PaymentType {
    CASH("cash"),
    CARD("card");

    private String value;

    PaymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
