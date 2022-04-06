import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MyJSONParser {
    private static ObjectMapper mapper = new ObjectMapper();

    public static List<CatFact> jsonToList(CloseableHttpResponse response) throws IOException {
        return mapper.readValue(response.getEntity().getContent(),
                new TypeReference<>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                });
    }
}
