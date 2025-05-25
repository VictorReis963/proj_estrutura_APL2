
package apl2;
/*
	Lendy Naiara Carpio Pacheco - 10428525
	Victor Reis da Silva        - 10420297
*/

//Representa um nó individual na lista encadeada original do sistema legado
//Cada nó guarda os dados de um aluno e a referência para o próximo nó
public class NodeOriginal {
	
	private int id; //Id do aluno
	private String nome; //Nome do aluno
	private int inteiro; //Parte inteira da nota
	private int decimo; //Parte decimal da note
	private NodeOriginal next; //Referencia o proximo nó 
	
	//Cria um nó vazio, sem parametros 
	public NodeOriginal() {
		this(-1, "", 0, 0, null);
	}
	
	//Cria um nó com parametros(nome, inteiro, decimo, next)
	public NodeOriginal(int id, String nome, int inteiro, int decimo, NodeOriginal next) {
		this.id = id;
		this.nome = nome;
		this.inteiro = inteiro;
		this.decimo = decimo;
		this.next = next;
	}
	
	//Retorna o Id do aluno
	public int getId() {
		return id;
	}
	
	//Define o Id do aluno
	public void setId(int id) {
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
	
	//Retorna a parte inteira da nota
	public int getInteiro() {
		return inteiro;
	}
	
	//Define a parte inteira da nota
	public void setInteiro(int inteiro) {
		this.inteiro = inteiro;
	}
	
	//Retorna a parte decimal da nota
	public int getDecimo() {
		return decimo;
	}
	
	//Define a parte decinal da nota
	public void setDecimo(int decimo) {
		this.decimo = decimo;
	}

	//Retorna a referencia para o próximo nó
	public NodeOriginal getNext() {
		return next;
	}
	
	//Define a referencia para o proximo nó
	public void setNext(NodeOriginal next) {
		this.next = next;
	}
	
	//Sobrescreve o método toString
	@Override
	public String toString() {
		//Exibe a saída para mostrar os dados do aluno e a referência ao próximo nó
		//Se o próximo nó for nulo, indica o fim da lista neste ponto
		return "[dados: (" + id + ";" + nome + ";" + inteiro + ";" + decimo + ") | next: " + next + "]";
	}
}