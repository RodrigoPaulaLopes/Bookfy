package api.crud.bookfy.livros.controller;

import api.crud.bookfy.livros.dtos.AlterarLivrosDto;
import api.crud.bookfy.livros.dtos.CreateLivroDto;
import api.crud.bookfy.livros.dtos.MostrarLivrosDto;
import api.crud.bookfy.livros.entity.Livros;
import api.crud.bookfy.livros.repository.LivrosRepository;
import api.crud.bookfy.usuarios.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("livros")
public class LivrosController {
    @Autowired
    private LivrosRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<Page<MostrarLivrosDto>> findAll(Pageable paginacao) {
        var livros = this.repository.findAll(paginacao).map(MostrarLivrosDto::new);

        return ResponseEntity.ok(livros);
    }

    @GetMapping("{id}")
    public ResponseEntity<MostrarLivrosDto> findById(@PathVariable("id") Long id) {
        var livro = this.repository.getReferenceById(id);

        return ResponseEntity.ok(new MostrarLivrosDto(livro));
    }
//
    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateLivroDto livrodto, UriComponentsBuilder uriBuilder) {

        Livros livros = new Livros(livrodto);

        this.repository.save(livros);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(livros.getId()).toUri();

        return ResponseEntity.created(uri).body(new MostrarLivrosDto(livros));

    }
    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid AlterarLivrosDto livrosDto) {

        Livros livros = this.repository.getReferenceById(livrosDto.id());
        livros.atualizarDados(livrosDto);

        this.repository.save(livros);

        return ResponseEntity.ok(new MostrarLivrosDto(livros));

    }
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") Long id) {
        var livro = this.repository.getReferenceById(id);
        this.repository.delete(livro);

        return ResponseEntity.noContent().build();

    }


}
