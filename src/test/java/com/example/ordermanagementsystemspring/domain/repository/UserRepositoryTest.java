package com.example.ordermanagementsystemspring.domain.repository;

import com.example.ordermanagementsystemspring.domain.model.Customer;
import com.example.ordermanagementsystemspring.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void findByUsername() {
        Customer customer = new Customer();
        customer.setRegistrationCode("1111111");
        customer.setFullName("Full Name");
        customer.setTelephone("51663377");
        customer.setEmail("email");

        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        user.setCustomer(customer);

        testEntityManager.persist(customer);
        testEntityManager.persist(user);

        User byUsername = userRepository.findByUsername(user.getUsername());

        assertNotNull(byUsername);
        assertEquals(user.getUsername(), byUsername.getUsername());
    }
}