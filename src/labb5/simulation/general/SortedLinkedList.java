package labb5.simulation.general;

public class SortedLinkedList {

	Node head;

	public SortedLinkedList() {
		this.head = null;
	}

	public void add(Event e) {
		Node new_node = new Node(e);
		Node current;

		/* Special case for head node */
		if (head == null || head.e.getTime() >= new_node.e.getTime()) {
			new_node.tail = head;
			head = new_node;
		} else {

			/* Locate the node before point of insertion. */
			current = head;

			while (current.tail != null && current.tail.e.getTime() < new_node.e.getTime())
				current = current.tail;

			new_node.tail = current.tail;
			current.tail = new_node;
		}
		/*
		 * Node n = new Node(e); if(this.isEmpty()) { this.head = n; } else {
		 * head.insert(n, this); }
		 */
	}

	public Event poll() {
		if (this.isEmpty())
			return null;
		Event returnEvent = this.head.e;
		this.head = this.head.tail;
		return returnEvent;
	}

	public boolean isEmpty() {
		return this.head == null;
	}

	class Node {
		Node head;
		Node tail;
		public Event e;

		public Node(Event e) {
			this.head = null;
			this.tail = null;
			this.e = e;
		}

		public void insert(Node nodeToInsert, SortedLinkedList l) {
			// place before
			if (nodeToInsert.e.getTime() < this.e.getTime()) {
				// is first element
				if (this.head == null) {
					nodeToInsert.tail = this;
					this.head = nodeToInsert;
				} else {
					// place before
					nodeToInsert.head = this.head;
					nodeToInsert.tail = this;
					this.head.tail = nodeToInsert;
					this.head = nodeToInsert;
				}
			}
			// place after
			else {
				// is last element
				if (this.tail == null) {
					nodeToInsert.head = this;
					this.tail = nodeToInsert;
				}
				// place after
				this.tail.insert(nodeToInsert, l);

			}

			/*
			 * 
			 * 
			 * if (nodeToInsert.e.getTime() < this.e.getTime()) { if(this.head == null) {
			 * this.head = nodeToInsert; l.head = nodeToInsert; } else { nodeToInsert.tail =
			 * this; this.head.tail = nodeToInsert;
			 * 
			 * } nodeToInsert.tail = this; } else { if(this.tail == null) { this.tail =
			 * nodeToInsert; nodeToInsert.tail = null; } else {
			 * this.tail.insert(nodeToInsert, l); } }
			 */
		}

		/*
		 * public void insert(Node n, Node head) { if(n.e.getTime() < this.e.getTime())
		 * {
		 * 
		 * 
		 * n.head = this.head; n.tail = this; if(this.head == null) { head = n; } else {
		 * this.head.tail = n; } this.head = n; }else { if(this.tail == null) { n.head =
		 * this; n.tail = this.tail; this.tail = n; } else { this.tail.insert(n, head);
		 * } } }
		 */

	}

}
