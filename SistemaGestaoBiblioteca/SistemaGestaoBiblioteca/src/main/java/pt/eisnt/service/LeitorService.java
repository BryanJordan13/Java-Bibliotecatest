package pt.eisnt.service;

import pt.eisnt.model.Autor;
import pt.eisnt.model.Leitor;
import java.util.ArrayList;
import java.util.List;

// Criação da lista da classe do model
public class LeitorService {
    private List<Leitor> listaLeitores = new ArrayList<>();
    private int proximoId = 1; //Contador para criar Ids sequenciais

    //Metodo de busca
    public Leitor buscarLeitor(String id){
        for(Leitor l: listaLeitores){
            if(l.getId().equals(id)) {
                return l;
            }
        }
        return null;
    }

    //Metodo adicionar Leitor
    public void adicionarLeitor(String nome, String email, String telemovel){

        String idGerado = String.valueOf(proximoId ++); //Id gerado seguencial
        Leitor novoLeitor = new Leitor(idGerado, nome, email, telemovel);

        //Consulta se o ID do Autor já existe, se sim da mensagem de erro e não prossegue
        if(buscarLeitor(novoLeitor.getId()) != null){
            throw new IllegalStateException("Erro, ID já registado para outro Leitor.");
        }

        //Adiciona o livro na lista
        this.listaLeitores.add(novoLeitor);
        System.out.println("O Leitor " + novoLeitor.getNome() + " com ID: " + idGerado + " foi adicionado com sucesso.");
    }

    //Metodo de editar Leitor
    public void editarLeitor(String id, String novoNome, String novoEmail, String novoTelemovel){
        Leitor leitor = buscarLeitor(id);
        if (leitor == null) {
            throw new IllegalArgumentException("Leitor não encontrado.");
        }
        if (!novoNome.isBlank()) leitor.setNome(novoNome);
        if (!novoEmail.isBlank()) leitor.setEmail(novoEmail);
        if (!novoTelemovel.isBlank()) leitor.setTelemovel(novoTelemovel);

        System.out.println("Leitor atualizado com sucesso.");
    }

    public void listarLeitores() {
        if (listaLeitores.isEmpty()){
            System.out.println("Nenhum leitor registado.");
        }
        else {
            for (Leitor l: listaLeitores){
                System.out.println("ID: " + l.getId() + " | Nome: " + l.getNome() + " | E-mail: " + l.getEmail() + " | Telemovel: " + l.getTelemovel());
            }
        }
    }
}





