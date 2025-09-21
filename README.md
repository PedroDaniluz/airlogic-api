
<div align="center">

# Air Logic API

API REST para gerenciamento e consulta de leituras de sensores, desenvolvida com Spring Boot, JPA e banco de dados H2. Backend para a aplicação mobile <a href="https://github.com/PedroDaniluz/airlogic">AirLogic</a>.

</div>

<br>

<div align="center">
  <a href="#colaboradores">Colaboradores</a> •
  <a href="#funcionalidades">Funcionalidades</a> •
  <a href="#estrutura-do-projeto">Estrutura do Projeto</a> •
  <a href="#como-executar">Como executar</a> •
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
        model/
          Reading.java
        repository/
          ReadingRepository.java
    resources/
      application.properties
```

<br>

<h2 id="como-executar">▶️ Como executar</h2>

1. **Pré-requisitos:** Java 21+, Maven, Alguma API Client como curl, Postman ou Insomnia.
2. **Clone do Repositório**

    ```bash
    git clone https://github.com/pedrodaniluz/airlogic-api.git
    cd airlogic-api
    ```
    
3. **Build do projeto:**

   ```bash
   ./mvnw clean install
   ```

4. **Executar a aplicação:**

   ```bash
   ./mvnw spring-boot:run
   ```
   
A aplicação estará disponível em:

```
http://localhost:8080/api/readings
```

⚠️ Um DataInitializer é executado automaticamente ao subir a aplicação, populando o banco de dados H2 com exemplos de leituras de sensores para facilitar os testes.

<br>

<h2 id="exemplos-de-uso">📚 Exemplos de uso</h2>

### Criar uma leitura

```http
POST /api/readings
Content-Type: application/json

{
  "sensorId": "Compressor",
  "value": 5.3,
  "timestamp": "2024-09-21T12:00:00"
}
```

### Listar todas as leituras

```http
GET /api/readings
```

### Buscar leituras por sensor

```http
GET /api/readings/sensor-01
```
