In order to run the FE Selenium tests, you need Docker. Run the following command: docker-compose up {CONTAINER_OF_YOUR_CHOICE}.
The options for the container are "selenium_chrome", "selenium_chromium" ->(for M1 chips) and "selenium_firefox".
In order to actually see the tests being run, you need a software like VNC Viewer to view the docker container running. Connect to the port specified in DriverFactory.java with password = aureum (specified in docker-compose.yml).

In order to run the API tests, run the following command: ./gradlew test.

Both set of tests can also be run through the IDE of your choice. FE selenium tests with TestNG; API tests with JUnit.
