package dev.flima.portfolio.modules.contact.controller.list;

import dev.flima.portfolio.modules.contact.dtos.ListContactResponseDTO;
import dev.flima.portfolio.modules.contact.useCase.list.ListContactUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ListContactController {

    @Autowired
    private ListContactUseCase useCase;

    @GetMapping("/")
    public List<ListContactResponseDTO> list() {
        return useCase.execute();
    }
}
