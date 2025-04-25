package dev.sambinello.currency.exchange.srm.challenge.adapter.input.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ConsultTransactionsResponse {

    private final List<ConsultTransactionsData> data;

    public ConsultTransactionsResponse(List<Transaction> result) {
        this.data = result.stream()
                .map(ConsultTransactionsData::new)
                .collect(Collectors.toList());
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class ConsultTransactionsData {

        private final String id;
        @JsonProperty("exchange_rate")
        private final BigDecimal exchangeRate;
        private final ConsultTransactionsProduct product;
        private final ConsultTransactionsOrigin origin;
        private final ConsultTransactionsTarget target;
        private final Long created;

        public ConsultTransactionsData(Transaction result) {
            this.id = result.getId();
            this.exchangeRate = result.getExchangeRate();
            this.product = ConsultTransactionsData.ConsultTransactionsProduct.builder()
                    .id(result.getProduct().getId())
                    .name(result.getProduct().getName())
                    .build();

            this.origin = ConsultTransactionsData.ConsultTransactionsOrigin.builder()
                    .realm(ConsultTransactionsData.ConsultTransactionsRealm.builder()
                            .id(result.getOriginRealm().getId())
                            .name(result.getOriginRealm().getName())
                            .build())
                    .currency(ConsultTransactionsData.ConsultTransactionsCurrency.builder()
                            .id(result.getOriginCurrency().getId())
                            .name(result.getOriginCurrency().getName())
                            .build())
                    .transactionValue(result.getOriginTransactionValue())
                    .build();
            this.target = ConsultTransactionsData.ConsultTransactionsTarget.builder()
                    .realm(ConsultTransactionsData.ConsultTransactionsRealm.builder()
                            .id(result.getTargetRealm().getId())
                            .name(result.getTargetRealm().getName())
                            .build())
                    .currency(ConsultTransactionsData.ConsultTransactionsCurrency.builder()
                            .id(result.getTargetCurrency().getId())
                            .name(result.getTargetCurrency().getName())
                            .build())
                    .transactionValue(result.getTargetTransactionValue())
                    .build();
            this.created = result.getCreated().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }

        @AllArgsConstructor
        @Builder
        @Getter
        public static class ConsultTransactionsProduct {

            private final String id;
            private final String name;

        }

        @AllArgsConstructor
        @Builder
        @Getter
        public static class ConsultTransactionsOrigin {

            private final ConsultTransactionsRealm realm;
            private final ConsultTransactionsCurrency currency;
            @JsonProperty("transaction_value")
            private final BigDecimal transactionValue;

        }

        @AllArgsConstructor
        @Builder
        @Getter
        public static class ConsultTransactionsTarget {

            private final ConsultTransactionsRealm realm;
            private final ConsultTransactionsCurrency currency;
            @JsonProperty("transaction_value")
            private final BigDecimal transactionValue;

        }

        @AllArgsConstructor
        @Builder
        @Getter
        public static class ConsultTransactionsRealm {

            private final String id;
            private final String name;

        }

        @AllArgsConstructor
        @Builder
        @Getter
        public static class ConsultTransactionsCurrency {

            private final String id;
            private final String name;

        }
    }
}
