package com.covid19.base;

import java.time.LocalDateTime;

public interface DailyData {

	int getConfirmed();

	String getCountryRegion();

	int getDeaths();

	LocalDateTime getLastUpdate();

	int getRecovered();

	String getStateProvince();

}