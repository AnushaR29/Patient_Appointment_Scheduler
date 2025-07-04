package com.appointment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private static final String FILE_NAME = "appointments.txt";

    public void bookAppointment(Patient patient) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(patient.getId() + "," + patient.getName() + "," + patient.getEmail());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error booking appointment: " + e.getMessage());
        }
    }

    public boolean cancelAppointment(String patientId) {
        List<Patient> patients = listAppointments();
        boolean removed = patients.removeIf(p -> p.getId().equals(patientId));
        if (removed) {
            saveAppointments(patients);
        }
        return removed;
    }

    public List<Patient> listAppointments() {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    patients.add(new Patient(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading appointments: " + e.getMessage());
        }
        return patients;
    }

    private void saveAppointments(List<Patient> patients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Patient p : patients) {
                writer.write(p.getId() + "," + p.getName() + "," + p.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving appointments: " + e.getMessage());
        }
    }
}
