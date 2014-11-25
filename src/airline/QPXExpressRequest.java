package airline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class QPXExpressRequest
{

	
	private String origin;
	private String destination;
	private Date date;
	private int adultCount;
	private int solutions;
	private boolean refundable;
	
	public void setOrigin(String origin)
	{
		this.origin = origin;
	}
	
	public void setDestination(String destination)
	{
		this.destination = destination;
	}
	
	public void setDate(Date date)
	{
		this.date = date;
		
	}
	
	public void setAdultCount(int count)
	{
		this.adultCount = count;
	
	}
	
	public void setSolutions(int solutions)
	{
		this.solutions = solutions;
	}
	
	public void setRefundable(boolean b)
	{
		this.refundable = b;
	}
	
	public String send()
	{
		
		
		return null;
	}
	
	
	public ObservableList<Solution> getResponse()
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://www.googleapis.com/qpxExpress/v1/trips/search?key=AIzaSyBxYS0Q6TUnvCwAMMR7qemiOP_1BkfBx9w");
		//HttpPost post = new HttpPost("http://localhost:8080/login");
		post.setHeader("Referer", "http://localhost");
		CloseableHttpResponse response = null;
		
		try 
		{
			StringEntity requestEntity = new StringEntity(this.toJson());
			requestEntity.setContentType("application/json");
			post.setEntity(requestEntity);
			response = httpclient.execute(post);	
			
	
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuilder jsonResponse = new StringBuilder();
			String line = "";
			while ((line = rd.readLine()) != null)
			{
				jsonResponse = jsonResponse.append(line);
			} 
			rd.close();
			
			System.out.println(jsonResponse.toString());
			EntityUtils.consume(response.getEntity());
			return getResponse(jsonResponse.toString());
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(response != null)
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
		
	}
	
	private static ObservableList<Solution> getResponse(String json)
	{
		JsonParser jsonParser = new JsonParser();
		JsonObject tree = jsonParser.parse(json).getAsJsonObject();
		
		JsonObject metaData = tree.get("trips").getAsJsonObject().get("data").getAsJsonObject();
		JsonArray airportMetaData = metaData.get("airport").getAsJsonArray();
		JsonArray cityMetaData = metaData.get("city").getAsJsonArray();
		JsonArray aircraftMetaData = metaData.get("aircraft").getAsJsonArray();
		JsonArray airlineMetaData = metaData.get("carrier").getAsJsonArray();
		
		JsonArray tripOptions = tree.get("trips").getAsJsonObject().get("tripOption").getAsJsonArray(); //A list of priced itinerary solutions to the QPX Express query.
		
		
		
		ObservableList<Solution> solutions = FXCollections.observableArrayList();
		Iterator<JsonElement> iterator = tripOptions.iterator();
		while(iterator.hasNext())								//loop through each solution
		{
			JsonObject jsonObject = iterator.next().getAsJsonObject();
			JsonArray sliceArray = jsonObject.get("slice").getAsJsonArray(); 	//The slices that make up this trip's itinerary.
			JsonObject leg = sliceArray.get(0).getAsJsonObject().get("segment").getAsJsonArray().get(0).getAsJsonObject().get("leg").getAsJsonArray().get(0).getAsJsonObject();
			Solution s = new Solution();
			
			/** HEADER FOR SOLUTION **/
			s.setSaleTotal(jsonObject.get("saleTotal").getAsString());
			s.setdepartureTime(leg.get("departureTime").getAsString());
			System.out.printf("%-25.25s ",s.getDepatureTime());
			s.setOriginAriportCode(leg.get("origin").getAsString());
			System.out.printf("%-4.4s ",s.getOriginAirportCode());
			s.setOriginAirport(getAirport(s.getOriginAirportCode(),airportMetaData));
			System.out.printf("%-30.30s ",s.getOriginAirport());
			s.setOriginCityCode(getCityCodeFromAirportCode(s.getOriginAirportCode(),airportMetaData));
			System.out.printf("%-5.5s ",s.getOriginCityCode());
			s.setOriginCity(getCity(s.getOriginCityCode(),cityMetaData));
			System.out.printf("%-15.15s ",s.getOriginCity());
			System.out.printf("%-10.10s ",s.getSaleTotal());
			/** END OF HEADER SECTION **/
			
			
			
			JsonArray segment = sliceArray.get(0).getAsJsonObject().get("segment").getAsJsonArray();
			
			Iterator<JsonElement> sliceIterator = segment.iterator();
			
			Route route = null;
			while(sliceIterator.hasNext())		//loop through each airplane route in this solution
			{
				JsonObject slice = sliceIterator.next().getAsJsonObject();
				JsonObject sliceLeg = slice.get("leg").getAsJsonArray().get(0).getAsJsonObject();
				//JsonObject flightInfo = segmentIterator.			
				
				route = new Route();
				route.setAircraftCode(sliceLeg.get("aircraft").getAsString());							// AIRCRAFT CODE
				System.out.printf("%-3.3s   ",route.getAircraftCode());
				route.setAircraft(getAircraft(route.getAircraftCode(),aircraftMetaData));				//AIRCRAFT NAME
				System.out.printf("%-20.20s ",route.getAircraft());
				route.setDepartureTime(sliceLeg.get("departureTime").getAsString());					//DEPARTURE TIME
				System.out.printf("%-30.30s ",route.getDepartureTime());
				route.setArrivalTime(sliceLeg.get("arrivalTime").getAsString());						//ARRIVAL TIME
				System.out.printf("%-30.30s ",route.getArrivalTime());
				route.setAirlineCode(segment.get(0).getAsJsonObject().get("flight").getAsJsonObject().get("carrier").getAsString());//AIRLINE CODE
				System.out.printf("%-3.3s   ", route.getAirlineCode());
				route.setAirlineName(getAirline(route.getAirlineCode(),airlineMetaData));				//AIRLINE NAME
				System.out.printf("%-15.15s   ", route.getAirlineName());
				route.setAirlineNumber(segment.get(0).getAsJsonObject().get("flight").getAsJsonObject().get("number").getAsString());//AIRLINE NUMBER
				System.out.printf("%-6.6s   ",route.getAirlineNumber());
				route.setSourceAirportCode(sliceLeg.get("origin").getAsString());						//SOURCE AIRPORT CODE
				System.out.printf("%-3.3s   ",route.getSourceAirportCode());
				route.setSourceAirport(getAirport(route.getSourceAirportCode(),airportMetaData));		//SOURCE AIRPORT NAME
				System.out.printf("%-30.30s   ",route.getSourceAirport());
				route.setDestinationAirportCode(sliceLeg.get("destination").getAsString());				//DESTINATION AIRPORT CODE
				System.out.printf("%-3.3s   ",route.getDestinationAirportCode());
				route.setDestinationAirport(getAirport(route.getDestinationAirportCode(),airportMetaData));//DESTINATION AIRPORT NAME
				System.out.printf("%-30.30s   ",route.getDestinationAirport());
				
				route.setSourceAirportCityCode(getCityCodeFromAirportCode(route.getSourceAirportCode(),airportMetaData)); //SOURCE AIRPORT CITY CODE
				route.setSourceAirportCity(getCity(route.getSourceAirportCityCode(),cityMetaData));		//SOURCE AIRPORT CITY NAME
				route.setDestinationAirportCityCode(getCityCodeFromAirportCode(route.getDestinationAirportCode(),airportMetaData)); //DESTINATION AIRPORT CITY CODE
				route.setDestinationAirportCity(getCity(route.getDestinationAirportCityCode(),cityMetaData));//DESTINATION AIRPORT CITY NAME
				s.getRoutes().add(route);
			}
			solutions.add(s);
			/** START OF FOOTER SECTION **/
			s.setArrivalTime(route.getArrivalTime());
			s.setDestinationCityCode(route.getDestinationAirportCityCode());
			s.setDestinationCity(route.getDestinationAirportCity());
			s.setDestinationAirportCode(route.getDestinationAirportCode());
			s.setDestinationAirport(route.getDestinationAirport());
			/** END OF FOOTER SECTION **/
			System.out.println();	
		}
		
		
		
		
		
		return solutions;
	}
	
	private static String getCity(String code,JsonArray cityMetaData)
	{
		Iterator<JsonElement> iterator = cityMetaData.iterator();
		while(iterator.hasNext())
		{
			JsonObject jsonObject = iterator.next().getAsJsonObject();
			
			if(jsonObject.get("code").getAsString().equals(code))
			{
				return jsonObject.get("name").getAsString();
			}
			
			
		}
		
		return null;
	}

	private static String getCityCodeFromAirportCode(String code,JsonArray airportMetaData)
	{
		Iterator<JsonElement> iterator = airportMetaData.iterator();
		while(iterator.hasNext())
		{
			JsonObject jsonObject = iterator.next().getAsJsonObject();
			
			if(jsonObject.get("code").getAsString().equals(code))
			{
				return jsonObject.get("city").getAsString();
			}
			
			
		}
		
		return null;
	}

	
	
	private static String getAirport(String code,JsonArray airportMetaData)
	{
		
		Iterator<JsonElement> iterator = airportMetaData.iterator();
		while(iterator.hasNext())
		{
			JsonObject jsonObject = iterator.next().getAsJsonObject();
			
			if(jsonObject.get("code").getAsString().equals(code))
			{
				return jsonObject.get("name").getAsString();
			}
			
			
		}
		
		return null;
	}

	private static String getAircraft(String aircraftCode,JsonArray aircraftMetaData)
	{
		Iterator<JsonElement> iterator = aircraftMetaData.iterator();
		while(iterator.hasNext())
		{
			JsonObject jsonObject = iterator.next().getAsJsonObject();
			
			if(jsonObject.get("code").getAsString().equals(aircraftCode))
			{
				return jsonObject.get("name").getAsString();
			}
			
			
		}
		
		return null;
		
		
	}
	
	private static String getAirline(String airlineCode,JsonArray airlineMetaData)
	{
		Iterator<JsonElement> iterator = airlineMetaData.iterator();
		while(iterator.hasNext())
		{
			JsonObject jsonObject = iterator.next().getAsJsonObject();
			
			if(jsonObject.get("code").getAsString().equals(airlineCode))
			{
				return jsonObject.get("name").getAsString();
			}
			
			
		}
		
		return null;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String toJson()
	{
		return "{" 							+
				"\"request\":"					+
				"{"							+
				"\"slice\": ["					+
				"{"							+
				"\"origin\": "				+			
				"\"" + origin + "\""			+
				","							+
				"\"destination\": "				+
				"\"" + destination + "\", "		+
				"\"date\": "					+
				"\"" + date.toString() + "\""	+
				"}"							+
				"],"						+
				"\"passengers\": {"				+
				"\"adultCount\": "				+
				"\"" + adultCount + "\""		+
				"},"						+
				"\"solutions\": " 				+
				"\"" + solutions + "\","		+
				"\"refundable\": "				+
				"\"" + refundable + "\""		+
				"}"							+
				"}";
		
		
	}


}
