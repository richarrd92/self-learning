"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var name = 'John Doe';
name = String(10);
console.log(name);
var guess = "10test";
var parsedNumber = parseInt(guess);
var convertedNumber = Number(guess);
console.log("parsedNumber", parsedNumber);
console.log("convertedNumber", convertedNumber);
var numList = [1, 2, 3, 4, 5];
console.log("numList", numList);
var foundNumber = numList.find(function (n) { return n === 22; });
if (foundNumber !== undefined) {
    console.log("foundNumber", foundNumber);
}
else {
    console.log("Number not found, returned ".concat(foundNumber));
}
// const enum Color {
//     Red,
//     Green,
//     Blue
// }
// let myColor: Color = Color.Red;
// console.log("myColor", myColor);
var thisUnknown;
thisUnknown = "Hello, world!";
thisUnknown = 42;
console.log("thisUnknown", thisUnknown);
