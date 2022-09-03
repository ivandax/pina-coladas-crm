package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    @DisplayName("Should properly create Contact")
    public void shouldProperlyCreateContact (){
        Lead testLead = new Lead("Daycar", 603445289, "daycar@gmail.com", "THETERNITY");
        Contact testContact = new Contact(testLead);
        assertEquals("Daycar", testContact.getName());
        assertEquals(603445289, testContact.getPhoneNumber());
        assertEquals("daycar@gmail.com", testContact.getEmailAdress());
        assertEquals("THETERNITY", testContact.getCompanyName());
        assertEquals(1, testContact.getId());
    }



}