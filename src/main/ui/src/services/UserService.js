export async function register(user, pass, flightnumber) {
    const response = await fetch(
        `api/register` +
        "?" +
        new URLSearchParams({
            username: user,
            password: pass,
            flightNumber: flightnumber
        }),
        {
            method: "POST",
            headers: {"Content-Type": "application/json"},
        }
    );
    return await response.text();
}

export async function login(user, pass) {
    const response = await fetch(
        `api/login` +
        "?" +
        new URLSearchParams({
            username: user,
            password: pass,
        }),
        {
            method: "POST",
            headers: {"Content-Type": "application/json"},
        }
    );
    return await response.text();
}

export async function logout() {
    const response = await fetch(
        `api/logout`,
        {
            method: "POST",
            headers: {"Content-Type": "application/json"},
        }
    );
    return await response.text();
}

export async function getLoggedInUser() {
    const response = await fetch(`api/loggedInUser`, {
        method: "GET",
        headers: {"Content-Type": "application/json"},
    });
    return await response.text();
}


export async function getUserData() {
    const response = await fetch(`api/users`, {
        method: "GET",
        headers: {"Content-Type": "application/json"},
    });
    return await response.json();
}
