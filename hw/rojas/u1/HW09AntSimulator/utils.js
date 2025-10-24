// Owner/Author: Josue Rojas
export const clamp = (v, lo, hi) => Math.max(lo, Math.min(hi, v));
export const choice = (arr) => arr[Math.floor(Math.random() * arr.length)];

export const DIRS = [
  { dx: 1, dy: 0 }, { dx: -1, dy: 0 },
  { dx: 0, dy: 1 }, { dx: 0, dy: -1 },
  { dx: 1, dy: 1 }, { dx: 1, dy: -1 },
  { dx: -1, dy: 1 }, { dx: -1, dy: -1 },
];

export function neighbors(x, y, w, h) {
  const nb = [];
  for (const d of DIRS) {
    const nx = clamp(x + d.dx, 0, w - 1);
    const ny = clamp(y + d.dy, 0, h - 1);
    if (!(nx === x && ny === y)) nb.push({ x: nx, y: ny, dx: d.dx, dy: d.dy });
  }
  return nb;
}
