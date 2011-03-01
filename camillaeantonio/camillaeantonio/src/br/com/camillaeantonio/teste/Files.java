package br.com.camillaeantonio.teste;

import java.io.File;

public class Files {

	public static void main(String[] args) {
		String dir = "D:/workspace/camillaeantonio/WebContent/fotos";

		File diretorio = new File(dir);
		File fList[] = diretorio.listFiles();

		for (int i = 0; i < fList.length; i++) {
			System.out.println(fList[i].getName());
		}

	}
}
