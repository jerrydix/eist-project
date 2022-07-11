export async function addPOIToFavourites(poi) {
    const response = await fetch(
        `api/favourite`,
        {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: poi,
        }
    );
    return await response.json();
}

export async function getPOIFavourites() {
    const response = await fetch(
        `api/favourites`,
        {
            method: "GET",
            headers: {"Content-Type": "application/json"},
        }
    );
    return await response.json();
}