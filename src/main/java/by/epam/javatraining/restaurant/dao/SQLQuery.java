package by.epam.javatraining.restaurant.dao;

public enum SQLQuery {
    READ_USER_BY_LOGIN_QUERY("select * from user inner join user_role r"
            + " on user.role_id = r.id"
            + " where user.login = ?"),
    READ_USER_BY_ID("select * from user inner join user_role r"
            + " on user.role_id = r.id"
            + " where user.user_id = ?"),
    READ_USER_BY_EMAIL("select * from user inner join user_role r"
            + " on user.role_id = r.id"
            + " where user.email = ?"),
    GET_ALL_USERS("select * from user inner join user_role r"
            + " on user.role_id = r.id"),
    DELETE_USER("delete from user where user_id = (?) and login = (?) "
            + "and password = (?)"),
    CREATE_USER("insert into user (login, password, email, "
            + "phone_number, first_name, last_name, role_id) values ((?), (?), (?), (?), (?), (?), (?))"),
    UPDATE_USER("update user set password = (?), email = (?), phone_number = (?) "
            + "where user_id = (?) and login = (?)"),
    CREATE_ADDRESS("insert into delivery_address (street, build, apartment)"
           + " values (?, ?, ?)"),
    CREATE_ORDER("insert into `order` (order_date, customer_id, total_price,"
            + " order_status, id_delivery_address) values (?, ?, ?, ?, (select delivery_address_id"
            + " from delivery_address where street = (?) and build = (?) and apartment = (?)))"),
    UPDATE_ORDER("update `order` set total_price = (?), order_status = (?)"
            + " where order_id = (?)"),
    GET_ALL_ORDERS("select * from `order` inner join delivery_address da"
            + " on `order`.id_delivery_address = da.delivery_address_id"),
    DELETE_ORDER("delete from `order` where order_id = (?) and customer_id = (?)"),
    READ_ORDER_BY_ID("select * from `order` inner join delivery_address da"
            + " on id_delivery_address = da.delivery_address_id where order_id = (?)"),
    CREATE_POSITION("insert into positions (item_name, group_id, item_price)"
            + " values ((?), (?), (?))"),
    UPDATE_POSITION("update positions set item_name = (?), group_id = (?), item_price = (?)"
            + " where item_id = (?)"),
    DELETE_POSITION("delete from positions where item_id = (?) and item_name = (?)"),
    GET_ALL_POSITIONS("select * from positions inner join positions_item_group pig"
            + " on positions.group_id = pig.group_id"),
    READ_POSITION_BY_ID("select * from positions inner join positions_item_group pig"
            + " on positions.group_id = pig.group_id where item_id = (?)");

    private String value;

    SQLQuery(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
