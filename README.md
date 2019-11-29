# Desafio tecnico

Ide para criação do projeto: Eclipse Juno

Ferramenta utilizada para realização dos teste - Postman

Banco de dados: MongoDB (Database: b2wdb, Collection: planetas)
	
Classe para start do serviço br.com.filmes.starwars.GerenciadorPlanetasApplication

No Postman:
Chamada para criação de um planetas

 http://localhost:8080/api/planetas/new
 
{
	"id": 1,
	"nome": "Alderaan",
	"clima": "Seco",
	"terreno": "Cheio de crateras"
}

Chamada para buscar todos os planetas cadastrados

 http://localhost:8080/api/planetas

Chamada para busca por ID, onde o 1 no final da url é o id a ser pesquisado

 http://localhost:8080/api/planetas/id/1

Chamada para busca por nome, onde a string Alderaan no final da url é o nome do planeta a ser consultado

 http://localhost:8080/api/planetas/nome/Alderaan

Chamada para exclusao de um planeta criado, onde o 1 no final da url é o id a ser excluído  
 
 http://localhost:8080/api/planetas/remove/103
 
Exemplo de nomes de paises que tiveram aparição em filmes:

Alderaan
Yavin IV
Hoth
Dagobah
Bespin
Endor
Naboo
Coruscant
Kamino
