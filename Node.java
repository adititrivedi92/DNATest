/*Cosc 2P03 Assignment 1
 * September 26, 2016
 * Aditi Trivedi 
 * Student#: 5930888
 */

/*
 * This class represents a simple wrapper, used by a linked structure (Our Dna strand)
 */
public class Node {
	char base;
	Node next;
/*constructor for creating a Node*/
	public Node (char b, Node n){
		base = b;
		next = n;
	}
}
