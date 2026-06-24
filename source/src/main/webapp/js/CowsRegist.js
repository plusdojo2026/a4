const form = document.getElementById("myForm");
const nameInput = document.getElementById("name");
const genderInput = document.getElementById("gender");
const birth_dayInput = document.getElementById("birth_day");
const statusInput = document.getElementById("status");
const photoInput = document.getElementById("photo");


ageInput.addEventListener("input", () => {
    ageInput.value = ageInput.value.replace(/\D/g, "")
  })

form.addEventListener("submit", function(event) {
	let messages = [];

  	// 必須チェック
  	//数字のみ
  	if (nameInput.value.trim() === "") {
    	messages.push(" ウシID ");
  	}
  	
	if (ageInput.value.trim() === "") {
    	messages.push(" 名前 ");
  	}

  	// エラーがあれば送信を止める
  	if (messages.length > 0) {
    	event.preventDefault(); // フォーム送信を止める
    	errorMessage1.textContent = messages.join(" ");
    	errorMessage2.textContent = ("を入力してください");

  	}
});

