package org.marketplace.server.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketplace.server.model.enums.EGender;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private EGender gender;
    private String login;

    public UserDTO(long id,
                   String firstName,
                   String lastName,
                   String phoneNumber,
                   EGender gender,
                   String login) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.login = login;
    }
}
