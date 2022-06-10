# Flight System (Scrumbags)
A flight management software developed for a team project in EIST at TUM


## Recommended IDE for Front-End Development
[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar)

## Project Setup
Install [Node.js](https://nodejs.org/en/download/package-manager/). Then:
```sh
./gradlew npmSetup
```

## Compile and Hot-Reload for Front-End Development
This does not start the Spring Boot backend!
```sh
./gradlew npmDev
```
open a web browser and visit `localhost:3000`
 
## Lint JavaScript with [ESLint](https://eslint.org/)
```sh
./gradlew npmLint
```

## Compile and View the Production Build
This starts both the server and client applications, but does not have hot-reload capabilities.
```sh
./gradlew bootRun
```
open a web browser and visit `localhost:8080`
