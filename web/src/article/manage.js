const articleManage = document.getElementById("articleManage");

if (articleManage) {
	const newBtn = articleManage.querySelector('#newBtn');
	const refreshBtn = articleManage.querySelector('#refreshBtn');

	function renderArticleList (articles) {

		articles.reverse();

		const tbody = document.getElementById('articleTableBody');
		tbody.innerHTML = '';

		const viewBtnTemplate = articleManage.querySelector('#viewBtn');
		const editBtnTemplate = articleManage.querySelector('#editBtn');

		articles.forEach((article, index) => {
			const tr = document.createElement('tr');

			const tdIndex = document.createElement('td');
			tdIndex.textContent = index + 1;

			const tdName = document.createElement('td');
			tdName.textContent = article.name;

			const tdTime = document.createElement('td');
			const date = new Date(article.createTime);
			tdTime.textContent = date.toLocaleString();

			const tdAuthor = document.createElement('td');
			tdAuthor.textContent = article.authorName;

			const tdActions = document.createElement('td');

			const viewBtn = viewBtnTemplate.cloneNode(true);
			viewBtn.dataset.id = article.id;
			viewBtn.addEventListener('click', () => {
				window.location.href = `/article/view/${article.id}`;
			});
			const editBtn = editBtnTemplate.cloneNode(true);
			editBtn.dataset.id = article.id;
			editBtn.addEventListener('click', () => {
				window.location.href = `/article/edit/${article.id}`;
			});
			tdActions.appendChild(viewBtn);
			tdActions.appendChild(editBtn);

			tr.appendChild(tdIndex);
			tr.appendChild(tdName);
			tr.appendChild(tdTime);
			tr.appendChild(tdAuthor);
			tr.appendChild(tdActions);

			tbody.appendChild(tr);
		});
	}

	async function loadArticleList() {
		const response = await fetch("/api/article/list/me", {
			method: "GET",
			headers: {
				"Accept": "application/json",
			},
		});

		const result = await response.json();
		if (result.success) {
			renderArticleList(result.data);
		}
		else {
			console.log(result.code);
			alert(result.code);
		}
	}

	async function newArticle() {
		const csrfToken = document.querySelector('meta[name="_csrf"]').content;
		const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

		const response = await fetch("/api/article/new", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				[csrfHeader]: csrfToken,
			},
		});

		const result = await response.json();
		if (! result.success) {
			console.log(result.code);
			alert(result.code);
		}

		loadArticleList();
	}

	refreshBtn.addEventListener('click', loadArticleList);
	newBtn.addEventListener('click', newArticle);

	loadArticleList();
}
