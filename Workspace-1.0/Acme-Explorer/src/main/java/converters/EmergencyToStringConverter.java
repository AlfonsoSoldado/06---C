package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Emergency;

@Component
@Transactional
public class EmergencyToStringConverter implements Converter<Emergency, String> {
	
	@Override
	public String convert(final Emergency emergency) {
		String result;

		if (emergency == null)
			result = null;
		else
			result = String.valueOf(emergency.getId());

		return result;
	}
}
