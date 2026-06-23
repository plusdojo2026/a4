const form = document.getElementById("myForm");
const nameInput = document.getElementById("name");
const genderInput = document.getElementById("gender");
const ageInput = document.getElementById("age");
const addressInput = document.getElementById("address");
const phoneInput = document.getElementById("phone");
const errorMessage = document.getElementById("error");

form.addEventListener("submit", function(event) {
	let messages = [];

  	// 必須チェック
  	if (nameInput.value.trim() === "") {
    	messages.push("お名前を入力してください。");
  	}
	//数字のみの処理をjspに書く予定
	if (ageInput.value.trim() === "") {
    	messages.push("年齢を入力してください。");
  	}
  	if (phoneInput.value.trim() === "") {
    	messages.push("電話番号を入力してください。");
  	}
  // メール形式チェック
  	if (!addressInput.value.includes("@")) {
    	messages.push("正しいメールアドレスを入力してください。");
  	}

  	// エラーがあれば送信を止める
  	if (messages.length > 0) {
    	event.preventDefault(); // フォーム送信を止める
    	errorMessage.textContent = messages.join(" ");
  	}
});