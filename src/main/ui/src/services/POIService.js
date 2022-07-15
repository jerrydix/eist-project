export async function addPOIToFavourites(id) {
    const response = await fetch(
        `api/favourite` +
        "?" +
        new URLSearchParams({
            id: id,
        }),
        {
            method: "POST",
            headers: {"Content-Type": "application/json"},
        }
    );
    return await response.json();
}

export async function removePOIFromFavourites(id) {
    const response = await fetch(
        `api/unfavourite` +
        "?" +
        new URLSearchParams({
            id: id,
        }),
        {
            method: "POST",
            headers: {"Content-Type": "application/json"},
        }
    );
    return await response.json();
}

export async function getPointsOfInterest() {
    const response = await fetch(
        `api/poi`,
        {
            method: "GET",
            headers: {"Content-Type": "application/json"},
        }
    );
    return await response.json();
}