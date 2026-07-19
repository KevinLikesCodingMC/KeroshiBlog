const form = document.getElementById("loginForm");

if (form) {
	form.addEventListener("submit", async (e) => {
		e.preventDefault();

		const username = document.getElementById("usernameInput").value;
		const password = document.getElementById("passwordInput").value;

		const csrfToken = document.querySelector('meta[name="_csrf"]').content;
		const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

		const response = await fetch("/api/login", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				[csrfHeader]: csrfToken,
			},
			body: JSON.stringify({
				username,
				password,
			}),
		});

		const result = await response.json();

		alert(result.code);
	});

}
