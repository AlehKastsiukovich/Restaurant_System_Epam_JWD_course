package by.epam.javatraining.restaurant.dao.query;

public enum DBQuery {
    READ_USER_BY_LOGIN_QUERY("select * from user inner join user_role r"
            + " on user.role_id = r.id"
            + " where user.login = ?"),
    GET_ALL_USERS("select * from user inner join user_role r"
            + " on user.role_id = r.id"),
    DELETE_USER("delete from user where user_id = (?) and login = (?) "
            + "and password = (?)");

    private String value;

    DBQuery(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
