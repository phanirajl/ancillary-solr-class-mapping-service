package com.delta.rm.ancillary.offer.mapping.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.delta.rm.ancillary.logger.AncLogger;
import com.delta.rm.ancillary.offer.mapping.model.ClassMapping;
import com.delta.rm.ancillary.offer.mapping.repository.ClassMappingRepository;

/**
 * Class ClassMappingTruncate
 * for temporary clean the table before insert
 * @author w35493
 *
 */
@Component("truncate")
public class ClassMappingTruncate implements ItemWriter<ClassMapping> {

	/** the object that has the info about: ancLogger*/
	protected AncLogger ancLogger;
	
	/** the object that has the info about: classMappingRepository*/
    private ClassMappingRepository classMappingRepository;
	
	/**
	 * @param ancLogger the ancLogger to set
	 */
	@Autowired 
	public void setAncLogger(AncLogger ancLogger) {
		this.ancLogger = ancLogger;
	}
	
	/**
	 * @param classMappingRepository the classMappingRepository to set
	 */
    @Autowired
	public void setClassMappingRepository(ClassMappingRepository classMappingRepository) {
		this.classMappingRepository = classMappingRepository;
	}
    
	@Override
	public void write(List<? extends ClassMapping> items) throws Exception {
		 ancLogger.info("Cleanning Table: ");
		 classMappingRepository.truncateTable();
	}

}
