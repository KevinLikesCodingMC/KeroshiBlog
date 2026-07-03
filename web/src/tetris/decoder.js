export const TetrisDecoder = {
	MAP: {
		'_': 0,
		'I': 1,
		'J': 2,
		'L': 3,
		'O': 4,
		'S': 5,
		'T': 6,
		'Z': 7,
		'X': 8,
	},

	decode(str) {
		if (! str) return [];

		const lines = str.trim().split('\n');
		const col = lines[0].trim().length;
		const grid = [];

		for (const line of lines) {
			if (line.length !== col) return [];

			const row = [];

			for (const c of line) {
				const val = this.MAP[c] !== undefined ? this.MAP[c] : 0;
				row.push(val);
			}

			grid.push(row);
		}

		return grid;
	}
};