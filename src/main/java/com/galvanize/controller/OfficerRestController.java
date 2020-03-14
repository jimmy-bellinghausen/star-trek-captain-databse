package com.galvanize.controller;

import com.galvanize.entities.Officer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfficerRestController {
    @Autowired
    JpaRepository jpaRepository;

    @PostMapping("/officers")
    public Officer postOfficer(@RequestBody Officer officer){
        return (Officer) jpaRepository.save(officer);
    }

    @GetMapping("/officers")
    public List<Officer> findAllOfficers(){
        return jpaRepository.findAll();
    }

    @GetMapping("/officer")
    public Officer findOfficerByID(@RequestParam(required = false) long id){
        return (Officer)jpaRepository.findById(id).get();
    }

    @DeleteMapping("/officers")
    public void deleteById(long id){
        jpaRepository.deleteById(id);
    }
}
