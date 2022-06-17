package pl.piekoszek.gorskimatches.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.piekoszek.gorskimatches.token.AccountInfo;

@Component
@Repository
public interface AccountRepository extends CrudRepository<AccountInfo, String> {
}

