import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class RestClient {

    public static CloseableHttpResponse getRequest (String url) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();){
        HttpGet httpget = new HttpGet(url); //http get request (create get connection with particular url)
            return httpClient.execute(httpget);
    } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
