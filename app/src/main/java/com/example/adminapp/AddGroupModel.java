package com.example.adminapp;

public class AddGroupModel {
    String Name;
    String Image;
    String Details;

    public AddGroupModel() {
    }

    public AddGroupModel(String name, String image, String details) {
        Name = name;
        Image = image;
        Details = details;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
