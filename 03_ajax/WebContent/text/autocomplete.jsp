<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Autocomplete - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</head>
<body>
 
<div class="ui-widget">
  <label for="tags">우리반 이름 : </label>
  <input id="tags">
</div>
<script>

$("#tags").autocomplete({
	source: function(request, response){
		//console.log(request);
		//console.log(response);
		//response([{label:'a', value:'a'}, {label:'b', value:'b'}]);
		
		$.ajax({
			url: "<%= request.getContextPath() %>/autocomplete",
			data: {
				search: request.term
			},
			success: function(data){
				console.log(data);
			},
			error: function(xhr, status, err){
				console.log(xhr, status, err);
			}
		})
	}
});

</script>

</body>
</html>