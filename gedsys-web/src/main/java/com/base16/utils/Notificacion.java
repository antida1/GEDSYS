/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.utils;

/**
 *
 * @author rober
 */
public class Notificacion {
    
    private void send(){
        
    }
    
}

/*
public final static String AUTH_KEY_FCM = "xyxyxyxxyxyxyxyxyxyxyxy";
public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

// userDeviceIdKey is the device id you will query from your database

public static void pushFCMNotification(String userDeviceIdKey) throws Exception{

   String authKey = AUTH_KEY_FCM; // You FCM AUTH key
   String FMCurl = API_URL_FCM; 

   URL url = new URL(FMCurl);
   HttpURLConnection conn = (HttpURLConnection) url.openConnection();

   conn.setUseCaches(false);
   conn.setDoInput(true);
   conn.setDoOutput(true);

   conn.setRequestMethod("POST");
   conn.setRequestProperty("Authorization","key="+authKey);
   conn.setRequestProperty("Content-Type","application/json");

   JSONObject json = new JSONObject();
   json.put("to",userDeviceIdKey.trim());
   JSONObject info = new JSONObject();
   info.put("title", "Notificatoin Title"); // Notification title
   info.put("body", "Hello Test notification"); // Notification body
   json.put("notification", info);

   OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
   wr.write(json.toString());
   wr.flush();
   conn.getInputStream();
}
*/