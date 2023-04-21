package com.crimerecord.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crimerecord.exception.RecordNotFoundException;
import com.crimerecord.model.Criminals;
import com.crimerecord.model.Criminals;
import com.crimerecord.model.Criminals;
import com.crimerecord.repository.CriminalsRepository;

@Service
public class CriminalsServiceImp implements CriminalsService{

	@Override
	public Criminals findCriminalsByState(String state) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static final int SEARCH_RESULT_PER_PAGE = 10;
	
	@Autowired
	private CriminalsRepository repo;
	
	public List<Criminals> findCriminalsByState;
	
	public Page<Criminals> search(String keyword, int pageNum){
		Pageable pageable = PageRequest.of(pageNum -1, SEARCH_RESULT_PER_PAGE);
		return repo.search(keyword, pageable);
	}

//	public List<Criminals> search(String keyword){
//		return repo.search(keyword);
//	}
	@Override
	public List<Criminals> findAllCriminals() {
		// TODO Auto-generated method stub
		return (List<Criminals>)repo.findAll();
	}

//	@Override
//	public Criminals findCriminalsByName(String firstName) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
public Criminals registerCriminals(String firstName, String lastName, String offence, int sentence, String state, String status) {
		
		if(firstName == null || lastName == null) {
			return null;	
		}
		
		else {
			Criminals criminals = new Criminals();
			criminals.setFirstName(firstName);
			criminals.setLastName(lastName);
			criminals.setOffence(offence);
			criminals.setSentence(sentence);
			criminals.setState(state);
			criminals.setStatus(status);
			return repo.save(criminals);
		}	
		
	}

public Criminals getCriminalById(Integer id) throws RecordNotFoundException {
	System.out.println("getCriminalById");
	Optional<Criminals> criminals = repo.findById(id);
	
	if(criminals.isPresent()) {
		return criminals.get();
	}
	else {
		throw new RecordNotFoundException("no criminal");
	}
}

public Criminals UpdateCriminal(Criminals criminal) {
	System.out.println("UpdateCriminal");
	Optional<Criminals> criminals = repo.findById(criminal.getId());
	if(criminals.isPresent()) {
		Criminals newC = criminals.get();
		newC.setOffence(criminal.getOffence());
		newC.setFirstName(criminal.getFirstName());
		newC.setLastName(criminal.getLastName());
		newC.setSentence(criminal.getSentence());
		newC.setState(criminal.getState());
		newC.setStatus(criminal.getStatus());
		
		newC = repo.save(criminal);
		return newC;
	}
	else {
		criminal = repo.save(criminal);
		return criminal;
	}
	
	
}

public void deleteCriminal(Integer id) throws RecordNotFoundException {
	System.out.println("deleteCriminal");
	Optional<Criminals> criminals = repo.findById(id);
	if(criminals.isPresent()) {
		repo.deleteById(id);
	}
	else {
		throw new RecordNotFoundException("no criminal");
	}
}

}
