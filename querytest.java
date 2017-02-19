package org.json.poc;

// necessary components are imported
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class querytest{

    static int n = 10;
    static double[][] a = new double[n][n];
    static int[] vert = new int[n];
    static int[][] paths = new int[n][n];
    static ArrayList<Integer> Path = new ArrayList<Integer>();

    // essential URL structure is built using constants
    public static final String ACCESS_KEY = "83aa18891e5404dce6b001446fb3d968";
    public static final String BASE_URL = "http://apilayer.net/api/";
    public static final String ENDPOINT = "live";
    static String source = null;

    // this object is used for executing requests to the (REST) API
    static CloseableHttpClient httpClient = HttpClients.createDefault();

    
    /**
     * 
     * Notes:
     * 
     * A JSON response of the form {"key":"value"} is considered a simple Java JSONObject.
     * To get a simple value from the JSONObject, use: <JSONObject identifier>.get<Type>("key");
     * 
     * A JSON response of the form {"key":{"key":"value"}} is considered a complex Java JSONObject.
     * To get a complex value like another JSONObject, use: <JSONObject identifier>.getJSONObject("key")
     * 
     * Values can also be JSONArray Objects. JSONArray objects are simple, consisting of multiple JSONObject Objects.
     * 
     * 
     */
     

    // sendLiveRequest() function is created to request and retrieve the data
    public static void sendLiveRequest(){

        // The following line initializes the HttpGet Object with the URL in order to send a request
        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY);
        try {
            CloseableHttpResponse response =  httpClient.execute(get);
            HttpEntity entity = response.getEntity();

            // the following line converts the JSON Response to an equivalent Java Object
            JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));
            
            System.out.println("Live Currency Exchange Rates");
            String [] currencyNames = new String[10];
            currencyNames[0] = "USD";
            currencyNames[1] = "CAD";
            currencyNames[2] = "CNY";
            currencyNames[3] = "GBP";
            currencyNames[4] = "EUR";
            currencyNames[5] = "CHF";
            currencyNames[6] = "AUD";
            currencyNames[7] = "ZAR";
            currencyNames[8] = "MXN";
            currencyNames[9] = "JPY";
            
/*            currencyRates[0][1] = 1.3909350;
            for (int i = 0; i < 10; i++){
            	for (int j = 0; j < 10; j++){
            		if (i == j){ currencyRates[i][i] = 1;}
            		else{
            			
            		}
            	}
            	
            }*/
            Date timeStampDate = new Date((long)(exchangeRates.getLong("timestamp")*1000)); 
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
            String formattedDate = dateFormat.format(timeStampDate);
            Random r = new Random();
            double rand = r.nextDouble() % 0.01;
            for (int i = 0; i < 10; i++){
            	for (int j = 0; j < 10; j++){
            		if (i == 0){
            			System.out.println(a[0][j]);
            			a[0][j] =  exchangeRates.getJSONObject("quotes").getDouble("USD" + currencyNames[j]);
	            		// System.out.println("1 " + exchangeRates.getString("source") + " in " + currencyNames[j] + " : " + exchangeRates.getJSONObject("quotes").getDouble("USD" + currencyNames[j]));
            		}
            		else
            		{
            			a[j][i] = a[0][j]/a[0][i];
            			rand = r.nextDouble() % 0.01;
            			if (r.nextBoolean()){
            				rand = (-1*rand);
            			}
            			a[j][i] += rand;
            		}
            	}
            }
            response.close();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } /*catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } */catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }


        // sendLiveRequest() function is executed
    public static void main(String[] args) throws IOException{
        sendLiveRequest();
        httpClient.close();

        for (int i = 0; i < 10; i++){
        	System.out.println();
        	for (int j = 0; j < 10; j++){
        		System.out.print(a[i][j]);
        	}
        }
    }
}