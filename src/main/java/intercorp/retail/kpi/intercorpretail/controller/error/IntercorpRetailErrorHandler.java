package intercorp.retail.kpi.intercorpretail.controller.error;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import intercorp.retail.kpi.intercorpretail.dto.ErrorDetailsDTO;

@ControllerAdvice
public class IntercorpRetailErrorHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> globleExcpetionHandler(RuntimeException ext) {
		return getResolverError(ext);
	}

	public ResponseEntity<ErrorDetailsDTO> getResolverError(Exception ext) {
		ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(LocalDate.now().toString(), ext.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}