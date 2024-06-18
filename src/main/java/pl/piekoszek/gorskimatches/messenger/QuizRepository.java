package pl.piekoszek.gorskimatches.messenger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface QuizRepository extends CrudRepository<IdQuizInfo, String> {
}

