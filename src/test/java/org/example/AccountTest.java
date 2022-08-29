package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    @DisplayName("Should create account with empty lists")
    public void shouldCreateAccount() {
        Account testAccount = new Account(Industry.ECOMMERCE, 5, "Test city", "Test country");
        assertEquals(0, testAccount.getContacts().size());
        assertEquals(0, testAccount.getOpportunityList().size());
    }

    @Test
    @DisplayName("Should property add contact")
    public void shouldAddContact() {
        Account testAccount = new Account(Industry.ECOMMERCE, 5, "Test city", "Test country");
        Lead newLead = new Lead("Mike", 3453, "some email", "some company");
        Contact newContact = new Contact(newLead);
        testAccount.addContact(newContact);
        assertEquals(1, testAccount.getContacts().size());
        assertEquals("Mike", testAccount.getContacts().get(0).getName());
        assertEquals(0, testAccount.getOpportunityList().size());
    }

}
