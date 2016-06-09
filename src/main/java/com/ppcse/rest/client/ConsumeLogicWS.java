/**
 * 
 */
package com.ppcse.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.client.Client;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;

/**
 * @author Anand
 *
 */
public class ConsumeLogicWS {

	String userName = "logicAcc";
	String password = "l0g1C@cc";
	String host = "http://cfa.log.rpt.shcc.com.au/Service/ActivityCSV?startId=1";

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try {
			ConsumeLogicWS c = new ConsumeLogicWS();
			c.logicWS();
			if (false)
				c.callWS();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void logicWS() throws IOException
	{
		Credentials credentials = new UsernamePasswordCredentials(userName, password);
		DefaultHttpClient client = new DefaultHttpClient();
		client.getCredentialsProvider().setCredentials(AuthScope.ANY, credentials);
		ClientExecutor exec = new ApacheHttpClient4Executor(client);
		ConsumeLogicWS prox = ProxyFactory.create(ConsumeLogicWS.class, host, exec);
		prox.callWS();
	}

	public void callWS() throws IOException
	{
		String url = "http://cfa.log.rpt.shcc.com.au/Service/ActivityCSV?startId=1";

		// url = RMS_URL + url + "username=" + loginBean.getUserName() +
		// "&password=" + loginBean.getPassword();
		System.out.println("Login RMS URL - " + url);
		// String encodedURL = URLEncoder.encode(url,"ISO-8859-1");
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is POST
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		if (con.getResponseCode() != 200) {
			// LOG.fatal("Failed : HTTP Error code is 200 ");
			throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
		}
		// InputStream jsonInputStream = con.getInputStream();

		BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

		String output, jsonStr = null;
		System.out.println("Output from Server .... \n" + con.getResponseMessage() + con.getContentEncoding());

		while ((output = br.readLine()) != null) {
			System.out.println(output);
			jsonStr = output;

		}
		System.out.println("output...");
		System.out.println(jsonStr);
		// userBean =
		// convertJsonInputStream(IOUtils.toString(jsonInputStream,"UTF-8"));
		// convertJsonInputStream(jsonStr, loginBean);

		con.disconnect();
	}

}
