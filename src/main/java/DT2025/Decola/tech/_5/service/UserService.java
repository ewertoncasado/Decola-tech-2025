package DT2025.Decola.tech._5.service;

import DT2025.Decola.tech._5.domain.model.User;

public interface UserService {

    User findById(Long id);

    User create(User userToCreate);
}