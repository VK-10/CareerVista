package com.example.carrervista;

public class Clients {
    String name;
    String email;
    String uid;
    String number;

    public Clients(String name, String email, String uid, String number) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", uid='" + uid + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
