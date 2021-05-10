package com.example.gcekaavishkarregistration;

public class User {
    private String Name;
    private String Password;
    private String TeamNumber;

    public User() {

    }

    public User(String name, String password, String teamNumber) {
        Name = name;
        Password = password;
        TeamNumber = teamNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getTeamNumber() {
        return TeamNumber;
    }

    public void setTeamNumber(String teamNumber) {
        Name = teamNumber;
    }
}
