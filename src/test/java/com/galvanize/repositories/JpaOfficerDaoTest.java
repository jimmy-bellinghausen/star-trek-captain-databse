package com.galvanize.repositories;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaOfficerDaoTest {
    @Autowired
    JpaRepository jpaRepository;

    @Test
    public void countOfficers(){
        assertEquals(5, jpaRepository.count());
    }

    @Test
    public void findAllOfficers(){
        assertEquals(5, jpaRepository.findAll().size());
    }

    @Test
    public void officerExistsById(){
        assertNotNull(jpaRepository.existsById(1L));
    }

    @Test
    public void officerDoesNotExistById(){
        assertFalse(jpaRepository.existsById(20L));
    }

    @Test
    public void findOfficerById(){
        Officer expectedOfficer = new Officer(1L, Rank.CAPTAIN, "James", "Kirk");
        assertEquals(expectedOfficer, jpaRepository.findById(1L).get());
    }

    @Test
    public void createNewOfficer(){
        Officer testOfficer = new Officer(Rank.COMMODORE, "Test", "Captain");
        assertNotNull(jpaRepository.save(testOfficer));
    }

    @Test
    public void deleteOfficer(){
        jpaRepository.deleteById(1L);
        assertEquals(4, jpaRepository.count());
    }
}