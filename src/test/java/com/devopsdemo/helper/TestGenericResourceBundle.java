package com.devopsdemo.helper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestGenericResourceBundle {

    @Test
    public void testGetPropertiesByCaseInsensitiveKey() {
        String value = GenericResourceBundle.getProperties("sample_key");
        assertEquals("sample-value", value);
    }

    @Test
    public void testGetPropertiesForMissingKeyReturnsEmptyString() {
        String value = GenericResourceBundle.getProperties("missing_key");
        assertEquals("", value);
    }
}
