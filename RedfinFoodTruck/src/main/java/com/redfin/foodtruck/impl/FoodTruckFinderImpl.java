package com.redfin.foodtruck.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.redfin.foodtruck.FoodTruckFinder;
import com.redfin.foodtruck.dto.Constants;
import com.redfin.foodtruck.dto.Response;
import com.redfin.foodtruck.dto.Response.Rows.Row;
import com.redfin.foodtruck.service.FoodTruckFinderService;


public class FoodTruckFinderImpl {
	
	public static int resultCountPosition = 0;

	public static void main(String[] args) {
		try {
			Response response = new Response();
			FoodTruckFinderService service = new FoodTruckFinder();
			LocalDateTime localDate = LocalDateTime.now();
			String dayOfWeek = localDate.getDayOfWeek().name();
			response = service.findFoodTrucks();

			if (response != null && response.getRows() != null && response.getRows().getRow() != null) {
				List<Row> foodTruckList = response.getRows().getRow();

				List<Row> result = foodTruckList.stream()
						.filter(row -> (row.getDayofweekstr().equalsIgnoreCase(dayOfWeek)
								&& isBetween(row.getStart24(), row.getEnd24())))
						.collect(Collectors.toList());
				Collections.sort(result);
				if (result != null && result.size() > 0 && result.size() <= 10) {
					printNextResults(result);
				} else if (result != null && result.size() > 0 && result.size() > 10) {
					printNextResults(result);
					showMoreResults(result, Constants.VALID);
				} else {
					System.out.println(Constants.NO_OPEN_TRUCK_MSG);
				}
			} else {
				System.out.println(Constants.NO_OPEN_TRUCK_MSG);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void showMoreResults(List<Row> result, String type) {
		if (type.equalsIgnoreCase(Constants.VALID)) {
			System.out.println(Constants.MORE_RESULTS_MSG);
		}
		Scanner in = new Scanner(System.in);
		String userInput = in.nextLine();
		if (userInput.equalsIgnoreCase(Constants.NO)) {
			System.out.println(Constants.GOOD_BYE);
			System.exit(0);
		} else if (userInput.equalsIgnoreCase(Constants.YES)) {
			printNextResults(result);
			showMoreResults(result, Constants.VALID);
		} else {
			System.out.println(Constants.INVALID_ENTRY_MSG);
			showMoreResults(result, Constants.INVALID);
		}
	}

	public static void printNextResults(List<Row> result) {
		if(resultCountPosition <= result.size()) {
			System.out.format("||%1$-60s||%2$-30s||\n", "======================", "======================");
			System.out.format("||%1$-60s||%2$-30s||\n", Constants.APPLICANT_NAME, Constants.LOCATION);
			System.out.format("||%1$-60s||%2$-30s||\n", "======================", "======================");
			for (int i = resultCountPosition; i < resultCountPosition + 10 && i < result.size(); i++) {
				System.out.format("||%1$-60s||%2$-30s||\n", result.get(i).getApplicant(), result.get(i).getLocation());
			}
			resultCountPosition += 10;
		} else {
			System.out.println(Constants.VIEWED_ALL_RESULTS);
			System.exit(0);
		}
	}

	public static boolean isBetween(String start, String end) {
		LocalTime lt = LocalTime.now();
		if (!end.equals(Constants.TWENTY_FOUR_HOURSE)) {
			return !lt.isBefore(LocalTime.parse(start)) && !lt.isAfter(LocalTime.parse(end));
		}
		return false;
	}
}
