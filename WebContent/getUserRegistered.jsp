
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-8"> 


<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> 
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/jquery-ui.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.11.1/jquery.validate.min.js"></script>

<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.loadingoverlay/latest/loadingoverlay.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.loadingoverlay/latest/loadingoverlay_progress.min.js"></script>


<div class="content" id="id_content" >


	<div id="id_input">Get User Registred:
	 <table style="width:100%;text-align: center;">
	  <tr>
	
	  </tr>
	  <tbody id="">
		  <tr>
		  <td><label>Email:</label><input id="id_email_user" type="text"> </td>
		  <td><label>Password:</label><input id="id_password_user" type="text"></td>
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
  	debugger;
	
	var email= $("#id_email_user").val();
	var password= $("#id_password_user").val();
	var sendInfo={
  			
  			"email": ""+email,
  			"password":""+password
  		
  		}
	
	
	 var  returnResponse;
	$.ajax({
   
     url: "http://localhost:8180/YellowLabel/getuserRegistered",
   
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
				if(response_server.responseText =="true"){
					$('#id_result').html('L utente risulta registrato!');
					
					
				}
				else $('#id_result').html('L utente NON risulta registrato!');

				
			}
			else {
				
				$('#id_result').html('Errore durante la chiamata al servizio!');
				
			}
		}
    
});


}




</script>






 
