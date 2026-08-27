import { renderMarkdown } from "../render/render.js";
import { renderTetris } from "../render/render.js";

const output = document.getElementById("articleContent");
if (output) {
	const content = document.getElementById("contentInput").value;
	output.innerHTML = renderMarkdown(content);
	renderTetris(output);
}
