package com.crimerecord.Service;

import java.util.List;

import com.crimerecord.model.Criminals;


public interface CriminalsService {
	public Criminals findCriminalsByState (String state);
	
	public List<Criminals> findAllCriminals();
	
	//public Criminals findCriminalsByName(String firstName);

}
