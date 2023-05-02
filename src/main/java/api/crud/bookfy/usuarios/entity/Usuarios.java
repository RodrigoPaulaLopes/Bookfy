package api.crud.bookfy.usuarios.entity;

import api.crud.bookfy.livros.entity.Livros;
import api.crud.bookfy.usuarios.dtos.CreateUsuarioDto;
import jakarta.persistence.*;
import lombok.*;
import api.crud.bookfy.usuarios.dtos.AlterarUsuarioDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuarios implements UserDetails {

    public Usuarios(CreateUsuarioDto usuarioDto){
        this.setEmail(usuarioDto.email());
        this.setSenha(usuarioDto.senha());
    }
    public Usuarios(AlterarUsuarioDto usuarioDto){
        this.setId(usuarioDto.id());
        this.setEmail(usuarioDto.email());
        this.setSenha(usuarioDto.senha());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String email;
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Livros> livros = new ArrayList<>();

    public void atualizarDados(AlterarUsuarioDto usuario) {
        this.setEmail(usuario.email());
        this.setSenha(usuario.senha());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
