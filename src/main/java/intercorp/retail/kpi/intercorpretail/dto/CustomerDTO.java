package intercorp.retail.kpi.intercorpretail.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Data
@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class CustomerDTO implements Serializable{

	private Long id;
	private String nombres;
	private String apellidos;
	private Integer edad;
	private LocalDate fechaNacimiento;	
	private LocalDate fechaMuerteEstimada;	
	
	
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
		this.fechaNacimiento=fechaNacimiento;
	}
	
	
}
