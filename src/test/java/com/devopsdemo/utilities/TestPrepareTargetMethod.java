package com.devopsdemo.utilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestPrepareTargetMethod {

    @Test
    public void testPrepareTargetMethodBuildsGetterName() {
        PrepareTargetMethod helper = new PrepareTargetMethod();
        assertEquals("getEmpName", helper.prepareTargetMethod("empName"));
    }
}
