# 7 da Sorte — protótipo de leilões

Projeto demonstrativo com Java 21, Spring Boot, Spring Security, Spring Data JPA, MySQL e frontend HTML/CSS/JavaScript servido pelo Spring Boot.

## Requisitos
- Java JDK 21
- Maven 3.9+ (ou Maven Wrapper)
- MySQL 8+

## 1. Criar o banco
No MySQL Workbench ou terminal, execute:
```sql
CREATE DATABASE setedasorte CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
A conexão do projeto está em `src/main/resources/application.properties`.

## 2. Configurar a conexão
Edite `spring.datasource.username` e `spring.datasource.password` em `application.properties`, ou defina variáveis de ambiente:
- Windows PowerShell:
```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/setedasorte?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=America/Sao_Paulo"
$env:DB_USER="root"
$env:DB_PASSWORD="SUA_SENHA_MYSQL"
```
O backend usa Hibernate `ddl-auto=update` para criar/atualizar tabelas no desenvolvimento.

## 3. Executar
Na pasta que contém `pom.xml`:
```bash
mvn spring-boot:run
```
Abra `http://localhost:8080`.

## Conta administrativa de demonstração
- E-mail: `admin@7dasorte.local`
- Senha inicial: `TroqueEstaSenha123!`

Troque a senha e remova/ajuste a conta seed antes de qualquer publicação. Essa conta é criada automaticamente no primeiro início se ainda não existir.

## O que está implementado
- Página responsiva com identidade azul-marinho/dourado.
- Lotes de demonstração em diversas categorias, com imagens externas.
- Pesquisa por texto e filtro de categoria.
- Cadastro com senha armazenada em BCrypt.
- Login com sessão.
- Lance acima do valor atual, histórico de lances e validação de encerramento.
- Área de histórico de lances e pagamentos.
- Área administrativa protegida para cadastrar lotes e encerrar leilões via endpoint.
- Pagamento **simulado**: não movimenta dinheiro.

## APIs principais
- `GET /api/leiloes`
- `GET /api/leiloes/{id}`
- `POST /api/auth/register` — JSON: `{"nome":"Seu Nome","email":"voce@exemplo.com","senha":"SenhaSegura123"}`
- `POST /api/auth/login` — JSON: `{"email":"voce@exemplo.com","senha":"SenhaSegura123"}`
- `POST /api/leiloes/{id}/lances` — autenticado, JSON: `{"valor":1200.00}`
- `GET /api/usuario/lances` — autenticado
- `GET /api/usuario/pagamentos` — autenticado
- `POST /api/admin/leiloes` — administrador
- `POST /api/admin/leiloes/{id}/encerrar` — administrador

## Limitações importantes
Este é um **protótipo funcional para aprendizado**, não uma plataforma pronta para operar leilões comerciais. Antes de uso real, é necessário adicionar proteção CSRF adequada para sessões, controle concorrente robusto nos lances (por exemplo, bloqueio otimista/pessimista), regras completas de encerramento e vencedor, recuperação/verificação de e-mail, termos e políticas, auditoria, logs, testes, proteção contra abuso e revisão de segurança. O fluxo de pagamento é demonstrativo e não aceita nem armazena dados de cartão. Para pagamento real, integre um provedor no servidor e valide webhooks assinados.
