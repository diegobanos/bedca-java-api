package bedca_java_api.bedca_java_api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {
	private String apiUrl = "http://www.bedca.net/bdpub/procquery.php";
	
	public String getFoodGroups() {
		String xml = XMLRequests.getFoodGroupsXML();
		return processRequest(xml);
	}
	
	public String getFoodsInGroup(int groupId) {
		String xml = XMLRequests.getFoodsInGroupXML(groupId);
		return processRequest(xml);
	}
	
	public String getFood(int foodId) {
		String xml = XMLRequests.getFoodXML(foodId);
		return processRequest(xml);
	}
	
	private String processRequest(String xml) {
		HttpURLConnection connection = null;
		
		try {
			URL url = new URL(apiUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "text/xml");
			connection.setRequestProperty("Connection", "close");
			connection.setUseCaches(false);
			connection.setDoOutput(true);
			
			//Send Request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(xml);
			wr.close();
			
			//Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuffer response = new StringBuffer();
		    String line;
		    
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    
		    rd.close();
		    
		    return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return  null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
