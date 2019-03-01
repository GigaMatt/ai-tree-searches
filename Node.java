/*
 * CS 165a
 * Lab 1: Tree Searches
 * By: Matthew S Montoya && Hiram Rios
 * Last Modified: 1 March 2019
 * Purpose: To practicing implementing BFS, IDS, & A* Search
 * NOTE: THIS IS THE NODE CLASS FOR SEARCHES
 */

public class Node {
    int x;                              //Position
    int y;
    int distance;                       //Distance to goal node
	int accumulated_path_cost;          //Cost from traversal (so far)
    Node prev;
    Node next;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
		distance = 0;
		accumulated_path_cost = 0;
        prev = null;
        next = null;
    }


    public Node(int x, int y, int distance, int accumulated_path_cost, Node prev, Node next){
        this.x = x;
        this.y = y;
        this.distance = distance;
		this.accumulated_path_cost = accumulated_path_cost;
        this.prev = prev;
        this.next = next;
    }
}