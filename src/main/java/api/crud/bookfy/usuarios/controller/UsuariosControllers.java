package api.crud.bookfy.usuarios.controller;

import api.crud.bookfy.usuarios.dtos.AlterarUsuarioDto;
import api.crud.bookfy.usuarios.dtos.CreateUsuarioDto;
import api.crud.bookfy.usuarios.dtos.MostrarUsuariosDto;
import api.crud.bookfy.usuarios.entity.Usuarios;
import api.crud.bookfy.usuarios.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController()
@RequestMapping("usuarios")
public class UsuariosControllers {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping
    public ResponseEntity<Page<MostrarUsuariosDto>> findAll(Pageable paginacao) {
        var usuarios = this.repository.findAll(paginacao).map(MostrarUsuariosDto::new);

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("{id}")
    public ResponseEntity<MostrarUsuariosDto> findById(@PathVariable("id") Long id) {
        var usuario = this.repository.getReferenceById(id);

        return ResponseEntity.ok(new MostrarUsuariosDto(usuario));
    }

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateUsuarioDto usuariodto, UriComponentsBuilder uriBuilder) {

        var newPass = this.encoder.encode(usuariodto.senha());

        CreateUsuarioDto userDto = new CreateUsuarioDto(usuariodto.email(), newPass);

        Usuarios usuario = new Usuarios(userDto);

        this.repository.save(usuario);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new MostrarUsuariosDto(usuario));

    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid AlterarUsuarioDto usuariodto) {

        Usuarios usuario = this.repository.getReferenceById(usuariodto.id());
        var newPass = this.encoder.encode(usuariodto.senha());
        AlterarUsuarioDto userDto = new AlterarUsuarioDto(usuariodto.id(), usuariodto.email(), newPass);
        usuario.atualizarDados(userDto);

        this.repository.save(usuario);

        return ResponseEntity.ok(new MostrarUsuariosDto(usuario));

    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") Long id) {
        var usuario = this.repository.getReferenceById(id);
        this.repository.delete(usuario);

        return ResponseEntity.noContent().build();

    }

}
