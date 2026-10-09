package br.com.setedasorte.service;
import br.com.setedasorte.model.*; import br.com.setedasorte.repo.*; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration; import org.springframework.security.crypto.password.PasswordEncoder; import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.List;
@Configuration public class SeedData {
 @Bean CommandLineRunner seed(LeilaoRepository ls,UsuarioRepository us,PasswordEncoder enc){return args->{
 if(us.findByEmailIgnoreCase("admin@7dasorte.local").isEmpty())us.save(new Usuario("Administrador 7 da Sorte","admin@7dasorte.local",enc.encode("TroqueEstaSenha123!"),"ADMIN"));
 if(ls.count()==0){LocalDateTime fim=LocalDateTime.now().plusDays(3);ls.saveAll(List.of(
 new Leilao("iPhone 16 Pro Max","Celulares","Smartphone premium. Lote demonstrativo; consulte as condições antes de participar.","https://images.unsplash.com/photo-1592750475338-74b7b21085ab?auto=format&fit=crop&w=900&q=85",new BigDecimal("1800"),fim),
 new Leilao("PlayStation 5 Slim","Games","Console de videogame. Lote demonstrativo.","https://images.unsplash.com/photo-1606813907291-d86efa9b94db?auto=format&fit=crop&w=900&q=85",new BigDecimal("950"),fim),
 new Leilao("Notebook para trabalho","Informática","Notebook para produtividade e estudos. Lote demonstrativo.","https://images.unsplash.com/photo-1496181133206-80ce9b88a853?auto=format&fit=crop&w=900&q=85",new BigDecimal("700"),fim),
 new Leilao("Smart TV 55 polegadas","Eletrodomésticos","Televisor inteligente. Lote demonstrativo.","https://images.unsplash.com/photo-1593359677879-a4bb92f829d1?auto=format&fit=crop&w=900&q=85",new BigDecimal("500"),fim),
 new Leilao("Fone Bluetooth","Áudio","Fone sem fio. Lote demonstrativo.","https://images.unsplash.com/photo-1505740420928-5e560c06d30e?auto=format&fit=crop&w=900&q=85",new BigDecimal("80"),fim),
 new Leilao("Kit de ferramentas","Ferramentas","Kit para uso doméstico. Lote demonstrativo.","https://images.unsplash.com/photo-1581147036324-c1c89c2c8a29?auto=format&fit=crop&w=900&q=85",new BigDecimal("60"),fim),
 new Leilao("Smartwatch","Acessórios","Relógio inteligente. Lote demonstrativo.","https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&w=900&q=85",new BigDecimal("100"),fim),
 new Leilao("Air fryer","Eletrodomésticos","Fritadeira elétrica. Lote demonstrativo.","https://images.unsplash.com/photo-1585515320310-259814833e62?auto=format&fit=crop&w=900&q=85",new BigDecimal("90"),fim)
 ));}};}
}
