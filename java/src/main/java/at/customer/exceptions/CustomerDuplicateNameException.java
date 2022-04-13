package at.customer.exceptions;

public class CustomerDuplicateNameException extends RuntimeException {

    public CustomerDuplicateNameException(String firstname, String lastName) {
        super("Customer with the name: " + firstname + " " + lastName + " already exists");
    }
}