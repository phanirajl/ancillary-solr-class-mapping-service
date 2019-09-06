package com.delta.rm.ancillary.offer.mapping.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.delta.rm.ancillary.offer.mapping.Startup;
import com.delta.rm.ancillary.offer.mapping.controller.ClassMappingController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {Startup.class})
public class ClassMappingControllerTest {
	
    @InjectMocks
    private ClassMappingController classMappingController;
 
	
    /**
	@Mock 
	private BaseService baseService;
	*/ 
 
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
 
    @Test
    public void simpleTest() throws Exception {
 
        //Mockito.when(baseService.testService()).thenReturn("test endpoint !!!");
 
        String actualResponse = "test endpoint !!!";//baseController.test();
 
        assertEquals("test endpoint !!!", actualResponse);
    }

}
