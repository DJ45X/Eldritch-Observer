package services.dj45x.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import services.dj45x.Models.WarningMessage;

@Repository
public interface WarningMessageRepository extends CrudRepository<WarningMessage, String> {
}
