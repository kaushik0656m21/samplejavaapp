package com.devopsdemo.tutorial.addressbook.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class TestContact {

    @Test
    public void testCloneCreatesDetachedCopy() throws Exception {
        Contact source = new Contact();
        source.setId(11L);
        source.setFirstName("Alice");
        source.setLastName("Brown");
        source.setPhone("555-1234");
        source.setEmail("alice@example.com");
        source.setBirthDate(new Date(1000L));

        Contact clone = source.clone();
        clone.setFirstName("Changed");

        assertNotSame(source, clone);
        assertEquals(Long.valueOf(11L), clone.getId());
        assertEquals("Alice", source.getFirstName());
        assertEquals("Changed", clone.getFirstName());
    }

    @Test
    public void testToStringContainsCoreFields() {
        Contact contact = new Contact();
        contact.setId(5L);
        contact.setFirstName("John");
        contact.setLastName("Smith");

        String output = contact.toString();

        assertTrue(output.contains("id=5"));
        assertTrue(output.contains("firstName=John"));
        assertTrue(output.contains("lastName=Smith"));
    }
}
