package api.crud.bookfy.usuarios.dtos;

import api.crud.bookfy.usuarios.entity.Usuarios;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlterarUsuarioDto(@NotNull Long id, @NotBlank @Email String email, @NotBlank String senha) {

    public AlterarUsuarioDto(Usuarios usuario){
        this(usuario.getId(), usuario.getEmail(), usuario.getSenha());
    }
}
