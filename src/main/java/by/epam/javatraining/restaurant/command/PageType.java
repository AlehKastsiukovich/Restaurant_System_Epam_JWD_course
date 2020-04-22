package by.epam.javatraining.restaurant.command;

public enum PageType {
    REGISTRATION_PAGE("/jsp/registration.jsp"),
    START_PAGE("/jsp/start_page.jsp"),
    SIGN_IN_PAGE("/jsp/login.jsp"),
    CART_PAGE("/jsp/order.jsp"),
    CONTACTS("/jsp/contacts.jsp");

    private String value;

    PageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
