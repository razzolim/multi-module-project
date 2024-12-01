
## Requirements
- Java 17
- Docker
- Gradle

## How to run the project

### You have two options:

1. There's a gradle `command` that runs docker compose and Liquibase migrations

   `gradle resources update`

2. You can run both tasks separately:

   `gradle resources` or `docker compose up -d`
   `gradle :entity:update`

Run the application through: `com.renan.launcher.ApplicationLauncher`

Once the project is up and running you can access [Swagger documentation](http://localhost:8080/swagger-ui/index.html) 
in order to get the most up-to-date endpoints avaialble.


## Project structure
```
.
├── README.md
├── build.gradle
├── docker-compose.yml
├── entity
│   └── build.gradle
├── launcher
│   └── build.gradle
├── rabbitmq
│   ├── consumer
│   │   └── build.gradle
│   └── publisher
│       └── build.gradle
├── repository
│   └── build.gradle
├── service
│   └── build.gradle
├── settings.gradle
└── webservice
    └── build.gradle
```