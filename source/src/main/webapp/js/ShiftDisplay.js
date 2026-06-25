document.querySelector("form[action='ShiftDisplayServlet']")
	.addEventListener("submit", function(e) {

	// 入力値取得

	// 従業員ID
	const id = document.querySelector("#submit_employee").value;

	// 日付
	const date = document.querySelector("input[name='day']").value;

	// シフト種別（早朝・朝・昼など）
	const time = document.querySelector("#submit_intime").value;
	
	// チェック処理
	// 従業員未選択
	if (!id) {
		alert("従業員を選択してください");
		e.preventDefault();
		return;
	}

	// 日付未選択
	if (!date) {
		alert("日付を選択してください");
		e.preventDefault();
		return;
	}

	// シフト未選択
	if (time === "") {
		alert("シフトを選択してください");
		e.preventDefault();
		return;
	}
});


/*// カレンダークリック

document.querySelectorAll(".shift-cell")
	.forEach(cell => {

	cell.addEventListener("click", function() {

		// 行（従業員ID）
		const empId = this.parentElement.dataset.empId;

		// 列（日付）
		const date = this.dataset.date;

		// 安全チェック
		if (!empId || !date) return;

		// 編集画面へ遷移
		location.href =
			"ShiftUpdateDeleteServlet?id=" + empId + "&day=" + date;
	});
});*/