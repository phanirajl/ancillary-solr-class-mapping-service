package com.delta.rm.ancillary.offer.mapping.interfaces;

import java.net.HttpURLConnection;

import javax.validation.Valid;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.delta.rm.ancillary.offer.mapping.model.ClassMapping;
import com.delta.rm.ancillary.offer.mapping.model.ClassMappingRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Interface ClassMappingEndpoint for using design pattern with interfaces and
 * clearing concrete class
 */
@Api(value = "Class of mapping ", description = "retreive class of services details in Codestar class of mapping", produces = "application/json", consumes = "application/json")
@RequestMapping("ancillary/offer")
public interface ClassMappingEndpoint {

	/**
     * Endpoint to retrieve all ClassOfMapping
     * no arguments
     * @return object response
     */
	@ApiOperation(value = "Class of mapping details", notes = "class of services details", response = ClassMapping.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 401, message = "you are not authorized to view the services"),
			@ApiResponse(code = 403, message = "Accessing the services you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The services you were trying is not found"), })
	@GetMapping("/retrieve")
	ResponseEntity<?> getAllClassofMapping();

	/**
     * Endpoint to search ClassOfMapping
     * @param classMappingRequest object request
     * @return object response
     */
	@ApiOperation(value = "Retrieve information", notes = "Retrieve Class Mapping Information",
            response = ClassMapping.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns OK when success."),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad Request"),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")})
    @PostMapping(value = "retrieve/class")
    @ResponseBody
	ResponseEntity<?> getClassofMapping(@Valid ClassMappingRequest classMappingRequest) throws Exception;


    /**
     * Endpoint to perform bulkload on Cassandra database
     * no arguments
     * @return batch status
     */
	@ApiOperation(value = "Bulk load", notes = "Load Information",
            response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns OK when success."),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad Request"),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")})
    @GetMapping("/load")
	BatchStatus bulkload() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException;

}
