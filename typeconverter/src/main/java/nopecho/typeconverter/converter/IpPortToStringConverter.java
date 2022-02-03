package nopecho.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import nopecho.typeconverter.type.IpPort;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IpPortToStringConverter implements Converter<IpPort,String> {

    @Override
    public String convert(IpPort source) {
        return source.getIp()+":"+source.getPort();
    }
}
