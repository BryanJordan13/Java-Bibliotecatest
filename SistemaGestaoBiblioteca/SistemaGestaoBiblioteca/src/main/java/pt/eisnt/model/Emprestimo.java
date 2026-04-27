package pt.eisnt.model;

import java.time.LocalDate;

public class Emprestimo {
    private Livro livro;
    private Leitor leitor;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    //Construtor com validação
    public Emprestimo(Livro livro, Leitor leitor, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        if(livro == null){
            throw new IllegalArgumentException("Erro, Livro associado é obrigatorio.");
        }
        else{
            this.livro = livro;
        }

        if(leitor == null){
            throw new IllegalArgumentException("Erro, Leitor associado é obrigatorio.");
        }
        else{
            this.leitor = leitor;
        }
        if(!livro.isDisponivel()){
            throw new IllegalStateException("Erro, Livro não disponível ou já requisitado."); // O State pois o metodo foi chamado para um objeto não disponivel no momento
        }
        livro.setDisponivel(false);

        this.dataEmprestimo = dataEmprestimo;

        if(dataDevolucao.isBefore(dataEmprestimo)){
            throw new IllegalArgumentException("Erro, a Data de Devolução não podes er inferior a data de Emprestimo.");
        }
        else{
            this.dataDevolucao = dataDevolucao;
        }

    }

    //Getters
    public Livro getLivro() {
        return livro;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
}
