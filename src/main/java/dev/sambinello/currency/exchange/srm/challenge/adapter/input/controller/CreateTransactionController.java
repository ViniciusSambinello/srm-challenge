package dev.sambinello.currency.exchange.srm.challenge.adapter.input.controller;

import dev.sambinello.currency.exchange.srm.challenge.adapter.input.controller.request.CreateTransactionRequest;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Transaction;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.input.CreateTransactionUseCase;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Controller
@RequestMapping("/api/v1/transactions")
public class CreateTransactionController {

    private final CreateTransactionUseCase createTransactionUseCase;

    public CreateTransactionController(CreateTransactionUseCase createTransactionUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
    }

    @PostMapping
    public ResponseEntity<Object> execute(@Valid @RequestBody CreateTransactionRequest request) {
        final Transaction transaction = createTransactionUseCase.execute(request.toCommand());

        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transaction.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
