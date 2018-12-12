package com.royalty.service.interfaces;

import com.royalty.model.RoyaltyPayment;
import com.royalty.service.ServiceException;
import java.util.List;

public interface PaymentInterface {

    List<RoyaltyPayment> getAllPayments() throws ServiceException;

    RoyaltyPayment getPaymentById(String id) throws ServiceException;

}
