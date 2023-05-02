package api.crud.bookfy.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacaoDto(@NotBlank @Email String email, @NotBlank String senha) {
}
