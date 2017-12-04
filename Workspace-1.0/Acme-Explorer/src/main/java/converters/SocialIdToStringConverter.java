package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.SocialId;

@Component
@Transactional
public class SocialIdToStringConverter implements Converter<SocialId, String> {
	
	@Override
	public String convert(final SocialId socialId) {
		String result;

		if (socialId == null)
			result = null;
		else
			result = String.valueOf(socialId.getId());

		return result;
	}
}
