package dev.flima.portfolio.modules.contact.controller.create;

import dev.flima.portfolio.modules.contact.dtos.ContactRequestDTO;
import dev.flima.portfolio.modules.contact.dtos.ContactResponseDTO;
import dev.flima.portfolio.modules.contact.useCase.create.CreateContactUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class CreateContactController {

    @Autowired
    private CreateContactUseCase useCase;

    @PostMapping("/")
    public ResponseEntity<ContactResponseDTO> create(@RequestBody @Valid ContactRequestDTO requestDTO) {
        ContactResponseDTO result = useCase.execute(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
