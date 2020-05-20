package by.epam.javatraining.restaurant.command;

public enum PageType {
    REGISTRATION_PAGE("/jsp/registration.jsp"),
    START_PAGE("/jsp/start_page.jsp"),
    SIGN_IN_PAGE("/jsp/login.jsp"),
    CART_PAGE("/jsp/order.jsp"),
    CONTACTS_PAGE("/jsp/contacts.jsp"),
    PROFILE_PAGE("jsp/profile.jsp"),
    ADDRESS_FORM_PAGE("/jsp/address.jsp"),
    PROFILE_USER_ORDERS("/jsp/profile_user_orders.jsp"),
    ADMIN_START_PAGE("/jsp/admin_start_page.jsp"),
    ADMIN_UNCONFIRMED_ORDERS_PAGE("/jsp/admin_unconfirmed_orders.jsp"),
    ADMIN_ALL_USERS_ORDERS("/jsp/admin_all_orders.jsp");

    private String value;

    PageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
