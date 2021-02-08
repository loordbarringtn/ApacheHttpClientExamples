import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.jupiter.api.Test;

public class ApiTests {

    @Test
    public void statusCodeTest(){
        CloseableHttpResponse closeableHttpResponse = RestClient.getRequest("https://reqres.in/api/users?page=2");
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode);
    }

}
