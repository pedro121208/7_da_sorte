package br.com.setedasorte.config;
import br.com.setedasorte.repo.UsuarioRepository; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*; import org.springframework.http.HttpMethod; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.core.userdetails.*; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.SecurityFilterChain; import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
@Configuration public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService userDetailsService(UsuarioRepository r){return email->r.findByEmailIgnoreCase(email).map(u->User.withUsername(u.getEmail()).password(u.getSenha()).roles(u.getPapel()).build()).orElseThrow(()->new UsernameNotFoundException("Usuário não encontrado"));}
 @Bean AuthenticationManager authenticationManager(org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration c)throws Exception{return c.getAuthenticationManager();}
 @Bean DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService u,PasswordEncoder p){DaoAuthenticationProvider a=new DaoAuthenticationProvider();a.setUserDetailsService(u);a.setPasswordEncoder(p);return a;}
 @Bean SecurityFilterChain filterChain(HttpSecurity h,DaoAuthenticationProvider p)throws Exception{
  h.authenticationProvider(p).csrf(c->c.disable()).authorizeHttpRequests(a->a.requestMatchers("/","/index.html","/styles.css","/app.js","/api/auth/register","/api/auth/login").permitAll()
  .requestMatchers(HttpMethod.GET,"/api/leiloes","/api/leiloes/**").permitAll()
  .requestMatchers("/api/admin/**").hasRole("ADMIN").anyRequest().authenticated())
  .formLogin(f->f.disable()).httpBasic(b->b.disable()).logout(l->l.logoutUrl("/api/auth/logout").logoutSuccessHandler((q,s,a)->s.setStatus(204)))
  .exceptionHandling(e->e.authenticationEntryPoint((q,s,x)->s.sendError(401,"Faça login para continuar")));
  return h.build();
 }
}
