package pl.piekoszek.gorskimatches.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.piekoszek.gorskimatches.token.AccountInfo;
@Component
public class AccountRepository {

    public AccountInfo save(AccountInfo accountInfo) {

        return accountInfo;
    }

    @Repository
    public interface AccountRepo extends CrudRepository<AccountInfo, String> {
    }
}
