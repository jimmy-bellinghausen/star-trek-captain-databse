package com.galvanize.entities;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcOfficerDao {
    JdbcTemplate jdbcTemplate;

    public JdbcOfficerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public long count() {
        try{
            return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM officers", Long.class);
        }catch(NullPointerException e){
            return 0;
        }
    }
}
