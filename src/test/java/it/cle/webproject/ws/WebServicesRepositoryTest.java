package it.cle.webproject.ws;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


	 




// TODO: Auto-generated Javadoc
/**
 * The Class WebServicesRepositoryTest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class  )
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration


/**
 * Testsuite YellowLabel
 * @author leo convertini
 *
 */
public class WebServicesRepositoryTest {
	
	
	 /** The mock mvc. */
 	private MockMvc mockMvc;
	 
	 
	 
	 /** The wsr. */
 	WebServicesRepository wsr;
	
	 
	 /**
 	 * Sets the up.
 	 *
 	 * @throws Exception the exception
 	 */
 	@Before
	  public void setUp() throws Exception {

	   this.mockMvc = MockMvcBuilders.standaloneSetup(new WebServicesRepository()).build();
	  }
	 
	 /**
 	 * Test indice adl.
 	 *
 	 * @throws Exception the exception
 	 */

	
 	
 	 /**
 		 * Test indiceSupportoReteSociale.
 		 *
 		 * @throws Exception the exception
 		 */
 		@Test
 		 public void testIndiceSupportoReteSocialeXML() throws Exception{
 			 
 			this.mockMvc.perform(post("/indiceSupportoReteSocialeXML").contentType(MediaType.APPLICATION_XML).content("<indiceSupportoReteSociale> " + 
 					 "		<preparazionePasti>5</preparazionePasti> " + 
 					 "		<puliziaCasa>10</puliziaCasa> " + 
 					 "		<lavanderia>5</lavanderia> " + 
 					 "		<effettuazioneAcquisti>0</effettuazioneAcquisti> " + 
 					 "		<alimentazione>0</alimentazione> " + 
 					 "		<bagno>0</bagno> " + 
 					 "		<toelettaPersonale>5</toelettaPersonale> " + 
 					 "		<abbigliamento>0</abbigliamento> " + 
 					 "		<usoWC>5</usoWC> " + 
 					 "		<assunzioneMedicinali>0</assunzioneMedicinali> " + 
 					 "		<trasferimenti>15</trasferimenti> " + 
 					 "		<deambulazione>5</deambulazione> " + 
 					 "		<sostegnoPsicoAffettivo>0</sostegnoPsicoAffettivo> " + 
 					 "		<gestioneDenaro>10</gestioneDenaro> " + 
 					 "		<supervisioneDiurna>5</supervisioneDiurna> " + 
 					 "		<supervisioneNotturna>0</supervisioneNotturna> " + 
 					 "	</indiceSupportoReteSociale>	 "))
 			.andExpect(status().isOk());
 		}
 		
 		
}
