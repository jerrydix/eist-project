# Flight System (Scrumbags)

A flight management software developed for a team project in EIST at TUM

## For Users
### How to
1. Enter `./gradlew bootRun` and head to `localhost:8080`
2. Click on "Register"
3. Enter the flight number: GA4200
3. Register with an arbitrary username and a password: "user" and "pw"
4. Login with these credentials
5. Enjoy!

### Read the Docs
You might want to open these in a web browser.
- [System Design Document](./documentation/SDD.html)
- [Requirements Analysis Document](./documentation/RAD.html)
- [Front-End Documentation](./documentation/ui_documentation.html)
- [Back-End Documentation](./documentation/javadoc/index.html)

## For Developers
### Recommended IDE for Front-End Development

[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar)

### Start the Front-End Development Server

This has hot-reload capabilities but does not start the Spring Boot backend.

```sh
./gradlew npm_run_dev
```

open a web browser and visit `localhost:3000`

### Lint JavaScript with [ESLint](https://eslint.org/)

```sh
./gradlew npm_run_lint
```

### View the Production Build

This starts both the server and client applications but does not have hot-reload capabilities.

```sh
./gradlew bootRun
```

open a web browser and visit `localhost:8080`

### Compile the JAR file

```sh
./gradlew clean build
```

