/*
	Lendy Naiara Carpio Pacheco - 10428525
	Victor Reis da Silva        - 10420297
*/
package apl2;
// Esta classe implementa uma lista duplamente encadeada
// Ela armazena os nós que representam os dados dos alunos no novo formato
public class DLinkedList {
	private Node head;
	private Node tail;
	private int size;

	//Cria uma lista duplamente encadeada vazia
	public DLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	//Insere um novo nó no início da lista
	public void insert(String id, String nome, float nota) {
		Node newNode = new Node(id, nome, nota); //Cria o novo nó
		if (isEmpty()) {
			head = newNode; //Se a lista estava vazia o novo nó é o head e o tail
			tail = newNode;
		} else {
			newNode.setNext(head); //O novo nó aponta para o antigo head
			head.setPrevious(newNode); //O antigo head aponta para o novo nó
			head = newNode; //O novo nó se torna o head da lista
		}
		size++;
	}

	//Adiciona um novo nó no final da lista
	public void append(String id, String nome, float nota) {
		Node newNode = new Node(id, nome, nota);
		if (isEmpty()) {
			head = newNode; //Se a lista estava vazia o novo nó é o head e o tail
			tail = newNode;
		} else {
			tail.setNext(newNode); //O antigo tail aponta para o novo nó
			newNode.setPrevious(tail); //O novo nó aponta para o antigo tail
			tail = newNode; //O novo nó se torna o tail da lista
		}
		size++;
	}

	//Remove e retorna o primeiro nó da lista
	public Node removeHead() {
		if (isEmpty()) {
			return null; //Retorna nulo se a lista estiver vazia
		}
		Node removed = head; //Guarda o nó a ser removido
		if (head == tail) { //Se tem apenas um nó na lista
			head = null; //A lista se torna vazia
			tail = null;
		} else {
			head = head.getNext(); //O proximo nó se torna o novo head
			head.setPrevious(null); //O novo head não tem nó anterior
		}
		size--;
		return removed; //Retorna o nó que foi removido
	}

	//Remove e retorna o ultimo nó da lista
	public Node removeTail() {
		if (isEmpty()) {
			return null; //Retorna nulo se a lista estiver vazia
		}
		Node removed = tail; //Guarda o nó a ser removido
		if (head == tail) { //Se tem apenas um nó na lista
			head = null; //A lista se torna vazia
			tail = null;
		} else {
			tail = tail.getPrevious(); // nó anterior se torna o novo tail
			tail.setNext(null); //O novo tail não aponta para um proximo nó
		}
		size--;
		return removed; //Retorna o nó que foi removido
	}

	//Remove um nó especifico da lista buscando pelo ID
	public Node removeNode(String id) {
		Node current = head; //Busca pelo head
		while (current != null) {
			// Compara os IDs
			if (current.getId().equals(id)) {
				if (current == head) { //Se o nó a remover é o head
					return removeHead();
				} else if (current == tail) { //Se o nó a remover é o tail
					return removeTail();
				} else { //Se o nó está no meio da lista
					//Conecta o nó anterior ao proximo nó pulando o nó atual
					current.getPrevious().setNext(current.getNext());
					current.getNext().setPrevious(current.getPrevious());
					size--; //Dimunui a contagem
					return current; //Retorna o nó removido
				}
			}
			current = current.getNext(); //Vai para o proximo nó
		}
		return null; //Retorna nulo se o nó não for encontrado
	}

	//Retorna o primeiro nó da lista head
	public Node getHead() {
		return head;
	}

	// Retorna o último nó da lista tail
	public Node getTail() {
		return tail;
	}

	//Busca e retorna um nó pelo ID
	public Node getNode(String id) {
		Node current = head; //Busca pelo head
		while (current != null) {
			if (current.getId().equals(id)) {
				return current; //Retorna o nó se o ID for encontrado
			}
			current = current.getNext(); //Vai para o proximo nó
		}
		return null; //Retorna nulo se o nó não for encontrado
	}

	//Retorna o numero de elementos na lista
	public int count() {
		return size;
	}

	//Verifica se a lista está vazia
	public boolean isEmpty() {
		return size == 0;
	}

	//Remove todos os nós da lista deixando vazia
	public void clear() {
		while (!isEmpty()) {
			removeHead(); //Remove o head até esvaziar
		}
	}

	@Override
	// Converte a lista para exibir em string
    // Exibe o número de nós e a conexão de cada um
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(size).append(")\n"); //Mostra a contagem de nós
		
		Node current = head; 
		//Percorre cada nó
		while (current != null) {
			//Adiciona a conexão com o nó anterior, o próprio nó e a conexão com o próximo
			sb.append(current.getPrevious() == null ? "null" : current.getPrevious().getId())
			  .append(" <- ")
			  .append(current.toString())
			  .append(" -> ")
			  .append(current.getNext() == null ? "null" : current.getNext().getId())
			  .append("\n");
			current = current.getNext(); //Vai para o próximo nó
		}
		return sb.toString();
	}
}