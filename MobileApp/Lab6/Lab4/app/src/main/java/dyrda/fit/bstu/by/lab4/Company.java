package dyrda.fit.bstu.by.lab4;

import java.io.Serializable;

public class Company implements Serializable {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String location;
    private String link;
    private int isChosen;


    public Company()
    {

    }
    public Company(int id, String name, String email, String phoneNumber, String location, String link, int isChosen) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.link = link;
        this.isChosen=isChosen;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIsChosen(int isChosen) {
        this.isChosen = isChosen;
    }

    public int getIsChosen() {
        return isChosen;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
