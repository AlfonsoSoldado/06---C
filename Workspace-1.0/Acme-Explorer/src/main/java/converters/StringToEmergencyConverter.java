package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.EmergencyRepository;
import domain.Emergency;

@Component
@Transactional
public class StringToEmergencyConverter implements Converter<String, Emergency> {

	@Autowired
	EmergencyRepository	emergencyRepository;

	@Override
	public Emergency convert(final String text) {
		Emergency result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.emergencyRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
