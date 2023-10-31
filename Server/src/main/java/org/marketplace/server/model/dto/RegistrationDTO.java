package org.marketplace.server.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketplace.server.model.enums.EGender;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private EGender gender;

    private String login;
    private String password;
    private String confirmPassword;

}
