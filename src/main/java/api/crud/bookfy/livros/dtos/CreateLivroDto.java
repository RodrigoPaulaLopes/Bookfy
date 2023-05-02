package api.crud.bookfy.livros.dtos;

import api.crud.bookfy.livros.entity.Livros;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import api.crud.bookfy.usuarios.dtos.AlterarUsuarioDto;

public record CreateLivroDto(@NotBlank String titulo, @NotBlank String autor, @NotBlank String editora, @NotBlank String isbn, @NotNull Integer anoPublicacao, @NotNull Integer edicao, @NotNull Integer numPaginas, @NotBlank String sinopse, @NotBlank String capa, @Valid @NotNull AlterarUsuarioDto usuariosDto) {

    public CreateLivroDto(Livros livros){
        this(livros.getTitulo(), livros.getAutor(), livros.getEditora(), livros.getIsbn(), livros.getAnoPublicacao(), livros.getEdicao(), livros.getNumPaginas(), livros.getSinopse(), livros.getCapa(), new AlterarUsuarioDto(livros.getUsuario()));

    }
}
