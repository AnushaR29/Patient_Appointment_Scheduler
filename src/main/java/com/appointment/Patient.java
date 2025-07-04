package com.appointment;

public class Patient {
    private int id;
    private String name;
    private String appointmentDate;

    public Patient(int id, String name, String appointmentDate) {
        this.id = id;
        this.name = name;
        this.appointmentDate = appointmentDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
