package dev.flima.portfolio.modules.faq.useCase.create;

import dev.flima.portfolio.modules.faq.dtos.FaqRequestDTO;
import dev.flima.portfolio.modules.faq.dtos.FaqResponseDTO;
import dev.flima.portfolio.modules.faq.service.FaqService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faq")
public class CreateFaqUseCase {

    @Autowired
    private FaqService faqService;

    @PostMapping("/")
    public ResponseEntity<FaqResponseDTO> create(@RequestBody @Valid FaqRequestDTO requestDTO) {
        return this.faqService.createFaq(requestDTO);
    }

}
