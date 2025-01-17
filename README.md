[![Build Status](https://travis-ci.com/hnnguye5/jira-clone-be.svg?token=Kc8c2mz2bFRyBWFJumHk&branch=main)](https://travis-ci.com/hnnguye5/jira-clone-be) 
![version](https://img.shields.io/badge/version-v1.0-blue)
[![MIT License](https://img.shields.io/apm/l/atomic-design-ui.svg?)](https://github.com/hnnguye5/jira-clone-be)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](https://github.com/hnnguye5/jira-clone-be/pulls)

Table of contents
=================

<!--ts-->
   * [Task Manager](#Task-Manager)
   * [Technologies](#Technologies)
   * [Getting Started](#Getting-Started)
      * [Run Application](#Run-Application)
   * [Usage](#Usage)
      * [Epic](#Epic)
      * [Backlog and Epic Task](#Backlog-and-Epic-Task)
   * [Contributing](#Contributing)
   * [License](#License)
<!--te-->

# Task Manager 

Simple task manager created with Spring Boot and MySQL.

## Technologies

* Java 11 (JDK-11.0.7)
* Spring Boot 2.3.4
* Spring Security
* JWT Authentication
* H2 Database
* MySQL
* Maven
* Heroku

## Getting Started

### Run Application

1. Clone the repo
   ```sh
   git clone https://github.com/hnnguye5/task-manager-be.git
   ```
2. To compile and run your application
   ```sh
   mvn spring-boot:run
   ```
3. Create jar to Deploy
   ```sh
   mvn package
   ```
## Usage

The following is more detail for the Epic and Epic Task API endpoints:

### Epic

Epic is a large body of work that can be broken down into a number of smaller tasks.

**POST**

`POST/api/epic`

Create a new Epic.

**JSON body:**
```json
    "id": 1,
    "epicIdentifier": "TEST",
    "epicName": "Test Name",
    "epicDescription": "test description",
    "startDate": 2020-12-25,
    "endDate": 2021-01-01,
    "createdOn": 2020-12-25,
    "updatedOn": null,
    "epicCreator": "test@email.com"
```
**Where:**

`id:` epic id that is automatically generated.

`epicIdentifier:` a unique identifier for the Epic.

`epicName:` name of the Epic.

`startDate:` date for which the Epic start.

`endDate:` date for which the Epic end.

`createdOn:` date for which the Epic is created.

`updatedOn:` date for which the Epic is updated.

`epicCreator:` user who created the Epic

**POST**

`POST/api/epic`

Update an Epic using the Epic's id.

**GET**

`GET/api/epic/all`

Retrieve all Epic created by the epicCreator.

`GET/api/epic/{epicIdentifier}`

Retrieve an Epic by the epicIdentifier.

**DELETE**

`DELETE/api/epic/{epicIdentifier}`

Delete an Epic by the epicIdentnfier.

### Backlog and Epic Task

A Backlog holds all existing Epic tasks of an Epic.

A Epic Task is made up of many smaller tasks to complete an Epic.

**POST**

`POST/api/backlog/{epicIdentifier}`

Can create many Epic Tasks in the backlog. A Backlog is created when a new Epic is created. It has a relation to the Epic by having the same epicIdentifer.

**JSON body:**
```json
       "id": 2,
       "epicIdentifier": "TEST",
       "epicSequence": "TEST-1",
       "summary": "Testing Epic Task Creation",
       "acceptanceCriteria": "If it is added",
       "status": "TO_DO",
       "priority": 3,
       "dueDate": 2020-12-30,
       "createdOn": "2020-12-25",
       "updatedOn": null"
```
**Where:**

`id:` Backlog id that is automatically generated.

`epicIdentifier:` a unique identifier for the Epic.

`epicSequence:` name of the Epic Task that is created.

`summary:` a brief description of the Epic Task.

`acceptanceCriteria:` a brief description for the Epic Task to be fulfilled.

`status:` status in which the Epic Task is in. (ex. To Do, In Progress, Done)

`priority:` ranking for the Epic Task. (ex. Low, Medium, High)

`dueDate:` date in which the Epic Task must be fulfilled.

`createdOn:` date for which the Epic Task is created.

`updatedOn:` date for which the Epic Task is updated.

**GET**

`GET/api/backlog/{epicIdentifier}`

Retrieve all the Epic Tasks in the Backlog by the Backlog's epicIdentifier.

`GET/api/backlog/{epicIdentifier}/{epicSequence}`

Retrieve an Epic Task by the epicSequence.

**PUT**

`PUT/api/backlog/{epicIdentifier}/{epicSequence}`

Update an Epic Task by using the epicIdentifier and epicSequence.

**DELETE**

`PUT/api/backlog/{epicIdentifier}/{epicSequence}`

Delete an Epic Task by using the epicIdentifier and epicSequence.

## Contributing

Feel free to contribute if you would like. Any contribution you make is greatly appreciated.

1. Fork the project
2. Create your feature branch `git checkout -b feature/YourFeature`
3. Commit the changes `git commit -m "Added some feature"`
4. Push the changes to branch `git push origin feature/YourFeature`
5. Open a pull request

## License

Distributed under the MIT License. See `LICENSE` for more information.