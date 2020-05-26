![GitHub repo size](https://img.shields.io/github/repo-size/pedroBarata/starwars-api) ![GitHub](https://img.shields.io/github/license/pedroBarata/starwars-api) 
# Star Wars API

API REST do desafio da B2W feita em Java com Spring Boot.

## Installation
  
Esta aplicação utiliza [MongoDB](https://www.mongodb.com/) como banco de dados, 
logo, para subir o projeto necessita que o mesmo esteja instalado.  
    Além disso, é necessário o `maven` (automatizador de tarefas e dependências) 
e do `java` (JDK) para conseguir compilar e rodar o `jar` que será posteriormente criado para ser usado em produção.  
    Para os dois últimos, eu recomendo a utilização do [SDKMan](https://sdkman.io/) 
que configura e os instalará de forma rápida.

### Developer
Para subir em modo de desenvolvedor, basta executar o arquivo ```StarwarsapiApplication.java``` 
ou então, executar o comando pelo maven:
```bash
$ mvn spring-boot:run
```
Com o cmd na raíz do projeto.

### Production
Para rodar em produção, precisamos, primeiramente, executar o build da aplicação 
e após isso rodar o jar criado:
```bash
$ mvn package
$ jar tvf target/starwarsapi-0.0.1-SNAPSHOT.jar
```

## Usage
A aplicação subirá na porta `8080` e as requisições serão testadas utilizando o [PostMan](https://www.postman.com/) 
ou qualquer outro método para fazer requisições HTTP.

### Endpoints
Caso estiver rodando em desenvolvimento, usa-se o `localhost`, caso contrário, 
usa-se o IP do servidor em que a aplicação está sendo executada.  
Ao subir a aplicação, por default ela já vem com um planeta cadastrado:

 ```json
 {
   "id": "5ebc7a7a65c0bf7a292d900c",
   "name": "Tatooine",
   "climate": "arid", 
   "terrain": "temperate",
   "qtyFilms": 5
 }
 ```

Requisições aceitas: 
* Deletar um planeta (método DELETE)
    * Endpoint `http://<ip_da_aplicação>:8080/api/planets/<id_do_planeta>`;
* Criar um planeta (método POST)
    * Endpoint `http://<ip_da_aplicação>:8080/api/planets`;
     Passando um objeto no formato JSON, como no exemplo abaixo:
   
 ```json
 {
   "name": "Coruscant",
   "climate": "arid", 
   "terrain": "desert"
 }
 ```

* Buscar um planeta pelo ID (método GET)
    * Endpoint `http://<ip_da_aplicação>:8080/api/planets/<id_do_planeta>`;
* Buscar um planeta pelo nome (método GET)
    * Endpoint `http://<ip_da_aplicação>:8080/api/planets/find?name=<nome_do_planeta>`;
* Listar todos os planetas (método GET), com paginação, onde é passado o nº de páginas e nº de itens por página: 
    * Endpoint `http://localhost:8080/api/planets?page=<num_page>&count=<num_items>`;

## Tests
Para rodar os testes em modo de desenvolvimento, usa-se:
```bash
$ mvn test
```
Ao executar o comando de `build` do maven, ele também executa a rotina de testes antes de gerar o `jar`.

## Task list
* [x] Fazer os testes da aplicação

## License
[MIT](https://choosealicense.com/licenses/mit/)
