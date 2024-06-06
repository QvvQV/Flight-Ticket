package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class AviaSoulsTest {
    Ticket ticket1 = new Ticket("Sochi", "Saratov", 5_000, 10, 12);
    Ticket ticket2 = new Ticket("Sochi", "Saratov", 6_500, 10, 11);
    Ticket ticket3 = new Ticket("Sochi", "Saratov", 7_500, 10, 13);
    Ticket ticket4 = new Ticket("Saint-Petersburg", "Kaliningrad", 5_200, 13, 12);
    Ticket ticket5 = new Ticket("Ulan-Ude", "Irkutsk", 5_000, 5_30, 7_20);

    @Test
    public void shouldPriceLessAnother() { //цена меньше чем вторая
        AviaSouls avia = new AviaSouls();

        avia.add(ticket1);
        avia.add(ticket2);

        int expected = -1;
        int actual = ticket1.compareTo(ticket2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldPriceMoreAnother() { //цена больше, чем вторая
        AviaSouls avia = new AviaSouls();

        avia.add(ticket3);
        avia.add(ticket2);

        int expected = 1;
        int actual = ticket3.compareTo(ticket2);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldPriceEqualTo() { //цена одинаковая
        AviaSouls avia = new AviaSouls();

        avia.add(ticket1);
        avia.add(ticket5);

        int expected = 0;
        int actual = ticket1.compareTo(ticket5);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldRouteSearch() { //поиск по маршруту
        AviaSouls avia = new AviaSouls();

        avia.add(ticket1);
        avia.add(ticket2);
        avia.add(ticket5);

        Ticket[] expected = {ticket1, ticket2};
        Ticket[] actual = avia.search("Sochi", "Saratov");

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldNoRouteSearch() { //поиск по не известному маршруту
        AviaSouls avia = new AviaSouls();

        avia.add(ticket1);
        avia.add(ticket2);
        avia.add(ticket3);
        avia.add(ticket4);
        avia.add(ticket5);

        Ticket[] expected = {};
        Ticket[] actual = avia.search("Sochi", "Irkutsk");

        Assertions.assertArrayEquals(expected, actual);
    }

        @Test
    public void shouldOneRouteSearch() { //поиск единственного маршрута
        AviaSouls avia = new AviaSouls();

        avia.add(ticket1);
        avia.add(ticket2);
        avia.add(ticket3);
        avia.add(ticket4);
        avia.add(ticket5);

        Ticket[] expected = {ticket4};
        Ticket[] actual = avia.search("Saint-Petersburg", "Kaliningrad");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFlightTime2 (){ //упорядочивание билетов по времени
        AviaSouls avia = new AviaSouls();
        Comparator<Ticket> comparator = new TicketTimeComparator();

        avia.add(ticket1);
        avia.add(ticket2);
        avia.add(ticket3);

        Ticket[] expected = {ticket1, ticket2, ticket3};
        Arrays.sort(expected, comparator);
    }

        @Test
    public void shouldFlightTime1 (){ //упорядочивание билетов по времени
        AviaSouls avia = new AviaSouls();
        Comparator<Ticket> comparator = new TicketTimeComparator();

        avia.add(ticket1);
        avia.add(ticket3);

        Ticket[] expected = {ticket1, ticket3};
        Arrays.sort(expected, comparator);
    }

    @Test
    public void shouldSortFlightTime() { //сортировка по веремени и названию
        AviaSouls avia = new AviaSouls();
        Comparator<Ticket> comparator = new TicketTimeComparator();

        avia.add(ticket1);
        avia.add(ticket2);
        avia.add(ticket3);
        avia.add(ticket4);

        Ticket[] expected = { ticket1, ticket2, ticket3 };
        Ticket[] actual = avia.searchAndSortBy("Sochi", "Saratov", comparator);
        Arrays.sort(expected, comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

}
