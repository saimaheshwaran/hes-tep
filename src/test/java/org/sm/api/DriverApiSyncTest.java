package org.sm.api;

import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class DriverApiSyncTest {

    @Test
    public void test_two_separate_instances() {
        DriverApi driverApi1 = DriverApi.getInstance();
        DriverApi driverApi2 = DriverApi.getInstance();
        driverApi1.setBody("SomeBody");
        assertEquals(driverApi1.toString(), driverApi2.toString());
    }

    @Test
    public void test_two_separate_instances_clear() {
        DriverApi driverApi1 = DriverApi.getInstance();
        DriverApi driverApi2 = DriverApi.getInstance();
        driverApi1.setBody("SomeBody");
        assertEquals(driverApi1.getBody(), driverApi2.getBody());
        driverApi1.clear();
        assertEquals(driverApi1.getBody(), driverApi2.getBody());
    }

}
