# Endava Summer Practice 2023 - Java API

The project uses an in-memory database by default, but you can plug in an SQL Server instance. 

To start the application run `./gradlew bootRun` in the project root. 

To plug-in the SQL Server database, do the following:
- in `application.properties`, comment the H2 connection details and uncomment the SQL Server ones; replace `{url}` (the database URL), `{username}` and `{password}` with the corresponding values
- rename the `Orders` entity to `Order` (`Order` is a reserved word for H2): adjust the value of the `@Table` annotation in `src/main/java/org/example/summer/apprentice/model/Order.java`
- rename the `Customer` entity to `User` (`User` is a reserved word for H2): adjust the value of the `@Table` annotation in `src/main/java/org/example/summer/apprentice/model/User.java`
- save the changes
- start the application (see above)
  
