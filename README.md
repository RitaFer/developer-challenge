## Diazero - Java Developer

### Execution:
It is possible to run the application by downloading the zip from github, decompiling it, opening it with Intellij and running the project.<br><br>
It is possible to run via docker:<br>
The container image for this project can be found at **[Docker hub](https://hub.docker.com/layers/ritaferreira/developerchallenge/latest/images/sha256-fd6690c5ab790d44af43e249a209b91f0b8325b99b8fb075113dcc68f59fa890?context=repo)**
<br>
<code>docker pull ritaferreira/developerchallenge:latest</code><br>
<code>docker run ritaferreira/developerchallenge:latest</code>

### Stack:
- Java 8
- Spring boot 2.7.7
- Apache Maven 4.0.0
- H2 runtime

### Dependencies
- spring-boot-starter: used to start the project with spring boot.
- spring-boot-devtools: used to avoid rebuilds during development.
- spring-boot-starter-web: allows the creation and insertion of annotations for the development of rest apis.
- spring-boot-starter-validation: allows the insertion of validators for variables via annotations.
- spring-boot-starter-data-jpa: enables communication with the database in a simplified way, using annotations.
- h2: creates an in-memory database.
- lombok: allows insertion of getters and setters and other shortcuts via annotations.
- spring-boot-starter-security: enables the creation of an authentication layer, making data more secure.
- commons-beanutils: used for communication between objects, avoiding conflicts between memory spaces.
- springfox-swagger2 and springfox-swagger-ui: enables the creation of a swagger for the api documentation.

### Considerations
- 2 memory users were created:

| username | password | scroll |
|---------|---------------|---|
| admin | testediazero | admin |
| rita | testediazero | user |

- The endpoints for the requests are different depending on the user's role type.
- Change endpoints such as: create, update, patch and delete, are only available to the admin;
- It is possible to close and reopen an incident;

### Resource utilization
localhost:8080

| Info | Method | URI | Content-Type |
|---------------------------------------------------------------- |--------|------------------------|---|
| SWAGGER | - | /swagger-ui.html | |
| Listing incidents | GET | /incidents | |
| Listing top 20 incidents in descending order | GET | /incidents/top20 | |
| Registering incident | POST | /incidents | app/json |
| Searching incident by ID | GET | /incidents/{Id} | |
| Updating incident | PUT | /incidents/{Id} | app/json |
| Updating Incident Information | PATCH | /incidents/{Id} | app/json |
| Closing incident | POST | /incidents/{Id}/close | |
| Reopening incident | POST | /incidents/{Id}/reopen | |
| Deleting incident | DELETE | /incident/{Id} | |

-------------------------------------------------- -------------------------------------------------- -------------------------------------------------- ------------------------
### Criteria for evaluation
#### Knowledge of the spring framework will be assessed, how you have structured the project and the clarity of the solution. Remember to use English in the coding process.
1) stack
    - JPA/Hibernate
    - Spring Boot
    - maven
2) Create a spring-boot application to register incidents. The application must provide operations
    REST that allow the registration/maintenance/removal of incidents.
    An incident is made up of fields (add more fields if necessary).
     - idIncident
     - name
     - description
     - createdAt
     - updatedAt
     - closedAt
   
3) Your application must have the features below:
    - Register Incidents
    - Update Incidents
    - Delete Incidents
    - List all incidents
    - List incident by ID
    - List the last 20 incidents sorted in descending order

4) Use some 'embeded' database (h2 for example) for data persistence. You can use any other database as long as you can document the steps to start the application.

5) Add a README with:
    - Instructions on how to compile and run the project;
    - A justification for the use of frameworks or libraries (if used);
    - Additional notes that you consider important for the evaluation.

6) Optional
    - docker and docker-compose
    - spring-security
    - swagger
    - unitary tests
