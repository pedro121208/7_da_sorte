package br.com.setedasorte.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="usuarios") public class Usuario {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=100) private String nome;
 @Column(nullable=false,unique=true,length=160) private String email;
 @Column(nullable=false) private String senha;
 @Column(nullable=false,length=20) private String papel="USER";
 private LocalDateTime criadoEm=LocalDateTime.now();
 public Usuario(){} public Usuario(String n,String e,String s,String p){nome=n;email=e;senha=s;papel=p;}
 public Long getId(){return id;} public String getNome(){return nome;} public String getEmail(){return email;} public String getSenha(){return senha;} public String getPapel(){return papel;} public LocalDateTime getCriadoEm(){return criadoEm;}
}
