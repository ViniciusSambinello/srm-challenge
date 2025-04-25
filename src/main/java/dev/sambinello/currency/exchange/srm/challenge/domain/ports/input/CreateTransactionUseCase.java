package dev.sambinello.currency.exchange.srm.challenge.domain.ports.input;

import dev.sambinello.currency.exchange.srm.challenge.domain.model.Transaction;
import dev.sambinello.currency.exchange.srm.challenge.domain.viewmodel.CreateTransactionCommand;

public interface CreateTransactionUseCase {

    Transaction execute(CreateTransactionCommand command);

}
