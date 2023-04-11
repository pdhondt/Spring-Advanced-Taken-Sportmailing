package be.vdab.sportmailing.messaging;

import be.vdab.sportmailing.events.ArtikelGemaakt;
import be.vdab.sportmailing.services.SporterService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ArtikelListener {
    private final SporterService sporterService;

    public ArtikelListener(SporterService sporterService) {
        this.sporterService = sporterService;
    }
    @RabbitListener(queues = "sportartikels")
    void verwerkBericht(ArtikelGemaakt gemaakt) {
        sporterService.stuurMails(gemaakt);
    }
}
