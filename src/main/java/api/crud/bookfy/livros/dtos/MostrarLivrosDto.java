package api.crud.bookfy.livros.dtos;

import api.crud.bookfy.livros.entity.Livros;
import api.crud.bookfy.usuarios.dtos.MostrarUsuariosDto;

public record MostrarLivrosDto(Long id, String titulo, String autor, String editora, String isbn, Integer anoPublicacao, Integer edicao, Integer numPaginas, String sinopse, String capa, MostrarUsuariosDto usuariosDto) {

    public MostrarLivrosDto(Livros livros){
        this(livros.getId(), livros.getTitulo(), livros.getAutor(), livros.getEditora(), livros.getIsbn(), livros.getAnoPublicacao(), livros.getEdicao(), livros.getNumPaginas(), livros.getSinopse(), livros.getCapa(), new MostrarUsuariosDto(livros.getUsuario()));

    }
}
