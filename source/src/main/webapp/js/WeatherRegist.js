'use strict';

// 1. 時間取得・表示の関数
function getCtime() {
    const now = new Date();
    const month = now.getMonth() + 1;
    const day = now.getDate();
    const hour = now.getHours();
    const minute = now.getMinutes();
    
    const displayHour = String(hour).padStart(2, '0');
    const displayMinute = String(minute).padStart(2, '0');
    
    const time = `現在は${month}月${day}日の${displayHour}時${displayMinute}分`;
    const timerElem = document.getElementById('timer');
    if (timerElem) timerElem.textContent = time;
}

// ページ読み込み時に時間を表示
getCtime();
setInterval(getCtime, 60000);

// 2. 緯度・経度（北海道 十勝・帯広付近）
const latitude = 42.92;
const longitude = 143.20;

// 3. Open-MeteoのAPI URL
// ※今日の最高気温・最低気温を取得するためのdailyパラメータと、現在の天気・湿度・降水量・風速を取得するためのcurrentパラメータ
const apiUrl = `https://api.open-meteo.com/v1/forecast?latitude=${latitude}&longitude=${longitude}&current=relative_humidity_2m,precipitation,weather_code,wind_speed_10m&daily=temperature_2m_max,temperature_2m_min&timezone=Asia%2FTokyo&forecast_days=1`;

// 4. 天気コードの定義（Open-Meteo仕様）
const weatherCodes = {
    0:  { text: '快晴' },
    1:  { text: '晴れ' },
    2:  { text: '一部曇り' },
    3:  { text: '曇り' },
    45: { text: '霧' },
    48: { text: '霧（氷霧）' },
    51: { text: '薄い霧雨' },
    53: { text: '霧雨' },
    55: { text: '濃い霧雨' },
    56: { text: '微弱な凍る霧雨' },
    57: { text: '凍る霧雨' },
    61: { text: '小雨' },
    63: { text: '雨' },
    65: { text: '大雨' },
    66: { text: '微弱な地雨' },
    67: { text: '強い地雨' },
    71: { text: '小雪' },
    73: { text: '雪' },
    75: { text: '大雪' },
    77: { text: '細氷・霧雪' },
    80: { text: 'わずかなにわか雨' },
    81: { text: 'にわか雨' },
    82: { text: '激しいにわか雨' },
    85: { text: 'わずかにわか雪' },
    86: { text: 'にわか雪' },
    95: { text: '雷雨' },
    96: { text: '雹を伴う微弱な雷雨' },
    99: { text: '雹を伴う激しい雷雨' }
};

// 5. 天気データを取得してフォームに自動入力する関数
async function getWeather() {
    alert('頑張って取得してます');
    
    try {
        // APIからデータを取得
        const response = await fetch(apiUrl);
        if (!response.ok) throw new Error('ネットワーク応答が正常ではありません。');
        const data = await response.json();
        
        console.log('取得したデータ:', data);
        
        // --- データの抽出 ---
        const current = data.current;
        const daily = data.daily;
        
        if (!current || !daily) {
            alert('天気データの構造が想定と異なります。');
            return;
        }

        // 天気コードとテキスト・アイコンの変換
        const code = current.weather_code;
		const weatherInfo = weatherCodes[code] || { text: '不明' };

		// 各種数値の取得
		const weatherText  = weatherInfo.text; // 例: "快晴", "不明"
        const highKion     = daily.temperature_2m_max[0]; // 最高気温
        const lowKion      = daily.temperature_2m_min[0]; // 最低気温
        const humidity     = current.relative_humidity_2m;// 湿度 (%)
        const precipitation = current.precipitation; // 降水量 (mm)
        const windSpeed    = current.wind_speed_10m; // 風速 (m/s)
        
        // --- HTMLのフォーム（input）へ自動入力 (.value を使用) ---
        const inputTenki = document.getElementById('weatherText');
        const inputHigh  = document.getElementById('highKion');
        const inputLow   = document.getElementById('lowKion');
        const inputHum   = document.getElementById('humidity');
        const inputPre   = document.getElementById('precipitation');
        const inputWind  = document.getElementById('windSpeed');
        
        if (inputTenki) inputTenki.value = weatherText;
        if (inputHigh)  inputHigh.value  = highKion;
        if (inputLow)   inputLow.value   = lowKion;
        if (inputHum)   inputHum.value   = humidity;
        if (inputPre)   inputPre.value   = precipitation;
        if (inputWind)  inputWind.value  = windSpeed;
        
        alert('データをフォームに反映しました！');

    } catch (error) {
        console.error('エラー発生しました:', error);
        alert('データ取得失敗しました。');
    }
}

// 6. イベントリスナーの登録
const Wbtn = document.getElementById('btn');
if (Wbtn) {
    Wbtn.addEventListener('click', getWeather);
}