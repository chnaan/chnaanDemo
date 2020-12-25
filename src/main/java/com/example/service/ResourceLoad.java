package com.example.service;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/11/16 17:49
 */
@Component
public class ResourceLoad implements ResourceLoaderAware {

    private ResourcePatternResolver resourcePatternResolver;
    private MetadataReaderFactory metadataReaderFactory;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.metadataReaderFactory = new SimpleMetadataReaderFactory(resourceLoader);
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
    }
}
