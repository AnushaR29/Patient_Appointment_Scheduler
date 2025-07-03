package com.appointment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppointmentService {
    private final String FILE_PATH = "appointments.txt";

    public void bookAppointment(Patient patient) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(patient.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean cancelAppointment(String email, String dateTime) {
        List<Patient> patients = getAppointments();
        boolean found = false;

        Iterator<Patient> iterator = patients.iterator();
        while (iterator.hasNext()) {
            Patient p = iterator.next();
            if (p.getEmail().equals(email) && p.getAppointmentDateTime().equals(dateTime)) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (found) {
            saveAppointments(patients);
        }

        return found;
    }

    public List<Patient> getAppointments() {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    patients.add(new Patient(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            // file may not exist yet, ignore
        }
        return patients;
    }

    public void listAppointments() {
        List<Patient> patients = getAppointments();
        if (patients.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            for (Patient p : patients) {
                System.out.println(p);
            }
        }
    }

    private void saveAppointments(List<Patient> patients) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Patient p : patients) {
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
