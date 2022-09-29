export async function register(user, pass, email, flightnumber) {
  const response = await fetch(
    `api/register` +
      "?" +
      new URLSearchParams({
        username: user,
        password: pass,
        email: email,
        flightNumber: flightnumber,
      }),
    {
      method: "POST",
      headers: { "Content-Type": "application/json" },
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
      headers: { "Content-Type": "application/json" },
    }
  );
  return await response.text();
}

export async function confirmEmail(code) {
  const response = await fetch(
    `api/confirm` +
      "?" +
      new URLSearchParams({
        code: code,
      }),
    {
      method: "POST",
      headers: { "Content-Type": "application/json" },
    }
  );
  return await response.text();
}

export async function logout() {
  const response = await fetch(`api/logout`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
  });
  return await response.text();
}

export async function addMoney(money) {
  const response = await fetch(
    `api/fund` +
      "?" +
      new URLSearchParams({
        money: money,
      }),
    {
      method: "POST",
      headers: { "Content-Type": "application/json" },
    }
  );
  return await response.json();
}

export async function hasCompletedSurvey() {
  const response = await fetch(`api/completedSurvey`, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  });
  return await response.json();
}

export async function exchangeReward(reward) {
  const response = await fetch(`api/exchange`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(reward),
  });
  return await response.json();
}

export async function getLoggedInUser() {
  const response = await fetch(`api/loggedInUser`, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  });
  return await response.text();
}

export async function getUserData() {
  const response = await fetch(`api/users`, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  });
  return await response.json();
}
