package by.epam.javatraining.restaurant.command;

public enum PageType {
    REGISTRATION_PAGE("/register.jsp"),
    START_PAGE("/start_page.jsp"),
    SIGN_IN_PAGE("/login.jsp");

    private String value;

    PageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
