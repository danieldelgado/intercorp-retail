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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers/")
@Api(description = "Api de clientes")
public class IntercorpRetailController {

	@Autowired
	private IntercorpRetailCustomerService intercorpRetailCustomerService;

    @ApiOperation("Creacion de clientes")
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Mono<Void> createCustomer(
			@ApiParam("informacion del cliente a crear")
			@RequestBody CustomerDTO createCustomerDTO) {
		return intercorpRetailCustomerService.createCustomer(createCustomerDTO);
	}

    @ApiOperation("KPI de los clientes")
	@GetMapping(value = "/kpideclientes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Mono<CustomerkpiDTO> kpideclientes() {
		return intercorpRetailCustomerService.kpideclientes();
	}

    @ApiOperation("Lista de clientes con fecha estimada de muerte")
	@GetMapping(value = "/listClientes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Flux<CustomerDTO> getCustomers() {
		return intercorpRetailCustomerService.getCustomers();
	}

}