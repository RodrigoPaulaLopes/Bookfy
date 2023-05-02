package api.crud.bookfy.livros.repository;

import api.crud.bookfy.livros.entity.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrosRepository extends JpaRepository<Livros, Long> {
}
