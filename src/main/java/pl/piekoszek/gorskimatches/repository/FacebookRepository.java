package pl.piekoszek.gorskimatches.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.piekoszek.gorskimatches.facebook.FacebookIdQuizInfo;

@Repository
public interface FacebookRepository extends CrudRepository<FacebookIdQuizInfo, String> {
}

