package net.greffchandler.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.greffchandler.clients.identityClient.IdentityClientFacade;
import net.greffchandler.clients.identityClient.models.PaginatedResults;
import net.greffchandler.clients.identityClient.models.User;

public class UserSearchingStepDefinitions {

    private PaginatedResults<User> searchResults;
    private String query;
    private int count;
    private int page;

    @Given("A user wishes to {string} on {int} with {int} users")
    public void a_user_searches_for_on_for_users(String query, Integer page, Integer count) {
        this.query = query;
        this.page = page;
        this.count = count;
    }

    @When("A search is performed")
    public void userOfExists() throws Exception {
        searchResults = IdentityClientFacade.getUsersByQuery(query, count, page);
    }

    @Then("Search results should return paginated results with {string} as user")
    public void searchResultsShouldReturn(String username) throws Exception {
        for (User user: searchResults.getResults()) {
            if (user.getUsername().equals(username)) {
                System.out.println("Search results has desired user present.");
                return;
            }
        }
        throw new Exception("Could not find user with username `" + username + "` in search results.");
    }

    @Then("A next page URL is {booleanValue}")
    public void aNextPageURLIsExpected(Boolean expected) throws Exception {
        if (searchResults.getNextPage() == null && expected) {
            throw new Exception("No next page URL present in search results.");
        }
        System.out.println("Search results has next page URL.");
    }

    @Then("A previous page URL is {booleanValue}")
    public void aPreviousPageURLIsExpected(Boolean expected) throws Exception {
        if (searchResults.getPreviousPage() == null && expected) {
            throw new Exception("No previous page URL present in search results.");
        }
        System.out.println("Search results has previous page URL.");
    }

}
