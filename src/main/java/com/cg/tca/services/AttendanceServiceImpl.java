package com.cg.tca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tca.entities.Attendance;
//import com.cg.tca.entities.Supervisor;
import com.cg.tca.exception.ResourceNotFoundException;
import com.cg.tca.repository.AttendanceRepository;

@Service
//@Transactional
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceRepository attdetails;

	@Override
	public List<Attendance> getAllAttendance() {
		return attdetails.findAll();
	}

	// @Override
	// public List<Attendance> getAttendanceByEmpId(Integer employeeId) throws
	// ResourceNotFoundException {
	// log.info("fetched all attendance by an employee with id "+employeeId);
	// return attdetails.findByEmpId(employeeId);
	// }

	@Override
	public boolean deleteAttendanceByEmpId(Integer attendanceId) throws ResourceNotFoundException {
		boolean del;
		Attendance att = attdetails.findById(attendanceId).orElseThrow(
				() -> new ResourceNotFoundException("Attendance not found for this id :: " + attendanceId));
		del = att != null;
		attdetails.deleteId(attendanceId);
		return del;
	}

	@Override
	public boolean deleteAttendanceById(Integer attendanceId) throws ResourceNotFoundException {
		Attendance attendance = attdetails.findById(attendanceId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + attendanceId));
		attdetails.delete(attendance);

		return true;
	}

	@Override
	public int updateAttendanceById(Integer attendanceId, Attendance atts) throws ResourceNotFoundException {
		Attendance att = attdetails.findById(attendanceId).orElseThrow(
				() -> new ResourceNotFoundException("Attendance not found for this id :: " + attendanceId));
		//att.setAttendanceId(atts.getAttendanceId());
		att.setInTime(atts.getInTime());
		att.setOffTime(atts.getOffTime());
		att.setFromDate(atts.getFromDate());
		att.setToDate(atts.getToDate());
		att.setStatus(atts.getStatus());
		attdetails.save(att);

		return att.getAttendanceId();
	}
	/*
	 * @Override public Supervisor updateSupervisor(Integer supervisorId, Supervisor
	 * supervisorDetails) throws ResourceNotFoundException { Supervisor supervisor =
	 * supervisorRepository.findById(supervisorId).orElseThrow(() -> new
	 * ResourceNotFoundException("Employee not found for this id :: " +
	 * supervisorId));
	 * supervisor.setSupervisorName(supervisorDetails.getSupervisorName());
	 * supervisor.setSupervisorNumber(supervisorDetails.getSupervisorNumber());
	 * supervisor.setSupervisorEmail(supervisorDetails.getSupervisorEmail());
	 * supervisor.setPass(supervisorDetails.getPass());
	 * supervisor.setUserId(supervisorDetails.getUserId()); final Supervisor
	 * updatedSupervisor = supervisorRepository.save(supervisor);
	 * 
	 * return updatedSupervisor; }
	 */

	/**@Override
	public Attendance saveAttendanceDetails(Attendance att) {
		if (att.getToDate() == null) {
			att.setToDate(att.getFromDate());
		}
		return attdetails.save(att);
	}**/

	@Override
	public Attendance getAttendanceById(int attendanceId) throws ResourceNotFoundException {
		Attendance a = attdetails.findById(attendanceId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + attendanceId));

		return a;
	}

	@Override
	public Attendance add(Attendance att) {
		return attdetails.save(att);

	}

	@Override
	public List<Attendance> getAttendanceByEmpId(Integer employeeId) throws ResourceNotFoundException {
		return attdetails.findByEmpId(employeeId);
	}

	@Override
	public Attendance saveAttendance(Attendance att) {
		return attdetails.save(att);
	}

	// @Override
	// public List<Attendance> getAttendanceByEmpId(Integer employeeId) throws
	// ResourceNotFoundException {
	// log.info("fetched all attendance by an employee with id "+employeeId);
	// return attdetails.findByEmpId(employeeId);
	// }
}
