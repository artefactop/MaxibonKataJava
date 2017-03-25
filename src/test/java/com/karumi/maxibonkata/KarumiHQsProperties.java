package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(JUnitQuickcheck.class)
public class KarumiHQsProperties {

    private Chat chat;
    private KarumiHQs karumiHQs;

    @Before
    public void setUp() {
        chat = mock(Chat.class);
        karumiHQs = new KarumiHQs(chat);
    }

    @Property(trials = 100)
    public void numberOfMaxibonsMayorThanTwo(@From(DevelopersGenerator.class) Developer developer) throws Exception {
        System.out.println(developer);
        karumiHQs.openFridge(developer);
        System.out.println(karumiHQs.getMaxibonsLeft());
        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

    @Property(trials = 101)
    public void numberOfMaxibonsMayorThanTwoInGroup(@Size(min = 0, max = 100) List<@From(DevelopersGenerator.class) Developer> developer) throws Exception {
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

    private int countMaxibons(int currentMaxibons, int wantedMaxibons) {
        int left = currentMaxibons - wantedMaxibons;
        if (left <= 2 && left > 0) {
            return left + 10;
        }
        if (left <= 0) {
            return 10;
        }
        return left;
    }

    @Property
    public void sendMessageToChat(@From(HungryDevelopersGenerator.class) Developer developer) throws Exception {
        System.out.println(developer);
        karumiHQs.openFridge(developer);
        System.out.println(karumiHQs.getMaxibonsLeft());
        verify(chat).sendMessage(anyString());

    }

    @Property
    public void notSendMessageToChat(@From(NotSoHungryDevelopersGenerator.class) Developer developer) throws Exception {
        System.out.println(developer);
        karumiHQs.openFridge(developer);
        System.out.println(karumiHQs.getMaxibonsLeft());
        verify(chat, never()).sendMessage(anyString());
    }

}
