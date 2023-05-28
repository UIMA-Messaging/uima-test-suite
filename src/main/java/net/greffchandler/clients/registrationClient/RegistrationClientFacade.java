package net.greffchandler.clients.registrationClient;

import net.greffchandler.clients.authClient.AuthClientFacade;
import net.greffchandler.clients.authClient.models.TokenResponse;
import net.greffchandler.clients.registrationClient.models.BasicUser;
import net.greffchandler.clients.registrationClient.models.ExchangeKeys;
import net.greffchandler.clients.registrationClient.models.RegisteredUser;
import net.greffchandler.helpers.http.HttpClientHelper;
import net.greffchandler.helpers.http.HttpRequestType;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;

public class RegistrationClientFacade {

    private static final String registrationApiBaseUrl = "https://greffchandler.net/";
    private static final String registerEndpoint = "protected/api/registrations/users/register/";
    private static final String unregisterEndpoint = "protected/api/registrations/users/unregister/";

    public static RegisteredUser registerUser(String displayName, String accessToken) throws Exception {

        BasicUser basicUser = BasicUser.generateDefault(displayName);

        URI uri = new URIBuilder(registrationApiBaseUrl)
                .setPath(registerEndpoint)
                .build();

        return HttpClientHelper.performRequest(HttpRequestType.POST, uri.toString(), basicUser, RegisteredUser.class, accessToken);
    }

    public static void unregisterUser(String id, String accessToken) throws Exception {
        TokenResponse tokenResponse = AuthClientFacade.fetchAuthToken();

        URI uri = new URIBuilder(registrationApiBaseUrl)
                .setPath(unregisterEndpoint + "/" + id)
                .build();

        HttpClientHelper.performRequest(HttpRequestType.DELETE, uri.toString(), null, null, accessToken);
    }
}
