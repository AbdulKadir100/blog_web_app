package exchange;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.context.annotation.Bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestExchangeDto {
	private HashMap<String, Double> rates;
    private String base;
    private LocalDate date;
    
    
    @Bean
	public HashMap<String, Double> getRates() {
		return rates;
	}

}
