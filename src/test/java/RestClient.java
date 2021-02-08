import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class RestClient {

    public CloseableHttpResponse getRequest (String url) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();){
        HttpGet httpget = new HttpGet(url); //http get request (create connection with particular url)
        CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); //hit the GET URL
        return closebaleHttpResponse;
    } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
