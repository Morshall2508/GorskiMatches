package pl.piekoszek.gorskimatches.facebook;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacebookRepository extends CrudRepository<FacebookIdQuizInfo, String> {
}

