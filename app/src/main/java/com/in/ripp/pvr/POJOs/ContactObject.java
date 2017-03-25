package com.in.ripp.pvr.POJOs;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ContactObject extends RealmObject {

    @PrimaryKey
    private String phoneNumber;
    private String name;
    private boolean nameValidity;
    private boolean numberValidity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isNameValid() {
        return nameValidity;
    }

    public void setNameValidity(boolean nameValidity) {
        this.nameValidity = nameValidity;
    }

    public boolean isNumberValid() {
        return numberValidity;
    }

    public void setNumberValidity(boolean numberValidity) {
        this.numberValidity = numberValidity;
    }
}

