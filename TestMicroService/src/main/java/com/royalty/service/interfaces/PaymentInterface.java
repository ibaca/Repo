package com.royalty.service.interfaces;

import java.util.List;

import com.royalty.model.RoyaltyPayment;
import com.royalty.service.ServiceException;

public interface PaymentInterface {
	
	List<RoyaltyPayment> getAllPayments() throws ServiceException;
	
	RoyaltyPayment getPaymentById(String id) throws ServiceException;

}
