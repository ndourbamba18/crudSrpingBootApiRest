package com.parlonsdev.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmployeeDto {

    @NotBlank
    @Size(min = 3, max = 25)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 25)
    private String lastName;

    @NotBlank
    @Email
    @Size(min = 3, max = 80)
    private String email;

    @NotBlank
    @Size(max = 11)
    private String phoneNumber;

    @NotBlank
    @Size(min = 3, max = 50)
    private String address;

    @NotBlank
    @Size(min = 3, max = 25)
    private String jobTitle;

    public EmployeeDto(@NotBlank @Size(min = 3, max = 25) String firstName, @NotBlank @Size(min = 3, max = 25) String lastName,
                       @NotBlank @Email @Size(min = 3, max = 80) String email, @NotBlank @Size(max = 11) String phoneNumber,
                       @NotBlank @Size(min = 3, max = 50) String address,
                       @NotBlank @Size(min = 3, max = 25) String jobTitle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.jobTitle = jobTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
