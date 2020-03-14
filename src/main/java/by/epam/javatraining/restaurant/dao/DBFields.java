package by.epam.javatraining.restaurant.dao;

public enum DBFields {
    DB_USER_ID("user_id"),
    DB_USER_LOGIN("login"),
    DB_USER_PASSWORD("password"),
    DB_USER_EMAIL("email"),
    DB_USER_PHONE_NUMBER("phone_number"),
    DB_USER_FIRST_NAME("first_name"),
    DB_USER_LAST_NAME("last_name"),
    DB_USER_ROLE_ID("role_id");

    private String value;

    DBFields(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
