const link = document.getElementById("loginLink");

if (link) {
	link.href += "?redirect=" +
		encodeURIComponent(
			window.location.pathname
			+ window.location.search
		);
}

const form = document.getElementById("loginForm");

if (form) {
	form.addEventListener("submit", async (e) => {
		e.preventDefault();

		const errorMessage = document.getElementById("errorMessage");
		errorMessage.classList.add("d-none");

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

		if (result.success) {
			const redirect =
				new URLSearchParams(window.location.search).get("redirect");
			window.location.href = redirect || "/";
		}
		else {
			const errorMessage = document.getElementById("errorMessage");
			errorMessage.classList.remove("d-none");
			errorMessage.innerHTML = result.code;
		}
	});

}
