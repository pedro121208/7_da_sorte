package br.com.setedasorte.repo;
import br.com.setedasorte.model.Lance; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface LanceRepository extends JpaRepository<Lance,Long>{List<Lance> findTop20ByLeilaoIdOrderByCriadoEmDesc(Long id); List<Lance> findByUsuarioIdOrderByCriadoEmDesc(Long id);}
