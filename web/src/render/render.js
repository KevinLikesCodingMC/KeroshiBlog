import { marked } from "marked";
import { TetrisDecoder } from "./tetris/decoder.js";
import { TetrisRenderer } from "./tetris/render.js";


export function renderMarkdown(markdown) {
	return marked.parse(markdown);
}

export function renderTetris(root) {
	root.querySelectorAll("code.language-tetris")
		.forEach(code => {
			const str = code.textContent.trim();
			const grid = TetrisDecoder.decode(str);
			const canvas = document.createElement("canvas");
			code.parentElement.replaceWith(canvas);
			TetrisRenderer.render(canvas, grid, true);
		});

	root.querySelectorAll("code.language-tetrispc")
		.forEach(code => {
			const str = code.textContent.trim();
			const grid = TetrisDecoder.decode(str);
			const canvas = document.createElement("canvas");
			code.parentElement.replaceWith(canvas);
			TetrisRenderer.render(canvas, grid, false);
		});
}
