package services.dj45x.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member_activity")
public class MemberModel {

    @Id
    @Column(name = "discord_id", nullable = false, unique = true)
    private String discordID;

    @Column(name = "member_name", nullable = false)
    private String memberName;

    @Column(name = "last_active", nullable = false)
    private LocalDateTime lastActive;

    @Column(name = "inactive", nullable = false)
    private boolean inactive;

}
