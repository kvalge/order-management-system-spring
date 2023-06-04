package com.example.ordermanagementsystemspring.domain.validation;

import com.example.ordermanagementsystemspring.domain.exception.AppException;
import com.example.ordermanagementsystemspring.domain.exception.UserException;
import com.example.ordermanagementsystemspring.domain.model.Customer;
import com.example.ordermanagementsystemspring.domain.model.User;
import com.example.ordermanagementsystemspring.domain.repository.UserRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.UserRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserValidationServiceTest {

    @InjectMocks
    private UserValidationService validationService;

    @Mock
    private UserRepository userRepository;

    @Test
    void userAlreadyExists() {
        Customer customer = new Customer();
        customer.setRegistrationCode("1111111");
        customer.setFullName("Full Name");
        customer.setTelephone("51663377");
        customer.setEmail("email");

        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        user.setCustomer(customer);

        UserRequest request = new UserRequest();
        request.setUsername("user");
        request.setPassword("password");
        request.setCustomerId(1L);

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        assertThatThrownBy(() ->
        {
            throw new UserException("User data already exists!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("User data already exists!");

        assertThrows(UserException.class, () -> {
            validationService.userAlreadyExists(request);
        });
    }
}