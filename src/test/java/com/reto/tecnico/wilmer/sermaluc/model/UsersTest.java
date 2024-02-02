package com.reto.tecnico.wilmer.sermaluc.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class UsersTest {

    @Test
    public void testGetSetName() {
        Users users = new Users();
        String name = "John Doe";
        users.setName(name);
        Assertions.assertEquals(name, users.getName());
    }

    @Test
    public void testGetSetEmail() {
        Users users = new Users();
        String email = "john.doe@example.com";
        users.setEmail(email);
        Assertions.assertEquals(email, users.getEmail());
    }

    @Test
    public void testGetSetPassword() {
        Users users = new Users();
        String password = "P@ssw0rd!";
        users.setPassword(password);
        Assertions.assertEquals(password, users.getPassword());
    }

    @Test
    public void testGetSetIsActive() {
        Users users = new Users();
        boolean isActive = true;
        users.setIsActive(isActive);
        Assertions.assertEquals(isActive, users.getIsActive());
    }

    @Test
    public void testGetSetPhones() {
        Users users = new Users();
        List<Phone> phones = new ArrayList<>();
        Phone phone1 = new Phone();
        phone1.setNumber("1234567890");
        Phone phone2 = new Phone();
        phone2.setNumber("9876543210");
        phones.add(phone1);
        phones.add(phone2);
        users.setPhones(phones);
        Assertions.assertEquals(phones, users.getPhones());
    }
}