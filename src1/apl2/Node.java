

/*
	Lendy Naiara Carpio Pacheco - 10428525
	Victor Reis da Silva        - 10420297
*/
package apl2;
//Representa um nó individual na nova lista duplamente encadeada
//Tem os dados de um aluno e referências para o nó anterior e próximo
public class Node {
	private String id; //O novo ID do aluno
	private String nome; //Nome do aluno
	private float nota; //A nota do aluno com ele já no formato decimal
	private Node next; //Referencia para o proximo nó na lista
	private Node previous; //Referencia para o nó anterior na lista

	//Começa com um nó com valores vazios/padrão
	public Node() {
		this.id = "";
		this.nome = "";
		this.nota = 99.9f;
		this.next = null;
		this.previous = null;
	}

	//Cria um nó com os dados do aluno
	public Node(String id, String nome, float nota) {
		this.id = id;
		this.nome = nome;
		this.nota = nota;
		this.next = null;
		this.previous = null;
	}

	//Retorna o Id do aluno
	public String getId() {
		return id;
	}

	//Define o Id do aluno
	public void setId(String id) {
		this.id = id;
	}

	//Retorna o nome do aluno
	public String getNome() {
		return nome;
	}

	//Define o nome aluno
	public void setNome(String nome) {
		this.nome = nome;
	}

	//Retorna a nota
	public float getNota() {
		return nota;
	}

	//Define a nota do aluno
	public void setNota(float nota) {
		this.nota = nota;
	}

	//Retorna a referencia para o proximo nó
	public Node getNext() {
		return next;
	}

	//Define a referencia para o próximo nó
	public void setNext(Node next) {
		this.next = next;
	}

	//Retorna a referencia para o nó anterior
	public Node getPrevious() {
		return previous;
	}

	//Define a referencia para o nó anterior
	public void setPrevious(Node previous) {
		this.previous = previous;
	}

	
	//Sobrescreve o metodo toString exibindo os dados do nó
	@Override
	public String toString() {
		return "(" + id + "; " + nome + "; " + nota + ")";
	}
}