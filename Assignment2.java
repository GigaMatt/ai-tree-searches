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
        read_print_map();        //Read in/print out map file to a 2d array
        generate_successor_nodes();
        search_algorithms();
    }


    //Read in/print out map file to a 2d array; store source/goal nodes
    public static void read_print_map(){
        //TODO: TEST ME AS WE MIGHT NEED A TRY/CATCH STATEMENT


        //TODO: COMPLETE DIRECTORY TO FILE
        String current_line, file_name = ("LOCATION_TO_FILE");


        FileReader file_reader = new FileReader(file_name);
        BufferedReader buffered_reader = new BufferedReader(file_reader);
        current_line = buffered_reader.readLine();
        String[] string_split = current_line.split(" ");
        int[][] map_space = new int[Integer.parseInt(string_split[0])][Integer.parseInt(split[1])];




        buffered_reader.close();
    }


    //Write a method to generate successor nodes (this should be the same for all three searches!) 
    public static void generate_successor_nodes(){
    }


    public static void search_algorithms(){
        breadth_first_search();
        iterative_deepening_search();
        a_star_search();
    }


    /**
	 * Breadth-First Search
	 * This method solves via a BFS tree search
	 */
    public static void breadth_first_search(){
        System.out.println("BFS Output:\n");
    }


    /**
	 * Iterative-Deepening BFS
	 * This method solves via a BFS tree search, complete with an IDS wrapper
	 */
    public static void iterative_deepening_search(){
        System.out.println("IDS Output:\n");
    }

///////////////////////////////////////////////////////////////////////////
    /**
	 * A* Search
	 * This method solves via A* Search
	 */
    public static void a_star_search(){
        int manhattan_distance = calculate_manhattan_distance();
        System.out.println("A* Output:\n");
    }


    public static int calculate_manhattan_distance(){
        return 0;
    }



}