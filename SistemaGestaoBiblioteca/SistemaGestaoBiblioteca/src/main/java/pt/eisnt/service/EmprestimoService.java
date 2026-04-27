package pt.eisnt.service;

import pt.eisnt.model.Emprestimo;
import pt.eisnt.model.Leitor;
import pt.eisnt.model.Livro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Criação da lista da classe do model + objetos do livroService e leitorService
public class EmprestimoService {
    private List<Emprestimo> listaEmprestimos = new ArrayList<>();
    private LivroService livroService;
    private LeitorService leitorService;

    //Construtor desses dois Objetos
    public EmprestimoService(LivroService livroService, LeitorService leitorService) {
        this.livroService = livroService;
        this.leitorService = leitorService;
    }

    public void registarEmprestimo(String isbn, String idLeitor) {
        Livro livro = livroService.buscarLivroIsbn(isbn);
        Leitor leitor = leitorService.buscarLeitor(idLeitor);

        if (livro == null) {
            throw new IllegalArgumentException("Erro, Livro não encontrado.");
        }
        if (leitor == null) {
            throw new IllegalArgumentException("Erro, Leitor não encontrado.");
        }
        //Verificação da disponibilidade do livro
        if(!livro.isDisponivel()) {
            throw new IllegalStateException("Erro, o livro " + livro.getTitulo() + "já está emprestado.");
        }

        //Criamos já a data de devolução no momento da requisição
        LocalDate dataDevolucao = LocalDate.now().plusDays(15);

        //Instanciamos o objeto Emprestimo
        Emprestimo novo = new Emprestimo(livro, leitor, LocalDate.now(), dataDevolucao);

        //Guarda na lista
        listaEmprestimos.add(novo);
        System.out.println("Emprestimo registado com sucesso para: " + leitor.getNome());

    }

    public void devolucaoEmprestimo(String isbn) {
        Emprestimo emprestimoEncontrado = null;

        //Verifica se na lista Emprestimos se existe um ISBN igual
        for (Emprestimo e: listaEmprestimos){
            if (e.getLivro().getIsbn().equals(isbn)) {
                emprestimoEncontrado = e;
                break;
            }
        }

        //Caso não da mensagem de erro
        if (emprestimoEncontrado == null){
            throw new IllegalStateException("Erro, Não foi encontrado um emprestimo ativo para este ISBN.");
        }

        //Caso sim seta o metodo disponivel do livro como true
        emprestimoEncontrado.getLivro().setDisponivel(true);

        //Retira o emprestimo da lista
        listaEmprestimos.remove(emprestimoEncontrado);

        System.out.println("Devolução concluida. Livro " + emprestimoEncontrado.getLivro().getTitulo() + " Disponível novamente.");


    }

    public void setListaEmprestimosAtivos(){
        if (listaEmprestimos.isEmpty()){
            System.out.println("Não há emprestimos registados.");
        }
        else {
            for (Emprestimo f: listaEmprestimos){
                System.out.println("Livro: " + f.getLivro().getTitulo() +" | Leitor: " + f.getLeitor().getNome() + " | Data: " + f.getDataEmprestimo() + " | Devolução Prevista: " + f.getDataDevolucao());
            }
        }
    }
}
