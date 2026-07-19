const logout = document.getElementById("logoutLink");

if (logout) {
	logout.addEventListener("click", async (e) => {
		e.preventDefault();

		const csrfToken = document.querySelector('meta[name="_csrf"]').content;
		const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

		const response = await fetch("/api/logout", {
			method: "POST",
			headers: {
				[csrfHeader]: csrfToken,
			},
		});

		const result = await response.json();

		if (result.success) {
			location.reload();
		}
	});
}
