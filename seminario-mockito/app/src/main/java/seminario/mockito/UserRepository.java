package seminario.mockito;

/**
 * Interface que representa a camada de dados (DB ou API).
 * Será nosso "Dublê" (Mock) nos testes.
 */
public interface UserRepository {
    boolean findByCredentials(String user, String password); //
}