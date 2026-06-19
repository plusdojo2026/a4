// JavaScript

'use strict';
/**/ 
function getCtime() {
    const now = new Date();

    const month = now.getMonth()+1;
    const day = now.getDate();
    const hour = now.getHours();
    const minute =now.getMinutes();

    const time = `現在は${month}月${day}日の${hour}時${minute}分`;
    document.getElementById('timer').textContent = time;
}
getCtime();
// 緯度・経度（北海道 十勝・帯広付近）
const latitude = 42.92;
const longitude = 143.20;

// Open-MeteoのAPI URL
const apiUrl = `https://api.open-meteo.com/v1/forecast?latitude=43.7706&longitude=142.3648&hourly=weather_code,temperature_2m,relative_humidity_2m,precipitation,wind_speed_10m&timezone=Asia%2FTokyo`;
//https://api.open-meteo.com/v1/forecast?latitude=43.432&longitude=142.9347&daily=weather_code,temperature_2m_max,temperature_2m_min,rain_sum,wind_speed_10m_max&hourly=temperature_2m&current=precipitation,relative_humidity_2m,weather_code,wind_speed_10m,rain,apparent_temperature&timezone=Asia%2FTokyo&forecast_days=1
