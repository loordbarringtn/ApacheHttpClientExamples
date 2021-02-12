import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

    public static CloseableHttpResponse postRequest (String url, String jsonBody) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("content-type", "applications/json");
        StringEntity stringEntity = new StringEntity(jsonBody);
        httpPost.setEntity(stringEntity);
        return httpClient.execute(httpPost);
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
