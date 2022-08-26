package maven.demo;

import java.util.Scanner;

public class Programa {
	
	private static Scanner in = new Scanner(System.in);
	
	private static Usuario createUser() {
		int newId;
		String newName, newLogin, newPass;
		
		System.out.println("Digite a id do usuario:");
		newId = in.nextInt();
		System.out.println("Digite o nome do usuario:");
		newName = in.next();
		System.out.println("Digite o login do usuario:");
		newLogin = in.next();
		System.out.println("Digite a senha do usuario:");
		newPass = in.next();
		
		return new Usuario(newId, newName, newLogin, newPass);
	}
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		boolean run = true;
		
		while(run) {
			System.out.println("Acesso ao banco de dados 'Exercicio2'\n:postgreSQL");
			System.out.println("1 - adicionar usuario   2 - remover usuario   3 - visualizar usuarios   4 - sair\n:");
			
			int opt = in.nextInt();
			switch(opt) {
				case 1: {
					Usuario newUser = createUser();
					dao.insertUser(newUser);
					break;
				}
				case 2: {
					System.out.println("ID do usuario:");
					int thisId = in.nextInt();
					dao.deleteUser(thisId);
					break;
				}
				case 3: {
					dao.displayUsers();
					break;
				}
				case 4 : {
					run = false;
					System.out.println("Finalizando...");
				}
			}
		}
	}
}
