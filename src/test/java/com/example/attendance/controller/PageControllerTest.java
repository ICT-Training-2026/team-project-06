package com.example.attendance.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PageController.class)
public class PageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowAttendancePage() throws Exception {
        mockMvc.perform(get("/attendance/register"))
               .andExpect(status().isOk())
               .andExpect(view().name("AttendanceRegistration"));
    }

    @Test
    public void testShowClockInPage() throws Exception {
        mockMvc.perform(get("/attendance/clockin-page"))
               .andExpect(status().isOk())
               .andExpect(view().name("ClockIn"));
    }

    @Test
    public void testShowClockOutPage() throws Exception {
        mockMvc.perform(get("/attendance/clockout-page"))
               .andExpect(status().isOk())
               .andExpect(view().name("ClockOut"));
    }

    @Test
    public void testShowBreakStartPage() throws Exception {
        mockMvc.perform(get("/attendance/breakstart-page"))
               .andExpect(status().isOk())
               .andExpect(view().name("StartBreak"));
    }

    @Test
    public void testShowBreakEndPage() throws Exception {
        mockMvc.perform(get("/attendance/breakend-page"))
               .andExpect(status().isOk())
               .andExpect(view().name("ENDBreak"));
    }

    @Test
    public void testShowUserHome() throws Exception {
        mockMvc.perform(get("/userhome"))
               .andExpect(status().isOk())
               .andExpect(view().name("userhome"));
    }

    @Test
    public void testShowAdminHome() throws Exception {
        mockMvc.perform(get("/adminhome"))
               .andExpect(status().isOk())
               .andExpect(view().name("managerhome"));
    }

    @Test
    public void testShowLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
               .andExpect(status().isOk())
               .andExpect(view().name("login"));
    }

    @Test
    public void testShowAttendanceConfirmationPage() throws Exception {
        mockMvc.perform(get("/attendance/confirmation"))
               .andExpect(status().isOk())
               .andExpect(view().name("AttendanceConfirmation"));
    }

    @Test
    public void testShowAttendanceManagementPage() throws Exception {
        mockMvc.perform(get("/attendance/management"))
               .andExpect(status().isOk())
               .andExpect(view().name("AttendanceManagement"));
    }

    @Test
    public void testShowAttendanceEditPage() throws Exception {
        mockMvc.perform(get("/attendance/edit"))
               .andExpect(status().isOk())
               .andExpect(view().name("AttendanceEdit"));
    }

    @Test
    public void testShowUserRegistrationPage() throws Exception {
        mockMvc.perform(get("/userregistration"))
               .andExpect(status().isOk())
               .andExpect(view().name("userRegistration"));
    }

    @Test
    public void testShowHolidayPage() throws Exception {
        mockMvc.perform(get("/holiday"))
               .andExpect(status().isOk())
               .andExpect(view().name("holiday"));
    }

    @Test
    public void testShowApplicationPage() throws Exception {
        mockMvc.perform(get("/application"))
               .andExpect(status().isOk())
               .andExpect(view().name("request"));
    }
}
