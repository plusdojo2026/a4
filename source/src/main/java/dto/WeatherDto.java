package dto;

import java.io.Serializable;
//データ型を使うためのインポート
//DBのdecimal→BigDecimal, date→LocalDateとしています
import java.math.BigDecimal;
import java.time.LocalDate;

public class WeatherDto implements Serializable{
	private LocalDate day;
	private int weather;
	private BigDecimal high_temperature;
	private BigDecimal low_temperature;
	private int humidity;
	private BigDecimal precipitation;
	private BigDecimal windpower;
	
	// 空コンストラクタ
	public WeatherDto() {
	}
	
	// コンストラクタ
	public WeatherDto(LocalDate day, int weather, BigDecimal high_temperature, BigDecimal low_temperature, int humidity,
			BigDecimal precipitation, BigDecimal windpower) {
		super();
		this.day = day;
		this.weather = weather;
		this.high_temperature = high_temperature;
		this.low_temperature = low_temperature;
		this.humidity = humidity;
		this.precipitation = precipitation;
		this.windpower = windpower;
	}
	

	// 以下、getterとsetter
	public LocalDate getDay() {
		return day;
	}
	public void setDay(LocalDate day) {
		this.day = day;
	}
	public int getWeather() {
		return weather;
	}
	public void setWeather(int weather) {
		this.weather = weather;
	}
	public BigDecimal getHigh_temperature() {
		return high_temperature;
	}
	public void setHigh_temperature(BigDecimal high_temperature) {
		this.high_temperature = high_temperature;
	}
	public BigDecimal getLow_temperature() {
		return low_temperature;
	}
	public void setLow_temperature(BigDecimal low_temperature) {
		this.low_temperature = low_temperature;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public BigDecimal getPrecipitation() {
		return precipitation;
	}
	public void setPrecipitation(BigDecimal precipitation) {
		this.precipitation = precipitation;
	}
	public BigDecimal getWindpower() {
		return windpower;
	}
	public void setWindpower(BigDecimal windpower) {
		this.windpower = windpower;
	}
	
	
	
}
