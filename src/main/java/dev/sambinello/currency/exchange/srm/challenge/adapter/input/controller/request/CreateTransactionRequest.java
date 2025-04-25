package dev.sambinello.currency.exchange.srm.challenge.adapter.input.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.sambinello.currency.exchange.srm.challenge.domain.viewmodel.CreateTransactionCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateTransactionRequest {

    @Valid
    private CreateTransactionData data;

    public CreateTransactionCommand toCommand() {
        return CreateTransactionCommand.builder()
                .productId(data.getProductId())
                .productQuantity(data.getProductQuantity())
                .originRealmId(data.getOrigin().getRealmId())
                .originCurrencyId(data.getOrigin().getCurrencyId())
                .targetRealmId(data.getTarget().getRealmId())
                .targetCurrencyId(data.getTarget().getCurrencyId())
                .created(LocalDateTime.now())
                .build();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class CreateTransactionData {

        @JsonProperty("product_id")
        @NotBlank
        private String productId;

        @JsonProperty("product_quantity")
        @Positive
        private Integer productQuantity;

        @Valid
        private CreateTransactionOrigin origin;

        @Valid
        private CreateTransactionTarget target;

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        public static class CreateTransactionOrigin {

            @JsonProperty("realm_id")
            private String realmId;
            @JsonProperty("currency_id")
            private String currencyId;

        }

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        public static class CreateTransactionTarget {

            @JsonProperty("realm_id")
            private String realmId;
            @JsonProperty("currency_id")
            private String currencyId;

        }
    }
}
