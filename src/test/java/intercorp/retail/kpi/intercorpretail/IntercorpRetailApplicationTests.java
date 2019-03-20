package intercorp.retail.kpi.intercorpretail;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;

import intercorp.retail.kpi.intercorpretail.dao.IntercorpRetailCustomerDAO;
import intercorp.retail.kpi.intercorpretail.dto.CustomerDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntercorpRetailApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Autowired
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
			createCustomer.setApellidos("Freldo");
			LocalDate fechaNacimiento = LocalDate.now();
			fechaNacimiento = fechaNacimiento.withYear(1982);
			createCustomer.setFechaNacimiento(fechaNacimiento);
			BodyInserter<Object, ReactiveHttpOutputMessage> inserterData = BodyInserters.fromObject(createCustomer);
			webTestClient.post().uri("/customers/").accept(MediaType.APPLICATION_JSON_UTF8).body(inserterData).exchange().expectStatus().isOk().expectBody(String.class).isEqualTo(null);
		}
		{
			CustomerDTO createCustomer = new CustomerDTO();
			createCustomer.setNombres("Ranie 7 l");
			createCustomer.setApellidos("Freldo");
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
	public void getStandarDesviation() {
		intercorpRetailCustomerDAO.clean();
		createManyCustomer();
		String response = "{\"promedioEdades\":30,\"desviacionEstandarEdades\":5.477225575051661}";
		webTestClient.get().uri("/customers/kpideclientes").accept(MediaType.APPLICATION_JSON_UTF8).exchange().expectStatus().isOk().expectBody(String.class).isEqualTo(response);

	}

}