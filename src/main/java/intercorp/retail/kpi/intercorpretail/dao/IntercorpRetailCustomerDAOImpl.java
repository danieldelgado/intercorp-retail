package intercorp.retail.kpi.intercorpretail.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import intercorp.retail.kpi.intercorpretail.model.Customer;
import reactor.core.publisher.Mono;

@Repository
public class IntercorpRetailCustomerDAOImpl implements IntercorpRetailCustomerDAO {

	private static final List<Customer> listCustomer = new ArrayList<Customer>();

	@Override
	public Mono<Void> save(Customer createCustomer) {
		return Mono.create(sink -> {
			listCustomer.add(createCustomer);
			sink.success();
		});
	}

	@Override
	public Mono<List<Customer>> getCustomers() {
		return Mono.just(listCustomer);
	}

	@Override
	public void clean() {
		listCustomer.clear();
	}

}
