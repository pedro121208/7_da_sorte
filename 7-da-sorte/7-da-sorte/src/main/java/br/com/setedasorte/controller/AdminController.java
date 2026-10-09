package br.com.setedasorte.controller;
import br.com.setedasorte.model.Leilao;import br.com.setedasorte.repo.LeilaoRepository;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import java.util.Map;
@RestController @RequestMapping("/api/admin") public class AdminController{private final LeilaoRepository repo;public AdminController(LeilaoRepository r){repo=r;}
 @PostMapping("/leiloes/{id}/encerrar") public ResponseEntity<?> close(@PathVariable Long id){Leilao l=repo.findById(id).orElse(null);if(l==null)return ResponseEntity.notFound().build();l.setAtivo(false);repo.save(l);return ResponseEntity.ok(Map.of("message","Leilão encerrado.","id",id));}}
