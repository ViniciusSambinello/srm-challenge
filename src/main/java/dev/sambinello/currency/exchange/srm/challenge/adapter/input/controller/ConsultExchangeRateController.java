package dev.sambinello.currency.exchange.srm.challenge.adapter.input.controller;

import dev.sambinello.currency.exchange.srm.challenge.adapter.input.controller.response.ConsultExchangeRateResponse;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.input.ConsultExchangeRateUseCase;
import dev.sambinello.currency.exchange.srm.challenge.domain.viewmodel.ConsultExchangeRateResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/currencies/{currency_id}/rates")
public class ConsultExchangeRateController {

    private final ConsultExchangeRateUseCase useCase;

    public ConsultExchangeRateController(ConsultExchangeRateUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ResponseEntity<ConsultExchangeRateResponse> execute(
            @PathVariable("currency_id") String currencyId) {
        final List<ConsultExchangeRateResult> result = useCase.execute(currencyId);

        final ConsultExchangeRateResponse response = new ConsultExchangeRateResponse(result);
        return ResponseEntity.ok(response);
    }
}
