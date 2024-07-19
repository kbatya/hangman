package batya.koltun.hangman;

import java.util.Objects;
// User class for credentials to enter the application
// the credentials are stored in the shared preferences
// there is only one user for the application in the device
public class User {
    private String userName;
    private String userPassword;

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public boolean equals(User other)
    {
        if (this == null) return true;
        if (other == null || getClass() != other.getClass()) return false;
        User us = (User) other;
        return Objects.equals(userName, us.userName) &&  Objects.equals(userPassword,us.userPassword);
    }
    @Override
    public int hashCode() {
        return Objects.hash(userName, userPassword);
    }
}