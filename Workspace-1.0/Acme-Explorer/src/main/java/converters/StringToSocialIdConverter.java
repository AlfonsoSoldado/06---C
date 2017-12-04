package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.SocialIdRepository;
import domain.SocialId;

@Component
@Transactional
public class StringToSocialIdConverter implements Converter<String, SocialId> {

	@Autowired
	SocialIdRepository	socialIdRepository;

	@Override
	public SocialId convert(final String text) {
		SocialId result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.socialIdRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
