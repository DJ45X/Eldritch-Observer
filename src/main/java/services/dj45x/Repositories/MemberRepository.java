package services.dj45x.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import services.dj45x.Models.Member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    @Query
    List<Member> findInactiveMembers();

    @Modifying
    @Transactional
    @Query("UPDATE MemberActivity m SET m.inactive = TRUE WHERE m.lastActive < :threshold")
    void markInactiveMembers(@Param("threshold")LocalDateTime threshold);

    Optional<Member> findByDiscordID(String discordId);
}
