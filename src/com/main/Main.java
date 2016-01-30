/**
 * Main: Inputs the file from the user and passes it to ReadFile
 * 
 * Part of Programming Assignment #1 for B551
 * Spring 2016
 */
package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.util.CityMapper;
import com.util.ReadFile;
import com.util.Search;

/**
 * @author asadana, jaynagle
 *
 */
public class Main {
	// CityMapper ArrayList to store all the cities and their neighbors
	private static ArrayList<CityMapper> cityList;

	public static void main(String[] args) throws IOException {
		// BufferedReader to read and store user input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		File fileDist;
		
		// fileName stores the fileName hardcoded or entered by the user
		String fileName = "romania-distance.txt";
		// delim stores the delimiter used to parse the txt file
		String delim = " ";
		
		// Input string for search
		String inputString;
		
		// while loop checks if the file exists or asks user to enter another file
		while (true) {
			fileDist = new File (fileName);
			
			// If file exists
			if (fileDist.exists()) {
				//System.out.println("\nFile Found!\n");
				break;
			}
			// If file not found
			else {
				System.out.println("\nERROR: File doesn't exist.\nPlease try again.\n");
			}
			
			// If file is not found, user is asked for a different file name in the parent directory
			System.out.println("Enter the name of the file for cities: ");
			fileName = br.readLine();
		} 
		
		// ReadFile reads the file given
		ReadFile readFileObj = new ReadFile(fileName, delim);
		cityList = readFileObj.readIt();
		
		// Display all cities in cityList and it's neighbors
		for (CityMapper cityObj : cityList) {
			System.out.println("City: " + cityObj.getCityName() + "\t" + cityObj.getNeighbors());
		}
		
		// While loop accepts user search queries
		while(true) {

			System.out.println("\nEnter input in the form of \"City1, City2 BFS/DFS/IDS\": ");
			inputString = br.readLine();
			
			// Splitting the start city before ', '
			String[] tempString = inputString.split(", ");
			String startCity = tempString[0];
			// Splitting goal city and type of city using space as delimiter 
			String[] tempString2 = tempString[1].split(" ");
			String goalCity = tempString2[0];
			String typeOfSearch = tempString2[1];
			
			// System.out.println("\n\nGoing from " + startCity + " to " + goalCity + " using " + typeOfSearch + "\n\n");
			if(typeOfSearch.compareToIgnoreCase("bfs") == 0 || typeOfSearch.compareToIgnoreCase("dfs") == 0 || typeOfSearch.compareToIgnoreCase("ids") == 0) {
				Search searchObj = new Search(startCity, goalCity, typeOfSearch, cityList);
			}
			else {
				System.out.println("Invalid type of search entered.");
			}
			
			
			System.out.println("Want to do another search? [Y/N]: ");
			inputString = br.readLine();
			if (inputString.compareToIgnoreCase("N") == 0) {
				break;
			}
			else if (inputString.compareToIgnoreCase("Y") == 0) {
				continue;
			}
			else {
				System.out.println("Invalid response.\nExiting...\n");
			}
		}
		
		br.close();
	}
}
