window.onload = function(){
	console.log("pokemon.js loaded");
	document.getElementById("pokeBtn").addEventListener("click",getPokemon);
}

function getPokemon(){
	 let pkmnId = document.getElementById("pokeId").value;
	 console.log('pokemon id ' + pkmnId);
	 fetch('https:pokeapi.co/api/v2/pokemon/' + pkmnId)
	 .then(function(response){
		 return response.json();
	 })
	 .then(function(data){
		 console.log(data);
		 document.getElementById("pokeImg").src = data.sprites.front_shiny;
		 //var pokemon = data;
	 })
}