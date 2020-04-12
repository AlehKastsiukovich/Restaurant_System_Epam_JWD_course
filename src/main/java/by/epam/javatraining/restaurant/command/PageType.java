package by.epam.javatraining.restaurant.command;

public enum PageType {
    REGISTRATION_PAGE("jsp/register.jsp"),
    START_PAGE("/jsp/start_page.jsp"),
    SIGN_IN_PAGE("jsp/login.jsp"),
    BUCKET_PAGE("/jsp/bucket.jsp");

    private String value;

    PageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
