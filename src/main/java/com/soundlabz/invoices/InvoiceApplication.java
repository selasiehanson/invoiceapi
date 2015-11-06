package com.soundlabz.invoices;

import com.soundlabz.invoices.domain.Receipt;
import com.soundlabz.invoices.domain.User;
import com.soundlabz.invoices.domain.repositories.ReceiptRepository;
import com.soundlabz.invoices.domain.repositories.UserRepository;
import com.soundlabz.invoices.security.UserRole;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@Configuration
public class InvoiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceApplication.class, args);
    }

    @Bean
    public InitializingBean insertDefaultUsers() {
        return new InitializingBean() {

            @Autowired
            private UserRepository userRepository;

            @Autowired
            private ReceiptRepository receiptRepository;

            @Override
            public void afterPropertiesSet() throws Exception {
                Receipt r = new Receipt();
                r.setName("some receipt");
                receiptRepository.save(r);

                System.out.println("We have " + receiptRepository.count() + " receipts");

                long count = userRepository.count();
                if (count == 0) {
                    addUser("user", "password");
                    addUser("admin", "password");
                }
            }

            private void addUser(String username, String password) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(new BCryptPasswordEncoder().encode(password));
                user.grantRole(username.equals("admin") ? UserRole.ADMIN : UserRole.USER);
                userRepository.save(user);
            }
        };
    }
}
