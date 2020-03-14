package com.galvanize.startrekcaptaindatabase;

import com.galvanize.JdbcOfficerDao;
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

}