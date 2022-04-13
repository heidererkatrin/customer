package at.customer.exceptions;

public class CustomerNameNotNullException extends RuntimeException {

    public CustomerNameNotNullException(String firstname, String lastName) {
        super("Customer name has to be filled out: " + firstname + " " + lastName);
    }
}