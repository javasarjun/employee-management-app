# Employee Management Application
Application with angular as front end and rest services to support management of employee vacation days accumulated based on days worked.
		
## Base package
```text
com.labcorp.employee.employeemanagement
```

## Project Setup
### Technology Stack 
```text
1. Maven 3
2. Java 8
3. Spring Boot v2.4.0
4. Spring Webflux
6. Mockito
7. Angular 10
8. Angular Material
9. Jasmine
10. Karma
```

### Build
#### This project uses [Maven](http://maven.apache.org/) for dependency management. Clone this repository and execute:
```
Navigate to employee-management-ui
$:> npm install
$:> ng serve -o 
Navigate to employee-management-core
$:> mvn install
$:> java -jar labcorp-0.0.1-SNAPSHOT.jar
```

Goto http://localhost:4200/ after above command.
## Views

### Home Page
![Alt text](docs/homePage.PNG?raw=true)
### Update Work Days
![Alt text](docs/update-work-days.PNG?raw=true "Edit Account Dialog")
### Take Vacation
![Alt text](docs/update-vacation-days.PNG?raw=true "Add Account Dialog")


## Rest Endpoints
This project uses Spring Boot Starter Web and hence all operations of the entities are exposed
as REST API following REST API Best practices.


### Employee APIs

#### Read: To get all employee go to http://localhost:8080/employees
```json
[
    {
        "id": "8b4255c7-76cc-49ff-b84c-36a0896a5c71",
        "vacationDaysAccumulated": 3.846154,
        "name": "HE2",
        "daysWorked": 100,
        "maxVacationDaysInWorkYear": 10.0,
        "type": "HOURLY"
    },
    {
        "id": "28906df7-1374-4bd6-8c6b-3ae7610ab60f",
        "vacationDaysAccumulated": 0.0,
        "name": "SE4",
        "daysWorked": 0,
        "maxVacationDaysInWorkYear": 15.0,
        "type": "SALARIED"
    },
    {
        "id": "94e9a252-8b08-4715-a326-c4b1ffd042e0",
        "vacationDaysAccumulated": 0.0,
        "name": "MA7",
        "daysWorked": 0,
        "maxVacationDaysInWorkYear": 30.0,
        "type": "MANAGER"
    }
    ]
```
#### Update: For Updating employee work days PUT to http://localhost:8080/employee/{id}/days-worked/{days}

Success Response
```json
{
    "id": "0ba8b9c2-723a-4198-a4b9-f2d68d19527e",
    "vacationDaysAccumulated": 11.538462,
    "name": "SE0",
    "daysWorked": 200,
    "maxVacationDaysInWorkYear": 15.0,
    "type": "SALARIED"
}
```
In case of invalid input  a 400 will be issued. For example below request will result in a 400 as employee cannot work more than work year in our case 260 days.
```json
{
    "message": "Cannot work for more than 260 days in a work year",
    "code": 400
}

```

#### Update: For Updating employee vacation days  PUT to http://localhost:8080/employee/{id}/take-vacation/{days}

Success
```json
{
    "id": "0ba8b9c2-723a-4198-a4b9-f2d68d19527e",
    "vacationDaysAccumulated": 6.5384617,
    "name": "SE0",
    "daysWorked": 200,
    "maxVacationDaysInWorkYear": 15.0,
    "type": "SALARIED"
}
```

In case of invalid input  a 400 will be issued. For example below request will result in a 400 as employee cannot take more vacation days than accumulated.
```json
{
    "message": "Cannot take more vacation than available vacation days 6.5384617",
    "code": 400
}

```

