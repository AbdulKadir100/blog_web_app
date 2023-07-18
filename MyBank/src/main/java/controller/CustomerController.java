package controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import Dto.CustomerDto;
import entity.Customer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto create(@Valid @RequestBody CustomerDto customerDto){
        return customerService.create(customerDto.toCustomer()).customerDto();
    }
    
    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable("id")Integer id) {
    	return customerService.get(id)
    			.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found!")).customerDto();
    }

    @GetMapping(value = "/customers",params = {"page","size"})
    public List<CustomerDto> getAllList(@Min(value = 0) @RequestParam("page") int page,@RequestParam("size") int size){
    	return customerService.list(PageRequest.of(page, size)).stream()
    			.map(Customer::customerDto) //map will convert all customers to dto
    			.collect(Collectors.toList());
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto update(@RequestBody CustomerDto customerDto) {
    	return customerService.update(customerDto.toCustomer()).customerDto();
    }
    
    @DeleteMapping("/delete/{/id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable("id")Integer id) {
    	customerService.delete(id);
    }
    
    
}
