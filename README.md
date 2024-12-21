# How to Run the E-commerce Application
This guide explains how to set up and run the E-commerce application using Docker Compose. The application consists of the main Spring Boot app, a PostgreSQL database, and Liquibase for database migrations. This Docker setup is designed for a local test profile where each team member works in their own isolated environment.
## Prerequisites
Before you begin, ensure that your system meets the following requirements:

1. Operating System any Linux distribution (configured with WSL2 if on Windows).

2. Install Docker and Docker Compose. You can follow the official Docker documentation for installation steps.

3. Ensure IntelliJ IDEA is installed. 

4. Java 17 or higher.

5. Create a .env file in the root directory with the following variables:
```declarative
DB_HOST_DOCKER=ecommerce_postgres_db
DB_PORT=5432
DB_NAME=ecommerce_db
DB_USERNAME=postgres
DB_PASSWORD=yourpassword
```
## Running the Application

### Option 1: Using IntelliJ IDEA UI

* Configure Docker in IntelliJ:

1. Navigate to File > Settings > Plugins, search for "Docker," and install it.
2. Go to File > Settings > Build, Execution, Deployment > Docker and configure Docker:
3. Add a new Docker server. Select a suitable way to connect to the Docker daemon. 
4. Add a new "Docker-Compose" configuration under Run/Debug Configurations.

* Run the Application:

1. Select the docker-compose.yml file as the configuration file.
2. Click the "Run" button to start the application.

### Option 2: Using Docker Compose Command
* Open a Linux-based terminal (e.g., Ubuntu terminal in WSL2) and navigate to the root directory of the project (where docker-compose.yml is located).
* Run Docker Compose by executing the following command: `docker-compose up --build`
* Verify: once the services are up and running, you can access the application at http://localhost:8082.
* Stop the Application:
1. press Ctrl+C in the terminal 
2. and run: `docker-compose down`

Follow these instructions to ensure everything works smoothly. Enjoy working with the E-commerce application!


