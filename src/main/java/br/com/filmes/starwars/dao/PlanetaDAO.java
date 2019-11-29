package br.com.filmes.starwars.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.filmes.starwars.entities.Planeta;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class PlanetaDAO {

    String host = "localhost";
    int    port = 27017;
    String dbName = "b2wdb";
	
	WriteResult result = null;
    DB db = null; 
	DBCollection col = null;
	DBObject doc = null;
	DBObject query = null;
	DBCursor cursor = null;
	
	public Planeta create(Planeta planeta) throws Exception{
      System.out.println("Dentro do DAO - Metodo Create 1 = "+planeta.getNome());
      Planeta _planeta = getById(planeta.getId()); 
      if (_planeta==null){
          _planeta = getByNome(planeta.getNome()); 
          if (_planeta==null){
        	  MongoClient mongo = new MongoClient(host, port);
              db = mongo.getDB(dbName);
        	  col = db.getCollection("planetas");
              doc = createDBObject(planeta);
        	  result = col.insert(doc);
        	  mongo.close();
          }else{
              System.out.println("O Nome "+planeta.getNome()+" já existe. Cadastre outro");
        	  throw new Exception("O Nome "+planeta.getNome()+" já existe. Cadastre outro");
          }
      }else{
          System.out.println("O ID "+planeta.getId()+" já existe. Cadastre outro");
    	  throw new Exception("O ID "+planeta.getId()+" já existe. Cadastre outro");
      }
      return planeta;
	}

	public void delete(Long id) throws Exception{
      System.out.println("Dentro do DAO - Metodo delete ID = "+id);
      Planeta planeta = getById(id); 
      if (planeta!= null){
        MongoClient mongo = new MongoClient(host, port);
        db = mongo.getDB(dbName);
        col = db.getCollection("planetas");
        col.remove(new BasicDBObject("_id", id));
    	mongo.close();
      }else{
          System.out.println("O ID "+id+" não foi encontrado para exclusão.");
    	  throw new Exception("O ID "+id+" não foi encontrado para exclusão.");
      }
	}

	public List<Planeta> list() {
      System.out.println("Dentro do DAO - Metodo list");
      MongoClient mongo = new MongoClient(host, port);
      db = mongo.getDB(dbName);
      col = db.getCollection("planetas");
      query = BasicDBObjectBuilder.start().get();
      System.out.println("Executando a query");
      cursor = col.find(query);
      List<Planeta> planetas = new ArrayList<Planeta>();
      Planeta planeta = null;
      while(cursor.hasNext()){
    	planeta = new Planeta();
    	DBObject planetaDocBuilder = cursor.next();  
    	planeta = createPlaneta(planetaDocBuilder);
    	planetas.add(planeta);
        System.out.println("populou array");
      }
	  mongo.close();
      return planetas;

	}
	
	public Planeta getById(Long id) {
      System.out.println("Dentro do DAO - Metodo getById");
      MongoClient mongo = new MongoClient(host, port);
      db = mongo.getDB(dbName);
      col = db.getCollection("planetas");
      query = BasicDBObjectBuilder.start().add("_id", id).get();
      cursor = col.find(query);
      Planeta planeta = null;
      if(cursor.hasNext()){
    	DBObject planetaDocBuilder = cursor.next();  
    	planeta = createPlaneta(planetaDocBuilder);
      }
	  mongo.close();
      return planeta;
	}	

	public Planeta getByNome(String nome) {
     System.out.println("Dentro do DAO - Metodo getByNome = "+nome);
     MongoClient mongo = new MongoClient(host, port);
     db = mongo.getDB(dbName);
     col = db.getCollection("planetas");
     query = BasicDBObjectBuilder.start().add("nome", nome).get();
     System.out.println("Executando a query");
     cursor = col.find(query);
     Planeta planeta = null;
     if(cursor.hasNext()){
	   	planeta = new Planeta();
	   	DBObject planetaDocBuilder = cursor.next();  
        System.out.println("Dentro do DAO - Metodo getByNome - encontrado planeta = "+planetaDocBuilder.get("nome"));
    	planeta = createPlaneta(planetaDocBuilder);
     }
     mongo.close();
     return planeta;
	}

	private static DBObject createDBObject(Planeta planeta) {
      System.out.println("Dentro do DAO - Metodo createDBObject = "+planeta.getNome());
      BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
      docBuilder.append("_id", planeta.getId());
      docBuilder.append("nome", planeta.getNome());
      docBuilder.append("clima", planeta.getClima());
      docBuilder.append("terreno", planeta.getTerreno());
      return docBuilder.get();
	}

	private static Planeta createPlaneta(DBObject planetaDBObj) {
      System.out.println("Dentro do DAO - Metodo createPlaneta = "+planetaDBObj.get("nome"));
      Planeta planeta = new Planeta();
   	  planeta.setId(Long.parseLong(planetaDBObj.get("_id").toString()));
  	  planeta.setNome((String)planetaDBObj.get("nome"));
  	  planeta.setClima((String)planetaDBObj.get("clima"));
  	  planeta.setTerreno((String)planetaDBObj.get("terreno"));
      return planeta;
	}
}
