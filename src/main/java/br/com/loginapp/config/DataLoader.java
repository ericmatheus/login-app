package br.com.loginapp.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.loginapp.repository.UserRepository;
import br.com.loginapp.model.User;

@Configuration
public class DataLoader {
    @SuppressWarnings("unused")
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository){
        return args -> {
            if (userRepository.findByUsername("admin4").isEmpty()) {
                System.out.println("PASSO 1");
                User admin = new User("admin4", "4321");
                System.out.println("PASSO 2");
                userRepository.save(admin);
                System.out.println("USUARIO ADMIN 4 Criado");
            }
        };
    }
}
