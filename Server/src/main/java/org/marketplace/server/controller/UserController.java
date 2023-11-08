package org.marketplace.server.controller;

import org.marketplace.server.model.dto.AuthDTO;
import org.marketplace.server.model.dto.UserDTO;
import org.marketplace.server.model.entity.User;
import org.marketplace.server.model.enums.EPermission;
import org.marketplace.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("login")
    public UserDTO login(@RequestBody AuthDTO dto){
        var opt = userRepository
                .findUserByLoginAndPassword(dto.getLogin(), dto.getPassword());
        if (opt.isPresent()){
            var u = opt.get();
            return new UserDTO(
                    u.getId(),
                    u.getFirstName(),
                    u.getLastName(),
                    u.getPhoneNumber(),
                    u.getGender(),
                    u.getLogin(),
                    u.getPassword(),
                    u.getPermissions().stream().map(x->x.name()).toArray(String[]::new)
            );
        }
        return null;
    }

    @PostMapping("register")
    public UserDTO register(@RequestBody UserDTO dto){
        System.out.println(dto.getGender());
        var opt = userRepository
                .findUserByLoginAndPassword(dto.getLogin(), dto.getPassword());
        if (!opt.isPresent()){
            var user = new User();
            user.setGender(dto.getGender());
            user.setLogin(dto.getLogin());
            user.setPassword(dto.getPassword());
            user.setPhoneNumber(dto.getPhoneNumber());
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setRegistrationDate(LocalDateTime.now());
            user.setPermissions(Set.of(EPermission.CLIENT));
            var u = userRepository.save(user);

            return new UserDTO(
                    u.getId(),
                    u.getFirstName(),
                    u.getLastName(),
                    u.getPhoneNumber(),
                    u.getGender(),
                    u.getLogin(),
                    u.getPassword(),
                    u.getPermissions().stream().map(x->x.name()).toArray(String[]::new)
            );

        }
        return null;
    }
}
