package by.epam.javatraining.restaurant.command;

public enum PageType {
    REGISTRATION_PAGE("/register.jsp"),
    HOME_PAGE("/index.jsp");

    private String value;

    PageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
