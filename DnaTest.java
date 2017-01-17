
public class DnaTest{
	/* 
	 * instance variables
	 **/
	private Node front;
	private Node tail;
	
	/*
	 * default constructor that initializes DNA strand linked list
	 **/
	public DnaTest(){
		front=null;
		tail=null;
	}
	
	/*
	 * append base Node to end of existing linked list
	 * if block adds the very first node*/
	public void addNode(char b){
		if (front==null){
			front = new Node(b, null);
			tail = front;
		}
		else{
			tail.next = new Node (b, null);
			tail = tail.next;
		}
	}
	/* 
	 * accessor method
	 * used to get value of instance variable by another class
	 * in this case, TestHarness.java
	 * */
	public Node getFront(){
		return front;
	}
	
	/* 
	 * recursive method to display all nodes of linked list
	 */
	public static void displayDNA(Node x){
		if (x==null)
			return;
		System.out.print(x.base);
		displayDNA(x.next);
	}
	/*
	 * method that returns gc content for a given node recursively
	 * if block returns 0 for a null list
	 * counter variable incremented for each instance of 'g' or 'c'
	 * not case sensitive
	 */
	public static int gcContent(Node l){
		int ctr = 0;
		if (l==null)
			return 0;
		else if (l.base =='G'|| l.base =='C' || l.base=='g'|| l.base=='c'){
			ctr++;
		}
		return (ctr+gcContent(l.next));
	}
	
	/*
	 * method to compare two given linked lists recursively
	 * given a pointer to the front node of each list
	 * returns true or false
	 */
	public static boolean compare(Node l1, Node l2){
		if (l1==null && l2==null)
			return true;
		if (l1==null || l2==null)
			return false;
		if (l1.base!=l2.base)
			return false;
		return compare(l1.next,l2.next);
	}
	
	/*
	 * helper method to complement base pairs of a given list
	 */
	public static char complementHelper(char base){
		char complement = 0;
		switch (base){
		case 'g': complement='c';
			break;
		case 'G': complement='C';
			break;
		case 'A': complement='T';
			break;
		case 'a': complement='t';
			break;
		case 't': complement='a';
			break;
		case 'T': complement='A';
			break;
		case 'c': complement='g';
			break;
		case 'C': complement='G';
			break;
		}
		return complement;
	}
	
	/*
	 * method creates complementary linked list
	 * using the helper method above
	 */
	public static Node complement( Node x){
		Node comp_x = x;
		
		if (comp_x == null)
			return null;
		/*
		 * creating new complementary linked list
		 * comp_head points to front of complementary linked list
		 */
		Node comp_head = new Node(complementHelper(comp_x.base), null);
		Node prevNode = comp_head;
		Node nextNode;
		
		/*
		 * while loop builds rest of complementary linked list
		 * traversing original linked list to do so
		 * incrementing comp_x and prevNode so that next node can be added
		 */
		while (comp_x.next != null){
			nextNode = new Node(complementHelper(comp_x.next.base), null);
			prevNode.next = nextNode;
			prevNode = nextNode;
			comp_x = comp_x.next;
			}
		return comp_head;
		}
	/*
	 * recursive method to reverse linked list
	 * if block returns the reversed list if there is no node or only one node
	 */
	public static Node reverseNode(Node head){
		if (head==null||head.next==null)
			return head;
		Node second = head.next;
		head.next=null;
		Node rest = reverseNode(second);
		second.next=head;
		return rest;
	}

	/*
	 * method to complement and reverse linked list
	 */
	public static Node reverseComplement(Node x){
		Node rev = reverseNode(complement(x));
		return rev;
	}
	
	/*
	 * helper method to alphabetically generate strands with given parameters
	 */
	public static void generateStrandsHelper(int n, int k, int rate){
		Node a = new Node('A', null);
		Node c = new Node('C', null);
		Node g = new Node('G', null);
		Node t = new Node('T', null);
		
		generateStrands(n, k, rate, a, a);
		generateStrands(n, k, rate, c, c);
		generateStrands(n, k, rate, g, g);
		generateStrands(n, k, rate, t, t);
	}

	/*
	 * recursive method that generates strands 
	 * and displays strands alphabetically with given parameters
	 * only displays if gc content matches input and strand is not self-complementary
	 * using the helper method above
	 */
	public static void generateStrands(int n, int k, int rate, Node x, Node p){
		if (k==n){
			Node rev = reverseComplement(p);
			if(!compare(p, rev) && gcContent(p)==rate){
				displayDNA(p);
				System.out.println();
			}
		}
		else{
			Node a = new Node ('A', null);
			Node c = new Node ('C', null);
			Node g = new Node ('G', null);
			Node t = new Node ('T', null);
			x.next=a;
			generateStrands(n, k+1, rate, a, p);
			x.next=c;
			generateStrands(n, k+1, rate, c, p);
			x.next=g;
			generateStrands(n, k+1, rate, g, p);
			x.next=t;
			generateStrands(n, k+1, rate, t, p);
		}
	}
}