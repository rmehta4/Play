package controllers;

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
/*
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */

public class HomeController extends Controller {
    //@Inject
    //FormFactory ​formFactory;

    private Database db;
    private final static double AVERAGE_RADIUS_OF_EARTH_KM = 637100;

    public Result index() {
        return ok(views.html.index.render());
    }
 
    @Inject
    public HomeController(Database db) {
        this.db = db;
    }

   public CompletionStage<Integer> saveLocation(ObjectNode result) {
       return CompletableFuture.supplyAsync(() -> {
           return db.withConnection(connection -> {
               Statement stmt = connection.createStatement();
               stmt.execute("insert into Location values('"+result.get("username")+"','"+result.get("timestamp")+"','"+result.get("latitude")+"','"+result.get("longitude")+"')");
	       stmt.close();
               return 1;
           });
       });
   }

  	public static ObjectNode getLocation(Connection conn,String username) throws SQLException{
 	ObjectNode param = Json.newObject();
	try{
    		ResultSet rs;
    		PreparedStatement ps = conn.prepareStatement("select username,timestamp,latitude,longitude from Location where username=? limit 1");
    		ps.setString(1,username);
    		rs = ps.executeQuery();
    		while (rs.next() ) {
			param.put("username", rs.getString("username"));
			param.put("timestamp", rs.getString("timestamp"));
			param.put("latitude", rs.getString("latitude"));
			param.put("longitude", rs.getString("longitude"));
                return param;
   		 }
		}catch(SQLException e){
		}
		finally{
    	conn.close();
	}
    	return param;
  }

    public int calculateDistanceInMeter(double userLat, double userLng,
  		double venueLat, double venueLng) {

    double latDistance = Math.toRadians(userLat - venueLat);
    double lngDistance = Math.toRadians(userLng - venueLng);

    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
      + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat))
      * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
}

    @Inject
    FormFactory formFactory;
    public Result handleupdates() throws SQLException{
    	 DynamicForm requestData = formFactory.form().bindFromRequest();
         ObjectNode param = Json.newObject();
         param.put("username", requestData.get("username"));
   	 param.put("timestamp", requestData.get("timestamp"));
	 param.put("latitude", requestData.get("latitude"));
	 param.put("longitude", requestData.get("longitude"));	
	 saveLocation(param);
         Connection conn = db.getConnection();
         ObjectNode result = getLocation(conn,"a2");
	 int distance = calculateDistanceInMeter(Double.valueOf(requestData.get("latitude").toString().replace("\"", "")),Double.valueOf(requestData.get("longitude").toString().replace("\"", "")),
         Double.valueOf(result.get("latitude").toString().replace("\"", "")),Double.valueOf(result.get("longitude").toString().replace("\"", "")));
         System.out.println(distance);
	 result.put("distance",distance);
         return ok(result);
   }

}
