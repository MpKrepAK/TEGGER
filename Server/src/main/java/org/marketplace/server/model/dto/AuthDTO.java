package org.marketplace.server.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO {
    private String login;
    private String password;
}
