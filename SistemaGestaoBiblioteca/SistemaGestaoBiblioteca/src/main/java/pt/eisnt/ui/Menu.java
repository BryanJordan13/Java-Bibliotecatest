package pt.eisnt.ui;

import pt.eisnt.model.Autor;
import pt.eisnt.model.Leitor;
import pt.eisnt.model.Livro;
import pt.eisnt.service.AutorService;
import pt.eisnt.service.EmprestimoService;
import pt.eisnt.service.LeitorService;
import pt.eisnt.service.LivroService;

import java.util.Scanner;


public class Menu {
    public static void main(String[] args) {
        //criação dos serviços
        Scanner sc = new Scanner(System.in);
        AutorService autorSrv = new AutorService();
        LeitorService leitorSrv = new LeitorService();
        LivroService livroSrv = new LivroService(autorSrv);
        EmprestimoService emprestimoSrv = new EmprestimoService(livroSrv, leitorSrv);

        int opcao = -1;

        do {
            System.out.println("\n\n=== Sistema de Gestão Biblioteca EISnt ===\n");
            System.out.println("1 - Gestão de Acervo (Livros/Autores)");
            System.out.println("2 - Gestão de Utilizadores (Leitores)");
            System.out.println("3 - Operações de Balcão (Emprestimos)");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 0:
                    System.out.println("\nFinalizando o Programa.");
                    break;

                case 1:
                    menuAcervo(sc, livroSrv, autorSrv);
                    break;

                case 2:
                    menuLeitor(sc, leitorSrv);
                    break;

                case 3:
                    menuBalcao(sc, emprestimoSrv);
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }


    private static void menuAcervo(Scanner sc, LivroService livroSrv, AutorService autorSrv) {

        int subOpcao = -1;

        do {
            System.out.println("\n\n=== Gestão de Acervo ===\n");
            System.out.println("1 - Adicionar Autor");
            System.out.println("2 - Listar Autores");
            System.out.println("3 - Editar Autor");
            System.out.println("========================");
            System.out.println("4 - Adicionar Livro");
            System.out.println("5 - Listar Livros");
            System.out.println("6 - Editar Autor");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Opção: ");

            subOpcao = sc.nextInt();
            sc.nextLine();

            switch (subOpcao) {

                case 0:
                    System.out.println("\nVoltando ao Menu Principal");
                    break;

                case 1:
                    try {
                        System.out.println("\n=== Adicionar Autor ===");
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();

                        System.out.print("Nacionalidade: ");
                        String nacionalidade = sc.nextLine();

                        autorSrv.adicionarAutor(nome, nacionalidade);
                    } catch (Exception e) {
                        System.out.println("Erro ao adicionar: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("\n=== Lista de Autores ===");
                    autorSrv.listarAutores();
                    break;

                case 3:
                    try {
                        System.out.println("\n=== Editar Autor ===");
                        System.out.print("ID do Autor  ");
                        String idEdit = sc.nextLine();

                        Autor atual = autorSrv.buscarAutor(idEdit);
                        if (atual != null) {
                            System.out.println("Autor: " + atual.getNome() + " | " + atual.getNacionalidade());

                            System.out.print("Novo Nome 'ou Enter para manter': ");
                            String nome = sc.nextLine();

                            System.out.print("Nova Nacionalidade 'ou Enter para manter': ");
                            String nacionalidade = sc.nextLine();

                            autorSrv.editarAutor(idEdit, nome, nacionalidade);
                        } else {
                            System.out.println("Erro, Autor não encontrado.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.println("\n=== Adicionar Livro ===");
                        System.out.print("ISBN: ");
                        String isbn = sc.nextLine();

                        System.out.print("Título: ");
                        String titulo = sc.nextLine();

                        System.out.print("ID Autor: ");
                        String idAutor = sc.nextLine();
                        Autor autorEcontrado = autorSrv.buscarAutor(idAutor);

                        if (autorEcontrado != null) {
                            Livro novoLivro = new Livro(isbn, titulo, autorEcontrado, true);
                            livroSrv.adicionarLivro(novoLivro);
                        } else {
                            System.out.println("Erro: Autor não econtrado. Registe o autor primeiro.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao adicionar: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("\n=== Lista de Livros ===");
                    livroSrv.listarLivros();
                    break;

                case 6:
                    try {
                        System.out.println("\n=== Editar Livro ===");
                        System.out.print("ISBN do Livro: ");
                        String isbnEdit = sc.nextLine();

                        Livro atual = livroSrv.buscarLivroIsbn(isbnEdit);
                        if (atual != null) {
                            System.out.println("Livro: " + atual.getTitulo() + " | Autor: " + atual.getAutor().getNome());

                            System.out.print("Novo Titulo 'ou Enter para manter': ");
                            String titulo = sc.nextLine();

                            System.out.print("Novo ID Autor 'ou Enter para manter': ");
                            String idAutor = sc.nextLine();

                            livroSrv.editarLivro(isbnEdit, titulo, idAutor);
                        } else {
                            System.out.println("Erro, Livro não encontrado.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (subOpcao != 0);
    }


    private static void menuLeitor(Scanner sc, LeitorService leitorSrv) {
        int subOpcao = -1;

        do {
            System.out.println("\n=== Gestão de Utilizadores (Leitores) ===");
            System.out.println("1 - Adicionar Leitor");
            System.out.println("2 - Lista de Leitores");
            System.out.println("3 - Editar Leitor");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Opção: ");

            subOpcao = sc.nextInt();
            sc.nextLine();

            switch (subOpcao) {

                case 0:
                    System.out.println("\nVoltando ao Menu Principal");
                    break;

                case 1:
                    try {
                        System.out.println("\n=== Adicionar Leitor ===");
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();

                        System.out.print("E-mail: ");
                        String email = sc.nextLine();

                        System.out.print("Nº Telemovel: ");
                        String telemovel = sc.nextLine();

                        leitorSrv.adicionarLeitor(nome, email, telemovel);
                    } catch (Exception e) {
                        System.out.println("Erro ao adicionar: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("\n=== Lista de Leitores ===");
                    leitorSrv.listarLeitores();
                    break;

                case 3:
                    try {
                        System.out.println("\n=== Editar Leitor ===");
                        System.out.print("ID do Leitor: ");
                        String idEdit = sc.nextLine();

                        Leitor atual = leitorSrv.buscarLeitor(idEdit);
                        if (atual != null) {
                            System.out.println("Nome: " + atual.getNome() + " | " + atual.getEmail() + " | " + atual.getTelemovel());

                            System.out.print("Novo Nome 'ou Enter para manter': ");
                            String nome = sc.nextLine();

                            System.out.print("Novo E-mail 'ou Enter para manter': ");
                            String email = sc.nextLine();

                            System.out.print("Novo Telemovel 'ou Enter para manter': ");
                            String telemovel = sc.nextLine();

                            leitorSrv.editarLeitor(idEdit, nome, email, telemovel);

                        } else {
                            System.out.println("Erro, Leitor não encontrado.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (subOpcao != 0);
    }


    private static void menuBalcao(Scanner sc, EmprestimoService emprestimoSrv) {
        int subOpcao = -1;

        do {
            System.out.println("\n=== Operações de Balcão (Emprestimos) ===");
            System.out.println("1 - Solicitar Emprestimo");
            System.out.println("2 - Lista de Emprestimos Ativos");
            System.out.println("3 - Devolver Livro");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Opção: ");

            subOpcao = sc.nextInt();
            sc.nextLine();

            switch (subOpcao) {
                case 1:
                    try {
                        System.out.println("\n=== Solicitar Emprestimo ===");
                        System.out.print("ISBN do livro a requisitar: ");
                        String isbn = sc.nextLine();

                        System.out.print("ID do Leitor: ");
                        String idLeitor = sc.nextLine();


                        emprestimoSrv.registarEmprestimo(isbn, idLeitor);
                    } catch (Exception e) {
                        System.out.println("Erro ao solicitar emprestimo: " + e.getMessage());
                    }
                    break;

                case 2:
                        System.out.println("\n=== Lista de Emprestimos Ativos ===");
                        emprestimoSrv.setListaEmprestimosAtivos();
                        break;

                case 3:
                    try {
                        System.out.println("\n === Devolver Livro ===");
                        System.out.print("ISBN do livro a devolver: ");
                        String isbn = sc.nextLine();

                        emprestimoSrv.devolucaoEmprestimo(isbn);
                    } catch (Exception e) {
                        System.out.println("Erro na devolução: " + e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }while (subOpcao != 0);
    }


}



