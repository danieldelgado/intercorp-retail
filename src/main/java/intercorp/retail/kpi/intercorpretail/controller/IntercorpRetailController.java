package intercorp.retail.kpi.intercorpretail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import intercorp.retail.kpi.intercorpretail.dto.CustomerDTO;
import intercorp.retail.kpi.intercorpretail.dto.CustomerkpiDTO;
import intercorp.retail.kpi.intercorpretail.service.IntercorpRetailCustomerService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers/")
public class IntercorpRetailController {
 
	@Autowired
	private IntercorpRetailCustomerService  intercorpRetailCustomerService;

	@PostMapping("/")
	private Mono<Void> createCustomer(@RequestBody CustomerDTO createCustomerDTO) {
	    return intercorpRetailCustomerService.createCustomer(createCustomerDTO);
	}
	
	@GetMapping("/kpideclientes")
	private Mono<CustomerkpiDTO> kpideclientes() {
	    return intercorpRetailCustomerService.kpideclientes();
	}
   
}