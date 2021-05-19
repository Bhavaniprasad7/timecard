package com.cg.tca.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tca.entities.Employee;
import com.cg.tca.entities.TimeCard;
import com.cg.tca.exception.ResourceNotFoundException;
import com.cg.tca.repository.EmployeeRepository;
import com.cg.tca.repository.TimeCardRepository;


@Service
public class TimeCardServiceImpl implements TimeCardService {

	
	@Autowired
	TimeCardRepository daoCaller;
	
	@Autowired
	public EmployeeRepository employeeRepository;
	
	
	@Override
	public TimeCard saveTimeEntry(TimeCard timeCard) {
		return daoCaller.save(timeCard);
	}

	@Override
	public boolean removeEntry(int timeCardId) throws ResourceNotFoundException {
		boolean check=false;
		TimeCard toDelete= daoCaller.findById(timeCardId).orElseThrow(() -> new ResourceNotFoundException("TimeCard not found for this id :: " + timeCardId));
		check=(toDelete!=null);
		daoCaller.deleteById(timeCardId);
		return check;
	}

	@Override
	public int updateEntries(int id, TimeCard tcard) throws ResourceNotFoundException {
		TimeCard timecard=daoCaller.findById(id).orElseThrow(() -> new ResourceNotFoundException(" TimeCard not found for this id :: " + id));
		timecard.setDate(tcard.getDate());
		timecard.setTimeEntry(tcard.getTimeEntry());
		timecard.setTimeExit(tcard.getTimeExit());
		daoCaller.save(timecard);
		return timecard.getTimeCardId();
	}

	
	@Override
	public List<TimeCard> displayAll() {
		return daoCaller.findAll();
	}

	@Override
	public TimeCard getTimeCard(Integer tcId) {
		TimeCard opt=null;
		if(daoCaller.findById(tcId).isPresent()) {
			opt=daoCaller.findById(tcId).get();
		}
		return opt;
	}


	@Override
	public Employee getEmployee(int employeeId) {
	return null;
	}
	
	

	
}