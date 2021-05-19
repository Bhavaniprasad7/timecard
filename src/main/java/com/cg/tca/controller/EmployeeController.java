package com.cg.tca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.tca.entities.Employee;
import com.cg.tca.entities.Supervisor;
import com.cg.tca.exception.ResourceNotFoundException;
import com.cg.tca.services.EmployeeService;
import com.cg.tca.services.SupervisorService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private SupervisorService supService;

	@PostMapping("/create")
	public String createEmployee(@RequestBody Employee employee) {
		employeeService.create(employee);
		return "Employee Created";
	}

	@GetMapping("/all")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable (value = "id") int empId) throws ResourceNotFoundException {
		return employeeService.getEmpById(empId);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer employeeId,
			@RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeService.updateEmployee(employeeId, employeeDetails);
		return ResponseEntity.ok(employee);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable(value = "id") Integer employeeId)
			throws ResourceNotFoundException {
		return ResponseEntity.ok(employeeService.deleteEmployeeById(employeeId));
	}

	@PutMapping("/{employeeId}/supervisor/{supervisorId}")
	public String asssignSupervisor(@PathVariable int employeeId, @PathVariable int supervisorId ) throws ResourceNotFoundException {
	
		Employee employee = employeeService.getEmpById(employeeId);
		Supervisor supervisor =  supService.getSupervisorById(supervisorId);
		employee.setSupervisor(supervisor);
		employeeService.save(employee);
		
		return "Supervisor Linked";
	}
	
	/**
	 * @PutMapping("/{id}") public ResponseEntity<Employee>
	 * createEmployee(@RequestBody Employee employee, @PathVariable("sup_id")
	 * Integer supervisorId) throws ResourceNotFoundException{ Supervisor
	 * sup=supServ.getSupervisorById(supervisorId); if(sup!=null)
	 * employee.setSupervisor(sup); return
	 * ResponseEntity.ok(employeeService.createEmployee(employee)); }
	 **/

}