
<div align="center">

# Air Logic API

API REST para gerenciamento e consulta de leituras de sensores, desenvolvida com Spring Boot, JPA e banco de dados H2.

</div>

<br>

<div align="center">
  <a href="#Colaboradores">Colaboradores</a> •
  <a href="#funcionalidades">Funcionalidades</a> •
  <a href="#estrutura-do-projeto">Estrutura do Projeto</a> •
  <a href="#como-executar">Como executar</a> •
  <a href="#exemplos-de-uso">Exemplos de uso</a>
</div>

<br>

## 🤝 Colaboradores

| Nome                              | RM       |
|-----------------------------------|----------|
| Lucas Camargo de Souza            | RM551898 |
| Kenzo Schiavone Inoue dos Santos  | RM99890  |
| Pedro Daniluz                     | RM97697  |
| Roberto Tetsuo Tagashira          | RM551838 |
| Sofia Barbosa de Souza            | RM552298 |


## ⚙️ Funcionalidades

- Cadastro de leituras de sensores (POST <code>/api/readings</code>)
- Listagem de todas as leituras (GET <code>/api/readings</code>)
- Consulta de leituras por sensor (GET <code>/api/readings/{sensorId}</code>)

<br>

## 🗂️ Estrutura do Projeto

```plaintext
src/
  main/
    java/
      com/fiap/airlogic/api/
        AirLogicApiApplication.java
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

## ▶️ Como executar

1. **Pré-requisitos:** Java 21+ e Maven.
2. **Build do projeto:**

   ```bash
   ./mvnw clean install
   ```

3. **Executar a aplicação:**

   ```bash
   ./mvnw spring-boot:run
   ```

<br>

## 📚 Exemplos de uso

### Criar uma leitura

```http
POST /api/readings
Content-Type: application/json

{
  "sensorId": "sensor-01",
  "value": 23.5,
  "timestamp": "2024-06-01T12:00:00"
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
