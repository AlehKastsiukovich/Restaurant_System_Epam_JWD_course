package by.epam.javatraining.restaurant.command;

public enum JSPParameter {
    COMMAND("command"),
    LOGIN("login"),
    PASSWORD("password"),
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    PHONE_NUMBER("phoneNumber"),
    EMAIL("email"),
    USER("user"),
    ROLE("role"),
    POSITIONS("positionList");


    private String value;

    JSPParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
