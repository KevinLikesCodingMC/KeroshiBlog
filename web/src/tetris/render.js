export const TetrisRenderer = {
	COLORS: {
		0: { main: '#f3f3ed' },
		1: { main: '#41afde' , top: '#43d3ff' , clear: '#3bbcf5'}, // I
		2: { main: '#1983bf' , top: '#1ba6f9' , clear: '#1893de' }, // J
		3: { main: '#ef9535' , top: '#ffbf60' , clear: '#f9a54b' }, // L
		4: { main: '#f7d33e' , top: '#fff952' , clear: '#f9de49'}, // O
		5: { main: '#66c65c' , top: '#88ee86' , clear: '#72d467' }, // S
		6: { main: '#b451ac' , top: '#e56add' , clear: '#cc5fc4' }, // T
		7: { main: '#ef624d' , top: '#ff9484' , clear: '#f97563' }, // Z
		8: { main: '#686868' , top: '#949494' , clear: '#848484' },
	},

	render(canvas, grid, is_clear = false) {
		const rows = grid.length;
		const cols = grid[0].length;

		canvas.width = cols * 20;
		canvas.height = (rows + 1) * 20;

		canvas.style.imageRendering = 'pixelated';

		const ctx = canvas.getContext('2d');

		ctx.fillStyle = this.COLORS[0].main;
		ctx.fillRect(0, 0, canvas.width, canvas.height);

		for (let i = rows - 1; i >= 0; i --) {
			let cleared = true;
			for(let j = 0; j < cols; j ++) {
				cleared &= grid[i][j] !== 0;
			}

			for(let j = 0; j < cols; j ++) {
				const x = grid[i][j];
				if (x === 0) continue;
				ctx.fillStyle = cleared && is_clear ? this.COLORS[x].clear : this.COLORS[x].main;
				ctx.fillRect(j * 20, (i + 1) * 20, 20, 20);
				ctx.fillStyle = this.COLORS[x].top;
				ctx.fillRect(j * 20, i * 20 + 16, 20, 4);
			}
		}
	}

};
