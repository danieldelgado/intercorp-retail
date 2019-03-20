package intercorp.retail.kpi.intercorpretail.dao;

import java.util.List;

import intercorp.retail.kpi.intercorpretail.model.Customer;
import reactor.core.publisher.Mono;

public interface IntercorpRetailCustomerDAO {

	Mono<Void> save(Customer createCustomer);

	Mono<List<Customer>> getCustomers();
	
	void clean();
	
}
