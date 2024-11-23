<h1 align="center">
    Expenses Management Service
</h1>

## Built with

![Spring]
![Docker]

## Prerequisites

- [Java](https://www.oracle.com/br/java/technologies/downloads/) (17+)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Docker](https://docs.docker.com/get-docker/)

## Setup environment

- Run `cp .env.example .env` to copy the environment variables

- (Optional) Change `JWT_SECRET` value

## Database configuration

- Create a PostgreSQL database via command line or [pgAdmin](https://www.pgadmin.org/download/)

- Set your database parameters (name, url, name and password) in the application.properties file

## Run with Docker

- Run `docker build -t ems:1.0 .` to build the image

- Run `docker run -p {your_port}:8080 ems:1.0` to start the container

[Docker]: https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white

[Spring]: https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white
