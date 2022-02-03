package nopecho.typeconverter.converter;


import nopecho.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ConverterTest {

    @Test
    void stringToIntegerConvertTest(){
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");
        assertThat(result).isEqualTo(10);
    }

    @Test
    void integerToStringConvertTest(){
        IntergerToStringConverter converter = new IntergerToStringConverter();
        String result = converter.convert(10);
        assertThat(result).isEqualTo("10");
    }

    @Test
    void stringToIpPortTest(){
        //given
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort ipPort = new IpPort("127.0.0.1", 8080);

        //when
        String result = converter.convert(ipPort);

        //then
        assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    @Test
    void IpPortToStringTest(){

        //given
        StringToIpPortConverter converter = new StringToIpPortConverter();
        String ipPort = "127.0.0.1:8080";

        //when
        IpPort result = converter.convert(ipPort);

        //then
        assertThat(result).isEqualTo(new IpPort("127.0.0.1",8080));
    }
}
