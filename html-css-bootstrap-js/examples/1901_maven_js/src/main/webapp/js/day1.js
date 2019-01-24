//"use strict"; //ensures the declaration of all variables

//f2();		//not working why??
			//can not hoist function expression but can hoist declared functions
console.log(f1()); 		//hoisting the function
//console.log(f2());	//why not working??? can't hoist function expression
var x = 10;

//alert("never use this!!");
//document.write("bad practice - never use this!!");

console.log(x);

var y = 20;
console.log(y + typeof(y));
y = "hello world!";
console.log(y + typeof(y));
//this is not possible - why? 
//can't redeclare or reassign var variable into a let variable inside the same scope
//let y = 30;
//console.log(y + typeof(y));

//can't redeclare or reassign let variable into a var variable inside the same scope
//let z = 30;
//var z = 40;

//const a = 12345;
//const a = 23;
//values of const variables can't changed or reassigned or redeclared

//scopes - global, local and lexical 
//const - const is block scoped
//let - let is also block scoped
//var - can't be blocked inside a block scope

var b = 10;
if (b == 10){
	var variableDeclaredUsingVar = "go pokemon"; 
	console.log(b + " inside the block - local scope");
	console.log(variableDeclaredUsingVar + " inside the block - local scope");
}
console.log(b);
console.log(variableDeclaredUsingVar);

let c = 20;
if (c == 20){
	let variableDeclaredUsingLet = "go pokemon"; 
	console.log(c + " inside the block - local scope");
	console.log(variableDeclaredUsingLet + " inside the block - local scope");
}
console.log(c + " outside the block - global scope");
//console.log(variableDeclaredUsingLet + " outside the block - global scope");

//commonly seen error messages - TypeError, NameError, ReferenceError

//js has methods and functions
//functions are objects in js

let d = "20";
let e = true;
let f = {};
let g = [];
let h = null;
let i = 0/0;
let j = function f(){}; 
//k
//NaN - Not a number

console.log(" d - " + typeof(d) + " e - " + typeof(e) + " f - " + typeof(f) + " g - " + typeof(g) + " h - " + typeof(h) + " i - " + typeof(i) + " j - " + typeof(j) + " k -  " + typeof(k) + " c - " + typeof(c) + " ")


//Truthy and Falsy
/*Truthy - all values are considered truthy unless they are defined as falsy
Falsy - False, 0, null, Undefined, NaN, ""
		- false, 0 and "" are equivalent
		- null and undefined are not equal to anything except each other and themselves
		- NaN is not equal to anything including NaN
*/

//what is so special about functions in JavaScript? 
	//functions are objects
	//functions can be anonymous
	//function can be self invoking
	//functions can be hoisted
	//functions have closures
	//functions can be callback
	//functions can be assigned to a variable
	//functions are different from methods

//simplest function
function f1(){
	return true;
}

//anonymous function

let f2 = function(){
	return false;
}

console.log(f1);		//printing the function
console.log(f1());		//invoking the function
console.log(typeof(f1));
console.log(f2);
console.log(f2());
console.log(typeof(f2));


//example for self invoking function
(function (){
	console.log("does this work? what does self invoking really mean???");
} ());

(function (){
	console.log("oh boy - syntax 1");
} ());

(function (){
	console.log("oh boy - syntax 2");
})();

let l = (function (){
	return true;
})();

console.log("value of l " + l);


//function declaration
function add(a, b){
	return a + b;
}

//function expression
let m = function (a,b){
	return a + b;
}

//ES6 feature
//Arrow function - multiple arguments
let n = (a, b) => { return a + b;}
//Arrow function - concise syntax
let o = (a, b) => a + b; 


function square(x){
	return x * x;
}

//Arrow function - single argument
const p = x => {return x * x};
const q = x => x * x;

const name = "Yuvi";
function sayName(){
	const mesg = "My name is " + name;
	console.log(mesg);
}

//Arrow function - no argument
const sn = () => {
	const mesg = "My name is " + name;
	console.log(mesg);
}
//closure
//var is lexical scope not global scope and can't blocked in a block scope

function add(){
	var count = 0;
	count += 1;
	console.log(count);
}

add();	//1
add();	//1
add();	//1


let close = (function (){
	var count =0;
	return function(){
		count += 1;
		return count;
	}
})();

console.log(close());
console.log(close());
console.log(close());

function simulateMouseOver(){
	document.getElementById("modifyThisByJS").innerHTML = "Did this get modified by JS? really, Huh";
}


function greetPerson(){
	var u = document.getElementById("user").value;
	document.getElementById("newParagraph").innerHTML = "<p> Hello, " + u + "</p>";
	console.log("value of u " + u);
}

var u2 = document.getElementById("user");
u2.onfocus = function(){
	if (u2.value ==''){
		u2.style.background = "lightblue";
		u2.value = '';
	}
}





