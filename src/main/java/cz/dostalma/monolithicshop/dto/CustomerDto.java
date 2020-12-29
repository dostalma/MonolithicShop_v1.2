package cz.dostalma.monolithicshop.dto;

import cz.dostalma.monolithicshop.validation.FieldsValueMatch;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmationEmail",
                message = "Email addresses do not match!"
        )
})
public class CustomerDto implements Serializable {

    private Long id;

    @NotBlank(message = "Full name is mandatory")
    private String fullName;

    @NotBlank(message = "Please enter a valid e-mail address")
    @Email(message = "Please enter a valid e-mail address")
    private String email;

    @NotBlank(message = "Please enter a valid e-mail address")
    @Email(message = "Please enter a valid e-mail address")
    private String confirmationEmail;

    @NotBlank(message = "Phone is mandatory")
    private String phone;

    @Valid
    private AddressDto address;

    public CustomerDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmationEmail() {
        return confirmationEmail;
    }

    public void setConfirmationEmail(String confirmationEmail) {
        this.confirmationEmail = confirmationEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
