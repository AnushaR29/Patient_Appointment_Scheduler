package com.appointment;

import java.io.Serializable;

public class Patient implements Serializable {
    private String name;
    private String email;
    private String appointmentDateTime;

    public Patient(String name, String email, String appointmentDateTime) {
        this.name = name;
        this.email = email;
        this.appointmentDateTime = appointmentDateTime;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAppointmentDateTime() {
        return appointmentDateTime;
    }

    @Override
    public String toString() {
        return name + "," + email + "," + appointmentDateTime;
    }
}
