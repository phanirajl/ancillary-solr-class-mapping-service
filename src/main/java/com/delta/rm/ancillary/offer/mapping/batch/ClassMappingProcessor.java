package com.delta.rm.ancillary.offer.mapping.batch;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.utils.UUIDs;
import com.delta.rm.ancillary.offer.mapping.model.ClassMapping;

/**
 * Class ClassMappingProcessor
 *
 */
@Component
public class ClassMappingProcessor implements ItemProcessor<ClassMapping, ClassMapping> {

	@Override
	public ClassMapping process(ClassMapping classMapping) throws Exception {
        classMapping.setId(UUIDs.timeBased());
        return classMapping;
	}
	
}
