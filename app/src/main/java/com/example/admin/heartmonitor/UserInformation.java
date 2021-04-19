package com.example.admin.heartmonitor;



public class UserInformation {

    public int heartbeat;
    public int bloodPressure;

    public UserInformation(){


    }

    public UserInformation(int heartbeat, int bloodPressure) {
        this.heartbeat = heartbeat;
        this.bloodPressure = bloodPressure;
    }
}
