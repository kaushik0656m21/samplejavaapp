package com.devopsdemo.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class TestStringUtilities {

    @Test
    public void testConvertStringToListReturnsNullForBlankInput() {
        assertNull(StringUtilities.convertStringToList("   ", "int"));
        assertNull(StringUtilities.convertStringToList(null, "int"));
    }

    @Test
    public void testConvertStringToListConvertsIntegers() {
        List<Object> values = StringUtilities.convertStringToList("1,2,3", "int");
        assertNotNull(values);
        assertEquals(3, values.size());
        assertEquals(Integer.valueOf(1), values.get(0));
        assertEquals(Integer.valueOf(3), values.get(2));
    }

    @Test
    public void testCreateParameterListSupportsMultiplePrimitiveTypes() {
        HashMap<String, Object> map = StringUtilities.createParameterList(
                "id=101;int",
                "enabled=true;boolean",
                "price=99.5;double");

        assertEquals(Integer.valueOf(101), map.get("id"));
        assertEquals(Boolean.TRUE, map.get("enabled"));
        assertEquals(Double.valueOf(99.5), map.get("price"));
    }

    @Test
    public void testCreateParameterListConvertsDateDefaultFormat() {
        HashMap<String, Object> map = StringUtilities.createParameterList(
                "when=2024-01-31 10:15:30;date");

        assertTrue(map.get("when") instanceof Date);
    }

    @Test
    public void testCreateParameterListConvertsDateCustomFormat() {
        HashMap<String, Object> map = StringUtilities.createParameterList(
                "when=31-01-2024@dd-MM-yyyy;date");

        assertTrue(map.get("when") instanceof Date);
    }

    @Test
    public void testCreateParameterListConvertsCommaSeparatedToList() {
        HashMap<String, Object> map = StringUtilities.createParameterList("ids=1,2,3;int");
        assertTrue(map.get("ids") instanceof List);
        List<?> values = (List<?>) map.get("ids");
        assertEquals(Integer.valueOf(2), values.get(1));
    }

    @Test
    public void testCreateParameterListKeepsStringWhenTypeUnknown() {
        HashMap<String, Object> map = StringUtilities.createParameterList("name=abc;notatype");
        assertEquals("abc", map.get("name"));
    }
}
