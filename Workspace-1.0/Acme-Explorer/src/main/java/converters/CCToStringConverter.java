package converters;

import java.net.URLEncoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.CC;

@Component
@Transactional
public class CCToStringConverter implements Converter<CC, String> {

	@Override
	public String convert(final CC cc) {
		String result;
		StringBuilder builder;

		if (cc == null)
			result = null;
		else
			try {
				builder = new StringBuilder();
				builder.append(URLEncoder.encode(cc.getHolderName(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(cc.getBrandName(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(cc.getNumber(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(Integer.toString(cc.getExpirationMonth()), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(Integer.toString(cc.getExpirationYear()), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(Integer.toString(cc.getCVV()), "UTF-8"));
				
				result = builder.toString();
			} catch (final Throwable oops) {
				throw new IllegalArgumentException(oops);
			}
		return result;
	}
}
