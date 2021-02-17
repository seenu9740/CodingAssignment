###  DEPENDENCIES
	
	1. Java 8 and above
  
### STEPS TO EXECUTE
	
	1. Please extract the zip file.
	2. Navigate to RedfinFoodTruck folder from the current location.
	3. Run displayFoodTruck.sh to see the first 10 results.
		-->	sh displayFoodTruck.sh 
        or
   open the terminal in CompleteProject\RedfinFoodTruck\target folder and run the below mentioned command,
    --> java -jar RedfinFoodTruck-1.0-SNAPSHOT.jar
	4. If we need to see more stores, please follow the instructions provided in console.


### CHALLENGE OVERVIEW
  Create a program that will print out a list of food trucks


#### DATA SOURCE
   From SanFranciso govt. site http://data.sfgov.org/resource/bbb8-hzi6.xml


#### PROBLEM
  Write a command line program that prints out a list of food trucks that are
  open at the current date, when the program is being run. So if I run the
  program at noon on a Friday, I should see a list of all the food trucks that
  are open then.

  * Additional notes
    1. Please display the name and address of the trucks and sort the output
       alphabetically by name.
    2. Please display results in pages of 10 trucks.
      That is: if there are more than 10 food trucks open, the program should
      display the first 10, then wait for input from the user before displaying
      the next 10 (or fewer if there are fewer than 10 remaining), and so on
      until there are no more food trucks to display.