package DT2025.Decola.tech._5.service.impl;

import DT2025.Decola.tech._5.domain.model.User;
import DT2025.Decola.tech._5.domain.model.repository.UserRepository;
import DT2025.Decola.tech._5.service.UserService;
import DT2025.Decola.tech._5.service.exception.AccountAlreadyExistsException;
import DT2025.Decola.tech._5.service.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        logger.info("Looking for user with id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", id);
                    return new UserNotFoundException("User not found with id: " + id);
                });
    }

    @Override
    public User create(User userToCreate) {
        // Verificando se o número da conta já existe
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            logger.error("Attempt to create user with existing account number: {}", userToCreate.getAccount().getNumber());
            throw new AccountAlreadyExistsException("This Account number already exists: " + userToCreate.getAccount().getNumber());
        }

        // Criando o usuário
        logger.info("Creating user with account number: {}", userToCreate.getAccount().getNumber());
        return userRepository.save(userToCreate);
    }
}