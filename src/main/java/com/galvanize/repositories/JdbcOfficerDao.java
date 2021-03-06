package com.galvanize.repositories;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Null;
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
        if(exists(id)){
            return jdbcTemplate.queryForObject( "select * from officers where id = ?", (rs, rowNum) -> new Officer(rs.getLong("id"),
                    Rank.valueOf(rs.getString("officer_rank")),
                    rs.getString("first_name"),
                    rs.getString("last_name")), id);
        }
        return null;

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

    public boolean exists(long id) {
        try{
            return jdbcTemplate.queryForObject("Select 1 from officers where id=?", Boolean.class, id);
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    public boolean changeRank(long id, Rank rank){
        return 1==jdbcTemplate.update("UPDATE officers SET officer_rank=? WHERE id=?",rank.toString(),id);
    }

    public boolean changeFirstName(long id, String newFirstName) {
        return 1==jdbcTemplate.update("UPDATE officers SET first_name=? WHERE id=?", newFirstName,id);
    }
}
