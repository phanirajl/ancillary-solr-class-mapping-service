package com.delta.rm.ancillary.offer.mapping.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.delta.rm.ancillary.offer.mapping.model.ClassMapping;

/**
 * Interface FlightInfoRepository
 *
 */
@Service
public interface ClassMappingRepository extends CassandraRepository<ClassMapping, Integer>{

	
	@Query("SELECT * FROM ancillary_class_mapping where marketingCarrierCode = :marketingCarrierCode  "
			+ "AND marketingFlightNumber = :marketingFlightNumber "
			+ "AND departureAirportCd = :departureAirportCd AND  arrivalAirportCd = :arrivalAirportCd "
			+ "AND effectiveDate >= :effectiveDate ALLOW FILTERING;")
    List<ClassMapping> findByCustomSearch(@Param("marketingCarrierCode") String marketingCarrierCode, 
    		                                  @Param("marketingFlightNumber") String marketingFlightNumber, 
    		                                  @Param("departureAirportCd") String departureAirportCd, 
    		                                  @Param("arrivalAirportCd") String arrivalAirportCd, 
    		                                  @Param("marketingClassOfService")String marketingClassOfService,
    		                                  @Temporal(value = TemporalType.TIMESTAMP) @Param("effectiveDate") Date departureDate);
	
	/**
	 * truncate the table
	 * no arguments
	 */
	@Query("truncate ancillary_class_mapping;")
	void truncateTable();

}
