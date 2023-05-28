package net.greffchandler.helpers.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClientHelper {

    public static <T> T performRequest(HttpRequestType requestType, String uri, Object body, Class<T> responseType, String accessToken) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpRequestBase httpRequest = switch (requestType) {
            case GET -> new HttpGet(uri);
            case POST -> new HttpPost(uri);
            case DELETE -> new HttpDelete(uri);
            case PUT -> new HttpPut(uri);
        };

        if (body != null) {
            StringEntity stringEntity = new StringEntity(body.toString(), ContentType.APPLICATION_JSON);
            assert httpRequest instanceof HttpEntityEnclosingRequestBase;
            ((HttpEntityEnclosingRequestBase) httpRequest).setEntity(stringEntity);
        }

        if (accessToken != null) {
            httpRequest.addHeader("Authorization", "Bearer " + accessToken);
        }

        HttpResponse response = httpClient.execute(httpRequest);

        if (!isResponseSuccessful(response)) {
            throw new Exception("Request to `" + uri + "` was unsuccessful with status code " + response.getStatusLine().getStatusCode());
        }

        String responseBody = EntityUtils.toString(response.getEntity());

        if (responseType.equals(String.class)) {
            return responseType.cast(responseBody);
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            return objectMapper.readValue(responseBody, responseType);
        }
    }

    public static <T> T performRequest(HttpRequestType requestType, String uri, Object body, Class<T> responseType) throws Exception {
        return performRequest(requestType, uri, body, responseType, null);
    }

    public static boolean isResponseSuccessful(HttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();
        return statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES;
    }
}
