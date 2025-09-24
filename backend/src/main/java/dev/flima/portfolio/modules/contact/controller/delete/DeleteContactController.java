package dev.flima.portfolio.modules.contact.controller.delete;

import dev.flima.portfolio.modules.contact.useCase.delete.DeleteContactUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/contact")
public class DeleteContactController {

    @Autowired
    private DeleteContactUseCase useCase;

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        boolean result = useCase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
    }

}
