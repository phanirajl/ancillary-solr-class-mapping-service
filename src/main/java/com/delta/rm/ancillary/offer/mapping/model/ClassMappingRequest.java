/**
 * 
 */
package com.delta.rm.ancillary.offer.mapping.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Class ClassMappingRequest
 *
 */
public class ClassMappingRequest implements Serializable{

	/**
	 * serial generated
	 */
	private static final long serialVersionUID = -692606560108638034L;
	/**The variable that has the info about: marketingCarrierCode*/
	private String marketingCarrierCode;
	
	/**The variable that has the info about: marketingFlightNumber*/
	private String marketingFlightNumber;
	
	/**The variable that has the info about: departureAirportCd*/
	private String departureAirportCd;
	
	/**The variable that has the info about: arrivalAirportCd*/
	private String arrivalAirportCd;
	
	/**The variable that has the info about: departureDate*/
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	private Date departureDate;
	
	/**The variable that has the info about: marketingClassOfService*/
	private String marketingClassOfService;

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
	 * @return the departureDate
	 */
	public Date getDepartureDate() {
		return departureDate;
	}

	/**
	 * @param departureDate the departureDate to set
	 */
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClassMappingRequest [marketingCarrierCode=").append(marketingCarrierCode)
				.append(", marketingFlightNumber=").append(marketingFlightNumber).append(", departureAirportCd=")
				.append(departureAirportCd).append(", arrivalAirportCd=").append(arrivalAirportCd)
				.append(", departureDate=").append(departureDate).append(", marketingClassOfService=")
				.append(marketingClassOfService).append("]");
		return builder.toString();
	}
	
	
	
	
}
