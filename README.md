# Employee Management Spring Boot Application

Build:
mvn clean package

Run:
java -jar target/employee-management-1.0.0.jar

Application Port:
8085

Endpoints:
GET    http://localhost:8085/employees
POST   http://localhost:8085/employees?name=David
DELETE http://localhost:8085/employees/David
GET    http://localhost:8085/employees/health
