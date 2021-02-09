import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

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



}
