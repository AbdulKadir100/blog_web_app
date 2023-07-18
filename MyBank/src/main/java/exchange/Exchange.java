package exchange;

import java.util.function.Function;

import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Data
public class Exchange {
    public static final Function<String,RestExchangeDto> convertedCurrency=(base->new RestTemplate()
    .getForObject("https://api.exchangeratesapi.io/latest?base="+base,RestExchangeDto.class));
}
