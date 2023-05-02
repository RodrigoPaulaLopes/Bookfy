package api.crud.bookfy.usuarios.dtos;

import api.crud.bookfy.usuarios.entity.Usuarios;

public record MostrarUsuariosDto(Long id, String email, String senha) {
    public MostrarUsuariosDto(Usuarios usuario){
        this(usuario.getId(), usuario.getEmail(), usuario.getSenha());
    }
}
