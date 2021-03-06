package service;

import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;
import com.google.inject.Inject;
import play.data.FormFactory;
import play.libs.Json;
import play.libs.Json.*;  
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.db.*;
import play.db.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.inject.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class DatabaseService{

 private Database db;

  @Inject
  public DatabaseService(Database db) {
        this.db = db;
  }

  public CompletionStage<Integer> saveLocation(ObjectNode result) {
       return CompletableFuture.supplyAsync(() -> {
           return db.withConnection(connection -> {
               Statement stmt = connection.createStatement();
               stmt.execute("insert into Location values('"+result.get("username")+"','"+result.get("timestamp")+"','"+result.get("latitude")+"','"+result.get("longitude")+"','"+result.get("distance")+"','"+result.get("active")+"')");
	       stmt.close();
               return 1;
           });
       });
   }


  public CompletionStage<ObjectNode> getLocation(String username) {
       return CompletableFuture.supplyAsync(() -> {
           return db.withConnection(connection -> {
               ObjectNode param = Json.newObject();
    		ResultSet rs;
    		PreparedStatement ps = connection.prepareStatement("select username,timestamp,latitude,longitude,distance,active from Location where username=? and active=1");
    		ps.setString(1,"\"" + username + "\"");
    		rs = ps.executeQuery();
    		while (rs.next() ) {
			param.put("username", rs.getString("username").replace("\"", ""));
			param.put("timestamp", rs.getString("timestamp").replace("\"", ""));
			param.put("latitude", rs.getString("latitude").replace("\"", ""));
			param.put("longitude", rs.getString("longitude").replace("\"", ""));
			param.put("distance", rs.getDouble("distance"));
			param.put("active", Integer.valueOf(rs.getString("active").replace("\"", "")));
                return param;
   		 }
             return param;
           });
       });
   }

  public CompletionStage<Integer> deactivate(String username) {
       return CompletableFuture.supplyAsync(() -> {
           return db.withConnection(connection -> {
                ResultSet rs;
    		PreparedStatement ps = connection.prepareStatement("update Location set active=0 where username=? and active=1");
    		ps.setString(1,"\"" + username + "\"");
    		ps.executeUpdate();
		ps.close();
             return 1;
           });
       });
   }

  
}
