package dev.sambinello.currency.exchange.srm.challenge.domain.usecases;

import dev.sambinello.currency.exchange.srm.challenge.domain.exceptions.CurrencyNotFoundException;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Currency;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.ExchangeRate;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.input.ConsultExchangeRateUseCase;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.output.CurrencyRepository;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.output.ExchangeRateRepository;
import dev.sambinello.currency.exchange.srm.challenge.domain.viewmodel.ConsultExchangeRateResult;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultExchangeRateUseCaseImpl implements ConsultExchangeRateUseCase {

    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;

    public ConsultExchangeRateUseCaseImpl(CurrencyRepository currencyRepository, ExchangeRateRepository exchangeRateRepository) {
        this.currencyRepository = currencyRepository;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public List<ConsultExchangeRateResult> execute(String currencyId) {
        final Currency currency = currencyRepository.findById(currencyId)
                .orElseThrow(CurrencyNotFoundException::new);

        final List<ExchangeRate> rates = exchangeRateRepository.findAllExchangeRatesByCurrency(currency);

        return rates.stream()
                .map(ConsultExchangeRateResult::new)
                .collect(Collectors.toList());
    }
}
