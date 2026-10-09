package br.com.setedasorte.model;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Table(name="leiloes") public class Leilao {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String titulo; @Column(nullable=false,length=40) private String categoria;
 @Column(nullable=false,length=1000) private String descricao; @Column(nullable=false,length=700) private String imagem;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal lanceInicial;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal lanceAtual;
 @Column(nullable=false) private LocalDateTime encerraEm; @Column(nullable=false) private boolean ativo=true; private Long vencedorId;
 public Leilao(){} public Leilao(String t,String c,String d,String i,BigDecimal l,LocalDateTime f){titulo=t;categoria=c;descricao=d;imagem=i;lanceInicial=l;lanceAtual=l;encerraEm=f;ativo=true;}
 public boolean encerrado(){return !ativo||!LocalDateTime.now().isBefore(encerraEm);}
 public Long getId(){return id;} public String getTitulo(){return titulo;} public String getCategoria(){return categoria;} public String getDescricao(){return descricao;} public String getImagem(){return imagem;} public BigDecimal getLanceInicial(){return lanceInicial;} public BigDecimal getLanceAtual(){return lanceAtual;} public LocalDateTime getEncerraEm(){return encerraEm;} public boolean isAtivo(){return ativo;} public Long getVencedorId(){return vencedorId;}
 public void setLanceAtual(BigDecimal v){lanceAtual=v;} public void setVencedorId(Long v){vencedorId=v;} public void setAtivo(boolean v){ativo=v;}
}
