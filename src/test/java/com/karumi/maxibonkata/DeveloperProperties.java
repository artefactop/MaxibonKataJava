package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitQuickcheck.class)
public class DeveloperProperties {

    private static final String ANY_NAME = "Pedro";
    private static final int ANY_NUMBER_OF_MAXIBONS = 1;

    @Property
    public void numberOfMaxibonsToConsumeCanNotBeNegative(String devName, int numberOfMaxibons) throws Exception {
        System.out.println(numberOfMaxibons);
        Developer developer = new Developer(devName, numberOfMaxibons);
        System.out.println(developer);
        assertTrue(developer.getNumberOfMaxibonsToGrab() >= 0);
    }

    @Test
    public void numberOfMaxibonsForDeveloper() {
        assertEquals(3, Karumies.PEDRO.getNumberOfMaxibonsToGrab());
        assertEquals(0, Karumies.DAVIDE.getNumberOfMaxibonsToGrab());
        assertEquals(1, Karumies.ALBERTO.getNumberOfMaxibonsToGrab());
        assertEquals(1, Karumies.JORGE.getNumberOfMaxibonsToGrab());
        assertEquals(2, Karumies.SERGIO.getNumberOfMaxibonsToGrab());
    }
}
