package by.epam.javatraining.restaurant.dao.query;

public enum SQLQuery {
    READ_USER_BY_LOGIN_QUERY("select * from user inner join user_role r"
            + " on user.role_id = r.id"
            + " where user.login = ?"),
    READ_USER_BY_ID("select * from user inner join user_role r"
            + " on user.role_id = r.id"
            + " where user.user_id = ?"),
    READ_USER_BY_EMAIL("select * from user inner join user_role r"
            + " on user_role_id = r.id"
            + " where user.email = ?"),
    GET_ALL_USERS("select * from user inner join user_role r"
            + " on user.role_id = r.id"),
    DELETE_USER("delete from user where user_id = (?) and login = (?) "
            + "and password = (?)"),
    CREATE_USER("insert into user (login, password, email, "
            + "phone_number, first_name, last_name, role_id) value ((?), (?), (?), (?), (?), (?), (?))"),
    UPDATE_USER("update user set password = (?), email = (?), phone_number = (?) "
            + "where user_id = (?) and login = (?)"),
    CREATE_ADDRESS("insert into delivery_address (street, build, apartment)"
           + " values (?, ?, ?)"),
    CREATE_ORDER("insert into `order` (order_date, customer_id, total_price,"
            + " order_status, id_delivery_address) values (?, ?, ?, ?, (select delivery_address_id"
            + " from delivery_address where street = (?) and build = (?) and apartment = (?)))"),
    UPDATE_ORDER("update `order` set total_price = (?), order_status = (?)"
            + " where order_id = (?)");

    private String value;

    SQLQuery(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
