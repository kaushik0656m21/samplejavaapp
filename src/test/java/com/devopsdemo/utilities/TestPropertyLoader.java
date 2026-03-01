package com.devopsdemo.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Test;

public class TestPropertyLoader {

    @Test(expected = IllegalArgumentException.class)
    public void testLoadPropertiesWithNullNameThrows() {
        PropertyLoader.loadProperties(null);
    }

    @Test
    public void testLoadPropertiesFromClasspath() {
        Properties properties = PropertyLoader.loadProperties("Dateformat");
        assertNotNull(properties);
        assertEquals("yyyy-MM-dd", properties.getProperty("EXPECTED"));
        assertEquals("dd-MM-yyyy", properties.getProperty("ACTUAL"));
    }

    @Test
    public void testLoadPropertiesWithSuffixAlsoWorks() {
        Properties properties = PropertyLoader.loadProperties("Dateformat.properties");
        assertNotNull(properties);
        assertTrue(properties.containsKey("TIME"));
    }

    @Test
    public void testLoadMissingPropertiesReturnsEmpty() {
        Properties properties = PropertyLoader.loadProperties("not_present_file");
        assertNotNull(properties);
        assertTrue(properties.isEmpty());
    }
}
