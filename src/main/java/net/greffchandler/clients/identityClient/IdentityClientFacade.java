package net.greffchandler.clients.identityClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.greffchandler.clients.identityClient.models.PaginatedResults;
import net.greffchandler.clients.identityClient.models.User;
import net.greffchandler.helpers.http.HttpClientHelper;
import net.greffchandler.helpers.http.HttpRequestType;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class IdentityClientFacade {

    private static final String identityApiBaseUrl = "https://staging.greffchandler.net/";
    private static final String searchEndpoint = "api/identity/users/search/";
    private static final String searchUsernameEndpoint = "api/identity/users/username/";

    public static PaginatedResults<User> getUsersByQuery(String query, int count, int page) throws Exception {

        URI uri = new URIBuilder(identityApiBaseUrl)
                .setPath(searchEndpoint + query)
                .setParameter("count", String.valueOf(count))
                .setParameter("offset", String.valueOf(page - 1))
                .build();

        System.out.println("Performing GET request to `" + uri.toString() + "`...");
        String body = HttpClientHelper.performRequest(HttpRequestType.GET, uri.toString(), null, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper.readValue(body, new TypeReference<>() {});
    }

    public static User getUserByUsername(String username) throws Exception {
        URI uri = new URIBuilder(identityApiBaseUrl)
                .setPath(searchUsernameEndpoint + username)
                .build();

        System.out.println("Performing GET request to `" + uri.toString() + "`...");

        String json = HttpClientHelper.performRequest(HttpRequestType.GET, uri.toString(), null, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(json, User.class);
        } catch (Exception ignore) {
            return null;
        }
    }
}
