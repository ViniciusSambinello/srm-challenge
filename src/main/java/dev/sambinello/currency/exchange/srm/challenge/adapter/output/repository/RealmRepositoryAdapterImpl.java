package dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository;

import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database.RealmEntityDatabase;
import dev.sambinello.currency.exchange.srm.challenge.domain.exceptions.RealmNotFoundException;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Currency;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Realm;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.output.CurrencyRepository;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.output.RealmRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RealmRepositoryAdapterImpl implements RealmRepository {

    private final RealmEntityDatabase database;
    private final CurrencyRepository currencyRepository;

    public RealmRepositoryAdapterImpl(RealmEntityDatabase database, CurrencyRepository currencyRepository){
        this.database = database;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Optional<Realm> findById(String realmId) {
        try {
            return database.findById(Long.valueOf(realmId))
                    .map(realmEntity -> {
                        final Currency currency = currencyRepository.findById(realmEntity.getCurrency().getId().toString())
                                .orElseThrow();

                        return new Realm(realmEntity.getId().toString(), realmEntity.getName(), currency);
                    });
        } catch (NumberFormatException ignored){
            throw new RealmNotFoundException();
        }
    }
}
