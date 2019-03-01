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

    /**
	 * GENERATE MAP SPACE
	 * This method reads the test case text file
     * @return int[][] the 2D Map Space.
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

        //Print the 2-D Map Space
        for(int i=0; i<map_space.length; i++){
            for(int j=0; j<map_space[i].length; j++){
                System.out.println(map_space[i][j]+" ");
            }
        }
        buffered_reader.close();
        return map_space;
    }


    /**
	 * SEARCH ALGORITHMS
     * @param int[][] map_space the 2D map we generated
	 * This method calls the various search algorithms we're tasked with implementing.
	 */
    public static void search_algorithms(int[][] map_space){
        breadth_first_search(map_space);
        iterative_deepening_search(map_space);
        a_star_search(map_space);
    }


    /**
	 * BREADTH-FIRST SEARCH
     * @param int[][] map_space the 2D map we generated
	 * This method implements BFS on our tree from our text file.
	 */
    public static void breadth_first_search(int[][] map_space){
        //Generate Goal Node
        Node goal_node = new Node(goal_position[0]-1, goal_position[1]-1);
        //Generate Starting Node
		Node start_node = new Node(starting_position[0]-1, starting_position[1]-1, manhattan_distance(starting_position[0]-1, starting_position[1]-1, goal_node), map_space[starting_position[0]-1][starting_position[1]-1], null);
        
        int expanded_nodes = 0;
        boolean[][] nodes_visited = new boolean[map_space.length][map_space[0].length];
        Queue<Node> queue_nodes = new LinkedList<>();
		queue_nodes.add(start_node);
        Node[] child_node_array;

        while(!(queue_nodes.isEmpty())){

            //Search && Expand the current node
            Node current_node = queue_nodes.poll();
			nodes_visited[current_node.x][current_node.y] = true;            //Call .x & .y position in Node class
			expanded_nodes++;
            
            //If a goal exists, display path information
			if(goalTest(current_node)){
                int path_cost = current_node.accumulated_path_cost;
				display_path_information(current_node, path_cost);
				break;
			}
            
            //If no goal exists, generate the children nodes & add them to the queue
			child_node_array = generate_successor_nodes(current_node, goal_node, nodes_visited, map_space);//get children and add the to queue
			for(int i=0; i<child_nodes.length; i++){
                queue_nodes.add(child_nodes[i]);
            }
            
            //FIXME: IMPLEMENT THE MAX NODES IN THE MEMORY
			//if(maxNodesInMemory < queue_nodes.size())//add the children to count for nodes in memory
			//	maxNodesInMemory = queue_nodes.size();
        }
    }


    /**
	 * ITERATIVE DEEPENING SEARCH
     * @param int[][] map_space the 2D map we generated
	 * This method implements IDS on our tree from our text file.
	 */
    public static void iterative_deepening_search(int[][] map_space){
        //FIXME: The following call should take arguments similar to: generate_successor_nodes(Node current, Node g, boolean[][] visited, int[][] map)
        //generate_successor_nodes();
        System.out.println("IDS Output:\n");
    }


    /**
	 * A* SEARCH
     * @param int[][] map_space the 2D map we generated
	 * This method implements A* Search on our tree from our text file.
	 */
    public static void a_star_search(int[][] map_space){        //Called from each search function
        int man_distance = manhattan_distance();
        //FIXME: The following call should take arguments similar to: generate_successor_nodes(Node current, Node g, boolean[][] visited, int[][] map)
        //generate_successor_nodes();

        System.out.println("Manhatten Distance\t" +manhattan_distance+ "\nA* Output:\n");
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
	 * MANHATTAN DISTANCE
	 * This method calculates and @return the manhattan distance.
	 */  
    public static int manhattan_distance(){
        int man_distance = 0;
        return man_distance;
    }


    /**
     * DISPLAY PATH
     * @param node the current node being traversed
     * @param path_cost the cost of the path thus far
     * This method prints the path & cost from the goal node to the start node
     */
    public static void display_path_information(Node current_node, int path_cost) {
		while(current_node != null){
            System.out.print("(" +(current_node.x+1)+ "," +(current_node.y+1)+ ")\n" +
            "Cost of path: " +path_cost+ ")\n");
			current_node = current_node.prev;
		}
    }
    

    /**
     * GENERATE SUCCESSOR NODES
     * @param node current the current node being traversed
     * @param path_cost the cost of the path thus far
     * This method prints the path & cost from the goal node to the start node
     */
    public static Node[] generate_successor_nodes(Node current_node, Node goal_node, boolean[][] nodes_visited, int[][] map_space){
        LinkedList<Node> children_nodes_LL = new LinkedList <Node> ();

        //Return an Object Array -- Cast to type Array
        return children_nodes_LL.toArray();
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args){
        int[][] map_space = generate_map_space();               //Read in/print out map file to a 2d array
        search_algorithms(map_space);
    }
}