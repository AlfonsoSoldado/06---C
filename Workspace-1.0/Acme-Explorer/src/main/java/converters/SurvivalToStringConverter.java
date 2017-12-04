package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Survival;

@Component
@Transactional
public class SurvivalToStringConverter implements Converter<Survival, String> {
	
	@Override
	public String convert(final Survival survival) {
		String result;

		if (survival == null)
			result = null;
		else
			result = String.valueOf(survival.getId());

		return result;
	}
}
