export async function getSuggestions(city) {
    const response = await fetch(
        `api/citySuggestion` +
        "?" +
        new URLSearchParams({
            city: city,
        }),
        {
            method: "POST",
            headers: {"Content-Type": "application/json"}
        }
    );
    return await response.json();
}

export async function getFlights(from, to, date) {
    const response = await fetch(
        `api/flights` +
        "?" +
        new URLSearchParams({
            from: from,
            to: to,
            date: date,
        }),
        {
            method: "POST",
            headers: {"Content-Type": "application/json"},
        }
    );
    return await response.json();
}

export async function constructJourney(flights) {
    const response = await fetch(
        `api/journey`,
        {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: flights
        }
    );
    return await response.json();
}

export async function getCurrentFlight() {
    const response = await fetch(
        `api/currentFlight`,
        {
            method: "POST",
            headers: {"Content-Type": "application/json"},
        }
    );
    return await response.json();
}
