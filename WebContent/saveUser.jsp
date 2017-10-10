
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-8"> 


<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> 
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/jquery-ui.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.11.1/jquery.validate.min.js"></script>

<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.loadingoverlay/latest/loadingoverlay.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.loadingoverlay/latest/loadingoverlay_progress.min.js"></script>


<div class="content" id="id_content" >


	<div id="id_input">Campi di input:
	 <table style="width:100%;text-align: center;">
	  <tr>
	    <th>firstName</th>
	 	<th>middelName</th>
	    <th>lastName</th>
		<th>gender</th>
		<th>dob</th>
		<th>email</th>
		<th>password</th>
	
	  </tr>
	  <tbody id="">
		  <tr>
		  	<td><input id="id_firstName" type="text"> </td>
		  	<td><input id="id_middleName" type="text"> </td>
		  	<td><input id="id_lastName" type="text"> </td>
		  	<td><input id="id_gender" type="text"> </td>
		  	<td><input id="id_dob" type="text"> </td>
		  	<td><input id="id_email" type="text"> </td>
		  	<td><input id="id_password" type="text"> </td>
		  	
		  	<td><input id="id_invia" type="button" id="id_invia" class="btn btn-primary" value="Invia" onclick="getValue();" ></td>
		  </tr>
	  
	  </tbody>
	</table> 
	
	</div>

</div>


<div id="id_result"></div>




<script type="text/javascript">
$(document).ready(function(){
	
	$('#id_result').hide();
   
});


function getValue() {

  	
  	var sendInfo={
  			"firstName": ""+firstName,
  			"middleName": ""+middleName,
  			"lastName": ""+lastName,
  			"gender": ""+gender,
  			"dob": ""+dob,
  			"email": ""+email,
  			"password": ""+password
  		}
  	
	 var  returnResponse;
	$.ajax({
   
     url: "http://localhost:8180/YellowLabel/getAllUser",
   
    type: 'POST',
    dataType:"json",
    contentType: "application/json;",
    data: JSON.stringify(sendInfo),
//     async: false, 
    success: function(msg) {
    	 returnResponse=msg;
    	
    	},
// 		error: function()
// 		{
// 		    $('.content').html('Si ט verificato un errore durante la chiamata al servizio!');
// 		},
		complete:function(response_server){
			debugger;
			$('#id_result').show();
			if(response_server.status == 200){
				
// 				alert("call ok"+response_server.responseText);
				
				
				   $("#id_result").html("Salvataggio avvenuta con successo!");
			}
			else {
				
// 				alert("call ko "+response_server.responseText);
				$('#id_result').html('Errore durante la chiamata al servizio!');
				
			}
			
			
			$('#id_firstName').val("");
		 	$('#id_middleName').val("");
		    $('#id_lastName').val("");
			$('#id_gender').val("");
			$('#id_dob').val("");
			$('#id_email').val("");
			$('#id_password').val("");
		}
    
});


}

</script>






 

