package api.crud.bookfy.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import api.crud.bookfy.usuarios.entity.Usuarios;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    UserDetails findByEmail(String email);
}
