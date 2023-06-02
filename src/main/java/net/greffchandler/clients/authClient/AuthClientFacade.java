package net.greffchandler.clients.authClient;

import net.greffchandler.clients.authClient.models.TokenResponse;
import net.greffchandler.helpers.http.HttpClientHelper;
import net.greffchandler.helpers.http.HttpRequestType;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;

public class AuthClientFacade {
    private static final String authApiBaseUrl = "https://staging.greffchandler.net/";
    private static final String tokenCreateEndpoint = "api/auth/tokens/create";

    public static TokenResponse fetchAuthToken() throws Exception {
        URI uri = new URIBuilder(authApiBaseUrl)
                .setPath(tokenCreateEndpoint)
                .build();

        return HttpClientHelper.performRequest(HttpRequestType.POST, uri.toString(), null, TokenResponse.class);
    }
}
