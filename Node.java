/**
 * CS 165a -- Artificial Intelligence
 * Lab 01
 * Assignment2.java
 * By: Hiram A Rios && Matthew S Montoya
 * Instructor: Dr. Chistopher Kiekintveld
 * TA: Khandoker Rahad
 * Purpose: To practice implementing BFS, DFS, and A* Search
 * Last Modified: March 2, 2019
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


    public Node(int x, int y, int distance, int accumulated_path_cost, Node prev){
        this.x = x;
        this.y = y;
        this.distance = distance;
		this.accumulated_path_cost = accumulated_path_cost;
        this.prev = prev;
    }
}