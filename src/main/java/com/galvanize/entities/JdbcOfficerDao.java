package com.galvanize.entities;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public List<Officer> findAllOfficers() {
        return jdbcTemplate.query("select * from officers", (rs, rowNum) -> new Officer(rs.getLong("id"),
                Rank.valueOf(rs.getString("officer_rank")),
                rs.getString("first_name"),
                rs.getString("last_name")));
    }

    public Officer findById(long id) {
        try{
            return jdbcTemplate.queryForObject( "select * from officers where id = ?", (rs, rowNum) -> new Officer(rs.getLong("id"),
                    Rank.valueOf(rs.getString("officer_rank")),
                    rs.getString("first_name"),
                    rs.getString("last_name")), id);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
}
