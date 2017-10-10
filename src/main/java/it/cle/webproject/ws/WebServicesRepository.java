package it.cle.webproject.ws;



import it.cle.project.config.AppConfig;
import it.cle.project.model.IndiceSupportoReteSociale;
import it.cle.project.model.User;
import it.cle.project.service.IndiceSupportoReteSocialeService;
import it.cle.project.service.UserService;
import it.cle.webproject.dto.ResultDTO;
import it.cle.webproject.dto.ResultDTOXML;
import it.cle.webproject.utils.Utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;



// TODO: Auto-generated Javadoc
/**
 * The Class WebServicesRepository.
 *
 * @author convertini leo
 * Classe contenente i servizi esposti 
 */
@Controller
public class WebServicesRepository {
	
	
	final static String pathSviluppo="/home/leo/Scrivania/Lavoro/Fertilità terreno/png/savedFile.png";
	final static String  pathProduzione="/home/apache-tomcat-7.0.42/webapps/ROOT/immaginiScaricateServizio/savedFile.png";

	
	
	final static int responsecodeOk=200;
	/** The request. */
	@Autowired
	private HttpServletRequest request;
 
	/** The logger. */
	protected static Logger logger = Logger.getLogger(WebServicesRepository.class);
	
	/** The ctx. */
	private ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	
	/** The indice supporto rete sociale service. */
	private IndiceSupportoReteSocialeService indiceSupportoReteSocialeService = (IndiceSupportoReteSocialeService) ctx.getBean("indiceSupportoReteSocialeService");
	
	
	private UserService userService_ws= (UserService) ctx.getBean("userService");
	
