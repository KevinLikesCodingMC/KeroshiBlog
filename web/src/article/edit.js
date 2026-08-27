import { EditorView, basicSetup } from "codemirror";
import { EditorState } from "@codemirror/state"

const form = document.getElementById("articleEditForm");
if (form) {

	const nameInput = document.getElementById("nameInput");
	const isHiddenInput = document.getElementById("isHidden");

	nameInput.addEventListener("keydown", (event) => {
		if (event.key === "Enter") {
			event.preventDefault();
		}
	});

	const title = document.getElementById("titleInput").value;
	const content = document.getElementById("contentInput").value;

	const titleEditor = new EditorView({
		state: EditorState.create({
			doc: title,
			extensions: [basicSetup]
		}),
		parent: document.getElementById("titleEditor")
	});

	const contentEditor = new EditorView({
		state: EditorState.create({
			doc: content,
			extensions: [basicSetup]
		}),
		parent: document.getElementById("contentEditor")
	});

	form.addEventListener("submit", async (e) => {
		e.preventDefault();

		const id = document.body.dataset.id;
		const name = nameInput.value;
		const title = titleEditor.state.doc.toString();
		const content = contentEditor.state.doc.toString();
		const hidden = isHiddenInput.checked;


		const csrfToken = document.querySelector('meta[name="_csrf"]').content;
		const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

		const response = await fetch("/api/article/edit", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				[csrfHeader]: csrfToken,
			},
			body: JSON.stringify({
				id, name, title, content, hidden
			}),
		});
	});
}
