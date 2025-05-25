/*
	Lendy Naiara Carpio Pacheco - 10428525
	Victor Reis da Silva        - 10420297
*/
package apl2;
// Esta classe representa a estrutura da lista encadeada original, usada no sistema legado.
// Ela armazena os nós que contêm os dados dos alunos.
public class LinkedListOriginal {
	
	private NodeOriginal head;
	private NodeOriginal tail;
	private int count;
	
	//Cria uma lista vazia
	public LinkedListOriginal() {
		head = null;
		tail = null;
		count = 0;
	}
	
	//Limpa completamente a lista, tornando-a vazia
	public void destroy() {
		clear();
	}
	
	//Insere um novo nó no início da lista
	public void insert(int id, String nome, int inteiro, int decimo) {
		//Cria um novo nó apontando para o que era o head anterior
		NodeOriginal node = new NodeOriginal(id, nome, inteiro, decimo, head);
		//Se a lista estava vazia o novo nó é também o tail
		if (isEmpty()) {
			tail = node;
		}
		
		head = node; //O novo nó se torna o head
		++count; //Incrementa a contagem de nós
	}
	
	//Adiciona um novo nó no final da lista
	public void append(int id, String nome, int inteiro, int decimo) {
		//Cria um novo nó que por enquanto não aponta para nada
		NodeOriginal node = new NodeOriginal(id, nome, inteiro, decimo, null);
		//Se a lista está vazia, este é o primeiro nó (head e tail)
		if (isEmpty()) {
			head = node;
		//Se não, o nó que era o tail agora aponta para o novo nó	
		} else {
			tail.setNext(node);
		}
		
		tail = node; //O novo nó se torna o tail
		++count;
	}
	
	//Remove o primeiro nó da lista
	public NodeOriginal removeHead() {
		//Retorna nulo se a lista já estiver vazia
		if (isEmpty()) {
			return null;
		}
		
		NodeOriginal toRemove = head; //Guarda o nó a ser removido
		head = head.getNext(); //O próximo nó se torna o novo head
		--count;
		
		//Se a lista ficar vazia após a remoção o tail tambem fica nulo
		if (isEmpty()) {
			tail = null;
		}
		
		toRemove.setNext(null); //Desconecta o nó removido da lista
		return toRemove; //Retorna o nó que foi removido
	}
	
	//Remove o último nó da lista
	public NodeOriginal removeTail() {
		//Se só tem um nó remove o head
		if (head == tail)
			return removeHead();
		
		NodeOriginal toRemove = head;
		NodeOriginal previous = null;
		//Percorre a lista até encontrar o tail
		while (toRemove != tail) {
			previous = toRemove; //Mantém a referência ao nó anterior
			toRemove = toRemove.getNext();
		}
		
		tail = previous; //O anterior ao tail se torna o novo tail
		tail.setNext(null); //O novo tail não aponta para mais nada
		--count;
		
		toRemove.setNext(null); //Desconecta o nó removido
		return toRemove; //Retorna o nó que foi removido
	}
	
	//Remove um nó específico da lista buscando pelo ID
	public NodeOriginal removeNode(int id) {
		NodeOriginal toRemove = head;
		NodeOriginal previous = null;
		//Procura o nó com o ID correspondente
		while (toRemove != null && toRemove.getId() != id) {
			previous = toRemove;
			toRemove = toRemove.getNext();
		}
		//Se o nó não foi encontrado retorna nulo
		if (toRemove == null) {
			return null;
		}

		// Se o nó a remover é o head
		if (toRemove == head) {
			return removeHead();
		}
		//Se o nó a remover é o tail
		if (toRemove == tail) {
			return removeTail();
		}
		//Se o nó está no meio da lista ajusta as referências
		previous.setNext(toRemove.getNext()); 
		--count;
		
		toRemove.setNext(null); //Desconecta o nó removido		
		return toRemove; //Retorna o nó
	}

	//Retorna o primeiro nó da lista(head)
	public NodeOriginal getHead() {
		return head;
	}
	//Retorna o último nó da lista(tail)
	public NodeOriginal getTail() {
		return tail;
	}
	
	//Busca e retorna um nó pelo ID
	public NodeOriginal getNode(int id) {
		NodeOriginal node = head;
		//Percorre a lista até encontrar o nó ou chegar ao fim
		while (node != null) {
			if (node.getId() == id) {
				return node; //Retorna o nó se o ID for encontrado
			}
			node = node.getNext();
		}
		return null; //Retorna nulo se o nó não for encontrado
	}

	//Retorna o número de nós na lista
	public int count() {
		return count;
	}
	
	//Verifica se a lista está vazia
	public boolean isEmpty() {
		return head == null;
	}
	
	//Remove todos os nós da lista deixando vazia
	public void clear() {
		while (!isEmpty()) {
			removeHead(); //Remove um por um até esvaziar
		}
	}

	@Override
	//Converte a lista para exibir em string
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(" + count + ") \n"); //Mostra a contagem de nós
		
		NodeOriginal node = head;
		//Percorre cada nó e adiciona seus dados para a string
		while (node != null) {
			sb.append("(")
			  .append(node.getId())
			  .append(" # ")
			  .append(node.getNome())
			  .append(" # ")
			  .append(node.getInteiro())
			  .append(" # ")
			  .append(node.getDecimo())
			  .append(") -> \n");
			node = node.getNext();
		}
		sb.append("null."); //Indica o final da lista
		return sb.toString();
	}
}