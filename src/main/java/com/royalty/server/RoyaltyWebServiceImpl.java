package com.royalty.server;

import com.royalty.api.RoyaltyPayment;
import com.royalty.api.RoyaltyWebService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoyaltyWebServiceImpl implements RoyaltyWebService {
    @Autowired PaymentService paymentService;
    @Autowired ViewingService viewingService;

    public ResponseEntity<List<RoyaltyPayment>> getAllPayments() {
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }

    public ResponseEntity<RoyaltyPayment> readPayment(
            @PathVariable("rightsOwnerId") String rightsOwnerId) {
        try {
            return new ResponseEntity<>(paymentService.getPaymentById(rightsOwnerId), HttpStatus.OK);
        } catch (NoSuchElementException notFound) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> createViewing(
            @RequestParam(value = "episode") String episode,
            @RequestParam(value = "customer") String customer) {
        try {
            viewingService.createViewing(episode, customer);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> resetViewing() {
        try {
            viewingService.resetViewing();
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
