package com.cg.tca.controller;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.tca.entities.Employee;
import com.cg.tca.entities.TimeCard;
import com.cg.tca.services.EmployeeService;
import com.cg.tca.services.TimeCardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TimeCardController.class)
class TimeCardControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TimeCardService tcs;
	
	@MockBean
	private EmployeeService empSer;
	 
	Employee emp;
	TimeCard tcard;
	
	@BeforeEach
	void setUp() throws Exception {
		emp=new Employee();
		tcard=new TimeCard();
		emp.setEmployeeId(100);
		emp.setEmployeeName("RAJU");
		emp.setEmployeeEmail("chiku@gmail.com");
		emp.setPhoneNumber("08512518301");
		tcard.setDate(LocalDate.now());
		tcard.setTimeEntry(LocalTime.MIN);
		tcard.setTimeExit(LocalTime.MAX);
		tcard.setEmployee(emp);
	}
	
	@AfterEach
	void tearDown() throws Exception{
		emp=null;
		tcard=null;
	}
	
	 @Test
	   public void testAddTimeCard() throws Exception{
		  String URI = "/api/v2/timecard/create/";
		  String jsonInput = this.converttoJson(tcard);

		  Mockito.when(tcs.saveTimeEntry(tcard)).thenReturn(tcard);
		  MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
				    		.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput)
				    		.contentType(MediaType.APPLICATION_JSON)).andReturn();
		  MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	      String jsonOutput = mockHttpServletResponse.getContentAsString();
	    //  assertThat(jsonInput).isEqualTo(jsonOutput);
	     // Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	 }
	 
	/*@Test
	 public void testViewEmployee() throws Exception{
		 String URI = "/api/v2/timecard/employee/{id}";
		 String jsonInput = this.converttoJson(tcard);
		 TimeCard newCard=new TimeCard();
		 newCard.setEmployee(emp);
		 newCard.setDate(LocalDate.of(2020, 05, 17));
		 newCard.setTimeEntry(LocalTime.of(9, 03));
		 newCard.setTimeExit(LocalTime.of(18, 35));
		 ArrayList<TimeCard> checklist=new ArrayList<>();
		 checklist.add(tcard);
		 checklist.add(newCard);
	     Mockito.when(tcs.displayEntries(emp.getEmployeeId())).thenReturn(checklist);
		 MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, 100)
		 		 			.accept(MediaType.APPLICATION_JSON)
				 			.content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				 			.andReturn();
		 MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		 String jsonOutput = mockHttpServletResponse.getContentAsString();
		 
		// assertThat(jsonInput).isEqualTo(jsonOutput);  	 
	 }*/
	 @Test
	 public void testUpdateTimeCradByEmpId() throws Exception{
		 String URI= "/api/timecard/gettimecard/{id}";
		 String jsonInput = this.converttoJson(tcard);
		 Mockito.when(tcs.updateEntries(100, tcard))
		 							.thenReturn(tcard.getTimeCardId());
		 MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, 3)
				 			.accept(MediaType.APPLICATION_JSON)
				 			.content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				 			.andReturn();
		 MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		 String jsonOutput = mockHttpServletResponse.getContentAsString();
	
	 	// assertThat(jsonInput).isEqualTo(jsonOutput);  	 
	 }
	    
	    @Test
	    public void testDeleteEmployee() throws Exception{
	    	
	       String URI= "/api/timecard/timecarddelete/1";
	       TimeCard newCard=new TimeCard();
	       newCard.setEmployee(emp);
	       newCard.setDate(LocalDate.of(2020, 05, 17));
	       newCard.setTimeEntry(LocalTime.of(9, 03));
	       newCard.setTimeExit(LocalTime.of(18, 35));
	       ArrayList<TimeCard> checklist=new ArrayList<>();
	       checklist.add(tcard);
	       checklist.add(newCard);

	    	String jsonInput = this.converttoJson(true);
	    	Mockito.when(tcs.removeEntry(tcard.getTimeCardId()))
	    	.thenReturn(true);
	    	MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
	    			.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	      //  assertThat(jsonInput).isEqualTo(jsonOutput);
	  
	    }
	 

	  /**
     * Convert Object into Json String by using Jackson ObjectMapper
     * @param ticket
     * @return
     * @throws JsonProcessingException
     */
   private String converttoJson(Object manager) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(manager); 
   }
}

