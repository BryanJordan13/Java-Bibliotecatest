package pt.eisnt.model;

public class Livro {
    private String isbn;
    private String titulo;
    private Autor autor;
    private boolean disponivel;

    //Construtor com validação
    public Livro(String isbn, String titulo, Autor autor, boolean disponivel) {
        if(isbn.isEmpty() || isbn.isBlank()){
            throw new IllegalArgumentException("Erro, ISBN inválido.");
        }
        else{
            this.isbn = isbn;
        }

        if(titulo.isEmpty() || titulo.isBlank()){
            throw new IllegalArgumentException("Erro, Título Livro é obrigatorio.");
        }
        else{
            this.titulo = titulo;
        }

        if(autor == null){
            throw new IllegalArgumentException("Erro, Autor associado é obrigatorio.");
        }
        else{
            this.autor = autor;
        }

        this.disponivel = true;

    }


    //Getters
    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }


    //Setters
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
