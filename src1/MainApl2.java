/*
	Lendy Naiara Carpio Pacheco - 10428525
	Victor Reis da Silva        - 10420297
*/
import apl2.Data;
import apl2.DLinkedList;
import apl2.LinkedListOriginal;
import apl2.Operation;

import java.io.IOException;

/*
	Classe principal do projeto, Carrega os dados legados, os transforma, 
	aplica filtros, calcula a media e salva o resultado no novo formato
*/
public class MainApl2 {
	public static void main(String[] args) {
		LinkedListOriginal list = new LinkedListOriginal();

		//Carregar e processar os dados do arquivo "dados.txt"
		try {
			//Lê todo o conteudo do arquivo texto
			String fileContent = Data.loadTextFileToString("dados.txt");
			String[] lines = fileContent.split("\\r?\\n|\\r"); //Divide o conteúdo em linhas e trata diferentes tipos de quebra de linha
			//Itera sobre cada linha para extrair e adicionar os dados a lista original
			for (String line : lines) {
				String[] parts = line.split("#"); //Divide a linha
				//Converte as partes para os tipos corretos e adiciona o nó á lista
				int id = Integer.parseInt(parts[0]);
				String nome = parts[1];
				int inteiro = Integer.parseInt(parts[2]);
				int decimo = Integer.parseInt(parts[3]);
				list.append(id, nome, inteiro, decimo);
			}
		} catch (IOException e) { //Erro na leitura
			System.err.println("Erro ao carregar arquivo dados.txt");
			e.printStackTrace();
			return;
		}

		//Exibe os dados que foram carregados do sistema legado
		System.out.println(">>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
		System.out.println(list);
		System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<\n");
		
		//Mapeia os dados da lista original para a nova estrutura de lista duplamente encadeada
		DLinkedList fixedList = Operation.map(list);
		//Exibe os dados após a conversão para o novo formato
		System.out.println(">>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>");
		System.out.println(fixedList);
		System.out.println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<\n");
		
		//Obtem uma nova lista contendo apenas alunos com notas válidas
		DLinkedList filteredGradedList = Operation.filterRemoveNonGraded(fixedList);
		// Exibe a lista das notas válidas.
		System.out.println(">>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
		System.out.println(filteredGradedList);
		System.out.println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<\n");
		
		//Obtem uma nova lista contendo apenas alunos com ausência de nota
		DLinkedList filteredNonGradedList = Operation.filterRemoveGraded(fixedList);
		// Exibe a lista de ausência de notas
		System.out.println(">>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
		System.out.println(filteredNonGradedList);
		System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<\n");

		//Calcula a média das notas validas que estão na lista filtrada
		float average = Operation.reduce(filteredGradedList);
		//Exibe o valor da média calculada
		System.out.println(">>>>>>>>>> Média das notas válidas >>>>>>>>>>");
		System.out.println(average);
		System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<\n");
		
		//Obtem a lista de notas válidas para ver os alunos com desempenho acima da média
		DLinkedList aboveAverageList = Operation.filterRemoveBelowAverage(filteredGradedList, average);
		//Exibe a lista de alunos com notas acima da média
		System.out.println(">>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
		System.out.println(aboveAverageList);
		System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<\n");
		
		//Converte a lista completa de dados já com o novo formato para uma unica string no formato CSV
		String contents = Operation.mapToString(fixedList);
		//Exibe a string formatada em CSV
		System.out.println(">>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
		System.out.println(contents);
		System.out.println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<\n");
		
		// Salvar arquivo CSV
		try {
			Data.saveStringToTextFile("dados.csv", contents);
			System.out.println("Arquivo dados.csv salvo com sucesso!");
		} catch (IOException e) { //Erro para salvar
			System.err.println("Erro ao salvar arquivo dados.csv");
			e.printStackTrace();
		}

		// Testes adicionais
		apl2.Node test1 = fixedList.getNode("23.S1-999");
		System.out.println(">>>>>>>>>> test1 >>>>>>>>>>\n" + test1 + "\n<<<<<<<<<< test1 <<<<<<<<<<\n");
     	}
	}