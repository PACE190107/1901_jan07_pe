"use strict";  //ensures variables have to be declared

//f2();  //hoisting the function
var x = 10;

//alert("never use this - very annoying");
//document.write("don't use this either - replaces entire document");

console.log(x);

//data type is implicit - don't have to declare if strict isn't enabled
var y = 20;
console.log(y + typeof(y));
y = "hello";
console.log(y + typeof(y));
//this is not possible because var variable cannot be redeclared
//or reassigned into let inside of the same scope
//
//let y = 30;
//console.log(y + typeof(y));

//this is not possible because let variable cannot be redeclared
//or reassigned into var inside of the same scope
//
//let z = 30;
//var z = 40;

//values of const can't be changed or reassigned or redeclared
//const a = 12345;
//const a = 23;

//scopes - global, local, lexical
//let = block
//var = can't be blocked inside of a block scope - goes through block scope
//const = 

var b = 10;
if (b == 10){
	var variableDeclaredUsingVar = "some string";
	console.log(b + "local scope - inside the block");
	console.log(variableDeclaredUsingVar + "local scope - inside the block");
}
console.log(b + "global scope - inside the block");
console.log(variableDeclaredUsingVar + "global scope - inside the block");
//var goes through block scope

let c = 20;
if (c == 10){
	let variableDeclaredUsingLet = "some string";
	console.log(c + "local scope - outside the block");
	console.log(variableDeclaredUsingLet + "local scope - outside the block");
}
console.log(c + "global scope - inside the block");
//console.log(variableDeclaredUsingLet + "global scope - inside the block");

//js has methods and functions
//functions are objects in js

let d = "20";
let e = true;
let f = {};
let g = [];
let h = null;
let i = 0/0;
//reference issue let j = Yuvi;
let j = function f(){};
//k
//NaN - not a number
console.log(typeof(d) + " " +typeof(e) + " " +typeof(f) + " " +typeof(g) + " " +typeof(h) + " " +typeof(i) + " " + typeof(j) + " " +typeof(k) + " " + typeof(c));

/*Truthy and falsey
truthy - all values are considered truthy unless they are defined as falsey
Falsey - False, 0, null, undefined, NaN, ""

 -false, null, and 0 are equivalent
 -null and undefined are not equal to anything except each other and themselves
 -NaN is not equal to anything including NaN
 -null is also an object

*/

//simplest function
//function that belongs to an object is a method, otherwise it's an object
function f1(){
	return true;
}

//anonymous function
let f2 = function(){
	return true;
}

console.log(f1); // printing the function
console.log(f1()); //invoking the function
console.log(typeof(f1));  
console.log(f2);
console.log(f2());
console.log(typeof(f2));

//example for self-invoking function
(function (){
	console.log("does this work?  what does self-invoking really mean?");
} ());

(function (){
	console.log("oh boy - syntax 1");
} ());

(function (){
	console.log("oh boy - syntax 2");
} )();

let l = (function (){
	return true;
})();

console.log("value of l " + l);

//function declaration - can be hoisted
function add(a, b){
	return a + b;
}

//function expression - cannot be hoisted
let m = function (a, b){
	return a + b;
}

//Arrow function - ES6 feature
//multiple arguments
let n= (a, b) => {return a + b;}
//arrow function - concise syntax
let o = (a, b) => a + b;

function square(x){
	return x * x;
}

//arrow function-single argument
const p = x => x * x;
//or
const q = x => {return x * x};

const name = "Josh";
function sayName(){
	const message = "My name is " + name;
	console.log(message);
}

//Arrow function - no argument

const sn = () => {
	const message = "My name is " + name;
	console.log(message);
}

//closure
//var is lexical scope not global scope + can't be blocked in a block scope
//to 1,2,3
//if var count is declared outside of function it'll be available for any function to use
function add(){
	var count = 0;
	count += 1;
	console.log(count);
}

add();
add();
add();

let close = (function (){
	var count = 0;
	return function (){
		count += 1;
		return count;
	}
})();

console.log(close());
console.log(close());
console.log(close());

function simulateMouseOver(){
	document.getElementById("modifyThisByJS").innerHTML = "Did this get modified by JS?";
}


function greetPerson(){
	var u = document.getElementById("user").value;
	document.getElementById("newParagraph").innerHTML = "Hello, " + u;
}


u.onblur = function (){
	var u2 = document.getElementById("user");
	if (u2.value == ''){
		u2.style.background = "lightblue";
		u2.value = '';
	}
}

//simple callback function example
window.onload = function(){
	var c1 = function(msg){
		console.log(msg);
	}
	
	function run(c2){
		c2('running call back function');
	}
	
	c1('this is not a call back function');
	run(c1);
}

//json vs java script object
let j1 = [{name:'tom',age:35},{name:'mike',age:20}]; //object

//convert js object to json -> stringify
let j2 = JSON.stringify(j1); //string

//convert string to js object
console.log(JSON.parse(j2)); //object




