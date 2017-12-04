package converters;

import java.net.URLEncoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Gps;

@Component
@Transactional
public class GpsToStringConverter implements Converter<Gps, String> {

	@Override
	public String convert(final Gps gps) {
		String result;
		StringBuilder builder;

		if (gps == null)
			result = null;
		else
			try {
				builder = new StringBuilder();
				builder.append(URLEncoder.encode(gps.getName(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(Double.toString(gps.getLatitude()), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(Double.toString(gps.getLongitude()), "UTF-8"));
				
				result = builder.toString();
			} catch (final Throwable oops) {
				throw new IllegalArgumentException(oops);
			}
		return result;
	}
}
