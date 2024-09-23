package services.dj45x.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "warningMessages")
public class WarningMessage {
    @Id
    private String id;

    private String defaultMessage;

    private String customMessage;
}
