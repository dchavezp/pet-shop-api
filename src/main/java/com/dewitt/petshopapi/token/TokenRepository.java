package com.dewitt.petshopapi.token;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from Token t inner join User u\s
      on t.user.uuid = u.uuid\s
      where u.uuid = :uuid and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(String uuid);

    Optional<Token> findByToken(String token);
}
