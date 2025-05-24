import apl2.Data;
import apl2.DLinkedList;
import apl2.LinkedListOriginal;
import apl2.NodeOriginal;
import apl2.Operation;

import java.io.IOException;
import java.util.Scanner;

public class MainApl2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        LinkedListOriginal originalList = null;
        DLinkedList convertedList = null;
        DLinkedList validGradesList = null;
        DLinkedList invalidGradesList = null;
        float average = 0;
        DLinkedList aboveAverageList = null;
        
        try {
            // Carrega os dados originais
            String fileContent = Data.loadTextFileToString("dados.txt");
            originalList = new LinkedListOriginal();
            
            String[] lines = fileContent.split("\\r?\\n|\\r");
            for (String line : lines) {
                String[] parts = line.split("#");
                int id = Integer.parseInt(parts[0]);
                String nome = parts[1];
                int inteiro = Integer.parseInt(parts[2]);
                int decimal = Integer.parseInt(parts[3]);
                
                originalList.append(new NodeOriginal(id, nome, inteiro, decimal));
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar arquivo dados.txt");
            e.printStackTrace();
            System.exit(1);
        }
        
        do {
            System.out.println("\nSistema Conversor de Notas");
            System.out.println("1) Dados originais: lê arquivo dados.txt e apresenta todos os dados do Sistema de Notas Legado");
            System.out.println("2) Dados convertidos: gera arquivo dados.csv e apresenta todos os dados do Sistema de Notas Atualizado");
            System.out.println("3) Lista notas filtradas válidas: apresenta os dados somente das notas válidas filtradas");
            System.out.println("4) Lista notas filtradas inválidas: apresenta os dados somente das notas filtradas pela 'ausência de notas'");
            System.out.println("5) Média de notas válidas: apresenta a média das notas válidas filtradas");
            System.out.println("6) Notas acima da média: apresenta os dados para as notas acima da média");
            System.out.println("7) Lista mapeada para uma única string: apresenta a String contendo os dados mapeados");
            System.out.println("8) Finaliza sistema");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            
            switch (opcao) {
                case 1:
                    System.out.println("\n>>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
                    System.out.println(originalList);
                    System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<");
                    break;
                    
                case 2:
                    convertedList = Operation.map(originalList);
                    System.out.println("\n>>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>");
                    System.out.println(convertedList);
                    System.out.println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<");
                    
                    try {
                        String csvContent = Operation.mapToString(convertedList);
                        Data.saveStringToTextFile("dados.csv", csvContent);
                        System.out.println("Arquivo dados.csv gerado com sucesso!");
                    } catch (IOException e) {
                        System.err.println("Erro ao salvar arquivo dados.csv");
                        e.printStackTrace();
                    }
                    break;
                    
                case 3:
                    if (convertedList == null) {
                        convertedList = Operation.map(originalList);
                    }
                    validGradesList = Operation.filterRemoveNonGraded(convertedList);
                    System.out.println("\n>>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
                    System.out.println(validGradesList);
                    System.out.println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<");
                    break;
                    
                case 4:
                    if (convertedList == null) {
                        convertedList = Operation.map(originalList);
                    }
                    invalidGradesList = Operation.filterRemoveGraded(convertedList);
                    System.out.println("\n>>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
                    System.out.println(invalidGradesList);
                    System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<");
                    break;
                    
                case 5:
                    if (validGradesList == null) {
                        if (convertedList == null) {
                            convertedList = Operation.map(originalList);
                        }
                        validGradesList = Operation.filterRemoveNonGraded(convertedList);
                    }
                    average = Operation.reduce(validGradesList);
                    System.out.println("\n>>>>>>>>>> Média das notas válidas >>>>>>>>>>");
                    System.out.println(average);
                    System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<");
                    break;
                    
                case 6:
                    if (average == 0) {
                        if (validGradesList == null) {
                            if (convertedList == null) {
                                convertedList = Operation.map(originalList);
                            }
                            validGradesList = Operation.filterRemoveNonGraded(convertedList);
                        }
                        average = Operation.reduce(validGradesList);
                    }
                    aboveAverageList = Operation.filterRemoveBelowAverage(validGradesList, average);
                    System.out.println("\n>>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
                    System.out.println(aboveAverageList);
                    System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<");
                    break;
                    
                case 7:
                    if (convertedList == null) {
                        convertedList = Operation.map(originalList);
                    }
                    System.out.println("\n>>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
                    System.out.println(Operation.mapToString(convertedList));
                    System.out.println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<");
                    break;
                    
                case 8:
                    System.out.println("Sistema finalizado.");
                    break;
                    
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 8);
        
        scanner.close();
    }
}