	/**
	 * Gets the indice supporto rete sociale json schema.
	 *
	 * @return the indice supporto rete sociale json schema
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/indiceSupportoReteSocialeJSONSchema", method = RequestMethod.GET)
	@ResponseBody
	public ResultDTO getIndiceSupportoReteSocialeJSONSchema() throws Exception {
		ResultDTO result = new ResultDTO();
		Map<String, Object> map = Maps.newHashMap();
		map.put("JSONSchema", Utils.JSONSchemaMapper(IndiceSupportoReteSociale.class));
		result.setMap(map);
		return result;
	}
	
    
	
	/**
	 * Gets the indice supporto rete sociale.
	 *
	 * @param indiceSupportoReteSociale the indice supporto rete sociale
	 * @param errors the errors
	 * @return the indice supporto rete sociale
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/indiceSupportoReteSociale", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO getIndiceSupportoReteSociale(@Valid @RequestBody IndiceSupportoReteSociale indiceSupportoReteSociale,Errors errors) throws Exception {
		if (errors.hasErrors()) {
			ResultDTO reserr = new ResultDTO();
			Map<String, Object> maperr = Maps.newHashMap();
			maperr.put("OK", "Errore nella richiesta");
			reserr.setMap(maperr);
			return reserr;
		}
		ResultDTO result = new ResultDTO();
		Map<String, Object> map = Maps.newHashMap();
		
		
		Integer vsoc = indiceSupportoReteSocialeService.calcolaIndiceSupportoReteSociale(indiceSupportoReteSociale);
		String codificaVsoc = indiceSupportoReteSocialeService.codificaPsoc(vsoc);
		map.put("indiceSupportoReteSociale", vsoc+ " - "+codificaVsoc);
		map.put("OK", "Calcolo effettuato");
		
		result.setMap(map);
		
				
				
		return result;
	}
	
	
	/**
	 * Gets the indice supporto rete sociale xml.
	 *
	 * @param indiceSupportoReteSociale the indice supporto rete sociale
	 * @param errors the errors
	 * @return the indice supporto rete sociale xml
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/indiceSupportoReteSocialeXML", method = RequestMethod.POST,headers="Accept=application/xml")
	@ResponseBody
	public ResultDTOXML getIndiceSupportoReteSocialeXML(@Valid @RequestBody IndiceSupportoReteSociale indiceSupportoReteSociale,Errors errors) throws Exception {
		if (errors.hasErrors()) {
			ResultDTOXML reserr = new ResultDTOXML();
			Map<String, Object> maperr = Maps.newHashMap();
			maperr.put("OK", "Errore nella richiesta");
			reserr.setMap(maperr);
			return reserr;
		}
		ResultDTOXML result = new ResultDTOXML();
		Map<String, Object> map = Maps.newHashMap();
				
		Integer vsoc = indiceSupportoReteSocialeService.calcolaIndiceSupportoReteSociale(indiceSupportoReteSociale);
		String codificaVsoc = indiceSupportoReteSocialeService.codificaPsoc(vsoc);
		map.put("indiceSupportoReteSociale", vsoc+ " - "+codificaVsoc);
		map.put("OK", "Calcolo effettuato");
		
		result.setMap(map);
		
		return result;
	}
	
	/**
	 * Gets the monitoring.
	 *
	 * @return the monitoring
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/monitoring/monitoring", method = RequestMethod.GET,headers="Accept=application/xml")
	@ResponseBody
	public String getMonitoring() throws Exception {
		
		String resultOK ="<html> "+
						"<head>"+
						"<style type=\"text/css\"></style>"+
						"</head>"+
						"<body>"+
						"OK"+
						"</body>"+
						"</html>";
		String resultError ="<html> "+
				"<head>"+
				"<style type=\"text/css\"></style>"+
				"</head>"+
				"<body>"+
				"ERROR"+
				"</body>"+
				"</html>";
		
		try {
			
		} catch (Exception e) {
			logger.error("Eccezione : "+ e.getMessage());
			return resultError;
		}
		return resultOK;
	}
	
	//	@RequestMapping(value = "/selectReportPrestazioniXML", method = RequestMethod.POST, headers="Accept=application/xml")
//	@ResponseBody
//	public ResultDTOXML selectReportPrestazioniXML(@RequestBody ReportPrestazioniRequestDTO reportPrestazioniRequestDTO) throws Exception {
		@RequestMapping(value = "/rest/grass/status/{starTime}/{endTime}/{lon}/{lat}", method = RequestMethod.GET,headers="Accept=application/json")
		@ResponseBody
		public  String getConCordinate(@PathVariable("starTime") String starTime,@PathVariable("endTime") String endTime,@PathVariable("lon") String lon, @PathVariable("lat") String lat) {
			logger.info("Start getStatus getConCordinate starTime ="+starTime);
		
			String result = "";
			 
			 try {
	            
				 URL url = new URL("https://actinia.mundialis.de/sentinel2_query?start_time="+starTime+"&end_time="+endTime+"&lon="+lon+"&lat="+lat+"&cloud_covert=10");
//				 URL url = new URL("https://actinia.mundialis.de/sentinel2_query?start_time=2017-01-01T12%3A00%3A00&end_time=2017-01-01T12%3A40%3A00&lon=-40.5&lat=-53&cloud_covert=10");
	            System.out.println("URL CHIAMATA getConCordinate :" +url.toString());
	            
	            Base64 b = new Base64();
	            String encoding = b.encodeAsString(new String("harvestplus:h#rv35tPlu5Pa55").getBytes());

	            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");
	            connection.setDoOutput(true);
	            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
//	            connection.setRequestProperty("Access-Control-Allow-Origin", "*");
	            
	            
	            
	            int responseCode = connection.getResponseCode();
	            logger.info("response code getConCordinate  "+connection.getResponseCode());
	            if (responseCode == responsecodeOk ) {
	            
	            InputStream content = (InputStream)connection.getInputStream();

	            StringBuilder responseStrBuilder = new StringBuilder();
	            BufferedReader in   = 
	                new BufferedReader (new InputStreamReader (content));
	            String line;
	            while ((line = in.readLine()) != null) {
	            	 responseStrBuilder.append(line);
	            }
	        
	            
	            result = responseStrBuilder.toString();
	           System.out.println("RESULT prima chiamata con cordinate e tempo "+result); 

	           
	        content.close();
				in.close();
			
			 } 
			 }
		        catch(Exception e) {
		            e.printStackTrace();
		        }
			 return result;
		}
		
		
		
		
			@RequestMapping(value = "/rest/grass/status/{ambiente}/{sceneId}/{south_lat}/{north_lat}/{east_lon}/{west_lon}/{south_lat_small}/{north_lat_small}/{east_lon_small}/{west_lon_small}", method = RequestMethod.GET,headers="Accept=application/json")
			@ResponseBody
			public  String getImage(@PathVariable("ambiente") String ambiente,@PathVariable("sceneId") String sceneId,
					@PathVariable("south_lat") String south_lat,@PathVariable("north_lat") String north_lat,
					@PathVariable("east_lon") String east_lon,@PathVariable("west_lon") String west_lon,
					@PathVariable("south_lat_small") String south_lat_small,
					@PathVariable("north_lat_small") String north_lat_small,
					@PathVariable("east_lon_small") String east_lon_small,@PathVariable("west_lon_small") String west_lon_small) {
				
				
				
				logger.info("Start getSaveAndCutImg   con ambiente="+ambiente+" sceneId="+sceneId+ " south_lat "+south_lat+" north_lat "+north_lat+ " east_lon "+east_lon+ " west_lon "+west_lon);

				String result_call="";
				String resource_id="";
				String resultSalvataggioImmagine="KO - Salvataggio non avvenuto";
				 try {
					 //passo sceneId

		           URL url = new URL ("https://actinia.mundialis.de/sentinel2_process/ndvi/"+sceneId);

		           logger.info("URL CHIAMATA getStatus con url  :" +url.toString());

		           Base64 b = new Base64();
		           String encoding = b.encodeAsString(new String("harvestplus:h#rv35tPlu5Pa55").getBytes());

		           HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		           connection.setRequestMethod("POST");
		           connection.setDoOutput(true);
		           connection.setRequestProperty  ("Authorization", "Basic " + encoding);
		           
		           int responseCode = connection.getResponseCode();
		          logger.info("response code "+connection.getResponseCode());
		          if (responseCode == responsecodeOk ) {
					
			
		           InputStream content = (InputStream)connection.getInputStream();
		          
		           
		           StringBuilder responseStrBuilder = new StringBuilder();
		           BufferedReader in   =
		               new BufferedReader (new InputStreamReader (content));
		           String line;
		           while ((line = in.readLine()) != null) {
		           	 responseStrBuilder.append(line);
		           }

		          JSONObject result = new JSONObject(responseStrBuilder.toString());
		         logger.info("Response : \n "+result);


		         resource_id=result.getJSONObject("urls").getString("status");
		       //ottengo lo stato
		        logger.info("Campo STATUS : \n"+resource_id);

		       content.close();
				in.close();



				//chiamo per ottenere l'immagine

				 resultSalvataggioImmagine = getUrlPngStatic(resource_id,ambiente);
				 	if(!resultSalvataggioImmagine.equals("")){

//					 south_lat="53.2560298512";
//					 north_lat="52.8703819712";
//					 east_lon="52.8703819712";
//					 west_lon="40.6113931789";
//					 south_lat_small="52.8703819712";
//					 north_lat_small="53.05";
//					 east_lon_small="40.4";
//					 west_lon_small="40.5";

				 	// taglio immagine e la risalvo aggiornata
				 		BufferedImage bimg = null;
						if(ambiente.equals("sviluppo")){
							bimg = ImageIO.read(new File(pathSviluppo));
							logger.info("path sviluppo "+pathSviluppo);
						}
						if(ambiente.equals("produzione")){
							bimg = ImageIO.read(new File(pathProduzione));
							logger.info("path Produzione "+pathProduzione);
						}

						Float west_lon_small_float= Float.parseFloat(west_lon_small);
						Float east_lon_small_float= Float.parseFloat(east_lon_small);
						Float west_lon_float= Float.parseFloat(west_lon);
						Float east_lon_float= Float.parseFloat(east_lon);
						Float north_lat_small_float= Float.parseFloat(north_lat_small);
						Float  south_lat_small_float= Float.parseFloat(south_lat_small);
						Float south_lat_float= Float.parseFloat(south_lat);
						Float north_lat_float= Float.parseFloat(north_lat);
						
						Float w_ratio_num = Math.abs(Math.abs(west_lon_small_float) - Math.abs(east_lon_small_float));
						Float w_ratio_den = Math.abs(Math.abs(west_lon_float) - Math.abs(east_lon_float));

						Float h_ratio_num = Math.abs(Math.abs(north_lat_small_float) - Math.abs(south_lat_small_float));
						Float h_ratio_den = Math.abs(Math.abs(north_lat_float) - Math.abs(south_lat_float));

						Float x_ratio_num = Math.abs(Math.abs(west_lon_small_float) - Math.abs(west_lon_float));
						Float x_ratio_den = Math.abs(Math.abs(east_lon_float) - Math.abs(west_lon_float));

						Float y_ratio_num = Math.abs(Math.abs(north_lat_small_float) - Math.abs(north_lat_float));
						Float y_ratio_den = Math.abs(Math.abs(north_lat_float) - Math.abs(south_lat_float));


						int width_img          = bimg.getWidth();
						int height_img         = bimg.getHeight();

						 int width = Math.round((w_ratio_num / w_ratio_den) * width_img);
						 int height = Math.round((h_ratio_num / h_ratio_den) * height_img);
				
						 int x =Math.round((x_ratio_num / x_ratio_den) * width_img);
						 int y = Math.round((y_ratio_num / y_ratio_den) * height_img);


						 		logger.info("width_img "+width_img);
						 		logger.info("height_img "+height_img);

								logger.info("My width "+width);
								logger.info("My height "+height);

								logger.info("X "+x);
								logger.info("Y "+y);



								if(x+width <=width_img && y+height<=height_img){

								BufferedImage dst = bimg.getSubimage(x, y, width, height);
								
										if(ambiente.equals("sviluppo")){
											ImageIO.write(dst, "png", new File(pathSviluppo));
										}
										if(ambiente.equals("produzione")){
											ImageIO.write(dst, "png", new File(pathProduzione));
										}
										
										result_call="L'immagine è stata tagliata e salvata correttamente";
								}
								
								else {
									result_call="Si è verificato un errore nel taglio dell'immagine in quanto i valori inseriti superano la grandezza dell'immagine sorgente!";
								
								logger.error("Si è verificato un errore nel taglio dell'immagine in quanto i valori inseriti superano la grandezza dell'immagine sorgente!");
								}
								 


						 }
				 	else{
				 		logger.error("Si è verificato un errore nella chiamara verso le API del serve esterno ");
			        	  result_call="KO-Si e verificato un errore nella chiamata verso le API del serve esterno";
				 	}
				 
			          }else {
			        	  logger.error("Si è verificato un errore nella chiamara verso le API del serve esterno ");
			        	  result_call="Si è verificato un errore nella chiamata verso le API del serve esterno";
			          }
				 }
			        catch(Exception e) {
			        	
			        	logger.error("Si è verificato un errore: "+e.getMessage());
			            e.printStackTrace();
			            result_call="Si  verificato un errore nella chiamata verso le API del serve esterno";
			        }
				 
				 logger.info(result_call);
				 return result_call;
			}
			
			
			public static String getUrlPngStatic(String resource_id, String ambiente) {
				
				logger.info("Start getUrlPngStatic con \n resource_id ="+resource_id +" ambiente "+ambiente);

			String statoSalvataggio ="";
			String urlPng="";
			String destinationFile ="";
			 try {
				 
		        	JSONArray resources = getUrlPngStatic(resource_id);
		        	
		        	
		        	
		        	while (resources.length() ==0) {
		        		 resources = getUrlPngStatic(resource_id);
		        		 if(resources.length()>0){
				        	   	urlPng=(String) resources.get(0);
				        
				        	   	logger.info("urlPng dentro: "+urlPng);
				           }
					}
		        	 if(resources.length()>0){
			        	   	urlPng=(String) resources.get(0);
			        
			        	   	logger.info("urlPng  fuori: "+urlPng);
			           }
		        	 
		        	//salvo immagino sul disco
	     		

		        	 if(ambiente.equals("sviluppo")){
		        		 destinationFile =pathSviluppo;
		        	 }
		        	 if(ambiente.equals("produzione")){
		        	 destinationFile=pathProduzione;
		        	 }
	     		
		        	 if(!urlPng.equals("")){
	     			
		        		 statoSalvataggio = saveImage(urlPng, destinationFile);
	     		}
			 } 
		        catch(Exception e) {
		            e.printStackTrace();
		            logger.error(e.getMessage());
		        }
			
			return statoSalvataggio;
		}
	
			 public static JSONArray getUrlPngStatic(String resource_id) {
					
					JSONArray resources = null;
					 try {
//				commentato xke nn fatto come servizio piu il nostro flusso  URL url2 = new URL ("https://actinia.mundialis.de/status/harvestplus/"+resource_id);
						 URL url2 = new URL (resource_id);
				            
						 logger.info("URL CHIAMATA in getUrlPngStatic :" +url2.toString());
				            
				            Base64 b2 = new Base64();
				            String encoding2= b2.encodeAsString(new String("harvestplus:h#rv35tPlu5Pa55").getBytes());

				            HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
				            connection2.setRequestMethod("GET");
				            connection2.setDoOutput(true);
				            connection2.setRequestProperty  ("Authorization", "Basic " + encoding2);
				            InputStream content2 = (InputStream)connection2.getInputStream();

				            StringBuilder responseStrBuilder2 = new StringBuilder();
				            BufferedReader in2   = 
				                new BufferedReader (new InputStreamReader (content2));
				            String line2;
				            while ((line2 = in2.readLine()) != null) {
				            	 responseStrBuilder2.append(line2);
				            }
				        
				            
				            
				           JSONObject result2 = new JSONObject(responseStrBuilder2.toString());
				           JSONObject urls=result2.getJSONObject("urls");
				           
				            resources= urls.getJSONArray("resources");
				            logger.info("resources "+resources);
				        content2.close();
			 			in2.close();
			 			
					 }
				        catch(Exception e) {
				        	logger.error(e.getMessage());
				            e.printStackTrace();
				            
				            JSONObject jInnerObject = new JSONObject();
				            jInnerObject.put("error", e.getMessage());
				            resources.put(jInnerObject);
				        }
					
					
					return resources;
					
				}
			 
			 public static String saveImage(String imageUrl, String destinationFile) throws IOException {
					String statoSalvataggio="KO -Immagine non salvata";
					 try {
					
					 URL url2 = new URL (imageUrl);
			            
			            logger.info("URL CHIAMATA  salva immagine con URL : \n " +url2.toString());
			            
			            Base64 b2 = new Base64();
			            String encoding2= b2.encodeAsString(new String("harvestplus:h#rv35tPlu5Pa55").getBytes());

			            HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
			            connection2.setRequestMethod("GET");
			            connection2.setDoOutput(true);
			            connection2.setRequestProperty  ("Authorization", "Basic " + encoding2);
			            InputStream content2 = (InputStream)connection2.getInputStream();
					
						OutputStream os = new FileOutputStream(destinationFile);

						byte[] b = new byte[2048];
						int length;

						while ((length = content2.read(b)) != -1) {
							os.write(b, 0, length);
						}

						content2.close();
						os.close();
						statoSalvataggio="OK- Savataggio avvenuto con successo";
						} catch (Exception e) {
							 e.printStackTrace();
						}
					 
					 logger.info("Stato Salvataggio immagine sul disco : " +statoSalvataggio);
					return statoSalvataggio;
					}
			 
			 
			 
			 @RequestMapping(value = "/getAllUser", method = RequestMethod.POST,headers="Accept=application/json")
				@ResponseBody
				public  String getAllUser() {
					logger.info("Start getAllUser ");
				
					String result = "" ;
					Gson gson = new Gson();
			    	
					
					 
					 try {
						 
						 List<User> userList=  userService_ws.selectAllUsers();
						  result = gson.toJson(userList);
					 
					 }
				        catch(Exception e) {
//				            e.printStackTrace();
				        	logger.error("Si è verificato un errore in getAllUser: "+e.getMessage());
				        }
					 return result;
				}
			 
			 
			 
			 @RequestMapping(value = "/saveUserGET", method = RequestMethod.GET,headers="Accept=application/json")
				@ResponseBody
				public  String saveUserGet() {
//					logger.info("Start getStatus getUser name ="+name);
				
					String result = "";
					 
					 try {
						 User usr = new User();
						 
						 usr.setFirstName("plupo");
						 usr.setMiddleName("pluto middle");
						 usr.setLastName("pluto last");
						 usr.setDob("01/10/1977");
						 usr.setEmail("email@gmail.com");		 
						 usr.setPassword("password");
						
						userService_ws.insert(usr);
//						 List<User> userList2=  userService_ws.selectAllUsers();
//					    	
//					    	for (int i = 0; i < userList2.size(); i++) {
//								System.out.println("NOMI "+userList2.get(i).getFirstName());
//							}
					    	
						 result = "User saved: ";
						 
					 
					 }
				        catch(Exception e) {
				            e.printStackTrace();
				            
				       	 result = "User NON saved: ";
				        }
					 return result;
				}
				
			 /**
			  * METODO PER FAR salvare un nuovo utente
			  * 
			  * Esempio json passato input: {
								"firstName": "leo",
								"middleName": "giuseppe",
								"lastName": "convertini",
								"gender": "M",
								"dob": "01/10/1988",
								"email": "convertinileo@gmail2.com",
								"password": "test"
							}
			  */
			 @RequestMapping(value = "/saveUser", method = RequestMethod.POST,headers="Accept=application/json")
				@ResponseBody
			 public String saveUserPost(@Valid @RequestBody User user) throws Exception {
				 logger.info("Start saveUserPost");
				 
//				 System.out.println("*** "+user.getFirstName());
				 
				 String result="KO- Error during saving...";
				 
				 try {
					 userService_ws.insert(user);
					 
					 result="OK- Success saving User!";
				} catch (Exception e) {
//					 e.printStackTrace();
					logger.error("Si è verificato un errore in saveUserPost: "+e.getMessage());
				}
					
				return result;
			 
			 }
			 
			 
			 /**
			  * METODO PER FAR RESTITUIRE I DATI DELL'UTENTE DATA LA PASSWORD COME PARAMETO DI INPUT
			  * 
			  * Esempio json passato input: {"email":"convertinileo@gmail.com"}
			  */
			 @RequestMapping(value = "/getUserFromEmail", method = RequestMethod.POST,headers="Accept=application/json")
				@ResponseBody
				public  String getUserFromEmail(@RequestBody String email) {
					logger.info("Start getUserFromEmail with email ="+email);
				
				 String result ="" ;
					Gson gson = new Gson();
					 
					 try {
						 
						 JSONParser parser = new JSONParser(); 
						 org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(email);

						   String mail = json.get("email").toString();
//						    String mail_decoded = URLDecoder.decode(mail_encoded, "UTF-8");
						    
						 User user=  userService_ws.selectByEmail(mail);
						  result = gson.toJson(user);
					
					 }
				        catch(Exception e) {
//				            e.printStackTrace();
				            logger.error("Si è verificato un errore in getUserFromEmail: "+e.getMessage());
				        }
				 return result;
				}
			 
			 
			 
			 @RequestMapping(value = "/getuserRegistered", method = RequestMethod.POST,headers="Accept=application/json")
				@ResponseBody
				public  String getUserRegistered(@RequestBody String email_password) {
					logger.info("Start getUserRegistered with parameter ="+email_password);
				
				 String result ="" ;
					Gson gson = new Gson();
					 
					 try {
						 
						 JSONParser parser = new JSONParser(); 
						 org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(email_password);

						   String mail = json.get("email").toString();
						   String password = json.get("password").toString();
//						    String mail_decoded = URLDecoder.decode(mail_encoded, "UTF-8");
						    
						 String userRegistered=  userService_ws.userRegistered(mail, password);						  result = gson.toJson(userRegistered);
					
					 }
				        catch(Exception e) {
//				            e.printStackTrace();
				            logger.error("Si è verificato un errore in getUserFromEmail: "+e.getMessage());
				        }
				 return result;
				}


}
