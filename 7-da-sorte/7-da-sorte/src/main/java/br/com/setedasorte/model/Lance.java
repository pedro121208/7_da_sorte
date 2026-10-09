package br.com.setedasorte.model;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Table(name="lances") public class Lance {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) private Leilao leilao; @ManyToOne(optional=false) private Usuario usuario;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal valor; @Column(nullable=false) private LocalDateTime criadoEm=LocalDateTime.now();
 public Lance(){} public Lance(Leilao l,Usuario u,BigDecimal v){leilao=l;usuario=u;valor=v;}
 public Long getId(){return id;} public Leilao getLeilao(){return leilao;} public Usuario getUsuario(){return usuario;} public BigDecimal getValor(){return valor;} public LocalDateTime getCriadoEm(){return criadoEm;}
}
