package com.covid19.base.deserialize;

import java.time.LocalDateTime;

import com.covid19.base.DailyData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyDataCsv implements DailyData {

	private String stateProvince;

	private String countryRegion;

	private LocalDateTime lastUpdate;

	private int confirmed;

	private int deaths;

	private int recovered;

	@Override
	public int getConfirmed() {
		return confirmed;
	}

	@Override
	public String getCountryRegion() {
		return countryRegion;
	}

	@Override
	public int getDeaths() {
		return deaths;
	}

	@Override
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	@Override
	public int getRecovered() {
		return recovered;
	}

	@Override
	public String getStateProvince() {
		return stateProvince;
	}

	@JsonProperty("CONFIRMED")
	@JsonDeserialize(using = IntDeserializer.class)
	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	@JsonDeserialize(using = StringDeserializer.class)
	@JsonProperty("COUNTRY_REGION")
	public void setCountryRegion(String countryRegion) {
		this.countryRegion = countryRegion;
	}

	@JsonDeserialize(using = IntDeserializer.class)
	@JsonProperty("DEATHS")
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("LAST_UPDATE")
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@JsonDeserialize(using = IntDeserializer.class)
	@JsonProperty("RECOVERED")
	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}

	@JsonDeserialize(using = StringDeserializer.class)
	@JsonProperty("PROVINCE_STATE")
	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	@Override
	public String toString() {
		return "DailyDataCsv [stateProvince=" + stateProvince + ", countryRegion=" + countryRegion + ", lastUpdate="
				+ lastUpdate + ", confirmed=" + confirmed + ", deaths=" + deaths + ", recovered=" + recovered + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + confirmed;
		result = prime * result + ((countryRegion == null) ? 0 : countryRegion.hashCode());
		result = prime * result + deaths;
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + recovered;
		result = prime * result + ((stateProvince == null) ? 0 : stateProvince.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyDataCsv other = (DailyDataCsv) obj;
		if (confirmed != other.confirmed)
			return false;
		if (countryRegion == null) {
			if (other.countryRegion != null)
				return false;
		} else if (!countryRegion.equals(other.countryRegion))
			return false;
		if (deaths != other.deaths)
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		if (recovered != other.recovered)
			return false;
		if (stateProvince == null) {
			if (other.stateProvince != null)
				return false;
		} else if (!stateProvince.equals(other.stateProvince))
			return false;
		return true;
	}

}
