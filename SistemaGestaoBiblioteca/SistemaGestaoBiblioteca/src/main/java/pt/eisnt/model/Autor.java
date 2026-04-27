package pt.eisnt.model;

public class Autor {
    private String id;
    private String nome;
    private String nacionalidade;

    //Construtor com validação
    public Autor(String id, String nome, String nacionalidade) {
        if(id.isEmpty() || id.isBlank()){
            throw new IllegalArgumentException("Erro, ID inválido.");
        }
        else{
            this.id = id;
        }

        if(nome.isEmpty() || nome.isBlank()){
            throw new IllegalArgumentException("Erro, Nome é obrigatorio.");
        }
        else{
            this.nome = nome;
        }

        if(nacionalidade.isEmpty() || nacionalidade.isBlank()){
            throw new IllegalArgumentException("Erro, Nacionalidade é obrigatorio");
        }
        else {
            this.nacionalidade = nacionalidade;
        }

    }

    //Getters
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }


    //Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}


