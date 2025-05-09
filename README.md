
# WCC Java Challenge

Java test challenge:

Write a REST (-y) service that will return the geographic (straight line) distance between two
postal codes in the UK.

Arguments to a request are two UK postal codes (you may decide how these arguments are
provided).

Result to a valid request must be a json document that contains the following information:

- For both locations, the postal code, latiude and longitude (both in degrees);

- The distance between the two locations (in kilometers);

- A fixed string 'unit' that has the value "km";

Starting the application

- mvn clean install
- mvn spring-boot:run

Postman collection is included in the project root folder.

Logs will be in .\logs.
