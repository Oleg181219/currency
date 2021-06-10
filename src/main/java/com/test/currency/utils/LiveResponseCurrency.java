package com.test.currency.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class LiveResponseCurrency {

    //    public static final String ACCESS_KEY = "f5b338e9b91ee6e8fd35c6c636cc9172";
//    public static final String BASE_URL = "http://api.currencylayer.com/";
//    public static final String ENDPOINT = "live";
    static CloseableHttpClient httpClient = HttpClients.createDefault();
    private JSONObject exchangeRates;

    public JSONObject sendLiveRequest(String requestToBase) {
//         = new JSONObject();
        HttpGet get = new HttpGet(requestToBase);

        try {
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            exchangeRates = new JSONObject(EntityUtils.toString(entity));
            response.close();
            httpClient.close();


//            Date timeStampDate = new Date((long)(exchangeRates.getLong("timestamp")*1000));
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
//            String formattedDate = dateFormat.format(timeStampDate);
//
//            var xxx = "1 " + exchangeRates.getString("source") + " in GBP : " + exchangeRates.getJSONObject("quotes").getDouble("USDGBP") + " (Date: " + formattedDate + ")"
////            System.out.println("1 " + exchangeRates.getString("source") + " in GBP : " + exchangeRates.getJSONObject("quotes").getDouble("USDGBP") + " (Date: " + formattedDate + ")");
//            System.out.println("\n");
//            System.out.println(xxx);
//
//
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return exchangeRates;
    }

    // sendLiveRequest() function is executed
//    public static void main(String[] args) throws IOException{
//        sendLiveRequest();
//        httpClient.close();
//        new BufferedReader(new InputStreamReader(System.in)).readLine();
//    }
}