# Flight System (Scrumbags)
A flight management software developed for a team project in EIST at TUM


## Recommended IDE for Front-End Development
[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar)

## Start the Front-End Development Server
This has hot-reload capabilities but does not start the Spring Boot backend.
```sh
./gradlew npm_run_dev
```
open a web browser and visit `localhost:3000`
 
## Lint JavaScript with [ESLint](https://eslint.org/)
```sh
./gradlew npm_run_lint
```

## View the Production Build
This starts both the server and client applications but does not have hot-reload capabilities.
```sh
./gradlew bootRun
```
open a web browser and visit `localhost:8080`

## Compile the JAR file
```sh
./gradlew clean build
```