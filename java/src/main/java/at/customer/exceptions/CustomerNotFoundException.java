package at.customer.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id) {
        super("Could not find customer " + id);
    }

    public CustomerNotFoundException(String firstname, String lastname) {
        super("Could not find customer " + firstname + " " +lastname);
    }
}