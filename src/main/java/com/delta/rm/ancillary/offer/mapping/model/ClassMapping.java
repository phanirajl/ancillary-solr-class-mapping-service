package com.delta.rm.ancillary.offer.mapping.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.mapping.annotations.Column;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class FlightInfo 
 * Entity class for mapping fields into Cassandra
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table("ancillary_class_mapping")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ClassMapping implements Serializable{

	/**
	 * Serial generated
	 */
	private static final long serialVersionUID = 5197800651068543996L;
	
	@PrimaryKey
	@Column(name = "id")
	@JsonIgnore
	private UUID id;

	@Column(name = "availableDays")
	private int availableDays;
	
	@Column(name = "marketingCarrierCode")
    private String marketingCarrierCode;
	
	@Column(name = "marketingFlightNumber")
    private String marketingFlightNumber;
	
	@Column(name = "operatingCarrierCode")
    private String operatingCarrierCode;
	
	@Column(name = "operatingFlightNumber")
    private String operatingFlightNumber;
	
	@Column(name = "departureAirportCd")
    private String departureAirportCd;
	
	@Column(name = "arrivalAirportCd")
    private String arrivalAirportCd;
	
	@Column(name = "effectiveDate")
    private Date effectiveDate;
	
	@Column(name = "expirationDate")
    private Date expirationDate;
	
	@Column(name = "flightStatusCode")
    private String flightStatusCode;
	
	@Column(name = "deltaOperatedIndicator")
    private String deltaOperatedIndicator;
	
	@Column(name = "equipmenOwnerCode")
    private String equipmenOwnerCode;
	
	@Column(name = "equipmenOwnerName")
    private String equipmenOwnerName;
	
	@Column(name = "marketedCompartmentCode")
    private String marketedCompartmentCode;
	
	@Column(name = "operatedCompartmentCode")
    private String operatedCompartmentCode;
	
	@Column(name = "inhibitedClassOfService")
    private String inhibitedClassOfService;
	
	@Column(name = "marketingClassOfService")
    private String marketingClassOfService;
	
	@Column(name = "operatedClassOfService")
    private String operatedClassOfService;

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * @return the availableDays
	 */
	public int getAvailableDays() {
		return availableDays;
	}

	/**
	 * @param availableDays the availableDays to set
	 */
	public void setAvailableDays(int availableDays) {
		this.availableDays = availableDays;
	}

	/**
	 * @return the marketingCarrierCode
	 */
	public String getMarketingCarrierCode() {
		return marketingCarrierCode;
	}

	/**
	 * @param marketingCarrierCode the marketingCarrierCode to set
	 */
	public void setMarketingCarrierCode(String marketingCarrierCode) {
		this.marketingCarrierCode = marketingCarrierCode;
	}

	/**
	 * @return the marketingFlightNumber
	 */
	public String getMarketingFlightNumber() {
		return marketingFlightNumber;
	}

	/**
	 * @param marketingFlightNumber the marketingFlightNumber to set
	 */
	public void setMarketingFlightNumber(String marketingFlightNumber) {
		this.marketingFlightNumber = marketingFlightNumber;
	}

	/**
	 * @return the operatingCarrierCode
	 */
	public String getOperatingCarrierCode() {
		return operatingCarrierCode;
	}

	/**
	 * @param operatingCarrierCode the operatingCarrierCode to set
	 */
	public void setOperatingCarrierCode(String operatingCarrierCode) {
		this.operatingCarrierCode = operatingCarrierCode;
	}

	/**
	 * @return the operatingFlightNumber
	 */
	public String getOperatingFlightNumber() {
		return operatingFlightNumber;
	}

	/**
	 * @param operatingFlightNumber the operatingFlightNumber to set
	 */
	public void setOperatingFlightNumber(String operatingFlightNumber) {
		this.operatingFlightNumber = operatingFlightNumber;
	}

	/**
	 * @return the departureAirportCd
	 */
	public String getDepartureAirportCd() {
		return departureAirportCd;
	}

	/**
	 * @param departureAirportCd the departureAirportCd to set
	 */
	public void setDepartureAirportCd(String departureAirportCd) {
		this.departureAirportCd = departureAirportCd;
	}

	/**
	 * @return the arrivalAirportCd
	 */
	public String getArrivalAirportCd() {
		return arrivalAirportCd;
	}

	/**
	 * @param arrivalAirportCd the arrivalAirportCd to set
	 */
	public void setArrivalAirportCd(String arrivalAirportCd) {
		this.arrivalAirportCd = arrivalAirportCd;
	}

	/**
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the flightStatusCode
	 */
	public String getFlightStatusCode() {
		return flightStatusCode;
	}

	/**
	 * @param flightStatusCode the flightStatusCode to set
	 */
	public void setFlightStatusCode(String flightStatusCode) {
		this.flightStatusCode = flightStatusCode;
	}

	/**
	 * @return the deltaOperatedIndicator
	 */
	public String getDeltaOperatedIndicator() {
		return deltaOperatedIndicator;
	}

	/**
	 * @param deltaOperatedIndicator the deltaOperatedIndicator to set
	 */
	public void setDeltaOperatedIndicator(String deltaOperatedIndicator) {
		this.deltaOperatedIndicator = deltaOperatedIndicator;
	}

	/**
	 * @return the equipmenOwnerCode
	 */
	public String getEquipmenOwnerCode() {
		return equipmenOwnerCode;
	}

	/**
	 * @param equipmenOwnerCode the equipmenOwnerCode to set
	 */
	public void setEquipmenOwnerCode(String equipmenOwnerCode) {
		this.equipmenOwnerCode = equipmenOwnerCode;
	}

	/**
	 * @return the equipmenOwnerName
	 */
	public String getEquipmenOwnerName() {
		return equipmenOwnerName;
	}

	/**
	 * @param equipmenOwnerName the equipmenOwnerName to set
	 */
	public void setEquipmenOwnerName(String equipmenOwnerName) {
		this.equipmenOwnerName = equipmenOwnerName;
	}

	/**
	 * @return the marketedCompartmentCode
	 */
	public String getMarketedCompartmentCode() {
		return marketedCompartmentCode;
	}

	/**
	 * @param marketedCompartmentCode the marketedCompartmentCode to set
	 */
	public void setMarketedCompartmentCode(String marketedCompartmentCode) {
		this.marketedCompartmentCode = marketedCompartmentCode;
	}

	/**
	 * @return the operatedCompartmentCode
	 */
	public String getOperatedCompartmentCode() {
		return operatedCompartmentCode;
	}

	/**
	 * @param operatedCompartmentCode the operatedCompartmentCode to set
	 */
	public void setOperatedCompartmentCode(String operatedCompartmentCode) {
		this.operatedCompartmentCode = operatedCompartmentCode;
	}

	/**
	 * @return the inhibitedClassOfService
	 */
	public String getInhibitedClassOfService() {
		return inhibitedClassOfService;
	}

	/**
	 * @param inhibitedClassOfService the inhibitedClassOfService to set
	 */
	public void setInhibitedClassOfService(String inhibitedClassOfService) {
		this.inhibitedClassOfService = inhibitedClassOfService;
	}

	/**
	 * @return the marketingClassOfService
	 */
	public String getMarketingClassOfService() {
		return marketingClassOfService;
	}

	/**
	 * @param marketingClassOfService the marketingClassOfService to set
	 */
	public void setMarketingClassOfService(String marketingClassOfService) {
		this.marketingClassOfService = marketingClassOfService;
	}

	/**
	 * @return the operatedClassOfService
	 */
	public String getOperatedClassOfService() {
		return operatedClassOfService;
	}

	/**
	 * @param operatedClassOfService the operatedClassOfService to set
	 */
	public void setOperatedClassOfService(String operatedClassOfService) {
		this.operatedClassOfService = operatedClassOfService;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightInfo [id=").append(id).append(", availableDays=").append(availableDays)
				.append(", marketingCarrierCode=").append(marketingCarrierCode).append(", marketingFlightNumber=")
				.append(marketingFlightNumber).append(", operatingCarrierCode=").append(operatingCarrierCode)
				.append(", operatingFlightNumber=").append(operatingFlightNumber).append(", departureAirportCd=")
				.append(departureAirportCd).append(", arrivalAirportCd=").append(arrivalAirportCd)
				.append(", effectiveDate=").append(effectiveDate).append(", expirationDate=").append(expirationDate)
				.append(", flightStatusCode=").append(flightStatusCode).append(", deltaOperatedIndicator=")
				.append(deltaOperatedIndicator).append(", equipmenOwnerCode=").append(equipmenOwnerCode)
				.append(", equipmenOwnerName=").append(equipmenOwnerName).append(", marketedCompartmentCode=")
				.append(marketedCompartmentCode).append(", operatedCompartmentCode=").append(operatedCompartmentCode)
				.append(", inhibitedClassOfService=").append(inhibitedClassOfService)
				.append(", marketingClassOfService=").append(marketingClassOfService)
				.append(", operatedClassOfService=").append(operatedClassOfService).append("]");
		return builder.toString();
	}
	
	
}
