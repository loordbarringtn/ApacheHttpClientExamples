import com.jayway.jsonpath.JsonPath;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class JsonTests {
    @Test
    public void JsonPathTest() throws IOException {
        //https://github.com/json-path/JsonPath
        //http://jsonpath.herokuapp.com/?path=$.store.book[*].author
        CloseableHttpResponse closeableHttpResponse = RestClient.getRequest("https://reqres.in/api/users?page=2");
        String json = EntityUtils.toString(closeableHttpResponse.getEntity());
        List<String> authors = JsonPath.read(json, "$..email");
        authors.forEach(System.out::println);
    }
}
