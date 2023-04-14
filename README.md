GoTrain-Spring-Boot-REST-API

This application can be tested via Postman or the browser.
All three end-points can be accessed through the following URLs

-GET Request to get all the schedules : http://localhost:8080/api/goTrain/v1/schedules

-GET Request to get schedules with a particular line : http://localhost:8080/api/goTrain/v1/schedules/{line}

-GET Request to get schedules of a particular line at a given time : http://localhost:8080/api/goTrain/v1/schedules/{line}?departure={time}

 -------------------
Steps to run the applications

-Compile and build your application (mvn clean compile (if using terminal))
-run the application (mvn spring-boot:run (if using the terminal))
