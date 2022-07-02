export async function getMovies() {
    const response = await fetch(
        `api/movies`,
        {
            method: "GET",
            headers: {"Content-Type": "application/json"}
        }
    );
    return await response.json();
}