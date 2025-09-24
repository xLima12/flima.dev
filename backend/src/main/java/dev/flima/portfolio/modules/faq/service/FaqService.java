package dev.flima.portfolio.modules.faq.service;

import dev.flima.portfolio.modules.faq.dtos.FaqRequestDTO;
import dev.flima.portfolio.modules.faq.dtos.FaqResponseDTO;
import dev.flima.portfolio.modules.faq.dtos.ListFaqResponseDTO;
import dev.flima.portfolio.modules.faq.exception.FaqException;
import dev.flima.portfolio.modules.faq.model.FaqModel;
import dev.flima.portfolio.modules.faq.repository.FaqRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
public class FaqService {

    @Autowired
    private FaqRespository faqRespository;

    public ResponseEntity<FaqResponseDTO> createFaq(FaqRequestDTO requestDTO) {
        FaqModel faq = new FaqModel();
        faq.setQuestion(requestDTO.getQuestion());
        faq.setAnswer(requestDTO.getAnswer());

        FaqModel respCreated = this.faqRespository.save(faq);

        FaqResponseDTO responseDTO = new FaqResponseDTO(respCreated.getId(), respCreated.getQuestion());
        return ResponseEntity.created(URI.create("/faq/" + respCreated.getId())).body(responseDTO);
    }

    public ResponseEntity<FaqResponseDTO> updateFaq(UUID id, FaqRequestDTO requestDTO) {
        FaqModel faq = this.faqRespository.findById(id).orElseThrow(FaqException::new);
        faq.setQuestion(requestDTO.getQuestion());
        faq.setAnswer(requestDTO.getAnswer());

        FaqModel updated = this.faqRespository.save(faq);

        FaqResponseDTO responseDTO = new FaqResponseDTO(updated.getId(), updated.getQuestion());

        return ResponseEntity.noContent().build();
    }

    public List<ListFaqResponseDTO> listFaq() {
        List<FaqModel> listFaq = this.faqRespository.findAll();

        return listFaq.stream().map(faq -> new ListFaqResponseDTO(
                faq.getId(),
                faq.getQuestion(),
                faq.getAnswer()
        )).toList();
    }

    public ResponseEntity<Void> deleteFaq(UUID id) {
        if(!this.faqRespository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        this.faqRespository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
