package com.appointment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppointmentService service = new AppointmentService();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nWelcome to Patient Appointment Scheduler");
            System.out.println("1. Book Appointment");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. List Appointments");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Patient Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Patient Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Appointment Date and Time (yyyy-MM-dd HH:mm): ");
                    String dateTime = scanner.nextLine();
                    Patient patient = new Patient(name, email, dateTime);
                    service.bookAppointment(patient);
                    System.out.println("Appointment booked successfully.");
                    break;

                case 2:
                    System.out.print("Enter Patient Email: ");
                    String cancelEmail = scanner.nextLine();
                    System.out.print("Enter Appointment Date and Time to Cancel: ");
                    String cancelDateTime = scanner.nextLine();
                    boolean cancelled = service.cancelAppointment(cancelEmail, cancelDateTime);
                    System.out.println(cancelled ? "Appointment cancelled successfully." : "Appointment not found.");
                    break;

                case 3:
                    System.out.println("\nAll Appointments:");
                    service.listAppointments();
                    break;

                case 4:
                    System.out.println("Exiting application...Good Bye");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);

        scanner.close();
    }
}
