const form = document.getElementById("myForm");
const nameInput = document.getElementById("name");
const genderInput = document.getElementById("gender");
const ageInput = document.getElementById("age");
const addressInput = document.getElementById("address");
const phoneInput = document.getElementById("phone");
const errorMessage1 = document.getElementById("error1");
const errorMessage2 = document.getElementById("error2");

ageInput.addEventListener("input", () => {
    ageInput.value = ageInput.value.replace(/\D/g, "")
  })

form.addEventListener("submit", function(event) {
	let messages = [];

  	// 必須チェック
  	if (nameInput.value.trim() === "") {
    	messages.push(" お名前 ");
  	}
	//数字のみの処理をjspに書く予定
	if (ageInput.value.trim() === "") {
    	messages.push(" 年齢 ");
  	}
  	if (phoneInput.value.trim() === "") {
    	messages.push(" 電話番号 ");
  	}
  // メール形式チェック
  	if (!addressInput.value.includes("")) {
    	messages.push(" 住所 ");
  	}

  	// エラーがあれば送信を止める
  	if (messages.length > 0) {
    	event.preventDefault(); // フォーム送信を止める
    	errorMessage1.textContent = messages.join(" ");
    	errorMessage2.textContent = ("を入力してください");

  	}
});

