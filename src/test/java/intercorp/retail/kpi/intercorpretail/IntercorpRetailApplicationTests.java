package intercorp.retail.kpi.intercorpretail;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;

import intercorp.retail.kpi.intercorpretail.dao.IntercorpRetailCustomerDAO;
import intercorp.retail.kpi.intercorpretail.dto.CustomerDTO;
import intercorp.retail.kpi.intercorpretail.main.IntercorpRetailApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntercorpRetailApplication.class,  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntercorpRetailApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@SpyBean
	private IntercorpRetailCustomerDAO intercorpRetailCustomerDAO;
	
	@Test
	public void createUser() {
		CustomerDTO createCustomer = new CustomerDTO();
		createCustomer.setNombres("Daniel");
		createCustomer.setApellidos("Delgado");
		LocalDate fechaNacimiento = LocalDate.now();
		fechaNacimiento = fechaNacimiento.withYear(1990);
		createCustomer.setFechaNacimiento(fechaNacimiento);
		BodyInserter<Object, ReactiveHttpOutputMessage> inserterData = BodyInserters.fromObject(createCustomer);
		webTestClient.post().uri("/customers/").accept(MediaType.APPLICATION_JSON_UTF8).body(inserterData).exchange().expectStatus().isOk().expectBody(String.class).isEqualTo(null);
	}
	
	@Test
	public void createUserErrorRuntime() {		
		Mockito.doThrow(new NullPointerException("Error null force")).when(intercorpRetailCustomerDAO).save(Mockito.any());		
		CustomerDTO createCustomer = new CustomerDTO();
		createCustomer.setNombres("Daniel");
		createCustomer.setApellidos("Delgado");
		LocalDate fechaNacimiento = LocalDate.now();
		fechaNacimiento = fechaNacimiento.withYear(1990);
		createCustomer.setFechaNacimiento(fechaNacimiento);
		BodyInserter<Object, ReactiveHttpOutputMessage> inserterData = BodyInserters.fromObject(createCustomer);	
		LocalDate l = LocalDate.now();
		String responseError = "{\"timestamp\":\""+l.toString()+"\",\"message\":\"Error null force\"}";
		webTestClient.post().uri("/customers/").accept(MediaType.APPLICATION_JSON_UTF8).body(inserterData).exchange().expectStatus().isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR).expectBody(String.class).isEqualTo(responseError);
		Mockito.reset(intercorpRetailCustomerDAO);	
	}
	
	@Test
	public void createManyCustomer() {
		{
			CustomerDTO createCustomer = new CustomerDTO();
			createCustomer.setNombres("Raniel");
			createCustomer.setApellidos("Freldo");
			LocalDate fechaNacimiento = LocalDate.now();
			fechaNacimiento = fechaNacimiento.withYear(1997);
			createCustomer.setFechaNacimiento(fechaNacimiento);
			BodyInserter<Object, ReactiveHttpOutputMessage> inserterData = BodyInserters.fromObject(createCustomer);
			webTestClient.post().uri("/customers/").accept(MediaType.APPLICATION_JSON_UTF8).body(inserterData).exchange().expectStatus().isOk().expectBody(String.class).isEqualTo(null);
		}
		{
			CustomerDTO createCustomer = new CustomerDTO();
			createCustomer.setNombres("Raniel 3");
			createCustomer.setApellidos("Freldo");
			LocalDate fechaNacimiento = LocalDate.now();
			fechaNacimiento = fechaNacimiento.withYear(1998);
			createCustomer.setFechaNacimiento(fechaNacimiento);
			BodyInserter<Object, ReactiveHttpOutputMessage> inserterData = BodyInserters.fromObject(createCustomer);
			webTestClient.post().uri("/customers/").accept(MediaType.APPLICATION_JSON_UTF8).body(inserterData).exchange().expectStatus().isOk().expectBody(String.class).isEqualTo(null);
		}
		{
			CustomerDTO createCustomer = new CustomerDTO();
			createCustomer.setNombres("Raniel 5");
			createCustomer.setApellidos("Freldo");
			LocalDate fechaNacimiento = LocalDate.now();
			fechaNacimiento = fechaNacimiento.withYear(1981);
			createCustomer.setFechaNacimiento(fechaNacimiento);
			BodyInserter<Object, ReactiveHttpOutputMessage> inserterData = BodyInserters.fromObject(createCustomer);
			webTestClient.post().uri("/customers/").accept(MediaType.APPLICATION_JSON_UTF8).body(inserterData).exchange().expectStatus().isOk().expectBody(String.class).isEqualTo(null);
		}
		{
			CustomerDTO createCustomer = new CustomerDTO();
			createCustomer.setNombres("Raniel 0");
			createCustomer.setApellidos("Freldo 2");
			LocalDate fechaNacimiento = LocalDate.now();
			fechaNacimiento = fechaNacimiento.withYear(1982);
			createCustomer.setFechaNacimiento(fechaNacimiento);
			BodyInserter<Object, ReactiveHttpOutputMessage> inserterData = BodyInserters.fromObject(createCustomer);
			webTestClient.post().uri("/customers/").accept(MediaType.APPLICATION_JSON_UTF8).body(inserterData).exchange().expectStatus().isOk().expectBody(String.class).isEqualTo(null);
		}
		{
			CustomerDTO createCustomer = new CustomerDTO();
			createCustomer.setNombres("Ranie 7 l");
			createCustomer.setApellidos("Freldo 2");
			LocalDate fechaNacimiento = LocalDate.now();
			fechaNacimiento = fechaNacimiento.withYear(1982);
			createCustomer.setFechaNacimiento(fechaNacimiento);
			BodyInserter<Object, ReactiveHttpOutputMessage> inserterData = BodyInserters.fromObject(createCustomer);
			webTestClient.post().uri("/customers/").accept(MediaType.APPLICATION_JSON_UTF8).body(inserterData).exchange().expectStatus().isOk().expectBody(String.class).isEqualTo(null);
		}
		{
			CustomerDTO createCustomer = new CustomerDTO();
			createCustomer.setNombres("Raniel 6");
			createCustomer.setApellidos("Freldo");
			LocalDate fechaNacimiento = LocalDate.now();
			fechaNacimiento = fechaNacimiento.withYear(1994);
			createCustomer.setFechaNacimiento(fechaNacimiento);
			BodyInserter<Object, ReactiveHttpOutputMessage> inserterData = BodyInserters.fromObject(createCustomer);
			webTestClient.post().uri("/customers/").accept(MediaType.APPLICATION_JSON_UTF8).body(inserterData).exchange().expectStatus().isOk().expectBody(String.class).isEqualTo(null);
		}
	}

	@Test
	public void getkpideclientes() {
		intercorpRetailCustomerDAO.clean();
		createManyCustomer();
		String response = "{\"promedioEdades\":30.0,\"desviacionEstandarEdades\":5.477225575051661}";
		webTestClient.get().uri("/customers/kpideclientes").accept(MediaType.APPLICATION_JSON_UTF8).exchange().expectStatus().isOk().expectBody(String.class).isEqualTo(response);

	}

	@Test
	public void getClientes() {
		intercorpRetailCustomerDAO.clean();
		createManyCustomer();
		String response = "[{\"nombres\":\"Raniel 3\",\"apellidos\":\"Freldo\",\"edad\":21,\"fechaNacimiento\":\"1998-03-20\",\"fechaMuerteEstimada\":\"2067-03-20\"},{\"nombres\":\"Raniel\",\"apellidos\":\"Freldo\",\"edad\":22,\"fechaNacimiento\":\"1997-03-20\",\"fechaMuerteEstimada\":\"2065-03-20\"},{\"nombres\":\"Raniel 6\",\"apellidos\":\"Freldo\",\"edad\":25,\"fechaNacimiento\":\"1994-03-20\",\"fechaMuerteEstimada\":\"2061-03-20\"},{\"nombres\":\"Ranie 7 l\",\"apellidos\":\"Freldo 2\",\"edad\":37,\"fechaNacimiento\":\"1982-03-20\",\"fechaMuerteEstimada\":\"2042-03-20\"},{\"nombres\":\"Raniel 0\",\"apellidos\":\"Freldo 2\",\"edad\":37,\"fechaNacimiento\":\"1982-03-20\",\"fechaMuerteEstimada\":\"2042-03-20\"},{\"nombres\":\"Raniel 5\",\"apellidos\":\"Freldo\",\"edad\":38,\"fechaNacimiento\":\"1981-03-20\",\"fechaMuerteEstimada\":\"2041-03-20\"}]";
		webTestClient.get().uri("/customers/listClientes").accept(MediaType.APPLICATION_JSON_UTF8).exchange().expectStatus().isOk().expectBody(String.class).isEqualTo(response);
	}
}