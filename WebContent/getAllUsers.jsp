
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-8"> 


<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> 
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/jquery-ui.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.11.1/jquery.validate.min.js"></script>

<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.loadingoverlay/latest/loadingoverlay.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.loadingoverlay/latest/loadingoverlay_progress.min.js"></script>


<div class="content" id="id_content" >


	<div id="id_input">GetAllUsers:
	 <table style="width:100%;text-align: center;">
	  <tr>
	
	  </tr>
	  <tbody id="">
		  <tr>
		  	
		  	<td><input id="id_invia" type="button" id="id_invia" class="btn btn-primary" value="Invia" onclick="getValue();" ></td>
		  </tr>
	  
	  </tbody>
	</table> 
	
	</div>

</div>

<div id="id_result">	   

I risultati sono:

 <table style="width:100%">
  <tr>
    <th>id</th>
    <th>firstName</th>
    <th>middleName</th>
    <th>lastName</th>
    <th>gender</th>
	<th>dob</th>
	<th>email</th>
	<th>password</th>
<!-- 	<th>Seleziona</th> -->
  </tr>
  <tbody id="tbody_id" style="text-align: center;">
  
  </tbody>
</table> 

</div>



<script type="text/javascript">
$(document).ready(function(){
	
	$('#id_result').hide();
   
});


function getValue() {
  	
	 var  returnResponse;
	$.ajax({
   
     url: "http://localhost:8180/YellowLabel/getAllUser",
   
    type: 'POST',
    dataType:"json",
    contentType: "application/json;",
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
				
				mostraRisultato(response_server)
				
			}
			else {
				
				$('#id_result').html('Errore durante la chiamata al servizio!');
				
			}
		}
    
});


}


function mostraRisultato(response_server){
debugger;
	$('#id_result').show();
	var obj=response_server.responseText;

var json = JSON.parse(obj);
	
	
var totale="";
	for (var i = 0; i < json.length; i++) {
		
		   var riga="";
	   	
	     riga = "<tr>"+
	     "<td  style=\"\"><input readonly id=\"id_id"+i+"\" type=\"text\" value=\""+json[i].id+"\"></td>"+
	     "<td  style=\"\"><input readonly id=\"id_firstName"+i+"\" type=\"text\" value=\""+json[i].firstName+"\"></td>"+
	     "<td  style=\"\"><input readonly id=\"id_middleName"+i+"\" type=\"text\" value=\""+json[i].middleName+"\"></td>"+
	     "<td  style=\"\"><input readonly id=\"id_lastName"+i+"\" type=\"text\" value=\""+json[i].lastName+"\"></td>"+
	    
	     "<td  style=\"\"><input readonly id=\"id_gender"+i+"\" type=\"text\" value=\""+json[i].gender+"\"></td>"+
	     "<td  style=\"\"><input readonly id=\"id_dob"+i+"\" type=\"text\" value=\""+json[i].dob+"\"></td>"+
	     "<td  style=\"\"><input readonly id=\"id_email"+i+"\" type=\"text\" value=\""+json[i].email+"\"></td>"+
	     "<td  style=\"\"><input readonly id=\"id_password"+i+"\" type=\"text\" value=\""+json[i].password+"\"></td>"+
// 	     "<td><input type=\"button\" id=\"id_seleziona\" class=\"btn btn-primary\" value=\"Seleziona\" onclick=\"seleziona("+i+");\"></td>"
	     		"</tr>";
			totale= totale+ riga;
	}
	
	$('#tbody_id').html(totale); 
}

</script>






 

