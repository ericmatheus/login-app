package br.com.loginapp.controller;

import br.com.loginapp.dto.LoginDTO;
import br.com.loginapp.model.User;
import br.com.loginapp.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        Optional<User> user = userRepository.findByUsername(loginDTO.getUsername());

        if (user.isPresent() && user.get().getPassword().equals(loginDTO.getPassword())) {
            return ResponseEntity.ok("Login bem-sucedido!");
        } else {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody LoginDTO loginDTO) {
        // Verifica se o usuário já existe
        Optional<User> existingUser = userRepository.findByUsername(loginDTO.getUsername());

        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe.");
        }

        // Cria um novo usuário
        User newUser = new User(loginDTO.getUsername(), loginDTO.getPassword());
        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }
}
