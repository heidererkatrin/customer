package at.customer.controller;

import at.customer.entities.Customer;
import at.customer.exceptions.CustomerDuplicateNameException;
import at.customer.exceptions.CustomerNameNotNullException;
import at.customer.exceptions.CustomerNotFoundException;
import at.customer.repositories.CustomerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping({CustomerController.BASE_URL, CustomerController.BASE_URL + "/"})
@Api(value="Customer", description="Shows controls to create / update / read / delete a customer")
public class CustomerController {
    public static final String BASE_URL = "/api/v1/customer";
    private final CustomerRepository repository;

    CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @ApiOperation(value = "View a list of available customers",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    List<Customer> all() {
        return repository.findAll();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new Customer.", notes = "Returns the URL of the new resource in the Location header.")
    @Transactional
    Customer newCustomer(@RequestBody Customer newCustomer) {
        Customer customer;
        checkNameNotEmpty(newCustomer);
        try {
            customer = repository.save(newCustomer);

        } catch (Exception e) {
            throw new CustomerDuplicateNameException(newCustomer.getFirstName(), newCustomer.getLastName());
        }
        return customer;
    }

    private void checkNameNotEmpty(Customer newCustomer) {
        if (newCustomer.getFirstName().isEmpty() || newCustomer.getLastName().isEmpty()){
            throw new CustomerNameNotNullException((newCustomer.getFirstName()), newCustomer.getLastName());
        }
    }

    @ApiOperation(value = "Search a customer with a Customer-ID",response = Customer.class)
    @GetMapping("/{id}")
    Customer getCustomerById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @ApiOperation(value = "Search a customer with a Customer-Name",response = Customer.class)
    @GetMapping("/{firstname}/{lastname}")
    Customer getCustomerByName(@PathVariable String firstname, @PathVariable String lastname) {
        return repository.findAll().stream()
                .filter(e -> e.getLastName().equals(lastname))
                .filter(a -> a.getFirstName().equals(firstname))
                .findFirst().orElseThrow(() -> new CustomerNotFoundException(firstname, lastname));
    }

    @ApiOperation(value = "Replaces a customer with a Customer-ID",response = Customer.class)
    @PutMapping("/{id}")
    @Transactional
    Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {

        return repository.findById(id)
                .map(customer -> {
                    customer.setFirstName(newCustomer.getFirstName());
                    customer.setLastName(newCustomer.getLastName());
                    customer.setId(newCustomer.getId());
                    return repository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return repository.save(newCustomer);
                });
    }

    @ApiOperation(value = "Delets a customer with a Customer-ID",response = Customer.class)
    @DeleteMapping("/{id}")
    @Transactional
    void deleteCustomer(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
