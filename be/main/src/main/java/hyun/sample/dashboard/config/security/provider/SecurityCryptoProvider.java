package hyun.sample.dashboard.config.security.provider;

import org.springframework.stereotype.Component;

@Component
public class SecurityCryptoProvider {

    public String encrypting(String password) {
        return password;
    }

    public String decrypting(String password) {
        return password;
    }
}
