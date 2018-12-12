package com.royalty.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.royalty.model.RoyaltyPayment;
@RequestMapping("/royaltymanager")
public interface RoyaltyWebService {
	
	/**
	 * Return a list of the royalty payments owed to the studios in GBP£.
	 * @return a list
	 */	
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", path = "/payments")
	ResponseEntity<List<RoyaltyPayment>> getAllPayments();
	
	/**
     * Returns the royalties owed to a specific Rights Owner 
     *
     * @param rightsOwnerId Rights Owner GUID
     * @return a royalty for the supplied righsOwnerId
     */	
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", path = "/payments/{rightsOwnerId}")
	ResponseEntity<RoyaltyPayment> readPayment(@PathVariable("rightsOwnerId") String rightsOwnerId);	
	
	/**
	 * Add viewings for a episode
	 * @param episode Episode GUID
	 * @param customer	Customer GUID
	 */	
	@RequestMapping(method= RequestMethod.POST, headers = "Accept=application/json", path = "/viewing")
	ResponseEntity<?> createViewing(@RequestParam(value="episode",  required = true) String episode, @RequestParam(value="customer",  required = true) String customer);
	
	/**
	 * Reset all viewings for all episodes
	 * @return
	 */	
	@RequestMapping(method= RequestMethod.POST, headers = "Accept=application/json", path = "/reset")
	ResponseEntity<?> resetViewing();
	
	
}
