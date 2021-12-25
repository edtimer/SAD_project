<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	background-color: #f2f7fb
}

#vol {
	height: 200px;
}

h4 {
	padding: 200px;
}
</style>
</head>
<body>
	<div class="container fluid">

		<div class="row">
			<h4 style="text-align: center;">This part is a mock for SAgile
				since its the source of the text file</h4>
			<div id="vol" class="row mt-100 ">
				<div class="row d-flex justify-content-center mt-100">
					<div class="col-md-6">
						<div class="card">
							<div class="card-header">
								<h5>File Upload</h5>
							</div>
							<div class="card-block">
								<form action="upload" class="dropzone dz-clickable"
									method="post" enctype="multipart/form-data"
									accept-charset="UTF-8">
									<div class="input-group mb-3">
										<input type="file" class="form-control" id="file" name="file"
											onchange="checkeExtension(this.value);"> <input
											type="submit" class="btn btn-primary" value="Upload Now">
									</div>
								</form>
								<div class="text-center m-t-20"></div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		function checkeExtension(filename) {
			var type = $("input[type='file']").val();
			
			if (type.includes("json")) {
				submitEl.disabled = false;
				return true;
			} else {

				setTimeout("location.reload(true);", 1000);

				alert('Invalid file type: ,please select another file system stopped processing');
				submitEl.disabled = true;

				return false;
			}
		}
	</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.js"></script>
</body>
</html>