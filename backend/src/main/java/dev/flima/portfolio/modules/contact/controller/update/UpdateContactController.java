package dev.flima.portfolio.modules.contact.controller.update;

import dev.flima.portfolio.modules.contact.dtos.ContactRequestDTO;
import dev.flima.portfolio.modules.contact.dtos.ContactResponseDTO;
import dev.flima.portfolio.modules.contact.useCase.update.UpdateContactUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/contact")
public class UpdateContactController {

    @Autowired
    private UpdateContactUseCase useCase;

    @PutMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> updateContact(@PathVariable UUID id, @RequestBody @Valid ContactRequestDTO requestDTO) {
        ContactResponseDTO result = useCase.execute(id, requestDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
    }

}
