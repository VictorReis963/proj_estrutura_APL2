package apl2;

public class Operation {
	public static DLinkedList map(LinkedListOriginal list) {
		DLinkedList newList = new DLinkedList();
		NodeOriginal current = list.getHead();
		
		while (current != null) {
			String oldId = String.format("%03d", current.getId());
			String newId = "23.S1-" + oldId;
			float nota;
			
			if (current.getInteiro() == -1 || current.getDecimo() == -1) {
				nota = 99.9f;
			} else {
				nota = Float.parseFloat(current.getInteiro() + "." + current.getDecimo());
			}
			
			newList.append(newId, current.getNome(), nota);
			current = current.getNext();
		}
		return newList;
	}

	public static DLinkedList filterRemoveNonGraded(DLinkedList list) {
		DLinkedList filteredList = new DLinkedList();
		Node current = list.getHead();
		
		while (current != null) {
			if (current.getNota() != 99.9f) {
				filteredList.append(current.getId(), current.getNome(), current.getNota());
			}
			current = current.getNext();
		}
		return filteredList;
	}

	public static DLinkedList filterRemoveGraded(DLinkedList list) {
		DLinkedList filteredList = new DLinkedList();
		Node current = list.getHead();
		
		while (current != null) {
			if (current.getNota() == 99.9f) {
				filteredList.append(current.getId(), current.getNome(), current.getNota());
			}
			current = current.getNext();
		}
		return filteredList;
	}

	public static DLinkedList filterRemoveBelowAverage(DLinkedList list, float average) {
		DLinkedList filteredList = new DLinkedList();
		Node current = list.getHead();
		
		while (current != null) {
			if (current.getNota() > average) {
				filteredList.append(current.getId(), current.getNome(), current.getNota());
			}
			current = current.getNext();
		}
		return filteredList;
	}

	public static float reduce(DLinkedList list) {
		float sum = 0;
		int count = 0;
		Node current = list.getHead();
		
		while (current != null) {
			if (current.getNota() != 99.9f) {
				sum += current.getNota();
				count++;
			}
			current = current.getNext();
		}
		return count > 0 ? sum / count : 0;
	}

	public static String mapToString(DLinkedList list) {
		StringBuilder sb = new StringBuilder();
		Node current = list.getHead();
		
		while (current != null) {
			sb.append(current.getId())
			  .append(";")
			  .append(current.getNome())
			  .append(";")
			  .append(current.getNota());
			
			if (current.getNext() != null) {
				sb.append("\n");
			}
			current = current.getNext();
		}
		return sb.toString();
	}
}