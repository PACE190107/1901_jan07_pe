"use strict"; // ensures the declaration of all varaibles

console.log(f1());// Hoisting the function

 var x = 10;

// alert("never use this!!");
// document.write("bad practice - never use this!!");

console.log(x);

var y = 20;
console.log(y + " " + typeof(y));

var y = "Hello";
console.log(y + " " + typeof(y));
// this is not possible - why?
// can't redeclare var variable
// into a let variable inside the same scope
// let y = 30;
// console.log(y + " " + typeof(y));

// can't redeclare or reassign let variable into a var variable inside the same
// scope
// let z = 30;
// var z = 40;
//
// const a = 12345;
// const a = 23;
// Values of const varaibes can't be changed or reassiged or redeclared
//
// scopes- global, local, and lexical
// const - const is block scoped
// let - let is also
// var - can't be blocked inside a block scope

// var b = 10;
// if(b == 10){
// var variableDeclaredUsingVar = "go pokemon";
// }
//
// console.log(b + " inside the block global scope");
// console.log(variableDeclaredUsingVar + " inside the block local scope");


let c = 20;
if(c == 20){
	let variableDeclaredUsingVar = "go pokemon";
	console.log(c + " inside the block global scope");
	console.log(variableDeclaredUsingVar + " inside the block local scope");
}

console.log(c + " outside the block global scope");
// console.log(variableDeclaredUsingVar + " outside the block local scope");

// Commonly seen error message

// JS has methods and functions
// Functions are objects in js

let d = "20";
let e = true;
let f = {};
let g = [];
let h = null;
let i = (0/0);
// let j = Yuvi;
// let k = undefined;
// NaN - not a number

console.log("d : " + typeof(d) + "\ne : " + typeof(e) + "\nf : "  + typeof(f) + "\ng : " + 
		typeof(g) + "\nh : " + typeof(h) +"\nk : " + typeof(k) +  "\ni : "  + typeof(i) + "\nc : " + typeof(c));

// Truthy and Falsy
// Truthy - all values are considered Truthy, unless they are defined as fasly
// Falsy - false, 0, null, undefined, NaN
	// -false, 0, and null are equivalent
// - null and undefined are not equal to anything except for each other and
// themselves
// -NaN is not eqaul to anything including NaN

// simplest function
function f1(){
	return true;
}

// anonymous function
let f2 = function(){
	return false;
}


console.log(f1);  // printing function itself
console.log(f1()); // Invoking the function
console.log(typeof(f1));
console.log(f2());
console.log(typeof(f2));


// example for self invoking function
(function(){
	console.log("does this work? What does self invoking really mean");
}());

(function(){
	console.log("Oh boy - syntax 2");
})();

let l = (function (){
console.log("Syntax 2");	
})();

console.log("value of 1 " + l)

// function declaration
function add(a, b){
	return a + b;
}

// function expression
let n = function (a,b){
	return a + b;
}
// Can not hoist function expression

// ArrowFunction - ES6 feature
let dm = (a,b) => {return a + b;}
// Arrow function - concise syntax
let es = (a, b) => a + b;

// sonar lint - helps in better coding, finds bugs faster, sometime

function sqaure(x){
	return x * x;
}

// Arrow function - single argument
const p = x => x*X;
const q = x => (x*x);

const name = "Omar"
function sayName(){
const mesg = "My name is " + name;
console.log(mesg);
}



const sn = () => {
	const mesg = "My name is " + name;
	console.log(mesg);
}

// closure
// var is lexical scope not global scope and can't blocked in a block scope
// to 1,2,3
function add(){
	var count = 0;
	count += 1;
	console.log(count);
}

add(); //1
add(); //1
add(); //1

let close = (function (){
	var count = 0;
	return function() {
		count += 1;
		return count;
	}
})();

console.log(close()); //1
console.log(close()); //2
console.log(close()); //3

function SimulateMouseOver(){
	document.getElementById("ModifyByJS").innerHTML = "Did this get modify by Java Script? Really, huh";
}

var u = document.getElementById("user");
function greetPerson() {
	document.getElementById("newParagragh").innerHTML = "Hello, user " + u.value;
}

var u2 = document.getElementById("user");
u2.onfocus = function(){
	if(u2.value == ''){
		u2.style.background = "lightblue";
		u2.value = '';
	}
}

var u2 = document.getElementById("user");
u2.onfocus = function(){
	if(u2.value == ''){
		u2.style.background = "blue";
		u2.value = '';
	}
}