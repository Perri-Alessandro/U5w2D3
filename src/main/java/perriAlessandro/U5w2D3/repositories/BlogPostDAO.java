package perriAlessandro.U5w2D3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import perriAlessandro.U5w2D3.entities.BlogPost;

import java.util.Optional;
import java.util.UUID;

public interface BlogPostDAO extends JpaRepository<BlogPost, UUID> {
    boolean existsByTitolo(String titolo);

    Optional<BlogPost> findByAuthorNome(String authorNome);
}
