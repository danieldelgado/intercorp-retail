package intercorp.retail.kpi.intercorpretail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import intercorp.retail.kpi.intercorpretail.dto.CustomerDTO;
import intercorp.retail.kpi.intercorpretail.dto.CustomerkpiDTO;
import intercorp.retail.kpi.intercorpretail.service.IntercorpRetailCustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers/")
public class IntercorpRetailController {

	@Autowired
	private IntercorpRetailCustomerService intercorpRetailCustomerService;

	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private Mono<Void> createCustomer(@RequestBody CustomerDTO createCustomerDTO) {
		return intercorpRetailCustomerService.createCustomer(createCustomerDTO);
	}

	@GetMapping(value = "/kpideclientes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private Mono<CustomerkpiDTO> kpideclientes() {
		return intercorpRetailCustomerService.kpideclientes();
	}

	@GetMapping(value = "/listClientes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private Flux<CustomerDTO> getCustomers() {
		return intercorpRetailCustomerService.getCustomers();
	}

}