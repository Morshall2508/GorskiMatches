package pl.piekoszek.gorskimatches.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.piekoszek.gorskimatches.account.AccountInfo;

@Repository
interface AccountRepository extends CrudRepository<AccountInfo, String> {
}

