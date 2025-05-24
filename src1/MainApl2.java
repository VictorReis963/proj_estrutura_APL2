import apl2.Data;
import apl2.DLinkedList;
import apl2.LinkedListOriginal;
import apl2.Operation;

import java.io.IOException;

public class MainApl2 {
	public static void main(String[] args) {
		LinkedListOriginal list = new LinkedListOriginal();

		// Carregar dados do arquivo
		try {
			String fileContent = Data.loadTextFileToString("dados.txt");
			String[] lines = fileContent.split("\\r?\\n|\\r");
			for (String line : lines) {
				String[] parts = line.split("#");
				int id = Integer.parseInt(parts[0]);
				String nome = parts[1];
				int inteiro = Integer.parseInt(parts[2]);
				int decimo = Integer.parseInt(parts[3]);
				list.append(id, nome, inteiro, decimo);
			}
		} catch (IOException e) {
			System.err.println("Erro ao carregar arquivo dados.txt");
			e.printStackTrace();
			return;
		}

		System.out.println(">>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
		System.out.println(list);
		System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<\n");
		
		DLinkedList fixedList = Operation.map(list);
		System.out.println(">>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>");
		System.out.println(fixedList);
		System.out.println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<\n");
		
		DLinkedList filteredGradedList = Operation.filterRemoveNonGraded(fixedList);
		System.out.println(">>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
		System.out.println(filteredGradedList);
		System.out.println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<\n");
		
		DLinkedList filteredNonGradedList = Operation.filterRemoveGraded(fixedList);
		System.out.println(">>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
		System.out.println(filteredNonGradedList);
		System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<\n");

		float average = Operation.reduce(filteredGradedList);
		System.out.println(">>>>>>>>>> Média das notas válidas >>>>>>>>>>");
		System.out.println(average);
		System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<\n");
		
		DLinkedList aboveAverageList = Operation.filterRemoveBelowAverage(filteredGradedList, average);
		System.out.println(">>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
		System.out.println(aboveAverageList);
		System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<\n");
		
		String contents = Operation.mapToString(fixedList);
		System.out.println(">>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
		System.out.println(contents);
		System.out.println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<\n");
		
		// Salvar arquivo CSV
		try {
			Data.saveStringToTextFile("dados.csv", contents);
			System.out.println("Arquivo dados.csv salvo com sucesso!");
		} catch (IOException e) {
			System.err.println("Erro ao salvar arquivo dados.csv");
			e.printStackTrace();
		}

		// Testes adicionais
		apl2.Node test1 = fixedList.getNode("23.S1-999");
		System.out.println(">>>>>>>>>> test1 >>>>>>>>>>\n" + test1 + "\n<<<<<<<<<< test1 <<<<<<<<<<\n");
     	}
	}