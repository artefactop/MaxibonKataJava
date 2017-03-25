package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(JUnitQuickcheck.class)
public class KarumiHQsProperties {

    private static KarumiHQs karumiHQs;

    @BeforeClass
    public static void setUp() {
        Chat chat = mock(Chat.class);
        karumiHQs = new KarumiHQs(chat);
    }

    @Property(trials = 100)
    public void numberOfMaxibonsMayorThanTwo(@From(DevelopersGenerator.class) Developer developer) throws Exception {
        System.out.println(developer);
        karumiHQs.openFridge(developer);
        System.out.println(karumiHQs.getMaxibonsLeft());
        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

    @Property
    public void refillToTen(@From(HungryDevelopersGenerator.class) Developer developer) {
        System.out.println(developer);
        int currentMaxibons = karumiHQs.getMaxibonsLeft();
        System.out.println(karumiHQs.getMaxibonsLeft());
        karumiHQs.openFridge(developer);
        System.out.println(karumiHQs.getMaxibonsLeft());
        assertTrue(karumiHQs.getMaxibonsLeft() == countMaxibons(currentMaxibons, developer.getNumberOfMaxibonsToGrab()));
    }

    private int countMaxibons(int currentMaxibons, int removedMaxibons) {
        int left = currentMaxibons - removedMaxibons;
        if (left <= 2 && left > 0) {
            return left + 10;
        }
        if (left <= 0) {
            return 10;
        }
        return left;
    }

}
