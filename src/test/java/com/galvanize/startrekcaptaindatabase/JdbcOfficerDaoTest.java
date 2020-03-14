package com.galvanize.startrekcaptaindatabase;

import com.galvanize.entities.JdbcOfficerDao;
import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcOfficerDaoTest {
    @Autowired
    JdbcOfficerDao jdbcOfficerDao;

    @Test
    public void countOfficers(){
        assertEquals(5L, jdbcOfficerDao.count());
    }

    @Test
    public void findAllOfficers(){assertEquals(5, jdbcOfficerDao.findAllOfficers().size());}

    @Test
    public void officerExistsById(){assertNotNull(jdbcOfficerDao.findById(1L));}

    @Test
    public void officerDoesNotExistById(){assertNull(jdbcOfficerDao.findById(10L));}

    @Test
    public void findOfficerById(){assertEquals(new Officer(1, Rank.CAPTAIN, "James", "Kirk"), jdbcOfficerDao.findById(1L));}

    @Test
    public void createNewOfficer(){
        Officer testOfficer = new Officer(Rank.COMMODORE, "Test", "Captain");
        Officer expectedOfficer = new Officer(jdbcOfficerDao.count()+1L, Rank.COMMODORE, "Test", "Captain");

        assertEquals(expectedOfficer, jdbcOfficerDao.save(testOfficer));
    }

    @Test
    public void deleteNewOfficer(){
        Officer testAndExpectedOfficer = new Officer(Rank.COMMODORE, "Test", "Captain");
        long expectedId = jdbcOfficerDao.count()+1L;
        Officer intermediateOfficer = new Officer(expectedId, Rank.COMMODORE, "Test", "Captain");

        assertEquals(intermediateOfficer, jdbcOfficerDao.save(testAndExpectedOfficer));

        assertTrue(jdbcOfficerDao.deleteByID(expectedId));
    }

    @Test
    public void failToDeleteNewOfficer(){
        assertFalse(jdbcOfficerDao.deleteByID(jdbcOfficerDao.count()+1));
    }
}