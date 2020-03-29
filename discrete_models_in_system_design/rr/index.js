const hgAlgorithm = require('./hg-alghoruthm');

// Square
const test1 = [
  [10, 19, 8, 15],
  [10, 18, 7, 17],
  [13, 16, 9, 14],
  [14, 17, 10, 19]
];
// Tall
const test2 = [
  [10, 19, 8, 15],
  [10, 18, 7, 17],
  [13, 16, 9, 14],
  [12, 19, 8, 18],
  [14, 17, 10, 19]
];
// Wide
const test3 = [
  [10, 19, 8, 15, 14],
  [10, 18, 7, 17, 17],
  [13, 16, 9, 14, 10],
  [12, 19, 8, 18, 19]
];

console.log(hgAlgorithm(test1, "min"));
console.log(hgAlgorithm(test1, "max"));
console.log(hgAlgorithm(test2, "min"));
console.log(hgAlgorithm(test2, "max"));
console.log(hgAlgorithm(test3, "min"));
console.log(hgAlgorithm(test3, "max"));
