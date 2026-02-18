package cadastropoo;

import java.util.Scanner;
import model.*;

public class CadastroPOO {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PessoaFisicaRepo repoPF = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoPJ = new PessoaJuridicaRepo();

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("=================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("=================================");

            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao >= 1 && opcao <= 5) {
                System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                char tipo = sc.nextLine().toUpperCase().charAt(0);

                switch (opcao) {

                    case 1: // Incluir
                        System.out.println("Digite o id da pessoa:");
                        int id = sc.nextInt(); sc.nextLine();

                        System.out.println("Insira os dados...");
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();

                        if (tipo == 'F') {
                            System.out.print("CPF: ");
                            String cpf = sc.nextLine();
                            System.out.print("Idade: ");
                            int idade = sc.nextInt();
                            repoPF.inserir(new PessoaFisica(id, nome, cpf, idade));
                        } else {
                            System.out.print("CNPJ: ");
                            String cnpj = sc.nextLine();
                            repoPJ.inserir(new PessoaJuridica(id, nome, cnpj));
                        }
                        break;

                    case 2: // Alterar
                        System.out.print("Digite o id da pessoa: ");
                        int idAlt = sc.nextInt(); sc.nextLine();

                        if (tipo == 'F') {
                            PessoaFisica pf = repoPF.obter(idAlt);
                            if (pf != null) {
                                pf.exibir();
                                System.out.print("Novo nome: ");
                                pf.setNome(sc.nextLine());
                                System.out.print("Novo CPF: ");
                                pf.setCpf(sc.nextLine());
                                System.out.print("Nova idade: ");
                                pf.setIdade(sc.nextInt());
                                repoPF.alterar(pf);
                            }
                        } else {
                            PessoaJuridica pj = repoPJ.obter(idAlt);
                            if (pj != null) {
                                pj.exibir();
                                System.out.print("Novo nome: ");
                                pj.setNome(sc.nextLine());
                                System.out.print("Novo CNPJ: ");
                                pj.setCnpj(sc.nextLine());
                                repoPJ.alterar(pj);
                            }
                        }
                        break;

                    case 3: // Excluir
                        System.out.print("Digite o id da pessoa: ");
                        int idExc = sc.nextInt();

                        if (tipo == 'F') repoPF.excluir(idExc);
                        else repoPJ.excluir(idExc);
                        break;

                    case 4: // Buscar por ID
                        System.out.print("Digite o id da pessoa: ");
                        int idBusca = sc.nextInt();

                        if (tipo == 'F') {
                            PessoaFisica pf = repoPF.obter(idBusca);
                            if (pf != null) pf.exibir();
                        } else {
                            PessoaJuridica pj = repoPJ.obter(idBusca);
                            if (pj != null) pj.exibir();
                        }
                        break;

                    case 5: // Exibir todos
                        if (tipo == 'F') {
                            for (PessoaFisica pf : repoPF.obterTodos()) pf.exibir();
                        } else {
                            for (PessoaJuridica pj : repoPJ.obterTodos()) pj.exibir();
                        }
                        break;
                }
            }

            else if (opcao == 6) {
                try {
                    System.out.print("Prefixo do arquivo: ");
                    String prefixo = sc.nextLine();
                    repoPF.persistir(prefixo + ".fisica.bin");
                    repoPJ.persistir(prefixo + ".juridica.bin");
                    System.out.println("Dados persistidos com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro ao persistir: " + e.getMessage());
                }
            }

            else if (opcao == 7) {
                try {
                    System.out.print("Prefixo do arquivo: ");
                    String prefixo = sc.nextLine();
                    repoPF.recuperar(prefixo + ".fisica.bin");
                    repoPJ.recuperar(prefixo + ".juridica.bin");
                    System.out.println("Dados recuperados com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro ao recuperar: " + e.getMessage());
                }
            }

            else if (opcao == 0) {
                System.out.println("Programa finalizado.");
            }
        }

        sc.close();
    }
}
