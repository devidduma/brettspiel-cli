
public class Hilfe {
	
	public static class Node<T> {
		public T info;
		public Node<T> next;
		public Node(T x) {
			info = x;
			next = null;
		}
	}

	public static class List<T> {
		
		public Node<T> head, tail;
		public int size;
		
		public List() {
			head = tail = null;
			size = 0;
		}
		
		public void insert(T x) {
			if(head == null) {
				head = tail = new Node<>(x);
				size = 1;
			} else {
				tail.next = new Node<>(x);
				tail = tail.next;
				size++;
			}
		}
	}

    //Hilfsmethode	=>	keine Codeduplizierung	=>	Minimale Wartung
	public static Move[] toMoveArray(String from, List<String> tempResult) {
		Move[] result = new Move[tempResult.size];
    	
    	Hilfe.Node<String> tempNode = tempResult.head;
    	for(int i = 0; i < tempResult.size; i++) {
    		result[i] = new Move(from, tempNode.info);
    		tempNode = tempNode.next;
    	}
    	return result;
	}
}
