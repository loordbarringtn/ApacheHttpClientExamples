import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ApiTests {

    @Test
    public void getStatusCodeTest() throws IOException {
        CloseableHttpResponse closeableHttpResponse = RestClient.getRequest("https://reqres.in/api/users?page=2");
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode);
    }

    @Test
    public void assertTest() throws IOException {
        CloseableHttpResponse closeableHttpResponse = RestClient.getRequest("https://reqres.in/api/users?page=2");
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,200);
    }

    @Test
    public void jsonResponseTest() throws IOException {
        CloseableHttpResponse closeableHttpResponse = RestClient.getRequest("https://reqres.in/api/users?page=2");
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println(responseJson);
    }

    @Test
    public void headersTest() throws IOException {
        CloseableHttpResponse closeableHttpResponse = RestClient.getRequest("https://reqres.in/api/users?page=2");
        Header[] headersArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<String, String>();
        for(Header header : headersArray){
            allHeaders.put(header.getName(),header.getValue());
        }
        System.out.println("Headers Array-->" + allHeaders);
    }

    @Test
    public void jsonExtractTest() throws IOException {
        CloseableHttpResponse closeableHttpResponse = RestClient.getRequest("https://reqres.in/api/users?page=2");
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        String value = RestClient.getValueByJPath(responseJson, "page");
        System.out.println("Page value:-->" + value);
    }

    @Test
    public void assertJsonEqualsTest() throws IOException {
        CloseableHttpResponse closeableHttpResponse = RestClient.getRequest("https://reqres.in/api/users?page=2");
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        String per_page = RestClient.getValueByJPath(responseJson, "per_page");
        Assert.assertEquals(per_page,"6");
    }

    @Test
    public void jsonArrayValueTest() throws IOException {
        CloseableHttpResponse closeableHttpResponse = RestClient.getRequest("https://reqres.in/api/users?page=2");
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        String idValue = RestClient.getValueByJPath(responseJson, "data[0]/id");
        System.out.println(idValue);
    }

    @Test
    public void methodEndpointTest() throws IOException, URISyntaxException {
        CloseableHttpResponse closeableHttpResponse = RestClient.getRequestEndpoint("api/users?page=2");
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode);
    }

    @Test
    public void printWithScanner() throws IOException, URISyntaxException {
        CloseableHttpResponse closeableHttpResponse = RestClient.getRequestEndpoint("api/users?page=2");
        Scanner scanner = new Scanner(closeableHttpResponse.getEntity().getContent());
        while (scanner.hasNext()){
            System.out.println(scanner.next());
        }
    }







}
