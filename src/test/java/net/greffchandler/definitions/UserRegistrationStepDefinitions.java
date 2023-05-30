package net.greffchandler.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.greffchandler.clients.authClient.AuthClientFacade;
import net.greffchandler.clients.authClient.models.TokenResponse;
import net.greffchandler.clients.registrationClient.RegistrationClientFacade;
import net.greffchandler.clients.registrationClient.models.RegisteredUser;

import java.util.Objects;

public class UserRegistrationStepDefinitions {

    private String displayName;
    private RegisteredUser user;
    private String accessToken;

    @Given("A user wants to register with display name {string}")
    public void aUserWantsToRegisterWithDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @When("An account registration is performed")
    public void anAccountRegistrationIsPerformed() throws Exception {
        TokenResponse tokenResponse = AuthClientFacade.fetchAuthToken();
        this.accessToken = tokenResponse.getAccessToken();
        this.user = RegistrationClientFacade.registerUser(displayName, accessToken);
    }

    @Then("A registered user account should have {string}")
    public void aRegisteredUserAccountShouldHave(String displayName) throws Exception {
        if (!Objects.equals(this.user.getDisplayName(), displayName)) {
            throw new Exception("Registered user display name does not match created one");
        }
        RegistrationClientFacade.unregisterUser(this.user.getId(), accessToken);
    }

}
