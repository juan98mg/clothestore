# sell

## Name

Clothings Seller

## Description

Clothesstore LATAM is Located in Colombia. This company works for selling each
fashion clothes or clothing accessories through internet. For that reason is created
this project. Clothesstore help us to buy easy and effectively.

## Installation

must have

- [ ] Node.js
- [ ] Angular 11
- [ ] OpenJDK-11
- [ ] maven

## RUN

Project has two parts fronted and backend
**APP**
This is how to run frontend part.

Due to this is a monorepo you must go to **app** folder and RUN the comman **ng serve**
then angular will run immediately also you can use the custom command **npm run cloth**

**API**

you must run the command into **api** folder: **mvn spring-boot:run**

to run spring boot project is needed a configuration of this environment variables

        "CL_ENV": "variable that define which properties is actived",
        "CL_DB_PLATFORM": "dialect thath hibernate will be use", example: org.hibernate.dialect.ostgreSQLDialect
        "CL_DB_PASSWORD": "data base password",
        "CL_DB_USER": "data base user",
        "CL_DB_URL": "data base url " example: jdbc:postgresql://localhost:5432/seller,
        "CL_DB_DRIVER": "data base driver" example org.postgresql.Driver

also the first time that the project is run must execute with the argument **populate**
this will be initialize de country table on data base with de principal country and its maximun discount

## Support

We have an active service desk that allow people write a ticket message

incoming+juan98mg-sell-29270076-issue-@incoming.gitlab.com

Written emails are going to be checking and they will be an issue if we agree it is an issue

## Authors and acknowledgment

**Juan Stiven Muñoz Garcia**

## Project status

If you have run out of energy or time for your project, put a note at the top of the README saying that development has slowed down or stopped completely. Someone may choose to fork your project or volunteer to step in as a maintainer or owner, allowing your project to keep going. You can also make an explicit request for maintainers.

## ADVICES

Duo to this project is a monorepo, it has a workspace. Workspace contains vs code extentions that could help you to develop and start api and app parts.

remember dad you can add launch.json to define the arguments in the run operation and de environment variables in the .vscode folder
