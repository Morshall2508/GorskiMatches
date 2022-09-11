package pl.piekoszek.gorskimatches.challange;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChallengeRepository extends CrudRepository<Challenge, UUID> {
    List<Challenge> findAll();
}
