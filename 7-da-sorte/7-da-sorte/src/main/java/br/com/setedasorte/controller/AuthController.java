package br.com.setedasorte.controller;
import br.com.setedasorte.model.Usuario; import br.com.setedasorte.repo.UsuarioRepository; import jakarta.validation.Valid; import jakarta.validation.constraints.*; import jakarta.servlet.http.HttpServletRequest; import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity; import org.springframework.security.authentication.*; import org.springframework.security.core.Authentication; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.context.HttpSessionSecurityContextRepository; import org.springframework.web.bind.annotation.*; import java.util.Map;
@RestController @RequestMapping("/api/auth") public class AuthController{
 private final UsuarioRepository users;private final PasswordEncoder encoder;private final AuthenticationManager manager;
 public AuthController(UsuarioRepository u,PasswordEncoder e,AuthenticationManager m){users=u;encoder=e;manager=m;}
 public record Cadastro(@NotBlank @Size(max=100) String nome,@Email @NotBlank String email,@Size(min=8,max=100) String senha){}
 public record Login(@Email @NotBlank String email,@NotBlank String senha){}
 @PostMapping("/register") public ResponseEntity<?> register(@Valid @RequestBody Cadastro d){
 if(users.findByEmailIgnoreCase(d.email()).isPresent())return ResponseEntity.badRequest().body(Map.of("message","Este e-mail já está cadastrado."));
 Usuario u=users.save(new Usuario(d.nome().trim(),d.email().trim().toLowerCase(),encoder.encode(d.senha()),"USER"));
 return ResponseEntity.ok(Map.of("message","Conta criada. Faça login para continuar.","id",u.getId()));}
 @PostMapping("/login") public ResponseEntity<?> login(@Valid @RequestBody Login d,HttpServletRequest req){
 try{Authentication a=manager.authenticate(new UsernamePasswordAuthenticationToken(d.email().trim().toLowerCase(),d.senha()));SecurityContextHolder.getContext().setAuthentication(a);HttpSession s=req.getSession(true);s.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,SecurityContextHolder.getContext());Usuario u=users.findByEmailIgnoreCase(d.email()).orElseThrow();return ResponseEntity.ok(Map.of("id",u.getId(),"nome",u.getNome(),"email",u.getEmail(),"papel",u.getPapel()));}
 catch(AuthenticationException e){return ResponseEntity.status(401).body(Map.of("message","E-mail ou senha incorretos."));}}
 @GetMapping("/me") public ResponseEntity<?> me(Authentication a){if(a==null)return ResponseEntity.status(401).body(Map.of("message","Não autenticado"));Usuario u=users.findByEmailIgnoreCase(a.getName()).orElseThrow();return ResponseEntity.ok(Map.of("id",u.getId(),"nome",u.getNome(),"email",u.getEmail(),"papel",u.getPapel()));}
}
