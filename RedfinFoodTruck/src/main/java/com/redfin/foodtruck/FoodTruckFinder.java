package com.redfin.foodtruck;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.redfin.foodtruck.dto.Constants;
import com.redfin.foodtruck.dto.Response;
import com.redfin.foodtruck.service.FoodTruckFinderService;


public class FoodTruckFinder implements FoodTruckFinderService {

	public Response findFoodTrucks() {
		Response response = new Response();
		try {
			URL url = new URL(Constants.API_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(Constants.GET);
			JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			response = (Response) jaxbUnmarshaller.unmarshal(conn.getInputStream());
        } catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return response;
	}

}