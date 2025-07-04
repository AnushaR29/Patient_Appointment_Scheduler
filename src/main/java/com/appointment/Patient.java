package com.appointment;

public class Patient {
    private String id;
    private String name;
    private String email;

    public Patient(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Patient ID: " + id + ", Name: " + name + ", Email: " + email;
    }
}
