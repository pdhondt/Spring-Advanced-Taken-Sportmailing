package be.vdab.sportmailing.mailing;

import be.vdab.sportmailing.domain.Sporter;
import be.vdab.sportmailing.events.ArtikelGemaakt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SporterMailing {
    private final JavaMailSender sender;
    private final String userName;

    public SporterMailing(JavaMailSender sender,
                          @Value("${spring.mail.username}") String userName) {
        this.sender = sender;
        this.userName = userName;
    }
    public void stuurMailNaOntvangstBericht(Sporter sporter,
                                            ArtikelGemaakt artikelGemaakt) {
        var message = new SimpleMailMessage();
        message.setFrom(userName);
        message.setTo(sporter.getEmailAdres());
        message.setSubject("Nieuw artikel");
        message.setText("Er is een nieuw artikel: " + artikelGemaakt.getNaam());
        sender.send(message);
    }
}
