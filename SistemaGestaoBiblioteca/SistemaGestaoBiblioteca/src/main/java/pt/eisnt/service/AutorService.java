package pt.eisnt.service;

import pt.eisnt.model.Autor;
import pt.eisnt.model.Livro;

import java.util.ArrayList;
import java.util.List;


// Criação da lista da classe do model
public class AutorService {
    private List<Autor> listaAutores = new ArrayList<>();
    private int proximoId = 1; //Contador para criar Ids sequenciais

    //Metodo de busca
    public Autor buscarAutor(String id){
        for(Autor a: listaAutores){
            if(a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    //Metodo adicionar Autor
    public void adicionarAutor(String nome, String nacionalidade){

        String idGerado = String.valueOf(proximoId ++); //Id gerado senquencial
        Autor novoAutor = new Autor(idGerado, nome, nacionalidade);

        //Consulta se o ID do Autor já existe, se sim da mensagem de erro e não prossegue
        if(buscarAutor(novoAutor.getId()) != null){
            throw new IllegalStateException("Erro, ID já registado para outro Autor.");
        }

        //Adiciona o autor na lista
        this.listaAutores.add(novoAutor);
        System.out.println("O Autor " + novoAutor.getNome() + "com ID: " + idGerado + " foi adicionado com sucesso.");

    }

    //Metodo editar Autor
    public void editarAutor(String id, String novoNome, String novaNacionalidade) {
        Autor autor = buscarAutor(id);
        if (autor == null) {
            throw new IllegalArgumentException("Autor não encontrado.");
        }
        if (!novoNome.isBlank()) autor.setNome(novoNome);
        if (!novaNacionalidade.isBlank()) autor.setNacionalidade(novaNacionalidade);

        System.out.println("Autor atualizado com sucesso.");
    }

    public void listarAutores() {
        if (listaAutores.isEmpty()) {
            System.out.println("A lista está vazia");
        }
        else {
            for (Autor a: listaAutores) {
                System.out.println("Nome: " + a.getNome() + " | Nacionalidade: " + a.getNacionalidade());
            }
        }
    }
}
