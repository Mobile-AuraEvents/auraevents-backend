package auraevents.auraev.repository;

import auraevents.auraev.model.Convidado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {
    List<Convidado> findByPatrocinadorId(Long patrocinadorId);
}
