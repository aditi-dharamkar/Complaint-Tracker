# Complaint Tracker

A web-based complaint management system that allows users to submit complaints and administrators to manage and track their status.

## Overview

Complaint Tracker is a web application developed to simplify the process of submitting, storing, and managing complaints. It provides a frontend for users and a backend powered by Java Spring Boot connected to a PostgreSQL database.

## Features

- Submit complaints through a web interface
- Store complaint details in a PostgreSQL database
- Manage complaint records through backend APIs
- Provide an administrator login
- View complaint information through the admin interface
- Track complaint status through the application

## Technology Stack

**Frontend**
- HTML
- CSS
- JavaScript

**Backend**
- Java
- Spring Boot
- Spring Data JPA

**Database**
- PostgreSQL

**Tools**
- Eclipse IDE
- Git and GitHub
- Maven

## Project Structure

```text
Complaint-Tracker/
├── Frontend/
│   ├── index.html
│   ├── script.js
│   ├── style.css
│   ├── admin.html
│   ├── admin.js
│   ├── dashboard.html
│   └── dashboard.js
│
├── complainttracker/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   └── resources/
│       └── test/
│
└── README.md
```

## Prerequisites

Before running the application, install:

- Java JDK 21
- PostgreSQL
- Git (optional, for cloning and version control)

## Setup and Installation

### 1. Clone the repository

```bash
git clone https://github.com/aditi-dharamkar/Complaint-Tracker.git
cd Complaint-Tracker
```

### 2. Configure PostgreSQL

Create a PostgreSQL database named `complaint_db`.

Configure the database connection in:

`complainttracker/src/main/resources/application.properties`

Set the database username and password through environment variables. Do not commit actual passwords or credentials to GitHub.

### 3. Run the backend

Open a terminal in the `complainttracker` directory and run:

```bash
mvnw.cmd spring-boot:run
```

On Windows, if Maven Wrapper cannot run, verify that Java and the project dependencies are installed correctly.

### 4. Open the frontend

Open `Frontend/index.html` using a local web server, such as the VS Code Live Server extension.

Make sure the frontend API URL and backend server address are configured correctly.

## Future Improvements

- Secure password hashing and authentication
- Improved input validation and error handling
- Complaint search and filtering
- Deployment to a cloud hosting platform
- Automated testing and improved documentation

## Author

**Aditi Dharamkar**

## License

This project is currently available for review and educational purposes. A formal open-source license has not yet been selected.