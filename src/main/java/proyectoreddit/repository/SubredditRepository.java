package proyectoreddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoreddit.models.Subreddit;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {
}
