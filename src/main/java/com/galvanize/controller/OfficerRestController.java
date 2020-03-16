package com.galvanize.controller;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import com.galvanize.repositories.JdbcOfficerDao;
import com.galvanize.repositories.JpaOfficerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfficerRestController {
    @Autowired
    JpaOfficerDao jpaRepository;
    @Autowired
    JdbcOfficerDao jdbcRepository;

    @PostMapping("/officers")
    public Officer postOfficer(@RequestBody Officer officer){
        return (Officer) jpaRepository.save(officer);
    }

    @GetMapping("/officers")
    public List<Officer> findAllOfficers(){
        return jpaRepository.findAll();
    }

    @GetMapping("/officer")
    public Officer findOfficerByID(@RequestParam long id){
        return jpaRepository.findById(id);
    }

    @GetMapping("/changerank")
    public Officer promote(@RequestParam long id, @RequestParam Rank rank){
        return jdbcRepository.changeRank(id,rank);
    }

    @DeleteMapping("/officers")
    public void deleteById(long id){
        jpaRepository.deleteById(id);
    }
}
