package com.oldschoolminecraft.client.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

import com.oldschoolminecraft.client.Client;

public class API {
	private static String get(String url) {
        try {
            String inputLine;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:78.0) Gecko/20100101 Firefox/78.0 Waterfox/78.12.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
	
	public static String getSkinURL(String username) {
        try {
            String uuid = new JSONObject(get("https://api.mojang.com/users/profiles/minecraft/" + username)).getString("id");
            JSONArray textures = new JSONObject(get("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid)).getJSONArray("properties");
            JSONObject texture = new JSONObject(new String(Base64.getDecoder().decode(textures.getJSONObject(0).getString("value").getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8));
            return texture.getJSONObject("textures").getJSONObject("SKIN").getString("url");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
	}
    
    public static String api_request(String endpoint, String ...parameters) {
        String base = Client.apiURL;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.length; ++i) {
            if (i == 0) {
                sb.append("?" + parameters[i]);
                continue;
            }
            sb.append("&" + parameters[i]);
        }
        String url = base + endpoint + sb.toString();
        return get(url);
    }
}
