package be.vdab.sportmailing.services;

import be.vdab.sportmailing.events.ArtikelGemaakt;
import be.vdab.sportmailing.mailing.SporterMailing;
import be.vdab.sportmailing.repositories.SporterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SporterService {
    private final SporterRepository sporterRepository;
    private final SporterMailing sporterMailing;

    public SporterService(SporterRepository sporterRepository, SporterMailing sporterMailing) {
        this.sporterRepository = sporterRepository;
        this.sporterMailing = sporterMailing;
    }
    public void stuurMails(ArtikelGemaakt gemaakt) {
        var sporters = sporterRepository.findAll();
        for (var sporter : sporters) {
            sporterMailing.stuurMailNaOntvangstBericht(sporter, gemaakt);
        }
    }
}
