const computeIsomorphisms = require('./graph-isomorphisms');

const iG = [[1, 2], [2, 3], [3, 1]];
console.log('Graph A-A is a isomorphic:', computeIsomorphisms(iG, iG).length === 3);

const H = [[1, 2], [2, 1], [1, 3]];
console.log('Graph A-B is a isomorphic:', computeIsomorphisms(iG, H).length > 0);

const I = [[42, 666], [666, 1], [1, 42]];
console.log('Graph A-C is a isomorphic:', computeIsomorphisms(iG, I).length > 0);

const A = 0, B = 1, C = 2, D = 3, E = 4, F = 5, G = 6;

const M1 = [
  [A, B],
  [B, C],
  [C, E],
  [E, G],
  [G, F],
  [F, D],
  [D, A]
];

const M2 = [
  [A, D],
  [D, F],
  [F, G],
  [G, E],
  [E, C],
  [C, B],
  [B, A]
];

console.log('Graph MY1-MY2 is a isomorphic:', computeIsomorphisms(M1, M2).length > 0);
