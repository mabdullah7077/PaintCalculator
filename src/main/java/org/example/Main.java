package org.example;
import java.util.Scanner;


public class Main {

    // getRoomInfo gets user to input number of walls, measurement of walls, number of obstructions and measurements of obstructions for a single room
    public static double getRoomInfo(Scanner scanner, int roomNumber) {
        System.out.println("Room " + roomNumber + ":"); // print current room number that is being referenced


        int numOfWalls = -1; // initialse numOfWalls to invalid number
        while (numOfWalls <= 0) { // Loop until valid number of walls is input
            System.out.print("Enter number of walls: "); // user inputs number of walls
            numOfWalls = scanner.nextInt(); // user input stored in numOfWalls variable
            if (numOfWalls <= 0) {
                System.out.println("Error: must have at least 1 wall"); // error message for invalid numOfWalls input
            }
        }

        double totalWallArea = 0; // initialise totalWallArea variable

        for (int i = 1; i <= numOfWalls; i++) { // for loop to calculate sum of all wall areas in the room
            double height = -1; // initialise height and width to invalid numbers
            double width = -1;


            while (height <= 0) {  // Loop until valid height is input
                System.out.print("Enter height of wall " + i + ": "); // user inputs wall height
                height = scanner.nextDouble(); // scanner stores user input to height variable
                if (height <= 0) {
                    System.out.println("Error: height must be greater than 0"); // error message for invalid height input
                }
            }


            while (width <= 0) {  // Loop until valid width is input
                System.out.print("Enter width of wall " + i + ": "); // user inputs wall width
                width = scanner.nextDouble(); // scanner stores user input to width variable
                if (width <= 0) {
                    System.out.println("Error: width must be greater than 0"); // error message for invalid width input
                }
            }

            totalWallArea += (height * width); // calculate and sum totalWallArea
        }

        int numOfObstructions = -1; // initalise to invalid number
        while (numOfObstructions < 0) { // loop until number of obstructions is valid
            System.out.print("Enter number of obstructions: "); // user inputs number of obstructions
            numOfObstructions = scanner.nextInt(); // scanner stores user input to numOfObstructions variable
            if (numOfObstructions < 0) {
                System.out.println("Error: number of obstructions must be 0 or greater"); // error message for invalid number of obstructions input
            }
        }

        double totalObstructionArea = 0; // initialise total obstruction area

        for (int i = 1; i <= numOfObstructions; i++) { //for loop to calculate total obstruction area
            double obstructionHeight = -1; // initialise obstruction width and height to invalid number
            double obstructionWidth = -1;

            while (obstructionHeight <= 0) { //loop until obstruction height is valid
                System.out.print("Enter height of obstruction " + i + ": ");
                obstructionHeight = scanner.nextDouble();
                if (obstructionHeight <= 0) {
                    System.out.println("Error: height must be greater than 0"); // error message for invalid height input
                }
            }

            while (obstructionWidth <= 0) { // loop until obstruction width is valid
                System.out.print("Enter width of obstruction " + i + ": ");
                obstructionWidth = scanner.nextDouble();
                if (obstructionWidth <= 0) {
                    System.out.println("Error: width must be greater than 0"); // error message for invalid width input
                }
            }

            totalObstructionArea += (obstructionHeight * obstructionWidth); // calculate and sum up total obstruction area
        }

        double paintableArea = totalWallArea - totalObstructionArea; // subtract total obstruction area from total wall area to calculate paintable area
        System.out.println("Paintable area for room " + roomNumber + ": " + paintableArea + " metres squared");

        return paintableArea;
    }


    public static void calculateTins(double litresNeeded) { // method to calculate tins needed so litres needed can be reached
        double[] tinSizes = {10.0, 5.0, 2.5, 1.0}; // array of general tin sizes from largest to smallest in litres
        int[] tinCount = new int[4]; // array to store the count of each tin size needed

        double remainingLitres = litresNeeded; // initally remaining litres will equal to the litres needed

        for (int i = 0; i < 4; i++) { // for loop to calculate tins needed
            if (remainingLitres >= tinSizes[i]) {
                tinCount[i] = (int) (remainingLitres / tinSizes[i]); // calculate number of i size tins can be used and convert to int, store in i'th element of tincount array
                remainingLitres %= tinSizes[i]; // calculate remaining litres after using i tin size
            }
        }

        // if remaoning litres are smaller than any tin sizes but bigger than 0, add 1 litre of paint to tin count
        if (remainingLitres > 0) {
            tinCount[3]++; // add one litre of paint to last element of tin count array
        }

        System.out.println("Tins required:");
        for (int i = 0; i < 4; i++) { // loop through each element of tincount array
            if (tinCount[i] > 0) {
                System.out.println(tinCount[i] + " tins of " + tinSizes[i] + " litres"); // if i'th element in tincount array is >0, print tincount[i] which is quantity of tins, and tinSizes[i] which is size of tin
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // create scanner object


        int numOfRooms = 0; // initialise num of rooms to invalid input
        while (numOfRooms <= 0) {  // loop until valid number of rooms is input
            System.out.print("Enter number of rooms: ");
            numOfRooms = scanner.nextInt();
            if (numOfRooms <= 0) {
                System.out.println("Error: must have at least 1 room"); // error message for invalid number of rooms input
            }
        }

        double totalPaintableArea = 0; // initialse total paintable area

        for (int i = 1; i <= numOfRooms; i++) { // for loop to retrieve paintable area for each room and calculate total paintable area for all rooms
            totalPaintableArea += getRoomInfo(scanner, i);
        }

        System.out.println("Total paintable area: " + totalPaintableArea + " metres squared");

        double areaPaintedPerLitre = 10; // 1 liter of paint generally covers 10 meters squared

        double litresNeeded = (totalPaintableArea / areaPaintedPerLitre); //calculate litres needed for total paintable area

        System.out.println("Total litres of paint needed: " + litresNeeded + " litres");

        double roundedUpLitresNeeded = Math.ceil(totalPaintableArea / areaPaintedPerLitre); // round up litres needed

        calculateTins(roundedUpLitresNeeded); // calculate tins of paint needed based on rounded litres needed

        scanner.close();
    }

}