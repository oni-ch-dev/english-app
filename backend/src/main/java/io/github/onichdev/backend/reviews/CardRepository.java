package io.github.onichdev.backend.reviews;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("""
        select c from Card c
        join fetch c.expression
        where c.due <= :now
        order by c.due asc
        """)
    List<Card> findDueWithExpression(@Param("now") Instant now, Limit limit);

    long countByDueLessThanEqual(Instant now);
}
