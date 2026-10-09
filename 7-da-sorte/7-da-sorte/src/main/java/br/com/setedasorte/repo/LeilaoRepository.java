package br.com.setedasorte.repo;
import br.com.setedasorte.model.Leilao; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface LeilaoRepository extends JpaRepository<Leilao,Long>{List<Leilao> findAllByOrderByEncerraEmAsc(); List<Leilao> findByCategoriaIgnoreCaseOrderByEncerraEmAsc(String categoria);}
