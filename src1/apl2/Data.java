
/*
	Lendy Naiara Carpio Pacheco - 10428525
	Victor Reis da Silva        - 10420297
*/
package apl2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

//Esta classe tem metodos para ler e escrever arquivos de texto e lida com a entrada e saída de dados de arquivos
public class Data {
	//Lê um arquivo de texto e retorna seu conteudo com uma string
	public static String loadTextFileToString(String filename) throws FileNotFoundException, IOException {
		//Abre o arquivo para leitura
		InputStream is = new FileInputStream(filename);
		//Prepara para ler caracteres do fluxo de bytes
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		//Usa um buffer para ler o arquivo linha por linha
		StringBuilder sb = new StringBuilder(); // Objeto para construir a string do conteudo
		//Loop para ler cada linha do arquivo
		while (true) {
			String line = br.readLine(); //Lê uma linha
			if (line == null) { //Se a linha for nula o fim do arquivo foi atingido
				break; //Sai do loop
			}
			sb.append(line).append('\n'); //Adiciona a linha lida e um caractere de nova linha
		}
		
		is.close(); //Fecha o fluxo de entrada para liberar recursos
		return sb.toString(); //Retorna todo o conteúdo do arquivo como uma string
	}

	//Salva uma string em um arquivo de texto
	public static void saveStringToTextFile(String filename, String contents) throws FileNotFoundException, IOException {
		//Abre o arquivo para escrita
		OutputStream os = new FileOutputStream(filename);
		//Prepara para escrever caracteres no fluxo de bytes
		OutputStreamWriter osw = new OutputStreamWriter(os);
		//Usa um buffer para escrever no arquivo 
		BufferedWriter bw = new BufferedWriter(osw);

		bw.write(contents); //Escreve todo o conteúdo da string no arquivo
		bw.flush(); //Garante que todos os dados em buffer sejam gravados no arquivo
		os.close(); //Fecha o fluxo de saída para liberar recursos
	}
}