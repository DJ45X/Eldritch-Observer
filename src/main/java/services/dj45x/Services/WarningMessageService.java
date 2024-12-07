package services.dj45x.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.dj45x.Models.WarningMessageModel;
import services.dj45x.Repositories.WarningMessageRepository;

import java.util.Optional;

@Service
public class WarningMessageService {

    @Autowired
    private WarningMessageRepository warningMessageRepository;

    public void updateWarningMessage(String message) {
        var warningMessages = warningMessageRepository.findAll();

        WarningMessageModel warningMessage;

        if (warningMessages.iterator().hasNext()) {
            // if a messages exists, update it
            warningMessage = warningMessages.iterator().next();
            warningMessage.setMessage(message);
        } else {
            // if no message exists, create a new one
            warningMessage = WarningMessageModel.builder()
                    .message(message)
                    .build();
        }

        // save the updated or new warning message
        warningMessageRepository.save(warningMessage);
    }

    public Optional<String> getWarningMessage() {
        var warningMessage = warningMessageRepository.findAll();

        if (warningMessage.iterator().hasNext()) {
            return Optional.of(warningMessage.iterator().next().getMessage());
        } else {
            return Optional.empty();
        }
    }
}
