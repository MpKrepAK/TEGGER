package org.marketplace.server.controller.auth;

import org.marketplace.server.model.dto.AuthDTO;
import org.marketplace.server.model.dto.ServerMessageDTO;
import org.marketplace.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("signin")
    public ServerMessageDTO signIn(@RequestBody AuthDTO dto){
        var opt = userRepository.findUserByLoginAndPassword(dto.getLogin(), dto.getPassword());
        if (opt.isPresent()){
            return new ServerMessageDTO(200, "Вход произведен успешно");
        }
        return new ServerMessageDTO(400, "Ошибка логина или пароля");
    }

    @PostMapping("signup")
    public ServerMessageDTO signUp(@RequestBody AuthDTO dto){
        var opt = userRepository.findUserByLogin(dto.getLogin());
        if (!opt.isPresent()){
            return new ServerMessageDTO(200, "Пользователь не найден");
        }
        return new ServerMessageDTO(400, "Пользователь уже существует");
    }
}
