package com.wealthwellness.security;

import com.wealthwellness.model.User;
import com.wealthwellness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Creates the shared demo account on startup if it doesn't exist yet,
 * so the credentials in the README work on a fresh clone or Docker run.
 * An existing account is left untouched.
 */
@Component
public class DemoAccountSeeder implements CommandLineRunner {

    private static final String DEMO_EMAIL    = "ventura404@gmail.com";
    private static final String DEMO_PASSWORD = "password123";
    private static final String DEMO_NAME     = "Ventura404";

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByEmail(DEMO_EMAIL).isPresent()) return;

        User user = new User();
        user.setEmail(DEMO_EMAIL);
        user.setName(DEMO_NAME);
        user.setPassword(passwordEncoder.encode(DEMO_PASSWORD));
        userRepository.save(user);
    }
}
