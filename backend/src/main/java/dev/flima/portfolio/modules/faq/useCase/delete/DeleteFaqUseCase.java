package dev.flima.portfolio.modules.faq.useCase.delete;

import dev.flima.portfolio.modules.faq.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/faq")
public class DeleteFaqUseCase {

    @Autowired
    private FaqService faqService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return this.faqService.deleteFaq(id);
    }

}
