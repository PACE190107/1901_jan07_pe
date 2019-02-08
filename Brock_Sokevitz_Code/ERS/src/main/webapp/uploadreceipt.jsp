<html>
<head>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>Login</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<style>
body {
	background-color: #6C5B7B;
}
</style>
</head>
<body>
<div class="mt-5"></div>

	<div class="container col-lg-4 offset-lg-4 jumbotron">
	<div class="btn-toolbar" style="width:100%">
<form action="/ERS/login" method="POST">
						<input type="submit" value="Home" name="button"
							class="list-group-item">
					</form>
					
									<form action="/ERS/login" method="POST">
						<input type="submit" value="Profile" name="button"
							class="list-group-item">
					</form>	
					
										<form action="/ERS/login" method="POST">
						<input type="submit" value="logout" name="button"
							class="list-group-item">
					</form>
</div>
		<form action="/ERS/login" method="POST" enctype = "multipart/form-data" id="uploadForm">
			<div class="form-group centered">
				<label for="username">Would you like to Upload a receipt?</label> <input type = "file" id="file" name ="file" size = "50" class="form-control-file">
			</div>

			<div>
				<input type="submit" value="upload" name="button"
					class="btn btn-primary"> 
			</div>
		</form><form  action="/ERS/login" method="POST"><input type="submit"
					value="cancel" name="button" class="btn btn-danger"></form>
	</div>
	<script>
	window.onload = function () {
		  var form = document.getElementById('uploadForm'),
		      imageInput = document.getElementById('file');

		  form.onsubmit = function (event) {
		    var isValid = /\.pne?g$/i.test(imageInput.value);
		    if (!isValid) {
		      alert('Please enter a png file!');
		      event.preventDefault();
		    }

		    return isValid;
		  };
		};
	</script>
</body>
</html>