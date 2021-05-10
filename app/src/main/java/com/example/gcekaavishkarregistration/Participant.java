package com.example.gcekaavishkarregistration;

public class Participant {

    private String Registration_ID;
    private String Name;
    private String Email;
    private String Mobile;
    private String College;
    private String Participants;
    private String Fees;
    private String Registered_By;
    private String Registered_By_Team;

    public Participant() {

    }

    public Participant(String registration_ID, String name, String email, String mobile, String college, String participants, String fees, String registered_By,String registered_By_Team) {
        Registration_ID = registration_ID;
        Name = name;
        Email = email;
        Mobile = mobile;
        College = college;
        Participants = participants;
        Fees = fees;
        Registered_By = registered_By;
        Registered_By_Team=registered_By_Team;
    }

    public String getRegistration_ID() {
        return Registration_ID;
    }

    public void setRegistration_ID(String registration_ID) {
        Registration_ID = registration_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getCollege() {
        return College;
    }

    public void setCollege(String college) {
        College = college;
    }

    public String getParticipants() {
        return Participants;
    }

    public void setParticipants(String participants) {
        Participants = participants;
    }

    public String getFees() {
        return Fees;
    }

    public void setFees(String fees) {
        Fees = fees;
    }

    public String getRegistered_By() {
        return Registered_By;
    }

    public void setRegistered_By(String registered_By) {
        Registered_By = registered_By;
    }

    public  String getRegistered_By_Team(){return Registered_By_Team;}
    public void setRegistered_By_Team(String registered_By_Team){Registered_By_Team=registered_By_Team;}
}
