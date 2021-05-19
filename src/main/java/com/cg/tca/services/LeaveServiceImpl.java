package com.cg.tca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tca.entities.Leave;
import com.cg.tca.exception.ResourceNotFoundException;
import com.cg.tca.repository.LeaveRepository;

@Service

public class LeaveServiceImpl implements LeaveService {

	@Autowired
	public LeaveRepository leaveRep;
	
	

	@Override
	public Leave addLeave(Leave leave) {
		return leaveRep.save(leave);
	}

	@Override
	public int removeLeave(int leaveId) throws ResourceNotFoundException {
		Leave toDelete = leaveRep.findById(leaveId)
				.orElseThrow(() -> new ResourceNotFoundException("Leave not found for this id :: " + leaveId));
		if (toDelete != null) {
			leaveRep.deleteById(leaveId);
		}
		return leaveId;
	}

	@Override
	public int update(int leaveId, Leave leaave) throws ResourceNotFoundException {
		Leave leave = leaveRep.findById(leaveId)
				.orElseThrow(() -> new ResourceNotFoundException("Leave not found for this id :: " + leaveId));
		leave.setFromDate(leave.getFromDate());
		leave.setToDate(leave.getToDate());
		leaveRep.save(leave);
		return leave.getLeaveId();
	}
	
	@Override
	public List<Leave> findByEmpId(int empId) {
		return leaveRep.findByEmpId(empId);

	}

	@Override
	public Leave findLeave(int leaveId) throws ResourceNotFoundException {
		return leaveRep.findById(leaveId)
				.orElseThrow(() -> new ResourceNotFoundException("Leave not found for this id :: " + leaveId));
	}

	@Override
	public List<Leave> findByAllLeaves() {
		return leaveRep.findAll();
	}

}
