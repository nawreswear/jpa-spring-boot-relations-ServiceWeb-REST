package iset.master.spring;

//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
//import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Ajouter le convertisseur XML
        converters.add(new MappingJackson2XmlHttpMessageConverter(new XmlMapper()));
    }
}
