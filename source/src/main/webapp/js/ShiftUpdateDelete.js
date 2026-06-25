document.querySelector("#form").addEventListener("submit", function(e) {

	const deleteBtn = document.activeElement;

	if (deleteBtn.name === "shift_delete") {
		const ok = confirm("本当に削除しますか？");

		if (!ok) {
			e.preventDefault();
		}
	}
});