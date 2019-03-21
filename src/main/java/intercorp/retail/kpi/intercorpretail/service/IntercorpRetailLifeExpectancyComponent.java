package intercorp.retail.kpi.intercorpretail.service;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import intercorp.retail.kpi.intercorpretail.dto.CustomerDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class IntercorpRetailLifeExpectancyComponent {

	private static final String ROW_HEADER = "Country Name";
	private static final String ROW_PERU = "Peru";
	private static final String COMMILLAS = "\"";
	private static final String BLANK = "";
	private static final String COMA = ",";
	private static final Map<String, String> FECHA_MUERTE_ESTIMADA = new HashMap<>();

	@Value("${ruta.base}")
	private String ruta_base;
	@Value("${ruta.archivo}")
	private String nombre_archivo;

	@PostConstruct
	public void setUp() {
		String[] listLEB = null;
		String[] listHeaders = null;
		try {
			URL filereosurces = ClassLoader.getSystemResource(nombre_archivo);
			URI fileUri = null;
			if (null != filereosurces) {
				fileUri = filereosurces.toURI();
			} else {
				fileUri = new URI(ruta_base + File.separator + FilenameUtils.getName(nombre_archivo));
			}
			log.info("read uri file:{}", fileUri);
			Path path = Paths.get(fileUri);
			listHeaders = Files.lines(path).filter(line -> line.contains(ROW_HEADER)).map(line -> {
				return line.split(COMA);
			}).findFirst().get();
			listLEB = Files.lines(path).filter(line -> line.contains(ROW_PERU)).map(line -> {
				String[] str = line.split(COMA);
				return str;
			}).findFirst().get();
			for (int i = 0; i < listHeaders.length; i++) {
				FECHA_MUERTE_ESTIMADA.put(listHeaders[i], listLEB[i]);
			}

		} catch (Exception e) {
			log.error("Error on read {}", e.getMessage(), e);
		}
	}

	public void getFechaMuerteEstimadaOfCustomer(CustomerDTO dto) {
		String value = FECHA_MUERTE_ESTIMADA.get(COMMILLAS + dto.getFechaNacimiento().getYear() + COMMILLAS);
		log.info("value:{}", value);
		if (null != value) {
			int maximoEstimado = Double.valueOf(value.replaceAll(COMMILLAS, BLANK).trim()).intValue();
			log.info("maximoEstimado:{}", maximoEstimado);
			dto.setFechaMuerteEstimada(dto.getFechaNacimiento().plusYears(maximoEstimado));
		}
	}

}
