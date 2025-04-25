package dev.sambinello.currency.exchange.srm.challenge.domain.ports.input;

import dev.sambinello.currency.exchange.srm.challenge.domain.model.Transaction;

import java.util.List;

public interface ConsultTransactionsUseCase {

    List<Transaction> execute(String realmId);

}
