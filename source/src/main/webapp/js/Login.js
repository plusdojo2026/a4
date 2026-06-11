/**
 * 
 */
 function validateForm(){
	var idInput = document.getElementById("loginId").value;
	var idInput = document.getElementById("loginPd").value;
	
	if(idInput.length === 0||pwInput.length === 0){
		alert("IDとパスワードを入力してください");
		return false; //サーブレットへの送信を中止
	}
	return true; //サーブレットへ送信
}