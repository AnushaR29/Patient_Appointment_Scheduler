package com.appointment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private List<Patient> appointments = new ArrayList<>();
    private final String FILE_PATH = "appointments.txt";

    public AppointmentService() {
        loadAppointmentsFromFile();
    }

    public boolean bookAppointment(int id, String name, String date) {
        for (Patient p : appointments) {
            if (p.getId() == id) return false;
        }
        Patient patient = new Patient(id, name, date);
        appointments.add(patient);
        saveAppointmentsToFile();
        return true;
    }

    public boolean cancelAppointment(int id) {
        boolean removed = appointments.removeIf(p -> p.getId() == id);
        if (removed) saveAppointmentsToFile();
        return removed;
    }

    public List<Patient> listAppointments() {
        return new ArrayList<>(appointments);
    }

    private void saveAppointmentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Patient p : appointments) {
                writer.write(p.getId() + "," + p.getName() + "," + p.getAppointmentDate());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAppointmentsFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            appointments.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) continue;
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String date = parts[2].trim();
                    appointments.add(new Patient(id, name, date));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
