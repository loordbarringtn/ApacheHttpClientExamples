import org.apache.http.NameValuePair;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestClient {

    public static CloseableHttpResponse getRequest (String url) throws IOException {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(url); //http get request (create get connection with particular url)
            return httpClient.execute(httpget);
    }

    public static CloseableHttpResponse getRequestEndpoint (String endpoint) throws IOException, URISyntaxException {
        URI uri = new URIBuilder("https://reqres.in/" + endpoint).build();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(uri); //http get request (create get connection with particular url)
        return httpClient.execute(httpget);
    }

    public static String getValueByJPath(JSONObject jsonResponse, String jsonPath){
        Object obj = jsonResponse;
        for(String s : jsonPath.split("/"))
            if(!s.isEmpty())
                if(!(s.contains("[") || s.contains("]")))
                    obj = ((JSONObject) obj).get(s);
                else if(s.contains("[") || s.contains("]"))
                    obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
        return obj.toString();
    }









}
