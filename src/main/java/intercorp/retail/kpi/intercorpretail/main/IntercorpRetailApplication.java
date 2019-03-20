package intercorp.retail.kpi.intercorpretail.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("intercorp.retail.kpi.intercorpretail")
public class IntercorpRetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntercorpRetailApplication.class, args);
	}

}
