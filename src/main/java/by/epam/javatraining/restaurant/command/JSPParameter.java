package by.epam.javatraining.restaurant.command;

public enum JSPParameter {
    COMMAND("command");

    private String value;

    JSPParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
