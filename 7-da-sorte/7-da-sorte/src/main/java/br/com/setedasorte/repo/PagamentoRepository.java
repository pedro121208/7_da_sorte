package br.com.setedasorte.repo;
import br.com.setedasorte.model.Pagamento; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface PagamentoRepository extends JpaRepository<Pagamento,Long>{List<Pagamento> findByUsuarioIdOrderByCriadoEmDesc(Long id);}
