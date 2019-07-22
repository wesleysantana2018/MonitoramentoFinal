package br.com.ebix.monitoramento.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class EventoService {

	// CONSULTA POR STATUS
	public static String getStatus(String url) throws IOException {

		String result = "";
		int code = 200;

		try {
			URL siteURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(3000);
			connection.connect();

			code = connection.getResponseCode();

			if (code == 200) {
				result = "Sucesso";

			} else {
				result = "Erro - " + code;

			}

		} catch (Exception e) {
			result = "Fora";

		}
		// System.out.println(url + "\t\tStatus:" + result);
		return result;
	}

}
