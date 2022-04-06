import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final String REMOTE_URL =
            "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = getClient();

        HttpGet request = new HttpGet(REMOTE_URL);
        CloseableHttpResponse response = client.execute(request);

        List<CatFact> facts = MyJSONParser.jsonToList(response);
        printNecessaryFacts(facts);
    }

    private static CloseableHttpClient getClient() {
        return HttpClientBuilder.create()
                .setUserAgent("Сat fact filtering app")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();
    }

    private static void printNecessaryFacts(List<CatFact> facts) {
        facts.stream()
                .filter(catFact -> catFact.getUpvotes() != null)
                .forEach(System.out::println);
    }
}
