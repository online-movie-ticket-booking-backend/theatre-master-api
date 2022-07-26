package org.example.theatre.master.repository;

import org.example.theatre.master.entity.TheatreMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheatreMasterRepository extends JpaRepository<TheatreMaster, Integer> {

  List<TheatreMaster> findAllByTheatreUniqueIdIn(List<String> movieUniqueKeyList);
}
