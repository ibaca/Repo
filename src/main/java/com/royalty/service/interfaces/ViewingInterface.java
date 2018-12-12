package com.royalty.service.interfaces;

import com.royalty.model.Viewing;
import com.royalty.service.ServiceException;
import java.util.List;

public interface ViewingInterface {

    List<Viewing> createViewing(String idEpisode, String idCustomer) throws ServiceException;

    List<Viewing> resetViewing() throws ServiceException;

}
