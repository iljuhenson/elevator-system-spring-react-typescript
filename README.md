<h1 align="center">
  Elevator System
</h1>

<p align="center">
  <a href="#tech-stack">Tech stack</a> •
  <a href="#overview">Overview</a> •
  <a href="#try">How to run it</a> •
  <a href="#app-architecture">Application architecture</a>
</p>

## <a name="tech-stack"></a>:computer: Tech stack

This was built with the help of the following technologies:

| Backend                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        | Frontend                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | CI/CD                                                                                                                          | Additional software                                                                                                                                                                                                                                                                                                                                                                                             |
|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [![Java](https://img.shields.io/badge/java-orange?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)<br/>[![Spring](https://img.shields.io/badge/spring-green?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/) | [![React](https://img.shields.io/badge/React-black?style=for-the-badge&logo=React)](https://react.dev/)<br/>[![Typescript](https://img.shields.io/badge/typescript-blue?style=for-the-badge&logo=typescript&logoColor=white)](https://www.typescriptlang.org/)<br/>[![vite](https://img.shields.io/badge/vite-black?style=for-the-badge&logo=vite)](https://vitejs.dev/) | [![Docker](https://img.shields.io/badge/docker-blue?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/) | [![Intelij Idea](https://img.shields.io/badge/Intelij%20Idea-grey?style=for-the-badge&logo=intellijidea)](https://www.jetbrains.com/idea/)<br/>[![Postman](https://img.shields.io/badge/Postman-orange?style=for-the-badge&logo=postman&logoColor=white)](https://www.postman.com/)<br/>[![Static Badge](https://img.shields.io/badge/figma-pink?style=for-the-badge&logo=figma&logoColor=black)](https://www.figma.com/) |

## <a name="overview"></a>:mag_right: Overview

This is an app for managing elevator system consisting of up to
16 elevators and 30 floors.

## <a name="try"></a>:monocle_face: How to run it

> **Note:**
> You need to have [Docker](https://docs.docker.com/engine/install/) installed on your machine.

```bash
# Run docker containers with compose
$ docker-compose up -d --build
```

The UI of application is available at [localhost:3000](http://localhost:3000).<br/>
You can query backend API over [localhost:8000](http://localhost:8000).

## <a name="app-architecture"></a>:triangular_ruler: Application architecture

### :gear: Algorithmic approach

To make algorithmic explanation easier I will be using graphics with
the following elements.

<img src="./readme-images/sign-explanation.png" alt="Sign explanation">

For the way how elevators will be serving elevator calls and going
to destination I chose SCAN Disk Scheduling algorithm:

<img src="./readme-images/scan-explanation.png" alt="SCAN explanation">

But the elevator is not just going from one pickup floor to another,
instead it picks up a so called "Elevator User", who has 3 parameters
such as: pickup floor, destination floor and whether this user has
already been picked up. These "Elevator Users" build up the destinations
arrays of all the Elevators:

<img src="./readme-images/elevator-user-explanation.png" alt="Elevator User explanation">

*But how those destination arrays are built up? How can "Elevator User" know
which elevator should he wait for?*

This is why I came up with an algorithm which calculates the distance
from the floor where elevator is located to the pickup floor.

This method requires to look through all the possible elevator positions,
relative to their next destination, current direction and pickup floor position
which we are checking against. There are 8 of such possibilities and 1 extra
for stationary elevator.

But thanks to the sign trick that I've noticed I reduced 8 possibilities
to 4 similar case scenarios. Here's the trick explanation:

<img src="./readme-images/elevator_sign_trick.png" alt="Sign trick">

All the described above business logic is located under
`src/main/java/com/example/elevator/service` directory.

### :herb: Spring application

Spring application contains all the business logic and exposes api
endpoints to all the elevator system functionalities.

### :newspaper: React application

React application doesn't contain any business logic all that it does
is displaying the whole elevator system and all the api
functionalities.