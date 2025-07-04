package com.appointment;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AppointmentService service = new AppointmentService();
        int choice;

        do {
            System.out.println("\n--- Patient Appointment Scheduler ---");
            System.out.println("1. Book Appointment");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. List Appointments");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Patient Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Patient Email: ");
                    String email = scanner.nextLine();
                    Patient patient = new Patient(id, name, email);
                    service.bookAppointment(patient);
                    System.out.println("Appointment booked successfully.");
                    break;

                case 2:
                    System.out.print("Enter Patient ID to Cancel: ");
                    String cancelId = scanner.nextLine();
                    boolean cancelled = service.cancelAppointment(cancelId);
                    System.out.println(cancelled ? "Appointment cancelled successfully." : "Appointment not found.");
                    break;

                case 3:
                    List<Patient> patients = service.listAppointments();
                    if (patients.isEmpty()) {
                        System.out.println("No appointments found.");
                    } else {
                        System.out.println("--- Appointments ---");
                        for (Patient p : patients) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);

        scanner.close();
    }
}
