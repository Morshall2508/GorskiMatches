package pl.piekoszek.gorskimatches.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
interface AccountRepository extends CrudRepository<AccountInfo, String> {
    List<AccountInfo> findAll();
}

