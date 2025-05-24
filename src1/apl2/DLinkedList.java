package apl2;

public class DLinkedList {
	private Node head;
	private Node tail;
	private int size;

	public DLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public void insert(String id, String nome, float nota) {
		Node newNode = new Node(id, nome, nota);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.setNext(head);
			head.setPrevious(newNode);
			head = newNode;
		}
		size++;
	}

	public void append(String id, String nome, float nota) {
		Node newNode = new Node(id, nome, nota);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.setNext(newNode);
			newNode.setPrevious(tail);
			tail = newNode;
		}
		size++;
	}

	public Node removeHead() {
		if (isEmpty()) {
			return null;
		}
		Node removed = head;
		if (head == tail) {
			head = null;
			tail = null;
		} else {
			head = head.getNext();
			head.setPrevious(null);
		}
		size--;
		return removed;
	}

	public Node removeTail() {
		if (isEmpty()) {
			return null;
		}
		Node removed = tail;
		if (head == tail) {
			head = null;
			tail = null;
		} else {
			tail = tail.getPrevious();
			tail.setNext(null);
		}
		size--;
		return removed;
	}

	public Node removeNode(String id) {
		Node current = head;
		while (current != null) {
			if (current.getId().equals(id)) {
				if (current == head) {
					return removeHead();
				} else if (current == tail) {
					return removeTail();
				} else {
					current.getPrevious().setNext(current.getNext());
					current.getNext().setPrevious(current.getPrevious());
					size--;
					return current;
				}
			}
			current = current.getNext();
		}
		return null;
	}

	public Node getHead() {
		return head;
	}

	public Node getTail() {
		return tail;
	}

	public Node getNode(String id) {
		Node current = head;
		while (current != null) {
			if (current.getId().equals(id)) {
				return current;
			}
			current = current.getNext();
		}
		return null;
	}

	public int count() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		while (!isEmpty()) {
			removeHead();
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(size).append(")\n");
		
		Node current = head;
		while (current != null) {
			sb.append(current.getPrevious() == null ? "null" : current.getPrevious().getId())
			  .append(" <- ")
			  .append(current.toString())
			  .append(" -> ")
			  .append(current.getNext() == null ? "null" : current.getNext().getId())
			  .append("\n");
			current = current.getNext();
		}
		return sb.toString();
	}
}