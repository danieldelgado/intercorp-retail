package intercorp.retail.kpi.intercorpretail.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class CustomerkpiDTO implements Serializable {
	private int promedioEdades;
	private double desviacionEstandarEdades;
}
