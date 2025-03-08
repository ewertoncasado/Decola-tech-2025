package DT2025.Decola.tech._5.service.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);  // Passa a mensagem para a classe pai (RuntimeException)
    }
}