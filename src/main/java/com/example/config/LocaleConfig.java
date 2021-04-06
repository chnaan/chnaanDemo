package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * 配置国际化语言
 */
@Configuration
public class LocaleConfig {

    /**
     * 默认解析器 其中locale表示默认语言
     */

    @Bean
    public ResourceBundleMessageSource messageSource() {
        Locale.setDefault(Locale.CHINESE);
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("i18n/test");// name of the resource bundle
        source.setDefaultEncoding("UTF-8");
        return source;
    }

}
