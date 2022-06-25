export async function submitSurvey(data) {
    const response = await fetch(`api/surveys`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    })
    return await response.json();
}