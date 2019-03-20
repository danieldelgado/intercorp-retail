package intercorp.retail.kpi.intercorpretail.service;

import java.util.List;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import intercorp.retail.kpi.intercorpretail.dao.IntercorpRetailCustomerDAO;
import intercorp.retail.kpi.intercorpretail.dto.CustomerDTO;
import intercorp.retail.kpi.intercorpretail.dto.CustomerkpiDTO;
import intercorp.retail.kpi.intercorpretail.model.Customer;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class IntercorpRetailCustomerServiceImpl implements IntercorpRetailCustomerService {

	@Autowired
	private IntercorpRetailCustomerDAO intercorpRetailCustomerDAO;

	@Autowired
	private IntercorpRetailLifeExpectancyComponent intercorpRetailLifeExpectancyComponent;

	@Override
	public Mono<Void> createCustomer(CustomerDTO createCustomerDTO) {
		log.info("create customer");
		return Mono.create(sink -> {
			Customer createCustomer = new Customer();
			createCustomer.setNombres(createCustomerDTO.getNombres());
			createCustomer.setApellidos(createCustomerDTO.getApellidos());
			createCustomer.setFechaNacimiento(createCustomerDTO.getFechaNacimiento());
			sink.success(createCustomer);
		}).flatMap(customer -> {
			return intercorpRetailCustomerDAO.save((Customer) customer);
		});
	}

	@Override
	public Mono<CustomerkpiDTO> kpideclientes() {
		log.info("kpi de clientes");
		return intercorpRetailCustomerDAO.getCustomers().flatMap(list -> {
			CustomerkpiDTO customerkpiDTO = new CustomerkpiDTO();
			List<Integer> edades = list.stream().map(s -> s.getEdad()).collect(Collectors.toList());
			int size = list.size();
			LongAdder intanceAcumulacion = new LongAdder();
			edades.parallelStream().forEach(intanceAcumulacion::add);
			int sumaEdades = intanceAcumulacion.intValue();
			double promedioEdades = sumaEdades / size;
			customerkpiDTO.setPromedioEdades(Long.valueOf(Math.round(promedioEdades)).intValue());
			customerkpiDTO.setDesviacionEstandarEdades(calculateSD(edades));
			return Mono.just(customerkpiDTO);
		});
	}

	public static double calculateSD(List<Integer> numbers) {
		int length = numbers.size();
		DoubleAdder intanceAcumulacion = new DoubleAdder();
		numbers.parallelStream().forEach(n -> {
			intanceAcumulacion.add(n);
		});
		double sum = intanceAcumulacion.doubleValue();
		double mean = sum / length;
		DoubleAdder desAcum = new DoubleAdder();
		numbers.parallelStream().forEach(n -> {
			desAcum.add(Math.pow(n - mean, 2));
		});
		double standardDeviation = intanceAcumulacion.doubleValue();
		return Math.sqrt(standardDeviation / length);
	}

	@Override
	public Flux<CustomerDTO> getCustomers() {
		log.info("obtener clientes");
		Flux<CustomerDTO> s = intercorpRetailCustomerDAO.getCustomers().flux().flatMap(l -> {
			return Flux.create(sink -> {
				l.parallelStream().forEach(c -> {
					CustomerDTO dto = new CustomerDTO();
					dto.setNombres(c.getNombres());
					dto.setApellidos(c.getApellidos());
					dto.setFechaNacimiento(c.getFechaNacimiento());
					intercorpRetailLifeExpectancyComponent.getFechaMuerteEstimadaOfCustomer(dto);
					sink.next(dto);
				});
				sink.complete();
			});
		});
		return s.sort((o1, o2) -> (o1.getEdad() > o2.getEdad()) ? ((o1.getEdad() == o2.getEdad()) ? 0 : 1) : -1);
	}

}
