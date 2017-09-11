/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.fcm;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author rober
 */
public class PushFCMNotification {

    public final static String AUTH_KEY_FCM = "AIzaSyCakiGah18Sw3aejnyREc-JbQ0-G9fFdWw";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

    public static void PushFCMNotification(String userDeviceIdKey, String titulo, String mensaje) throws Exception {
        String authKey = AUTH_KEY_FCM; // You FCM AUTH key
        String FMCurl = API_URL_FCM;

        URL url = new URL(FMCurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + authKey);
        conn.setRequestProperty("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        //json.put("to", "eLpabhs4Wbk:APA91bENwU-1iqJAfCbSNZXvO_PDZGAvmnJfjCNslRfxlol9SPmWdyQOWK_JKKZvPnRps8ME9HTzuN2jqRzUqPd8ZkSuZCG6y-dDoJexa4CrUzjwnlgVT4fp_9gal0FtK0NXDWjVVywm");
        json.put("to", userDeviceIdKey);
        JSONObject info = new JSONObject();
        info.put("title", titulo); // Notification title
        info.put("body", mensaje); // Notification body
        info.put("sound","default");
        json.put("notification", info);
        json.put("priority", "high");
         
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(json.toString());
        wr.flush();
        conn.getInputStream();
    }
}
