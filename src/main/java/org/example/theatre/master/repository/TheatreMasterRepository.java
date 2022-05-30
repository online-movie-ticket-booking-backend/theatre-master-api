package org.example.theatre.master.repository;

import org.example.theatre.master.entity.TheatreMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TheatreMasterRepository extends JpaRepository<TheatreMaster, Integer> {

  Optional<TheatreMaster> findByTheatreUniqueId(String movieUniqueKey);
}
