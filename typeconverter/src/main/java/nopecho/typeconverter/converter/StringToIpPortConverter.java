package nopecho.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import nopecho.typeconverter.type.IpPort;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIpPortConverter implements Converter<String, IpPort> {
    
    @Override
    public IpPort convert(String source) {
        log.info("convert source = {}",source);

        String[] split = source.split(":");
        String ip = split[0];
        Integer port = Integer.parseInt(split[1]);

        return new IpPort(ip, port);
    }
}
