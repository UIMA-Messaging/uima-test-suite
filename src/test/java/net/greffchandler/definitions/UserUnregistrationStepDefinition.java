package net.greffchandler.definitions;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.greffchandler.clients.authClient.AuthClientFacade;
import net.greffchandler.clients.authClient.models.TokenResponse;
import net.greffchandler.clients.identityClient.IdentityClientFacade;
import net.greffchandler.clients.identityClient.models.User;
import net.greffchandler.clients.registrationClient.RegistrationClientFacade;
import net.greffchandler.clients.registrationClient.models.RegisteredUser;

public class UserUnregistrationStepDefinition {

    private RegisteredUser user;
    private String accessToken;

    @Given("A user wants to deregister with display name {string}")
    public void aUserWantsToRegisterWithDisplayName(String displayName) throws Exception {
        TokenResponse tokenResponse = AuthClientFacade.fetchAuthToken();
        this.accessToken = tokenResponse.getAccessToken();
        this.user = RegistrationClientFacade.registerUser(displayName, this.accessToken);
    }

    @When("An account deregistration is performed")
    public void anAccountUnregistrationIsPerformed() throws Exception {
        RegistrationClientFacade.unregisterUser(this.user.getId(), this.accessToken);
    }

    @Then("No user with user {string} should exist")
    public void noUserWithUsernameShouldExist(String ignore) throws Exception {
        User search = IdentityClientFacade.getUserByUsername(this.user.getUsername());
        if (search != null) {
            throw new Exception("User deregistration failed as user still exists.");
        }
    }

}
