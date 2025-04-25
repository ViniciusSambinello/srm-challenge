package dev.sambinello.currency.exchange.srm.challenge.domain.ports.output;

import dev.sambinello.currency.exchange.srm.challenge.domain.model.Realm;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> findAllByRealm(Realm realm);
    Transaction create(Transaction transaction);

}
