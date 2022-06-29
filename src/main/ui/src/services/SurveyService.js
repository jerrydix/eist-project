export async function submitSurvey(user, data) {
	const response = await fetch(
		`api/surveys` +
			"?" +
			new URLSearchParams({
				username: user,
			}),
		{
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(data),
		}
	);
	return await response.json();
}
