package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Endereco;
import model.Pessoa;

public class Principal {

	public static void main(String[] args) throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conexao = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1312");
			System.out.println("conexão OK!");
			
			Scanner tc = new Scanner(System.in);
			System.out.println("====================");
			System.out.println("BANCO DE DADOS FUCTURA");
			System.out.println("====================");
			System.out.println();
			int opcao;
			while(true) {
			System.out.println("~~~~~~~~~~~~~~~~");
			System.out.printf("Digite uma opção:\n1-Cadastrar Pessoa com Endereco\n2-Alterar Pessoa com Endereco\n3-Pesquisar Pessoa\n4-Remover Pessoa e Endereco\n5-Listar todas as Pessoas e seu  Endereco\n6-SAIR\n");
			System.out.println("~~~~~~~~~~~~~~~~");
			opcao = tc.nextInt();
			System.out.printf("VOCÊ ESCOLHEU A OPCÃO %d\n", opcao);
			while(opcao<1||opcao>6) {
				System.out.println("Opção Inválida!!!");
				System.out.println("~~~~~~~~~~~~~~~~");
				System.out.printf("Digite uma opção:\n1-Cadastrar Pessoa com Endereco\n2-Alterar Pessoa com Endereco\n3-Pesquisar Pessoa\n4-Remover Pessoa e Endereco\n5-Listar todas as Pessoas e seu  Endereco\n6-SAIR\n");
				System.out.println("~~~~~~~~~~~~~~~~");
				opcao = tc.nextInt();
			}
			if(opcao==6) {
				System.out.println("VOCÊ ESCOLHEU OPÇÃO SAIR. ");
				System.out.println("OBRIGADO POR UTILIZAR O Banco de Dados FUCTURA!ATÉ A PROXIMA!");
			break;
			}
			
			switch(opcao) {
					case 1:
						String sql = "insert into Endereco (rua, numero,bairro, complemento) VALUES (?, ?,?,?)";
						 
						//Prepara a instruçao SQL:
						PreparedStatement ps = conexao.prepareStatement(sql);
						
						Endereco end = new Endereco();
						System.out.println("Digite o nome da Rua:");
						tc.nextLine();
						end.setRua(tc.nextLine());
						end.getRua();
						
						System.out.println("Digite o numero Rua:");
						end.setNumero(tc.nextLine());
						end.getNumero();
						System.out.println("Digite o nome do Bairro:");
						end.setBairro(tc.nextLine());
						end.getBairro();
						System.out.println("Digite o nome do Complemento:");
						end.setComplemento(tc.nextLine());
						end.getComplemento();
						
						ps.setString(1,end.getRua());
						ps.setString(2,end.getNumero());
						ps.setString(3,end.getBairro());
						ps.setString(4,end.getComplemento());
						System.out.println("Endereço cadastrado com sucesso! " );
						
						ps.execute();
						
						String sql2 = "insert into Pessoa (nome, idade,sexo, cpf, id_endereco) VALUES (?,?, ?,?,?)"; 
						ps=conexao.prepareStatement(sql2);		
		
						Pessoa pessoa= new Pessoa();
						System.out.println("Digite o seu  Nome:");
						pessoa.setNome(tc.nextLine());
						pessoa.getNome();
						System.out.println("Digite a sua Idade:");
						pessoa.setIdade(tc.nextLong());
						pessoa.getIdade();
						tc.nextLine();
						System.out.println("Digite o seu sexo[M / F]:");
						pessoa.setSexo(tc.nextLine());
						pessoa.getSexo();
						System.out.println("Digite o número do seu Cpf:");
						pessoa.setCpf(tc.nextLine());
						pessoa.getCpf();
						System.out.println("Digite o ID do Endereço:");
						end.setIdEndereco(tc.nextLong());
						
						
						ps.setString(1,pessoa.getNome());
						ps.setLong(2,pessoa.getIdade());
						ps.setString(3,pessoa.getSexo());
						ps.setString(4,pessoa.getCpf());
						ps.setLong(5,end.getIdEndereco());
						
						System.out.println("Pessoa cadastrado com sucesso!");
						
						
						ps.execute();
						
				
					break;
				case 2:
					//aLTERAR PESSOA COM ENDEREÇO:
					
					
					System.out.println("Quais dados deseja mudar?\n 1- nome \n 2- idade \n 3- cpf");
					int op;
					op = tc.nextInt();
					while(op<1 || op>3) {
						System.out.println("Opção inválida...Quais dados deseja mudar?\n 1- nome \n 2- idade \n 3- cpf");
						op= tc.nextInt();
					}
					if (op==1) {
						String sql3 = "Update  pessoa set nome=?  where id_pessoa =?";
						ps =conexao.prepareStatement(sql3);
						pessoa =new Pessoa();
						System.out.println("Digite o novo Nome do Usuário:");
						tc.nextLine();
						pessoa.setNome(tc.nextLine());
						pessoa.getNome();
						
						System.out.println("Digite o Id do Usuario onde deseja mudar: ");
						pessoa.setId_pessoa(tc.nextLong());
						pessoa.getId_pessoa();
						
						
						ps.setString(1, pessoa.getNome());
						ps.setLong(2, pessoa.getId_pessoa());
						System.out.println("Usuário alterado com sucesso!");
						ps.execute();
						

					}
					if (op ==2) {
						String sql3 = "Update  pessoa set idade=?  where id_pessoa =?";
						ps =conexao.prepareStatement(sql3);
						pessoa =new Pessoa();
						System.out.println("Digite a nova Idade do Usuário:");
						pessoa.setIdade(tc.nextLong());
						
						System.out.println("Digite o Id do Usuario onde deseja mudar: ");
						pessoa.setId_pessoa(tc.nextLong());
						pessoa.getId_pessoa();
						
						ps.setLong(1, pessoa.getIdade());
						ps.setLong(2, pessoa.getId_pessoa());
						System.out.println("Usuário alterado com sucesso!");
						ps.execute();
					}
					 if (op==3) {
						String sql3 = "Update  pessoa set cpf=?  where id_pessoa =?";
						ps =conexao.prepareStatement(sql3);
						pessoa =new Pessoa();
						System.out.println("Digite o novo CPF do Usuário:");
						tc.nextLine();
						pessoa.setCpf(tc.nextLine());
						pessoa.getCpf();
						
						System.out.println("Digite o Id do Usuario onde deseja mudar: ");
						pessoa.setId_pessoa(tc.nextLong());
						pessoa.getId_pessoa();
						
						
						ps.setString(1, pessoa.getCpf());
						ps.setLong(2, pessoa.getId_pessoa());
						System.out.println("Usuário alterado com sucesso!");
						ps.execute();
					}	
					break;
					
				case 3:
					//Pesquisar Pessoa
					String sql4 = "Select * from pessoa where id_pessoa=?";
					ps = conexao.prepareStatement(sql4);
					
					
					pessoa=new Pessoa();
					System.out.println("Digite o Id do Usuário a ser Consultado: ");
					pessoa.setId_pessoa(tc.nextLong());
					ps.setLong(1, pessoa.getId_pessoa());
					ps.execute();
					ResultSet res = ps.executeQuery();
					System.out.println("A Sua Lista De Usuários é: ");
					List<Pessoa>listaPessoa = new ArrayList<Pessoa>();
					
					while(res.next()) {
						end=new Endereco();
						pessoa = new Pessoa();
						pessoa.setId_pessoa(res.getLong("id_pessoa"));
						pessoa.setNome(res.getString("Nome"));
						pessoa.setIdade(res.getLong("idade"));
						pessoa.setSexo(res.getString("sexo"));
						pessoa.setCpf(res.getString("idade"));
						end.setIdEndereco(res.getLong("id_endereco"));
						pessoa.setEndereco(end);
						
						
						listaPessoa.add(pessoa);
						System.out.println(pessoa);
					}
					break;
				case 4:
					//Remover Pessoa e Endereco
					System.out.println("Qual dado você quer deletar? \n 1-Pessoa \n 2-Endereco ");
					int option = tc.nextInt();
					while(option <1 || option> 2) {
						System.out.println("Número inválido...Qual dado você quer deletar? \n 1-Pessoa \n 2-Endereco ");
						option = tc.nextInt();
					}
						if(option==1) {
						String sql5= "delete from pessoa where id_pessoa=?";
						ps = conexao.prepareStatement(sql5);
						pessoa= new Pessoa();
						System.out.println("Qual o ID do Usuário a ser deletado?");
						pessoa.setId_pessoa(tc.nextLong());
						
						ps.setLong(1, pessoa.getId_pessoa());
						ps.execute();
						
					}if(option==2) {
						String sql6= "delete from endereco where id_endereco=?";
						ps = conexao.prepareStatement(sql6);
						end= new Endereco();
						System.out.println("Qual o ID do Endereço a ser deletado?");
						end.setIdEndereco(tc.nextLong());
						
						ps.setLong(1, end.getIdEndereco());
						ps.execute();	
						System.out.println("Usuário deletado com Sucesso!");
					}
					break;
				case 5:
					//Listar todas as Pessoas e seu  Endereco
					String sql7 = "Select * from pessoa";
					pessoa =new Pessoa();
					Endereco ender = new Endereco();
					ps = conexao.prepareStatement(sql7);
					
					ps.execute();
					
					List<Pessoa> pessoaLista = new ArrayList<Pessoa>();
					ResultSet resultado = ps.executeQuery();
					
					while(resultado.next()) {
						pessoa.setId_pessoa(resultado.getLong("id_pessoa"));
						pessoa.setNome(resultado.getString("nome"));
						pessoa.setIdade(resultado.getLong("idade"));
						pessoa.setSexo(resultado.getString("sexo"));
						pessoa.setCpf(resultado.getString("cpf"));
						ender.setIdEndereco(resultado.getLong("id_endereco"));
						pessoa.setEndereco(ender);
						pessoaLista.add(pessoa);
						
						System.out.println(pessoa);
						
					}
					break;
				
			}
			}
	
			
//			
		} catch (ClassNotFoundException e) {
			System.out.println("deu ruim "+ e.getMessage());
			e.printStackTrace();
		}

	}

}
