package dyrda.fit.bstu.by.lab4;

import java.io.Serializable;

public class Company implements Serializable {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String location;
    private String socialN;

    public Company(int id, String name, String email, String phone, String location, String socialN){
        this.id=id;
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.location=location;
        this.socialN=socialN;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLink(String socialN) {
        this.socialN = socialN;
    }

    public String getSocialN() {
        return socialN;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
