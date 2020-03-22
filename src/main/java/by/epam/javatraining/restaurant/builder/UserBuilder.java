package by.epam.javatraining.restaurant.builder;

import by.epam.javatraining.restaurant.entity.Role;
import by.epam.javatraining.restaurant.entity.User;

public class UserBuilder {
    private static final int DEFAULT_USER_ROLE_ID = 2;

    private int id;
    private String login;
    private String password;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Role role;

    public UserBuilder() {
        role = new Role();
        role.setRoleId(DEFAULT_USER_ROLE_ID);
    }

    public UserBuilder buildId(int id) {
        this.id = id;
        return this;
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

    public User build() {
        return new User(this);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }

    public Integer getId() { return id; }
}
