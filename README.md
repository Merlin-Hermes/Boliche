# Boliche
Projeto pessoal de contagem de pontos de boliche.

## Intuito do Projeto

- O intuito do projeto é criar um sistema que conte os pontos de uma partida de boliche, utilizando TDD.

## Como usar
 
- Para rodar o projeto, basta clonar o repositório e abrir o postman.
- Para marca os pontos você deve especificar o nome do jogador e a quantidade de pinos derrubados e qual frame ele está jogando.



## Tecnologias utilizadas

- Java
- Spring Boot
- JPA
- H2
- Maven
- Postman
- Git

## Endpoints

Para criar uma partida: 
 - POST: http://localhost:8080/partida

Para buscar o vencedor: 
- GET: http://localhost:8080/Partida

Para marca pontos:
- PUT: http://localhost:8080/jogador
