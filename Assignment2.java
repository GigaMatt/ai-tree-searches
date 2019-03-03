
import java.io.*;          //For fileReader() & bufferedReader()
import java.util.*;        //For scanner when TA executes .jar file

public class Assignment2 {
	public static final long startTime = 0;
	public static int[] goal;
	public static int[] start;
	public static int children_expanded = 0;        //Counter for the number of expanded successor nodes
	public static int memory_nodes = 0;             //Counter for our nodes in memory
	public static int[] starting_position;
	public static int[] goal_position;

	/**
	 * GENERATE MAP SPACE
	 * This method reads the test case text file
	 * @return int[][] the 2D Map Space.
	 */
	public static int[][] generate_map_space() {
		try {
			//TODO: COMPLETE DIRECTORY TO FILE
			String current_line, file_name = ("/Users/hiramrios/Desktop/cs165a-projectwork/test_case_files/test_case_5_5.txt");
			FileReader file_reader = new FileReader(file_name);
			BufferedReader buffered_reader = new BufferedReader(file_reader);

			//Create the 2-D Map Space
			current_line = buffered_reader.readLine();
			String[] string_split = current_line.split(" ");
			int[][] map_space = new int[Integer.parseInt(string_split[0])][Integer.parseInt(string_split[1])];

			//Retrieve Starting Position
			current_line = buffered_reader.readLine();
			string_split = current_line.split(" ");
			start = new int[]{Integer.parseInt(string_split[0]), Integer.parseInt(string_split[1])};

			//Retrieve Goal Position
			current_line = buffered_reader.readLine();
			string_split = current_line.split(" ");
			goal = new int[]{Integer.parseInt(string_split[0]), Integer.parseInt(string_split[1])};
			int row = 0;

			//Populate 2-D Map Space with position info
			while((current_line = buffered_reader.readLine()) != null) {
				string_split = current_line.split(" ");
				for(int column = 0; column < string_split.length; column++) {
					map_space[row][column] = Integer.parseInt(string_split[column]);
				}
			}

			//Print the 2-D Map Space
			for(int i=0; i<map_space.length; i++) {
				for(int j=0; j<map_space[i].length; j++) {
					System.out.println(map_space[i][j]+" ");
				}
			}
			buffered_reader.close();
			return map_space;
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		return null;
	}

	public static void print_search_Results(boolean times_up, long start_timer) {//prints results of search
		if(!(times_up)) {
			System.out.println("\tNumber of Nodes Expanded\t\t" + children_expanded);
			System.out.println("\tMaximum Number of Nodes held in Memory\t" + memory_nodes);
			System.out.println("\tTime of Execution (in ms)\t\t" + (System.currentTimeMillis() - start_timer));
		}
		
		//Our search timed out
		else {
			System.out.println("Null");
			System.out.println("\tCost of Path:\t-1");
			System.out.println("\tMaximum Number of Nodes Held in Memory\t" +memory_nodes);
			System.out.println("\tTime of Execution (in ms)\t\t" + (System.currentTimeMillis() - start_timer));
		}
	}


	/**
	 * SEARCH ALGORITHMS
	 * @param int[][] map_space the 2D map we generated
	 * This method calls the various search algorithms we're tasked with implementing.
	 */
	public static void search_algorithms(int[][] map_space) {
		breadth_first_search(map_space);
		iterative_deepening_search(map_space);
		a_star_search(map_space);
	}


	/**
	 * BREADTH-FIRST SEARCH
	 * @param int[][] map_space the 2D map we generated
	 * This method implements BFS on our tree from our text file.
	 */
	public static void breadth_first_search(int[][] map_space) {
		//Dr. K --> USE A 3-MINUTE TIMER
		long begin_timer = System.currentTimeMillis();
		long end_timer = 180000;
		boolean times_up = false;

		//Generate Goal Node
		Node goal_node = new Node(goal[0]-1, goal[1]-1);
		//Generate Starting Node
		Node start_node = new Node(start[0]-1, start[1]-1,
				manhattan_distance(start[0]-1, start[1]-1, goal_node), 
				map_space[start[0]-1][start[1]-1], null);
		boolean[][] nodes_visited = new boolean[map_space.length][map_space[0].length];
		Queue<Node> queue_nodes = new LinkedList<>();
		queue_nodes.add(start_node);

		while(!(queue_nodes.isEmpty())) {
			//Start the 3-Minute timer
			if((System.currentTimeMillis()-begin_timer) > end_timer) {
				System.out.println("3 minutes have passed. Your search time has expired!");
				times_up = true;
				break;
			}

			//Search && Expand the current node
			Node current_node = queue_nodes.poll();                             //Removes + Returns head of queue 
			nodes_visited[current_node.x][current_node.y] = true;               //Call .x & .y position in Node class
			memory_nodes++;

			//Check if this is a goal node
			if(is_goal_node(current_node)){
				int path_cost = current_node.accumulated_path_cost;
				display_path_information(current_node, path_cost);              //Display goal path information 
				System.out.println("Our path cost: "+path_cost);
				break;
			}

			//Generate Successor Nodes && add to the queue
			Node[] children_nodes_array = generate_successor_nodes(current_node, goal_node, nodes_visited, map_space);
			for(int i=0; i<children_nodes_array.length; i++){
				queue_nodes.add(children_nodes_array[i]);
			}

			//Add Successor Nodes to memory
			if(children_expanded<queue_nodes.size())
				children_expanded = queue_nodes.size();
		}
		System.out.println("\nBREADTH-FIRST SEARCH--");
		print_search_Results(times_up, begin_timer);//prints results
	}


	/**
	 * ITERATIVE DEEPENING SEARCH
	 * @param int[][] map_space the 2D map we generated
	 * This method implements IDS on our tree from our text file.
	 */
	public static void iterative_deepening_search(int[][] map_space) {
		//Dr. K --> USE A 3-MINUTE TIMER
		long begin_timer = System.currentTimeMillis();
		long end_timer = 180000;
		boolean times_up = false;

		//Generate Goal Node
		Node goal_node = new Node(goal[0]-1, goal[1]-1);
		//Generate Starting Node
		Node start_node = new Node(start[0]-1, start[1]-1,
				manhattan_distance(start[0]-1, start[1]-1, goal_node), 
				map_space[start[0]-1][start[1]-1], null);
		boolean[][] nodes_visited = new boolean[map_space.length][map_space[0].length];
		int total = 0, limit = 0, infitite_count = limit+1;      // might have to set to Integer.MAX_VALUE()
		//REMEMBER TO USE STACK AS A FRINGE (FILO)
		Stack<Node> the_fringe = new Stack<Node>();

		while(limit<infitite_count){
			//Reset variables from previous search
			falsify_visited_nodes(nodes_visited);
			children_expanded = 0;
			memory_nodes = 0;
			//Build the stack
			the_fringe.push(start_node);

			while(!(the_fringe.isEmpty())) {
				//Start the 3-Minute timer
				if((System.currentTimeMillis()-begin_timer) > end_timer) {
					System.out.println("3 minutes have passed! Your search time has expired!");
					times_up = true;
					break;
				}

				//Expand current node by popping from the stack
				Node current_node = the_fringe.pop();
				nodes_visited[current_node.x][current_node.y] = true;
				children_expanded++;

				//Check if this is a goal node
				if(is_goal_node(current_node)) {
					int path_cost = current_node.accumulated_path_cost;
					display_path_information(current_node, path_cost);
					limit = infitite_count+1;        //Add limit for IDS
					System.out.println("Our path cost: "+path_cost);
					break;
				}

				//Check if we've reached our limit
				if(total==limit) {
					total++;
					the_fringe.removeAllElements();     //Limit reached; reset stack
					break;
				}

				//Generate Successor Nodes && push to the fringe (stack)
				Node[] children_nodes_array = generate_successor_nodes(current_node, goal_node, nodes_visited, map_space);
				for(int i=0; i<children_nodes_array.length; i++) {
					the_fringe.push(children_nodes_array[i]);
				}

				//Add successor nodes to our memory counter
				if(memory_nodes<the_fringe.size())//add the children to count for nodes in memory
					memory_nodes=the_fringe.size();
			}
			total++;
			infitite_count++;
		}	
		System.out.println("\nITERATIVE DEEPENING DFS--");
		print_search_Results(times_up, begin_timer);//prints results
	}


	/**
	 * A* SEARCH
	 * @param int[][] map_space the 2D map we generated
	 * This method implements A* Search on our tree from our text file.
	 */
	public static void a_star_search(int[][] map_space) {
		//Dr. K --> USE A 3-MINUTE TIMER
		long begin_timer = System.currentTimeMillis();
		long end_timer = 180000;
		boolean times_up = false;

		boolean[][] nodes_visited = new boolean[map_space.length][map_space[0].length];//to see what nodes have been visited
		Node goal_node = new Node(goal[0] - 1, goal[1] - 1);//creates goal node
		Node start_node = new Node(start[0] - 1, start[1] - 1,
				manhattan_distance(start[0] - 1, start[1] - 1, goal_node),
				map_space[start[0] - 1][start[1] - 1], null);//creates start node
		Queue<Node> queue_nodes = new PriorityQueue<>(map_space.length*map_space[0].length);//creates priority queue
		queue_nodes.add(start_node);//adds the start node
		Node[] children;//creates an array for the children nodes


		while(!queue_nodes.isEmpty()){

			if((System.currentTimeMillis() - begin_timer) > end_timer){//the search has timed out
				System.out.println("Search has timed out");
				times_up = true;
				break;
			}

			Node current_node = queue_nodes.poll();//gets first node in queue
			nodes_visited[current_node.x][current_node.y] = true;
			children_expanded++;
			int path_cost = current_node.accumulated_path_cost;
			if(is_goal_node(current_node)){//if goal is reached print information and break
				display_path_information(current_node, path_cost);
				System.out.println("Cost of path: " + path_cost);
				break;
			}

			children = generate_successor_nodes(current_node, goal_node, nodes_visited, map_space);//add children to queue
			for(int i = 0; i < children.length; i++)
				queue_nodes.add(children[i]);

			if(memory_nodes < queue_nodes.size())//add the children to count for nodes in memory
				memory_nodes = queue_nodes.size();	
		}
		System.out.println("\nA* SEARCH--");
		print_search_Results(times_up, begin_timer);//prints results
	}

	/** 									HELPER METHODS FOR SEARCH ALGORITHMS 												*/


	//sets the visited matrix all to false
	/**
	 * SET VISITED NODES TO FALASE
	 * @param boolean[][] nodes_visited the nodes already explored
	 * Sets the visited nodes in 2D Map Space to false
	 */  
	public static void falsify_visited_nodes(boolean[][] nodes_visited) {
		for(int i=0; i<nodes_visited.length; i++)
			for(int j=0; j<nodes_visited[i].length; j++)
				nodes_visited[i][j] = false;
	} 

	/**
	 * GOAL NODE CHECKER
	 * @param Node current_node the node we're currently at in the tree
	 * @return boolean indicates if we've reached our goal node
	 */  
	public static boolean is_goal_node(Node current_node) {
		if(current_node.distance == 0)
			return true;
		else
			return false;
	}


	/**
	 * MANHATTAN DISTANCE
	 * This method calculates and @return the Manhattan Distance.
	 * @param x the row in the matrix
	 * @param y the column in the matrix
	 * @param goal_node our goal node
	 */  
	public static int manhattan_distance(int x, int y, Node goal_node) {
		return(Math.abs(goal_node.x - x) + Math.abs(goal_node.y - y));
	}


	/**
	 * DISPLAY PATH
	 * @param node the current node being traversed
	 * @param path_cost the cost of the path thus far
	 * This method prints the path & cost from the goal node to the start node
	 */
	public static void display_path_information(Node current_node, int path_cost) {
		while(current_node != null) {
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
	public static Node[] generate_successor_nodes(Node current_node, Node goal_node, boolean[][] nodes_visited, int[][] map_space) {

		//We can only move UP, DOWN, LEFT, & RIGHT
		LinkedList<Node> children_nodes_LL = new LinkedList <Node> ();

		//moving right
		if(current_node.y + 1 < map_space[0].length && map_space[current_node.x][current_node.y + 1] != 0 && !nodes_visited[current_node.x][current_node.y + 1]){
			children_nodes_LL.add(new Node(current_node.x, current_node.y + 1, manhattan_distance(current_node.x, current_node.y + 1, goal_node), current_node.accumulated_path_cost+ map_space[current_node.x][current_node.y + 1], current_node));
			nodes_visited[current_node.x][current_node.y + 1] = true;
		}
		//moving left
		if(current_node.y - 1 >= 0 && map_space[current_node.x][current_node.y - 1] != 0 && !nodes_visited[current_node.x][current_node.y - 1]){
			children_nodes_LL.add(new Node(current_node.x, current_node.y - 1, manhattan_distance(current_node.x, current_node.y - 1, goal_node), current_node.accumulated_path_cost + map_space[current_node.x][current_node.y - 1], current_node));
			nodes_visited[current_node.x][current_node.y - 1] = true;
		}
		//moving up
		if(current_node.x - 1 >= 0 && map_space[current_node.x - 1][current_node.y] != 0 && !nodes_visited[current_node.x - 1][current_node.y]){
			children_nodes_LL.add(new Node(current_node.x - 1, current_node.y, manhattan_distance(current_node.x - 1, current_node.y, goal_node), current_node.accumulated_path_cost + map_space[current_node.x - 1][current_node.y], current_node));
			nodes_visited[current_node.x - 1][current_node.y] = true;
		}
		//moving down
		if(current_node.x + 1 < map_space.length && map_space[current_node.x + 1][current_node.y] != 0 && !nodes_visited[current_node.x + 1][current_node.y]){
			children_nodes_LL.add(new Node(current_node.x + 1, current_node.y, manhattan_distance(current_node.x + 1, current_node.y, goal_node), current_node.accumulated_path_cost + map_space[current_node.x + 1][current_node.y], current_node));
			nodes_visited[current_node.x + 1][current_node.y] = true;
		}
		Node[] children = new Node[children_nodes_LL.size()];
		return children_nodes_LL.toArray(children);
	}


	/** MAIN RUNNER */
	public static void main(String[] args) {
		int[][] map_space = generate_map_space();               //Read in/print out map file to a 2d array
		search_algorithms(map_space);
	}
}