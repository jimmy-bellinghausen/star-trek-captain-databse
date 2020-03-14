package com.galvanize.entities;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JdbcOfficerDao {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertOfficer;

    public JdbcOfficerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        insertOfficer = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("officers")
                .usingGeneratedKeyColumns("id");
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

    public Officer save(Officer testOfficer) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("officer_rank", testOfficer.getRank());
        parameters.put("first_name", testOfficer.getFirst());
        parameters.put("last_name", testOfficer.getLast());


        long newId = insertOfficer.executeAndReturnKey(parameters).longValue();
        testOfficer.setId(newId);

        return testOfficer;
    }

    public boolean deleteByID(long expectedId) {
        return jdbcTemplate.update("DELETE FROM officers WHERE id = ?", expectedId)==1;
    }
}
