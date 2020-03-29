const hgAlgorithm = require('./hg-alghoruthm');

// Square
const matrix1 = [
  [10, 19, 8, 15],
  [10, 18, 7, 17],
  [13, 16, 9, 14],
  [14, 17, 10, 19]
];
// Tall
const matrix2 = [
  [10, 19, 8, 15],
  [10, 18, 7, 17],
  [13, 16, 9, 14],
  [12, 19, 8, 18],
  [14, 17, 10, 19]
];
// Wide
const matrix3 = [
  [10, 19, 8, 15, 14],
  [10, 18, 7, 17, 17],
  [13, 16, 9, 14, 10],
  [12, 19, 8, 18, 19]
];

console.log(hgAlgorithm(matrix1, "min"));
console.log(hgAlgorithm(matrix1, "max"));
console.log(hgAlgorithm(matrix2, "min"));
console.log(hgAlgorithm(matrix2, "max"));
console.log(hgAlgorithm(matrix3, "min"));
console.log(hgAlgorithm(matrix3, "max"));
