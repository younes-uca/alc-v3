package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.ConfirmationToken;
import ma.learn.quiz.bean.Etudiant;
import ma.learn.quiz.dao.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConfirmationTokenService {

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	public ConfirmationToken getToken(String token) throws Exception {

		Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByToken(token);

		if (!confirmationToken.isPresent()) {
			throw new Exception("Token Not Found");
		}
		return confirmationToken.get();
	}

	public ConfirmationToken  save(ConfirmationToken entity) {
		return confirmationTokenRepository.save(entity);
	}

	public String addNewConfirmationToken(Etudiant userRequest) {

		// TODO: generate validation token
		String token = UUID.randomUUID().toString();

		// TODO: create a new confirmation token
		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
				LocalDateTime.now().plusDays(1), userRequest);

		// TODO: save the generated token in db
		confirmationTokenRepository.save(confirmationToken);
		return token;
	}

}