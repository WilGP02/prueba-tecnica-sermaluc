package com.reto.tecnico.wilmer.sermaluc.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class PhoneTest {

    @Test
    public void testGetSetId() {
        Phone phone = new Phone();
        UUID id = UUID.randomUUID();
        phone.setId(id);
        Assertions.assertEquals(id, phone.getId());
    }

    @Test
    public void testGetSetNumber() {
        Phone phone = new Phone();
        String number = "1234567890";
        phone.setNumber(number);
        Assertions.assertEquals(number, phone.getNumber());
    }

    @Test
    public void testGetSetCityCode() {
        Phone phone = new Phone();
        String cityCode = "123";
        phone.setCitycode(cityCode);
        Assertions.assertEquals(cityCode, phone.getCitycode());
    }

    @Test
    public void testGetSetCountryCode() {
        Phone phone = new Phone();
        String countryCode = "1";
        phone.setCountrycode(countryCode);
        Assertions.assertEquals(countryCode, phone.getCountrycode());
    }
}