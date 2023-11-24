# SpaceFileManager

## Overview

SpaceFileManager is a web-based application that facilitates the management of files stored in DigitalOcean Spaces. It provides a user-friendly interface for browsing, downloading, and searching files. The application is built using Java with Spring Boot for backend development and HTML, CSS, and JavaScript for frontend development.

## Features

- **File Browsing**: List all files available in the specified DigitalOcean Space.
- **File Downloading**: Download files directly from the application.
- **File Searching**: Quickly find files using a search feature.
- **User Authentication**: Secure access to the application through a login system.

## Technology Stack

- **Backend**: Java, Spring Boot
- **Frontend**: HTML, CSS, JavaScript
- **Security**: Spring Security
- **Cloud Storage**: DigitalOcean Spaces
- **Containerization**: Docker

## Getting Started

### Prerequisites

- JDK 17
- Docker (for containerized deployment)

### Setting Up

1. Clone the repository:
   ```bash
   git clone https://github.com/Andreichenko/SpaceFileManager.git
   ```
2. Navigate to the project directory:
   ```bash
   cd SpaceFileManager
   ```

### Running the Application
Locally

1. Build the application using Gradle:
```bash
./gradlew build
```
2. Run the application:
```bash
java -jar build/libs/SpaceFileManager-0.0.1-SNAPSHOT.jar
```

Using Docker

1. Build the Docker image:
```bash
docker build -t spacefilemanager .
````
2. Run the Docker container:
```bash
docker run -p 8080:8080 spacefilemanager
```   
The application will be accessible at http://localhost:8080.

## Configuration

Set the following environment variables for DigitalOcean Spaces access and other configurations:

- AWS_ACCESS_KEY_ID: Your access key for DigitalOcean Spaces.
- AWS_SECRET_ACCESS_KEY: Your secret key for DigitalOcean Spaces.
- SPACES_REGION: The region your Space resides in.
- SPACES_BUCKET: The name of your Space.

## Contributing

Contributions to SpaceFileManager are welcome. Please follow the standard procedures for submitting issues and pull requests.
