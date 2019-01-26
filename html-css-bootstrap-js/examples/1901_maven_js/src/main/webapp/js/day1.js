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

//commonly seen error messages - TypeError, NameError, ReferenceError, 
//SyntaxError, EvalError, URIError, RangeError

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

var a = 123;
var a = "asdfsadfsd";
console.log(a);
//let a = 456;

//Redeclaring a var variable with var, in the same scope or same block - allowed 
//Redeclaring a let variable with var, in the same scope or same block - not allowed
//Redeclaring a var variable with let, in the same scope or same block - not allowed
//Redeclaring a let variable with let, in the same scope or same block - not allowed
//Redeclaring a let variable with let, in the another scope - allowed

console.log("value of c- " + c);
//c = 40;
if(true){
	let c = 50;
	console.log("value of c- " + c);
}

//var c = 60;


//simple example for call back function
var c1 = function(msg){
	console.log(msg);
}
//	c1('hello message');
//	c1("this is not a call back function, because we're calling the function");
function run(plsPassAFunction){
	plsPassAFunction('this is an example for call back function');
}
run(c1); //this is callback function

//json vs java script object
let j1 = [{name:'tom',age:21},{name:'mike',age:20}];
console.log(j1);
console.log(typeof(j1));	//object

//convert js object to json - stringify
let j2 = JSON.stringify(j1);
console.log(j2);
console.log(typeof(j2));	//json -string

//convert string to js object
console.log(JSON.parse(j2));
console.log(typeof(JSON.parse(j2)));	//object

var j4 = [];
//Array methods 
//filter - filter from an array of value 
//shift  - to remove the first element of an array and returns that element
//length - returns the size of an array	myArray.length
//split  - split a string based on a delimiter var myArray = myData.split(',');
//toString - to convert an array to string (doesn't need a parameter) 
//join	- converts an array to string (takes a parameter to join)
//push	- to add a value at the end of an array
//pop		- to remove a value at the end of an array
//unshift() and shift() work in exactly the same way as push() and pop(), respectively, except that they work on the beginning of the array, not the end.

x = "my name is ${name}";
name = 'Yuvi';

console.log(x);	//prints my name is ${name}
a = `my name is ${name}`
console.log(a);	//prints my name is Yuvi

//create an object using literal 
let o1 = {name:"WATMAN", nickname: "WAT", age: "WAT"};

//is JavaScript pass by reference or value?
//pass by value for arguments
//pass by reference for objects
let o2 = o1;

for (let x in o1){
	console.log(x + " - " + o1[x]);
}

o1.specialMove = "confusion";
o1.superPower = "WAT POWER";
o1.occupation = "does WAT?";

for (let x in o1){
	console.log(x + " - " + o1[x]);
}

//create an object using new keyword
let o3 = new Object();
o3.name = "Carl Lucas" ;
o3.hero = "Luke Cage" ;
o3.occupation = "King of Harlem";
//o3.gift = "punch holes in a steel wall";
o3.gift = function(){
	return "bullet proof";
}

//create object using constructor 
function o4(name, hero, occupation){
	this.name = name;
	this.hero = hero;
	this.occupation = occupation;
	this.gift = function(){
		return "bullet proof";
	}
}




