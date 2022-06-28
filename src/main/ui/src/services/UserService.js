export async function register(user, pass) {
    const response = await fetch(`api/register` + "?" + new URLSearchParams({
        username: user,
        password: pass
    }), {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
    })
    return await response.text();
}


export async function login(user, pass) {
    const response = await fetch(`api/login` + "?" + new URLSearchParams({
        username: user,
        password: pass
    }), {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
    })
    return await response.text();
}

export async function logout(user) {
    const response = await fetch(`api/logout` + "?" + new URLSearchParams({
        username: user
    }), {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
    })
    return await response.text();
}

export async function getUserData(user) {
    const response = await fetch(`api/users` + "/" + user
        , {
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
        })
    return await response.json();
}