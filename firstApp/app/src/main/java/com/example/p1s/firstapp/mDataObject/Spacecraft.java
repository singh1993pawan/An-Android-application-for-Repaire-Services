package com.example.p1s.firstapp.mDataObject;

/**
 * Created by P1 S on 4/11/2017.
 */

public class Spacecraft {

    int id;
    String name;
    String address;
    String contact;
    String prize;
    String avg;
    String person;


    public void setAvg(String avg) {
        this.avg=avg;
    }

    public void setPerson(String person) {
        this.person=person;
    }

    public String getAvg() {
        return avg;
    }



    public String getPerson() {
        return person;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
}
