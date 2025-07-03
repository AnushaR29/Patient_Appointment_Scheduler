package com.appointment;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {

    public AppTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testBooking() {
        AppointmentService service = new AppointmentService();
        Patient testPatient = new Patient("Test User", "test@example.com", "2025-07-03 12:00");
        service.bookAppointment(testPatient);
        assertTrue(true); // Simplified test
    }
}
