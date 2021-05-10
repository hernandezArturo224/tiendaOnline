package tienda.arturo.hernandez.utilidades;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import tienda.arturo.hernandez.models.Provincias;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Util {
	
	public static Provincias[] getProvincias(){
		String cadenaJson = leerUrl("https://raw.githubusercontent.com/IagoLast/pselect/master/data/provincias.json");

		Provincias[] prov = new Gson().fromJson(cadenaJson, Provincias[].class);
		Arrays.sort(prov);
		
		return prov;
	}
	
	
	public static String leerUrl(String sUrl) {
		String output = "";
		try {
			URL url = new URL(sUrl);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			// conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				// si la respuesta del servidor es distinta al codigo 200 lanzaremos una
				// Exception
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			// creamos un StringBuilder para almacenar la respuesta del web service
			StringBuilder sb = new StringBuilder();
			int cp;
			while ((cp = br.read()) != -1) {
				sb.append((char) cp);
			}
			// en la cadena output almacenamos toda la respuesta del servidor
			output = sb.toString();
			// System.out.println(output);

			conn.disconnect();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return output;
	}
	

}
