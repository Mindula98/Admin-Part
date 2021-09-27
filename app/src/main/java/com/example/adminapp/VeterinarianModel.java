package com.example.adminapp;

import android.os.Parcel;
import android.os.Parcelable;

public class VeterinarianModel implements Parcelable {
    private String vetName;
    private String vetAddress;
    private String vetContact;
    private String vetEmail;
    private String vetID;

    public static final Creator<VeterinarianModel> CREATOR = new Creator<VeterinarianModel>() {
        @Override
        public VeterinarianModel createFromParcel(Parcel in) {
            return new VeterinarianModel(in);
        }

        @Override
        public VeterinarianModel[] newArray(int size) {
            return new VeterinarianModel[size];
        }
    };

    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }

    public String getVetAddress() {
        return vetAddress;
    }

    public void setVetAddress(String vetAddress) {
        this.vetAddress = vetAddress;
    }

    public String getVetContact() {
        return vetContact;
    }

    public void setVetContact(String vetContact) {
        this.vetContact = vetContact;
    }

    public String getVetEmail() {
        return vetEmail;
    }

    public void setVetEmail(String vetEmail) {
        this.vetEmail = vetEmail;
    }

    public String getVetID() {
        return vetID;
    }

    public void setVetID(String vetID) {
        this.vetID = vetID;
    }

    public VeterinarianModel(String vetName, String vetAddress, String vetContact, String vetEmail, String vetID) {
        this.vetName = vetName;
        this.vetAddress = vetAddress;
        this.vetContact = vetContact;
        this.vetEmail = vetEmail;
        this.vetID = vetID;
    }

    public VeterinarianModel(){

    }

    protected VeterinarianModel(Parcel in) {
        vetName = in.readString();
        vetAddress = in.readString();
        vetContact = in.readString();
        vetEmail = in.readString();
        vetID = in.readString();
    }





    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(vetName);
        parcel.writeString(vetAddress);
        parcel.writeString(vetContact);
        parcel.writeString(vetEmail);
        parcel.writeString(vetID);
    }
}
