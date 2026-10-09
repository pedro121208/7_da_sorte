package br.com.setedasorte.model;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Table(name="pagamentos") public class Pagamento {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) private Usuario usuario; @ManyToOne(optional=false) private Leilao leilao;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal valor; @Column(nullable=false,length=30) private String status="SIMULADO_APROVADO";
 @Column(nullable=false,length=30) private String metodo; @Column(nullable=false) private LocalDateTime criadoEm=LocalDateTime.now();
 public Pagamento(){} public Pagamento(Usuario u,Leilao l,BigDecimal v,String m){usuario=u;leilao=l;valor=v;metodo=m;}
 public Long getId(){return id;} public Usuario getUsuario(){return usuario;} public Leilao getLeilao(){return leilao;} public BigDecimal getValor(){return valor;} public String getStatus(){return status;} public String getMetodo(){return metodo;} public LocalDateTime getCriadoEm(){return criadoEm;}
}
