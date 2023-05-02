package api.crud.bookfy.usuarios.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import api.crud.bookfy.usuarios.entity.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public record CreateUsuarioDto(@NotBlank @Email String email, @NotBlank String senha) {


    public CreateUsuarioDto(Usuarios usuario){
        this(usuario.getEmail(), usuario.getSenha());
    }
}
