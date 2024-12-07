package services.dj45x.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import services.dj45x.Models.MemberModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberModel, String> {

    @Query("SELECT m FROM MemberModel m WHERE m.inactive = TRUE")
    List<MemberModel> findInactiveMembers();

    @Modifying
    @Transactional
    @Query("UPDATE MemberModel m SET m.inactive = TRUE WHERE m.lastActive < :threshold")
    void markInactiveMembers(@Param("threshold")LocalDateTime threshold);

    Optional<MemberModel> findByDiscordID(String discordId);
}
