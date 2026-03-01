package com.devopsdemo.utilities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestPropertyHelper {

    @Before
    public void setUp() {
        PropertyHelper.loadProperties("Dateformat");
    }

    @Test
    public void testGetPropertyReturnsConfiguredValue() {
        assertEquals("yyyy-MM-dd", PropertyHelper.getProperty("EXPECTED"));
    }

    @Test
    public void testGetPropertyReturnsDefaultWhenMissing() {
        assertEquals("fallback", PropertyHelper.getProperty("UNKNOWN_KEY", "fallback"));
    }

    @Test
    public void testGetAliasDelegatesToGetProperty() {
        assertEquals("hh:mm:ss aa", PropertyHelper.get("TIME"));
    }
}
