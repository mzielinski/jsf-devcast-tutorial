package devcast.entities.builders;

import devcast.entities.User;

/**
 * @author mzielinski on 15.12.14.
 */
public class UserBuilder {

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String address;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder but() {
        return anUser().withId(id).withEmail(email).withFirstName(firstName).withLastName(lastName).withAddress(address);
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        return user;
    }
    
}
