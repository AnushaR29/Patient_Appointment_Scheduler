package com.appointment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppointmentService service = new AppointmentService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n Patient Appointment Scheduler ");
            System.out.println("1. Book Appointment");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. List Appointments");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Patient ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Appointment Date (yyyy-mm-dd): ");
                    String date = scanner.nextLine();

                    boolean booked = service.bookAppointment(id, name, date);
                    if (booked) {
                        System.out.println("Appointment booked.");
                    } else {
                        System.out.println("Appointment ID already exists.");
                    }
                    break;

                case "2":
                    System.out.print("Enter Patient ID to cancel: ");
                    int cancelId = Integer.parseInt(scanner.nextLine());
                    boolean cancelled = service.cancelAppointment(cancelId);
                    System.out.println(cancelled ? "Appointment cancelled." : "Appointment not found.");
                    break;

                case "3":
                    System.out.println("Appointments:");
                    for (Patient p : service.listAppointments()) {
                        System.out.println("ID: " + p.getId() + ", Name: " + p.getName() + ", Date: " + p.getAppointmentDate());
                    }
                    break;

                case "4":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}
