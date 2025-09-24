package dev.flima.portfolio.modules.faq.useCase.update;

import dev.flima.portfolio.modules.faq.dtos.FaqRequestDTO;
import dev.flima.portfolio.modules.faq.dtos.FaqResponseDTO;
import dev.flima.portfolio.modules.faq.service.FaqService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/faq")
public class UpdateFaqUseCase {

    @Autowired
    private FaqService faqService;

    @PutMapping("/{id}")
    public ResponseEntity<FaqResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid FaqRequestDTO requestDTO) {
        return this.faqService.updateFaq(id, requestDTO);
    }

}
