package intercorp.retail.kpi.intercorpretail.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Data
@Setter
@Getter
@JsonInclude(Include.NON_NULL)
@ApiModel(description = "Clase del cliente")
public class CustomerDTO implements Serializable{

	@ApiModelProperty(notes = "Idenificador unico", example = "1", required = false, position = 0)
    private Long id;
	@ApiModelProperty(notes = "Nombres del cliente", example = "Daniel", required = true, position = 1)
    private String nombres;
	@ApiModelProperty(notes = "Apellidos del cliente", example = "Delgado", required = true, position = 2)
    private String apellidos;
	@ApiModelProperty(notes = "Edad del clente", required = false, position = 3)
    private Integer edad;
	@ApiModelProperty(notes = "Fecha de nacimiento", example = "1990-03-20", required = true, position = 4)
    private LocalDate fechaNacimiento;	
	@ApiModelProperty(notes = "Fecha de muerte estimada", example = "2048-03-20", required = true, position = 5)
    private LocalDate fechaMuerteEstimada;	
	
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
		this.fechaNacimiento=fechaNacimiento;
	}
	
	
}
