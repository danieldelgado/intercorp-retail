package intercorp.retail.kpi.intercorpretail.service;

import intercorp.retail.kpi.intercorpretail.dto.CustomerDTO;
import intercorp.retail.kpi.intercorpretail.dto.CustomerkpiDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IntercorpRetailCustomerService {

	Mono<Void> createCustomer(CustomerDTO createCustomerDTO);

	Mono<CustomerkpiDTO> kpideclientes();

	Flux<CustomerDTO> getCustomers();

}
