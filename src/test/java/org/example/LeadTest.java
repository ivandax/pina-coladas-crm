package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeadTest {

    @Test
    @DisplayName("Should properly create Lead")
    public void shouldProperlyCreateLead (){
        Lead testLead = new Lead("Daycar", 603445289, "daycar@gmail.com", "THETERNITY");
        assertEquals("Daycar", testLead.getName());
        assertEquals(603445289, testLead.getPhoneNumber());
        assertEquals("daycar@gmail.com", testLead.getEmailAdress());
        assertEquals("THETERNITY", testLead.getCompanyName());
        assertEquals(1, testLead.getId());
    }



}