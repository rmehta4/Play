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
import javax.inject.*;
import service.LocationService;
import service.DatabaseService;
import java.util.concurrent.ExecutionException;
/*
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */

public class HomeController extends Controller {
   

    public Result index() {
        return ok(views.html.index.render());
    }
 
    @Inject
    public HomeController() {
        
    }

    @Inject
    DatabaseService databaseService;
    @Inject
    LocationService locationService;
    @Inject
    FormFactory formFactory;

    public Result handleupdates(){
    	 DynamicForm requestData = formFactory.form().bindFromRequest();
         ObjectNode param = Json.newObject();
         param.put("username", requestData.get("username"));
   	 param.put("timestamp", requestData.get("timestamp"));
	 param.put("latitude", requestData.get("latitude"));
	 param.put("longitude", requestData.get("longitude"));	
         ObjectNode result = Json.newObject();
	 ObjectNode response = Json.newObject();
         double totalDistance = 0.0;
	 try
	{
          result = databaseService.getLocation(requestData.get("username")).toCompletableFuture().get();
	  if(result.size()==0) {
		param.put("distance",0.0);
		param.put("active",1);
		databaseService.saveLocation(param);
		response.put("totalDistance",0.0);
		return ok(response);	
         }else{
	  databaseService.deactivate(requestData.get("username"));
          totalDistance = Double.valueOf(result.get("distance").toString());
          double distance = locationService.calculateDistanceInMeter(Double.valueOf(requestData.get("latitude").toString().replace("\"", "")),Double.valueOf(requestData.get("longitude").toString().replace("\"", "")),
         Double.valueOf(result.get("latitude").toString().replace("\"", "")),Double.valueOf(result.get("longitude").toString().replace("\"", "")));
          totalDistance += distance;
	  param.put("distance",totalDistance);
	  param.put("active",1);
	  databaseService.saveLocation(param);
	}
	}
	catch(InterruptedException ie){
	}
	catch(ExecutionException ee){
	}

         
         
	 response.put("totalDistance",totalDistance);
         return ok(response);
   }

}
