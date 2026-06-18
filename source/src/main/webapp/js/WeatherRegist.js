// JavaScript

// 緯度・経度（北海道 十勝・帯広付近）
const latitude = 42.92;
const longitude = 143.20;

// Open-MeteoのAPI URL
const apiUrl = `https://api.open-meteo.com/v1/forecast?latitude=43.7706&longitude=142.3648&hourly=weather_code,temperature_2m,relative_humidity_2m,precipitation,wind_speed_10m&timezone=Asia%2FTokyo`;
//https://api.open-meteo.com/v1/forecast?latitude=43.432&longitude=142.9347&daily=weather_code,temperature_2m_max,temperature_2m_min,rain_sum,wind_speed_10m_max&hourly=temperature_2m&current=precipitation,relative_humidity_2m,weather_code,wind_speed_10m,rain,apparent_temperature&timezone=Asia%2FTokyo&forecast_days=1
// ボタンの取得
const btn = document.getElementById('get-weather-btn');
const iconElem = document.getElementById('weather-icon');
const textElem = document.getElementById('weather-text');
const tempElem = document.getElementById('temperature');

// 天気コードの変換
const weatherCodes = {
    0: { text: '快晴', icon: '☀️' },
    1: { text: '晴れ', icon: '🌤️' },
    2: { text: '一部曇り', icon: '⛅' },
    3: { text: '曇り', icon: '☁️' },
    61: { text: '小雨', icon: '🌧️' },
    63: { text: '雨', icon: '🌧️' },
    65: { text: '大雨', icon: '🌧️' }
};

// 天気情報を取得する関数
async function fetchWeather() {
    console.log('天気情報を取得中...');
    
    // APIからデータを取得
    const response = await fetch(apiUrl);
    const data = await response.json();
    
    // コンソールでデータを確認
    console.log('取得したデータ:', data);
    console.log('現在の天気情報:', data.current_weather);
    console.log('気温:', data.current_weather.temperature);
    console.log('天気コード:', data.current_weather.weathercode);

    // データの取り出し
    const current = data.current_weather;
    const temp = current.temperature; // 気温
    const code = current.weathercode; // 天気コード

    // 登録がないコードは「不明」とする
    const weatherInfo = weatherCodes[code] || { text: '不明', icon: '?' };
    
    // 画面に表示
    if (iconElem) iconElem.textContent = weatherInfo.icon;
    if (textElem) textElem.textContent = weatherInfo.text;
    if (tempElem) tempElem.textContent = `${temp}度`;
}

// ボタンをクリックしたら実行
if (btn) {
    btn.addEventListener('click', fetchWeather);
}