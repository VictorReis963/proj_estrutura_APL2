/*
	Lendy Naiara Carpio Pacheco - 10428525
	Victor Reis da Silva        - 10420297
*/

package apl2;
//Classe que contém as operações principais de transformação e filtragem de dados
public class Operation {
	//Converte a lista antiga para a nova estrutura de dados
	public static DLinkedList map(LinkedListOriginal list) {
		DLinkedList newList = new DLinkedList();
		NodeOriginal current = list.getHead();
		
		//Percorre cada item da lista antiga
		while (current != null) {
			//Formata o ID para o novo padrão
			String oldId = String.format("%03d", current.getId());
			String newId = "23.S1-" + oldId;
			float nota;
			
			//Checa se a nota está ausente no formato antigo
			if (current.getInteiro() == -1 || current.getDecimo() == -1) {
				nota = 99.9f;
			} else {
				//Junta a parte inteira e decimal para formar a nota completa
				nota = Float.parseFloat(current.getInteiro() + "." + current.getDecimo());
			}
			//Adiciona o aluno com os dados no novo formato para a nova lista
			newList.append(newId, current.getNome(), nota);
			current = current.getNext();
		}
		return newList; //Retorna a lista já convertida
	}

	//Obtem a lista mantendo só os alunos com notas válidas
	public static DLinkedList filterRemoveNonGraded(DLinkedList list) {
		DLinkedList filteredList = new DLinkedList();
		Node current = list.getHead();
		
		//Itera por todos os alunos
		while (current != null) {
			//Se a nota não for 99.9f (ou seja, uma nota real), adiciona à nova lista
			if (current.getNota() != 99.9f) { 
				filteredList.append(current.getId(), current.getNome(), current.getNota());
			}
			current = current.getNext();
		}
		return filteredList; //Retorna a lista só com notas válidas
	}

	//Obtem a lista, mantendo apenas os alunos que não têm nota
	public static DLinkedList filterRemoveGraded(DLinkedList list) {
		DLinkedList filteredList = new DLinkedList();
		Node current = list.getHead();
		
		//Itera por todos os alunos
		while (current != null) {
			//Se a nota for 99.9f (ausência de nota), adiciona à nova lista
			if (current.getNota() == 99.9f) { 
				filteredList.append(current.getId(), current.getNome(), current.getNota());
			}
			current = current.getNext();
		}
		return filteredList; //Retorna a lista só com alunos sem nota
	}

	//Obtem a lista para encontrar alunos com nota acima da média
	public static DLinkedList filterRemoveBelowAverage(DLinkedList list, float average) {
		DLinkedList filteredList = new DLinkedList();
		Node current = list.getHead();
		//Itera por todos os alunos.
		while (current != null) {
			//Se a nota for maior que a média ele adiciona à nova lista
			if (current.getNota() > average) {
				filteredList.append(current.getId(), current.getNome(), current.getNota());
			}
			current = current.getNext();
		}
		return filteredList; //Retorna a lista de alunos acima da média
	}

	//Calcula a media de todas as notas validas na lista
	public static float reduce(DLinkedList list) {
		float sum = 0;
		int count = 0;
		Node current = list.getHead();
		
		//Soma as notas e conta quantos alunos tem nota
		while (current != null) {
			if (current.getNota() != 99.9f) {
				sum += current.getNota();
				count++;
			}
			current = current.getNext();
		}
		return count > 0 ? sum / count : 0; //Retorna a média, se não houver notas retorna 0 para evitar divisão por zero
	}

	//Converte o conteudo da lista para uma string no formato CSV (ID;Nome;Nota)
	public static String mapToString(DLinkedList list) {
		StringBuilder sb = new StringBuilder();
		Node current = list.getHead();
		
		//Itera por todos os alunos para montar a string
		while (current != null) {
			//Adiciona os dados do aluno separados por ponto e vírgula
			sb.append(current.getId())
			  .append(";")
			  .append(current.getNome())
			  .append(";")
			  .append(current.getNota());
			
			//Coloca uma quebra de linha para o próximo aluno se tiver
			if (current.getNext() != null) {
				sb.append("\n");
			}
			current = current.getNext();
		}
		return sb.toString(); //Retorna a string CSV final
	}
}