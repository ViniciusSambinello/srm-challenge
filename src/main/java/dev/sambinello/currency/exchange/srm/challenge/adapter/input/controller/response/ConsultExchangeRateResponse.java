package dev.sambinello.currency.exchange.srm.challenge.adapter.input.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.sambinello.currency.exchange.srm.challenge.domain.viewmodel.ConsultExchangeRateResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ConsultExchangeRateResponse {

    private final List<ConsultExchangeRateData> data;

    public ConsultExchangeRateResponse(List<ConsultExchangeRateResult> results) {
        this.data = results.stream()
                .map(result -> ConsultExchangeRateData.builder()
                        .currencyId(result.getCurrencyId())
                        .rate(result.getRate())
                        .build())
                .collect(Collectors.toList());
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class ConsultExchangeRateData {

        @JsonProperty("currency_id")
        private String currencyId;
        private BigDecimal rate;

    }
}
