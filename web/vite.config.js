import { defineConfig } from "vite";

export default defineConfig({
	build: {
		outDir: '../src/main/resources/static/dist',
		emptyOutDir: true,
		rollupOptions: {
			input: './src/main.js',
			output: {
				entryFileNames: 'app.js',
				assetFileNames: 'app.[ext]',
			},
		},
	},
	server: {
		host: true,
	}
})
