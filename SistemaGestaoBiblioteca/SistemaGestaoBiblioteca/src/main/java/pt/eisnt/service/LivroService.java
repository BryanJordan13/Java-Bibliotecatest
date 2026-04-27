package pt.eisnt.service;

import pt.eisnt.model.Autor;
import pt.eisnt.model.Livro;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


// Criação da lista da classe do model
public class LivroService {
    private List<Livro> listaLivros = new ArrayList<>();
    private AutorService autorService;

    // LivroService vai ter que passar pelo AutorService para que ele funcione
    public LivroService(AutorService autorService){
        this.autorService = autorService;
    }

    //Metodos de Gestão e Pesquisa
    public Livro buscarLivroIsbn(String isbn){
        for(Livro l: listaLivros){
            if(l.getIsbn().equals(isbn)) {
                return l;
            }
        }
        return null;
    }

    public void adicionarLivro(Livro novolivro){
        //Consulta se o Isbn já existe, se sim da mensagem de erro e não prossegue
        if(buscarLivroIsbn(novolivro.getIsbn()) != null){
            throw new IllegalStateException("Erro: ISBN já registado para outro Livro.");
        }
        //Consulta se o Autor já existe na lista de Autores, caso não exista informa ao utilizador que ele primeiro precisa adicionar o Autor
        if(this.autorService.buscarAutor(novolivro.getAutor().getId()) == null) {
            throw new IllegalStateException("Erro, O Autor deste livro não está rgistado no sistema. Faça o registo do Autor primeiro.");
        }
        //Se tudo nada der IllegalState adiciona o livro na lista
        this.listaLivros.add(novolivro);
        System.out.println("Livro " + novolivro.getTitulo() + " adicionado com sucesso.");

    }

    public void editarLivro(String isbn, String novoTitulo, String idNovoAutor){
        Livro livro = buscarLivroIsbn(isbn);
        if (livro == null) {
            throw new IllegalArgumentException("Livro com ISBN não encontrado.");
        }
        if (!novoTitulo.isBlank()) livro.setTitulo(novoTitulo);
        if(!idNovoAutor.isBlank()) {
            Autor novoAutor = autorService.buscarAutor(idNovoAutor);
            if (novoAutor != null){
                livro.setAutor(novoAutor);
            }
            else {
                throw new IllegalArgumentException("novo autor não encontrado. Cancelando edição.");
            }
        }
        System.out.println("Livro atualizado com sucesso.");
    }

    public void listarLivros() {
        if (listaLivros.isEmpty()) {
            System.out.println("O acervo está vazio");
        }
        else {
            for (Livro l: listaLivros) {
                String status = l.isDisponivel() ? "[Disponivel] " : "[Emprestado] ";

                System.out.println(status + "ISBN: " + l.getIsbn() + " | Titulo: " + l.getTitulo() + " | Autor: " + l.getAutor().getNome());
            }
        }
    }




}