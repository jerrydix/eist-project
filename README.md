# Flight System (Scrumbags)
A flight management software developed for a team project in EIST at TUM


## Recommended IDE for Front-End Development
[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar)

## Project Setup
Install [Node.js](https://nodejs.org/en/download/package-manager/). Then:
```sh
./gradlew npmSetup
```

## Compile and Hot-Reload for Development
```sh
./gradlew npmDev
```
open a web browser and visit `localhost:3000`

## Lint with [ESLint](https://eslint.org/)
```sh
./gradlew npmLint
```

## Compile and View the Production Build
```sh
./gradlew clean build
cd build/libs
java -jar ./<name>.jar
```
open a web browser and visit `localhost:8080`
