package pt.eisnt.model;

public class Leitor {
    private String id;
    private String nome;
    private String email;
    private String telemovel;

    //Construtor com validação
    public Leitor(String id, String nome, String email, String telemovel) {
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

        if(email.isEmpty() || email.isBlank()){
            throw new IllegalArgumentException("Erro, E-mal é obrigatorio.");
        }
        else{
            this.email = email;
        }

        if(telemovel.isEmpty() || telemovel.isBlank()){
            throw new IllegalArgumentException("Erro, Nº Telemovel é obrigatorio.");
        }
        else{
            this.telemovel = telemovel;
        }

    }

    //Getters
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelemovel() {
        return telemovel;
    }

    //Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }
}
