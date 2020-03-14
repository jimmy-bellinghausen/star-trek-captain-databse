package com.galvanize.controller;

import com.galvanize.entities.Officer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfficerRestController {
    @Autowired
    JpaRepository jpaRepository;

    @PostMapping("/officer")
    public Officer postOfficer(@RequestBody Officer officer){
        return (Officer) jpaRepository.save(officer);
    }
}
