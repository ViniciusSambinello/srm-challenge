package dev.sambinello.currency.exchange.srm.challenge.adapter.input.controller;

import dev.sambinello.currency.exchange.srm.challenge.adapter.input.controller.response.ConsultTransactionsResponse;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Transaction;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.input.ConsultTransactionsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/realms/{realm_id}/transactions")
public class ConsultTransactionsController {

    private final ConsultTransactionsUseCase useCase;

    public ConsultTransactionsController(ConsultTransactionsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ResponseEntity<ConsultTransactionsResponse> execute(@PathVariable("realm_id") String realmId) {
        final List<Transaction> result = useCase.execute(realmId);
        final ConsultTransactionsResponse response = new ConsultTransactionsResponse(result);
        return ResponseEntity.ok(response);
    }
}
