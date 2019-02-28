/*
 * CS 165a
 * Lab 1: Tree Searches
 * By: Matthew S Montoya && Hiram Rios
 * Last Modified: 26 February 2019
 * Purpose: To practicing implementing BFS, IDS, & A* Search
 * NOTE: THIS IS THE RUNNER FILE
 */

 import java.io.*;          //For fileReader() & bufferedReader()
 import java.util.*;        //For scanner

public class Assignment2 {
    public static void main(String[] args){
        int[][] map_space = generate_map_space();               //Read in/print out map file to a 2d array
        generate_successor_nodes();
        search_algorithms(map_space);
    }


    //Read in/print out map file to a 2d array; store source/goal nodes

    /**
	 * Print Map
	 * This method reads the test case text file & @return int[][] the 2D Map Space.
	 */
    public static int[][] generate_map_space(){
        //TODO: TEST THIS AS WE MIGHT NEED A TRY/CATCH STATEMENT


        //TODO: COMPLETE DIRECTORY TO FILE
        String current_line, file_name = ("LOCATION_TO_FILE");
        FileReader file_reader = new FileReader(file_name);
        BufferedReader buffered_reader = new BufferedReader(file_reader);

        //Create the 2-D Map Space
        current_line = buffered_reader.readLine();
        String[] string_split = current_line.split(" ");
        int[][] map_space = new int[Integer.parseInt(string_split[0])][Integer.parseInt(string_split[1])];

        //Retrieve Starting Position
        current_line = buffered_reader.readLine();
        string_split = current_line.split(" ");
        starting_position = new int[]{Integer.parseInt(string_split[0]), Integer.parseInt(string_split[1])};

        //Retrieve Goal Position
        current_line = buffered_reader.readLine();
        string_split = current_line.split(" ");
        goal_position = new int[]{Integer.parseInt(string_split[0]), Integer.parseInt(string_split[1])};

        //Populate 2-D Map Space with position info
        for(int row = 0; row<(currentLine = br.readLine()) != null; row++){
            string_split = currentLine.split(" ");
            for(int column = 0; column < string_split.length; column++) {
                map_space[row][column] = Integer.parseInt(string_split[column]);
            }
        }

/*         //FOR DEBUGGING: PRINTING OUT THE STATE OF OUR MAP
        System.out.println("The size of the map is: " + map_space.length() + " " + map_space[0].length() +
        "\nOur starting position is: " + starting_position[0] + " " + starting_position[1] +
        "\nOur Goal position is: " + goal_position[0] + " " + goal_position[1]); */

        buffered_reader.close();
        return map_space;
    }


    //Write a method to generate successor nodes (this should be the same for all three searches!) 
    public static void generate_successor_nodes(){
    }


    public static void search_algorithms(int[][] map_space){
        breadth_first_search(map_space);
        iterative_deepening_search(map_space);
        a_star_search(map_space);
    }


    /**
	 * Breadth-First Search
	 * This method solves via a BFS tree search
	 */
    public static void breadth_first_search(int[][] map_space){
        System.out.println("BFS Output:\n");
    }


    /**
	 * Iterative-Deepening BFS
	 * This method solves via a BFS tree search, complete with an IDS wrapper
	 */
    public static void iterative_deepening_search(int[][] map_space){
        System.out.println("IDS Output:\n");
    }

///////////////////////////////////////////////////////////////////////////
    /**
	 * A* Search
	 * This method solves via A* Search
	 */
    public static void a_star_search(int[][] map_space){
        int manhattan_distance = calculate_manhattan_distance();
        System.out.println("Manhatten Distance\t" + manhattan_distance + "\nA* Output:\n");
    }


    public static int calculate_manhattan_distance(){
        return 0;
    }



}