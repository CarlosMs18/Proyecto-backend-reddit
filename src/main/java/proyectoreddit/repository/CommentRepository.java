package proyectoreddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoreddit.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
