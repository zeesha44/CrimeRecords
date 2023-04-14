package com.crimerecord.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crimerecord.model.Criminals;

public interface CriminalsRepository extends JpaRepository<Criminals, Integer> {
	
	//public Criminals findByState(String state);
	List<Criminals> findByState(String state);
	
}
