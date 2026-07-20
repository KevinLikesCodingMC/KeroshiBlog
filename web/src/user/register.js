const form = document.getElementById("registerForm");

if (form) {
	form.addEventListener("submit", async (e) => {
		e.preventDefault();

		const username = document.getElementById("usernameInput").value;
		const password = document.getElementById("passwordInput").value;
		const inviteCode = document.getElementById("inviteInput").value;

		const csrfToken = document.querySelector('meta[name="_csrf"]').content;
		const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

		const response = await fetch("/api/register", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				[csrfHeader]: csrfToken,
			},
			body: JSON.stringify({
				username,
				password,
				inviteCode
			}),
		});

		const result = await response.json();

		if (result.success) {
			window.location.href = "/login";
		}
		else {
			const errorMessage = document.getElementById("errorMessage");
			errorMessage.classList.remove("d-none");
			errorMessage.innerHTML = result.code;
		}
	});

}
