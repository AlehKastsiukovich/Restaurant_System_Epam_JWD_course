package by.epam.javatraining.restaurant.builder;

import by.epam.javatraining.restaurant.entity.Role;

public class UserBuilder {
    private static final int DEFAULT_USER_ROLE_ID = 2;
    private int userId;
    private String login;
    private String password;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Role role;

    public UserBuilder() {
    }

    public UserBuilder buildLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder buildPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder buildEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder buildPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder buildFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder buildLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder buildRole() {
        this.role = new Role();
        role.setRoleId(DEFAULT_USER_ROLE_ID);
        return this;
    }
}
