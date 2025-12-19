import resolve from '@rollup/plugin-node-resolve';
import commonjs from '@rollup/plugin-commonjs';
import terser from '@rollup/plugin-terser'

export default {
	input: 'src/main.js',
	output: {
		file: 'dist/codemirror.umd.js',
		format: 'umd',
		name: 'CodeMirror',
	},
	plugins: [
		resolve({
			browser: true,
			dedupe: [
				'@codemirror/state',
				'@codemirror/view',
			],
		}),
		commonjs(),
		terser(),
	]
};
