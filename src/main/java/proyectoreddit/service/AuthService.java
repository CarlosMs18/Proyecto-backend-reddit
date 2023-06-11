package proyectoreddit.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyectoreddit.dto.RegisterRequest;
import proyectoreddit.exceptions.RedditApplicationException;
import proyectoreddit.models.NotificationEmail;
import proyectoreddit.models.User;
import proyectoreddit.models.VerificationToken;
import proyectoreddit.repository.UserRepository;
import proyectoreddit.repository.VerificationTokenRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    /*
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    */
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    private final VerificationTokenRepository verificationTokenRepository;


    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);


        String token =  generateVerificationToken(user);

        mailService.sendMail(new NotificationEmail("Por favor, activa tu cuenta",user.getEmail(),"Gracias por inscribirte" +
                "presione el click para que te lleve al url de activacion de tu cuenta en Spring Reddit: : " +
                "http://localhost:8080/api/auth/accountVerification/" +  token));





    }

    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
       Optional<VerificationToken> verificationToken =  verificationTokenRepository.findByToken(token);
       verificationToken.orElseThrow(() -> new RedditApplicationException("Token invalido"));
       fetchUserAndEnable(verificationToken.get());
    }



    @Transactional
    private void fetchUserAndEnable(VerificationToken verificationToken){
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(()-> new RedditApplicationException("Usuario no encontrado " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
