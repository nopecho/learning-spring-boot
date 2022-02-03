package nopecho.typeconverter.formatter;

import nopecho.typeconverter.converter.IpPortToStringConverter;
import nopecho.typeconverter.converter.StringToIpPortConverter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.*;

public class FormattingConversionServiceTest {

    @Test
    void formattingConversionServiceTest(){
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        conversionService.addFormatter(new MyNumberFormatter());

        String convert = conversionService.convert(1000, String.class);
        assertThat(convert).isEqualTo("1,000");

        Integer convert1 = conversionService.convert("100,000", Integer.class);
        assertThat(convert1).isEqualTo(100000L);

    }
}
