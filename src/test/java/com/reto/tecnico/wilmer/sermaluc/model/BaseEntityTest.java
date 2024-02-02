package com.reto.tecnico.wilmer.sermaluc.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.UUID;

public class BaseEntityTest {

    @Test
    public void testGetSetId() {
        BaseEntity baseEntity = new BaseEntity();
        UUID id = UUID.randomUUID();
        baseEntity.setId(id);
        Assertions.assertEquals(id, baseEntity.getId());
    }

    @Test
    public void testGetSetCreated() {
        BaseEntity baseEntity = new BaseEntity();
        LocalDateTime created = LocalDateTime.now();
        baseEntity.setCreated(created);
        Assertions.assertEquals(created, baseEntity.getCreated());
    }

    @Test
    public void testGetSetModified() {
        BaseEntity baseEntity = new BaseEntity();
        LocalDateTime modified = LocalDateTime.now();
        baseEntity.setModified(modified);
        Assertions.assertEquals(modified, baseEntity.getModified());
    }

    @Test
    public void testGetSetLastLogin() {
        BaseEntity baseEntity = new BaseEntity();
        LocalDateTime lastLogin = LocalDateTime.now();
        baseEntity.setLast_login(lastLogin);
        Assertions.assertEquals(lastLogin, baseEntity.getLast_login());
    }
}