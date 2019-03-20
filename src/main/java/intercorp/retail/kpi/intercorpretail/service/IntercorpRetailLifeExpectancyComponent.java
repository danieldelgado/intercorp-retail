package intercorp.retail.kpi.intercorpretail.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import intercorp.retail.kpi.intercorpretail.dto.CustomerDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class IntercorpRetailLifeExpectancyComponent {

	private static final String ROW_HEADER = "Country Name";
	private static final String ROW_PERU = "Peru";
	private static final String FILE = "life_expectancy_birth.csv";
	private static final Map<String, String> FECHA_MUERTE_ESTIMADA = new HashMap<>();
	private static final String COMMILLAS = "\"";

	@PostConstruct
	public void setUp() {
		String[] listLEB = null;
		String[] listHeaders = null;
		try (Stream<String> stream = Files.lines(Paths.get(getClass().getClassLoader().getResource(FILE).toURI()))) {
			listHeaders = stream.filter(line -> line.contains(ROW_HEADER)).map(line -> {
				return line.split(",");
			}).findFirst().get();
		} catch (IOException ioe) {
			log.error("Error on read {}", ioe.getMessage(), ioe);
		} catch (URISyntaxException e) {
			log.error("Error on location {}", e.getMessage(), e);
		}
		try (Stream<String> stream = Files.lines(Paths.get(getClass().getClassLoader().getResource(FILE).toURI()))) {
			listLEB = stream.filter(line -> line.contains(ROW_PERU)).map(line -> {
				String[] str = line.split(",");
				return str;
			}).findFirst().get();
		} catch (IOException ioe) {
			log.error("Error on read {}", ioe.getMessage(), ioe);
		} catch (URISyntaxException e) {
			log.error("Error on location {}", e.getMessage(), e);
		}
		for (int i = 0; i < listHeaders.length; i++) {
			FECHA_MUERTE_ESTIMADA.put(listHeaders[i], listLEB[i]);
		}
	}

	public void getFechaMuerteEstimadaOfCustomer(CustomerDTO dto) {
		String value = FECHA_MUERTE_ESTIMADA.get(COMMILLAS + dto.getFechaNacimiento().getYear() + COMMILLAS);
		log.info("value:{}", value);
		if (null != value) {
			int maximoEstimado = Double.valueOf(value.replaceAll("\"", "").trim()).intValue();
			log.info("maximoEstimado:{}", maximoEstimado);
			dto.setFechaMuerteEstimada(dto.getFechaNacimiento().plusYears(maximoEstimado));
		}
	}

}
