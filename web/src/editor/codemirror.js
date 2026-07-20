import { EditorView, basicSetup } from "codemirror";
import { EditorState } from "@codemirror/state"

const toolEditor = document.getElementById("toolEditor");

if (toolEditor) {
	new EditorView({
		state: EditorState.create({
			extensions: [basicSetup]
		}),
		parent: toolEditor
	});
}
