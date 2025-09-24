package dev.flima.portfolio.modules.rabbitmq.useCase.retry;

import dev.flima.portfolio.modules.rabbitmq.service.ContactRetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact/retry")
public class RetryContactUseCase {

    @Autowired
    private ContactRetryService retryService;

    @PostMapping
    public String retry() {
        this.retryService.retryOneMessage();
        return "Tentativa de reprocessamento feita.";
    }

}
