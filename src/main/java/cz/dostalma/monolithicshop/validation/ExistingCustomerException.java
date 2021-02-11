package cz.dostalma.monolithicshop.validation;

public class ExistingCustomerException extends RuntimeException {

    public ExistingCustomerException(String message) {
        super(message);
    }
}
