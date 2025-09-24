package dev.flima.portfolio.modules.faq.useCase.list;

import dev.flima.portfolio.modules.faq.dtos.ListFaqResponseDTO;
import dev.flima.portfolio.modules.faq.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/faq")
public class ListFaqUseCase {

    @Autowired
    private FaqService faqService;

    @GetMapping("/")
    public List<ListFaqResponseDTO> list() {
        return this.faqService.listFaq();
    }

}
