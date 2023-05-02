package api.crud.bookfy.livros.entity;

import jakarta.persistence.*;
import lombok.*;
import api.crud.bookfy.livros.dtos.AlterarLivrosDto;
import api.crud.bookfy.livros.dtos.CreateLivroDto;
import api.crud.bookfy.usuarios.entity.Usuarios;

@Entity(name = "livro")
@Table(name = "livros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Livros {

    public Livros(CreateLivroDto livros){
        this.setTitulo(livros.titulo());
        this.setAutor(livros.autor());
        this.setEditora(livros.editora());
        this.setIsbn(livros.isbn());
        this.setAnoPublicacao(livros.anoPublicacao());
        this.setEdicao(livros.edicao());
        this.setNumPaginas(livros.numPaginas());
        this.setSinopse(livros.sinopse());
        this.setCapa(livros.capa());
        this.setUsuario(new Usuarios(livros.usuariosDto()));
    }

    public void atualizarDados(AlterarLivrosDto livros){
        this.setId(livros.id());
        this.setTitulo(livros.titulo());
        this.setAutor(livros.autor());
        this.setEditora(livros.editora());
        this.setIsbn(livros.isbn());
        this.setAnoPublicacao(livros.anoPublicacao());
        this.setEdicao(livros.edicao());
        this.setNumPaginas(livros.numPaginas());
        this.setSinopse(livros.sinopse());
        this.setCapa(livros.capa());
        this.setUsuario(new Usuarios(livros.usuariosDto()));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String autor;

    private String editora;

    private String isbn;

    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;

    private Integer edicao;

    @Column(name = "num_paginas")
    private Integer numPaginas;

    private String sinopse;

    private String capa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;
}
