package com.galvanize.repositories;

import com.galvanize.entities.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface JpaOfficerDao extends JpaRepository<Officer, Id> {

}
