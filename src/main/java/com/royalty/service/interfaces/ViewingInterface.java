package com.royalty.service.interfaces;

import java.util.List;

import com.royalty.model.Viewing;
import com.royalty.service.ServiceException;

public interface ViewingInterface {
	
	List<Viewing> createViewing(String idEpisode, String idCustomer) throws ServiceException;
	
	List<Viewing> resetViewing() throws ServiceException;
	
}
