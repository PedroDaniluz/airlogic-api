<div align="center">

# Air Logic API

API REST para gerenciamento e consulta de leituras de sensores, desenvolvida com Spring Boot, JPA e banco de dados MySQL.  
Backend para a aplicação mobile <a href="https://github.com/PedroDaniluz/airlogic">AirLogic</a>.

</div>

<br>

<div align="center">
  <a href="#colaboradores">Colaboradores</a> •
  <a href="#funcionalidades">Funcionalidades</a> •
  <a href="#estrutura-do-projeto">Estrutura do Projeto</a> •
  <a href="#como-executar">Como executar</a> •
  <a href="#mysql">MySQL</a> •
  <a href="#autenticacao">Autenticação</a> •
  <a href="#exemplos-de-uso">Exemplos de uso</a>
</div>

<br>

<h2 id="colaboradores">🤝 Colaboradores</h2>

| Nome                              | RM       |
|-----------------------------------|----------|
| Lucas Camargo de Souza            | RM551898 |
| Kenzo Schiavone Inoue dos Santos  | RM99890  |
| Pedro Daniluz                     | RM97697  |
| Roberto Tetsuo Tagashira          | RM551838 |
| Sofia Barbosa de Souza            | RM552298 |


<h2 id="funcionalidades">⚙️ Funcionalidades</h2> 

- Cadastro de leituras de sensores (POST <code>/api/readings</code>)
- Listagem de todas as leituras (GET <code>/api/readings</code>)
- Consulta de leituras por sensor (GET <code>/api/readings/{sensorId}</code>)
- Registro e autenticação de usuários (JWT)
- Proteção de rotas com token JWT

<br>

<h2 id="estrutura-do-projeto">🗂️ Estrutura do Projeto</h2>

```plaintext
src/
  main/
    java/
      com/fiap/airlogic/api/
        AirLogicApiApplication.java
        config/
          DataInitializer.java
        controller/
          ReadingController.java
          AuthController.java
        model/
          Reading.java
          User.java
        repository/
          ReadingRepository.java
          UserRepository.java
        security/
          JwtService.java
          JwtAuthenticationFilter.java
          SecurityConfig.java
    resources/
      application.properties
```

<br>

<h2 id="como-executar">▶️ Como executar</h2>

1. **Pré-requisitos:**  
   - Java 21+  
   - Maven  
   - Servidor MySQL rodando localmente  (consulte a <a href="#mysql">próxima seção</a>)
   - API client (curl, Postman ou Insomnia)

2. **Clone do Repositório**

    ```bash
    git clone https://github.com/pedrodaniluz/airlogic-api.git
    cd airlogic-api
    ```
    
3. **Build do projeto**

   ```bash
   ./mvnw clean install
   ```

4. **Executar a aplicação**

   ```bash
   ./mvnw spring-boot:run
   ```

A aplicação estará disponível em:

```
http://localhost:8080
```

⚠️ Um DataInitializer é executado automaticamente ao subir a aplicação, populando o banco de dados MySQL com exemplos de leituras de sensores para facilitar os testes.

<br>

<h2 id="mysql">🛢️ Configuração do MySQL</h2>

A aplicação utiliza um banco **MySQL** chamado `airlogic_mobile`, com o usuário padrão `airlogic_mobile_adm`.

### Comandos para criar o banco e o usuário

```sql
CREATE DATABASE airlogic_mobile;
CREATE USER 'airlogic_mobile_adm'@'%' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON airlogic_mobile.* TO 'airlogic_mobile_adm'@'%';
FLUSH PRIVILEGES;
```

### Configuração no `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/airlogic_mobile?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&characterEncoding=UTF-8
spring.datasource.username=airlogic_mobile_adm
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=update
```

*(opcional)* — se quiser usar outro usuário ou senha, basta alterar os campos  
`spring.datasource.username` e `spring.datasource.password` no arquivo `application.properties`.

<br>

<h2 id="autenticacao">🔐 Autenticação e JWT</h2>

Esta API utiliza **autenticação baseada em JWT (JSON Web Token)**.  
Antes de acessar qualquer rota protegida, é necessário **registrar um usuário** e **obter um token** de autenticação.

### 1️⃣ Registrar um novo usuário
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "usuario",
  "password": "senha"
}
```

### 2️⃣ Fazer login e obter o token JWT
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "usuario",
  "password": "senha"
}
```

A resposta trará um token:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 3️⃣ Usar o token nas requisições protegidas
Envie o token no cabeçalho `Authorization`:
```http
Authorization: Bearer SEU_TOKEN_AQUI
```

Exemplo:
```bash
curl -H "Authorization: Bearer SEU_TOKEN_AQUI" http://localhost:8080/api/readings
```

<br>

<h2 id="exemplos-de-uso">📚 Exemplos de uso</h2>

### Criar uma leitura

```http
POST /api/readings
Content-Type: application/json
Authorization: Bearer SEU_TOKEN_AQUI

{
  "sensorId": "Compressor",
  "value": 5.3,
  "timestamp": "2024-09-21T12:00:00"
}
```

### Listar todas as leituras

```http
GET /api/readings
Authorization: Bearer SEU_TOKEN_AQUI
```

### Buscar leituras por sensor

```http
GET /api/readings/sensor-01
Authorization: Bearer SEU_TOKEN_AQUI
```
