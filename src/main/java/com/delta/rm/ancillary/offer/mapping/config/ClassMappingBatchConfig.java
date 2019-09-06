package com.delta.rm.ancillary.offer.mapping.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.delta.rm.ancillary.offer.mapping.model.ClassMapping;
import com.delta.rm.ancillary.offer.mapping.util.CommonConstant;

/**
 * Class ClassMappingBatchConfig
 * define all the steps and jobs related to batch configurations 
 * and it’s execution logic
 */
@Configuration
@EnableBatchProcessing
public class ClassMappingBatchConfig {
	
	   @Value("${input.file}") 
	   private Resource[] inputResources;
	   @Autowired
	   private JobBuilderFactory jobBuilderFactory;
	   @Autowired
       private StepBuilderFactory stepBuilderFactory;
       @Autowired
       private ItemProcessor<ClassMapping, ClassMapping> itemProcessor;
       @Autowired
       @Qualifier("writer")
	   private ItemWriter<ClassMapping> itemWriter;
       @Autowired
       @Qualifier("truncate")
	   private ItemWriter<ClassMapping> itemTruncate;
	  
	   
       
       @Bean("truncateTask")
       public Step stepTruncate(){
    	   Step step = stepBuilderFactory.get("truncate-class-mapping-table")
				   	.<ClassMapping, ClassMapping>chunk(100)
				   	.reader(multiResourceItemReader())
				   	.writer(itemTruncate)
				   	.build();	
    	   return step;
       } 
       
       @Bean("writerTask")
       public Step stepWriter(){
    	   Step step = stepBuilderFactory.get("insert-class-mapping")
				   	.<ClassMapping, ClassMapping>chunk(100)
				   	.reader(multiResourceItemReader())
				   	.processor(itemProcessor)
				   	.writer(itemWriter)
				   	.build();	
    	   return step;
       }  
       
	  @Bean/**(name = "writerJob")*/
	  public Job jobClassMappingWriter() {     
		   Job job = jobBuilderFactory.get("bulk-load-class-mapping")
	                .incrementer(new RunIdIncrementer())
	                .start( stepTruncate() ).next(stepWriter() )
	                .build();
		   return job;
	  }


    
    @Bean
    public FlatFileItemReader<ClassMapping> itemReader() {
        FlatFileItemReader<ClassMapping> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setLinesToSkip(0);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }
    
    @Bean
    public MultiResourceItemReader<ClassMapping> multiResourceItemReader()
    {
        MultiResourceItemReader<ClassMapping> resourceItemReader = new MultiResourceItemReader<ClassMapping>();
        resourceItemReader.setResources(inputResources);
        resourceItemReader.setDelegate(itemReader());
        return resourceItemReader;
    }
    
  
    @Bean
    public LineMapper<ClassMapping> lineMapper() {

        DefaultLineMapper<ClassMapping> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(CommonConstant.PARAM_COMMA);
        lineTokenizer.setStrict(false);
    
        lineTokenizer.setNames(new String[]{"availableDays", "marketingCarrierCode", "marketingFlightNumber", "operatingCarrierCode",
            "operatingFlightNumber","departureAirportCd", "arrivalAirportCd","effectiveDate","expirationDate","flightStatusCode","deltaOperatedIndicator","equipmenOwnerCode"
            ,"equipmenOwnerName","marketedCompartmentCode","operatedCompartmentCode","inhibitedClassOfService","marketingClassOfService","operatedClassOfService"});

        BeanWrapperFieldSetMapper<ClassMapping> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(ClassMapping.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

}
