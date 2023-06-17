# UIMA Test Suite

The UIMA test suite aims to maintain logic integraty in the UIMA backend system. It uses Cucumber with the gherkin notation to build test features. These features are then attended to be tested before a deployed to a production environment is made. 

## Features 

#### Public user searching 

A feature responsible for testing whether paginated results of users is returned given a fuzzy search. For this feature, there are 3 scnearios, one for testing the contents of the search results, and the remaining for testing if a next or previous page is present. 

#### User registration

A feature responsible for testing whether users can register given a display name. 
> ⚠ This feature requires authentication

#### User deregistration

A feature responsible for testing whether users can remove theor account given their id. 
> ⚠ This feature requires authentication

## CICD integration

The regression tests are ran during the workflow of all production pipelines of the organisation. Should a regression test fail, the pipeline fails and deployement to production is terminated. 

> More information regarding the deployement pipeline can be found in [this exemple](https://github.com/UIMA-Messaging/registration-service/blob/master/.github/workflows/release.yml#L25)

These tests via Github Actions is enlabed via [this project](https://github.com/greffgreff/run-java-tests/network/dependents)

