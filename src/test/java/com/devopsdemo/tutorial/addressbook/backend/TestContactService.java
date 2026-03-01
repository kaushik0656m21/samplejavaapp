package com.devopsdemo.tutorial.addressbook.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class TestContactService {

    @Test
    public void testSaveAssignsIdAndIncreasesCount() {
        ContactService service = new ContactService();
        Contact contact = new Contact();
        contact.setFirstName("Alice");
        contact.setLastName("Smith");

        service.save(contact);

        assertEquals(Long.valueOf(0L), contact.getId());
        assertEquals(1L, service.count());
    }

    @Test
    public void testFindAllReturnsDescendingById() {
        ContactService service = new ContactService();
        Contact first = new Contact();
        first.setFirstName("A");
        Contact second = new Contact();
        second.setFirstName("B");

        service.save(first);
        service.save(second);
        List<Contact> result = service.findAll(null);

        assertEquals(2, result.size());
        assertEquals(Long.valueOf(1L), result.get(0).getId());
        assertEquals(Long.valueOf(0L), result.get(1).getId());
    }

    @Test
    public void testFindAllAppliesCaseInsensitiveFilter() {
        ContactService service = new ContactService();
        Contact alice = new Contact();
        alice.setFirstName("Alice");
        alice.setLastName("Jones");
        Contact bob = new Contact();
        bob.setFirstName("Bob");
        bob.setLastName("Stone");

        service.save(alice);
        service.save(bob);

        List<Contact> filtered = service.findAll("ALICE");

        assertEquals(1, filtered.size());
        assertEquals("Alice", filtered.get(0).getFirstName());
    }

    @Test
    public void testFindAllReturnsDetachedClones() {
        ContactService service = new ContactService();
        Contact source = new Contact();
        source.setFirstName("Original");
        service.save(source);

        List<Contact> result = service.findAll(null);
        Contact clone = result.get(0);
        clone.setFirstName("Changed");
        List<Contact> secondRead = service.findAll(null);

        assertNotSame(clone, secondRead.get(0));
        assertEquals("Original", secondRead.get(0).getFirstName());
    }

    @Test
    public void testDeleteRemovesContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact();
        contact.setFirstName("DeleteMe");
        service.save(contact);
        assertEquals(1L, service.count());

        service.delete(contact);

        assertEquals(0L, service.count());
        assertTrue(service.findAll(null).isEmpty());
    }

    @Test
    public void testCreateDemoServiceIsSingletonAndSeeded() {
        ContactService one = ContactService.createDemoService();
        ContactService two = ContactService.createDemoService();

        assertTrue(one == two);
        assertFalse(one.findAll(null).isEmpty());
    }
}
