package com.cg.tca.services;

import java.util.List;

import com.cg.tca.exception.ResourceNotFoundException;
import com.cg.tca.entities.Leave;

public interface  LeaveService {
	
	public Leave addLeave(Leave leave);

	public Leave findLeave(int leaveId) throws ResourceNotFoundException;

	int removeLeave(int leaveId) throws ResourceNotFoundException;
	
	List<Leave> findByEmpId(int empId);

	public int update(int leaveId, Leave leave) throws ResourceNotFoundException;
	
	List<Leave>findByAllLeaves();

      
}