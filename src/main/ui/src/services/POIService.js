export async function addPOIToFavourites(id, locationID) {
    const response = await fetch(
        `api/favourite` +
        "?" +
        new URLSearchParams({
            id: id,
            locationID: locationID
        }),
        {
            method: "POST",
            headers: {"Content-Type": "application/json"},
        }
    );
    return await response.json();
}

export async function removePOIFromFavourites(id, locationID) {
    const response = await fetch(
        `api/unfavourite` +
        "?" +
        new URLSearchParams({
            id: id,
            locationID: locationID
        }),
        {
            method: "POST",
            headers: {"Content-Type": "application/json"},
        }
    );
    return await response.json();
}

export async function getPointsOfInterest(locationID) {
    const response = await fetch(
        `api/poi` +
        "?" +
        new URLSearchParams({
            locationID: locationID
        }),
        {
            method: "GET",
            headers: {"Content-Type": "application/json"},
        }
    );
    return await response.json();
}