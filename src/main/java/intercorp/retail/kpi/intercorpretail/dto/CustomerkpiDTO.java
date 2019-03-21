package intercorp.retail.kpi.intercorpretail.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Data
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@ApiModel(description = "Clase del KPI de clientes")
public class CustomerkpiDTO implements Serializable {
	private double promedioEdades;
	private double desviacionEstandarEdades;
}
