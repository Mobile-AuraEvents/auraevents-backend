package auraevents.auraev.repository;

import auraevents.auraev.model.PatrocinioShow;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatrocinioShowRepository extends JpaRepository<PatrocinioShow, Long> {
    List<PatrocinioShow> findByShowId(Long showId);
}
