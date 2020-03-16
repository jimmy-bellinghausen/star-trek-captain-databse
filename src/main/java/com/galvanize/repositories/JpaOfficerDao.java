package com.galvanize.repositories;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface JpaOfficerDao extends JpaRepository<Officer, Id> {

    void deleteById(long id);

    Officer findById(long id);
}
