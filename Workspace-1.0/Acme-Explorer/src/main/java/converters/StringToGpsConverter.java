package converters;

import java.net.URLDecoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Gps;

@Component
@Transactional
public class StringToGpsConverter implements Converter<String, Gps> {
	
	@Override
	public Gps convert(final String text) {
		Gps result;
		String parts[];

		if (text == null)
			result = null;
		else
			try {
				parts = text.split("\\|");
				result = new Gps();
				result.setName(URLDecoder.decode(parts[0], "UTF-8"));
				result.setLatitude(Double.valueOf(URLDecoder.decode(parts[1], "UTF-8")));
				result.setLongitude(Double.valueOf(URLDecoder.decode(parts[2], "UTF-8")));

			} catch (final Throwable oops) {
				throw new IllegalArgumentException(oops);
			}
		return result;
	}
}
