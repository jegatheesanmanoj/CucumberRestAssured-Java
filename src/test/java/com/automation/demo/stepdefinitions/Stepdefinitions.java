package com.automation.demo.stepdefinitions;

import java.util.List;

import com.automation.api.request.dto.BookingRequest;
import com.automation.api.request.dto.Bookingdates;
import com.automation.api.response.dto.BookingResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class Stepdefinitions {

	RequestSpecification ovjRequestSpecification = null;
	BookingRequest objBookingRequest = null;
	Response objResponse = null;
	ObjectMapper objObjectMapper = new ObjectMapper();
	Gson objgson =new Gson();
	final String BOOKING_API_URL = "https://restful-booker.herokuapp.com/booking";

	
	
	@Given("^Prepare booking request with the necessary details$")
	public void prepareBookingRequest(DataTable objDataTable)
	{
		List<List<String>> objValues = objDataTable.asLists();
		System.out.println(objValues.get(0).get(0));	
		
		Bookingdates objBookingdates=new Bookingdates();
		objBookingdates.setCheckin(objValues.get(1).get(3));
		objBookingdates.setCheckout(objValues.get(1).get(4));
		
		objBookingRequest=new BookingRequest();
		objBookingRequest.setFirstname(objValues.get(1).get(0));
		objBookingRequest.setLastname(objValues.get(1).get(1));
		objBookingRequest.setTotalprice(Integer.parseInt(objValues.get(1).get(2)));
		objBookingRequest.setDepositpaid(true);
		objBookingRequest.setBookingdates(objBookingdates);
		
		ovjRequestSpecification = RestAssured.given(); 
		ovjRequestSpecification.header("Content-Type", "application/json");

		
	}
	
	@When("User post the booking request")
	public void user_post_the_booking_request() {
		try {
			ovjRequestSpecification.body(objObjectMapper.writeValueAsString(objBookingRequest));
			objResponse = ovjRequestSpecification.post(BOOKING_API_URL);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@Then("User should be able to receive {string} response code")
	public void user_should_be_able_to_receive_success_response_code(String success) {

		BookingResponse objBookingResponse=objgson.fromJson(objResponse.getBody().asString(),BookingResponse.class);
		Assert.assertNotNull(objBookingResponse.getBookingid());
		Assert.assertEquals(success, objResponse.getStatusCode()+"");
	}


}
