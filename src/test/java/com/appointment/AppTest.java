package com.appointment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {

    private static AppointmentService service;

    @BeforeAll
    public static void setup() {
        service = new AppointmentService();
    }

    @Test
    @Order(1)
    public void testBookAppointment() {
        boolean result = service.bookAppointment(100, "Test User", "2025-07-10");
        assertTrue(result, "Appointment should be booked successfully");
    }

    @Test
    @Order(2)
    public void testDuplicateAppointment() {
        boolean result = service.bookAppointment(100, "Test User", "2025-07-10");
        assertFalse(result, "Duplicate ID should not be allowed");
    }

    @Test
    @Order(3)
    public void testListAppointments() {
        List<Patient> patients = service.listAppointments();
        assertFalse(patients.isEmpty(), "Appointment list should not be empty");

        Patient patient = patients.stream()
                .filter(p -> p.getId() == 100)
                .findFirst()
                .orElse(null);

        assertNotNull(patient, "Patient with ID 100 should exist");
        assertEquals("Test User", patient.getName());
        assertEquals("2025-07-10", patient.getAppointmentDate());
    }

    @Test
    @Order(4)
    public void testCancelAppointment() {
        boolean result = service.cancelAppointment(100);
        assertTrue(result, "Appointment should be canceled");

        List<Patient> patients = service.listAppointments();
        assertTrue(patients.stream().noneMatch(p -> p.getId() == 100),
                "Patient with ID 100 should no longer exist");
    }

    @Test
    @Order(5)
    public void testCancelNonExistingAppointment() {
        boolean result = service.cancelAppointment(9999);
        assertFalse(result, "Canceling non-existent appointment should return false");
    }
}
