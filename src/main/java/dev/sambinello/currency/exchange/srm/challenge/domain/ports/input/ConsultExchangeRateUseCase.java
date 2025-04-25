package dev.sambinello.currency.exchange.srm.challenge.domain.ports.input;

import dev.sambinello.currency.exchange.srm.challenge.domain.viewmodel.ConsultExchangeRateResult;

import java.util.List;

public interface ConsultExchangeRateUseCase {

    List<ConsultExchangeRateResult> execute(String currencyId);

}
