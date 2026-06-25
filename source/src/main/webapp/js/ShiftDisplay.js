document.addEventListener("DOMContentLoaded", () => {
  // 編集する日付のinput要素を取得
  const updateDateInput = document.getElementById("update_date");
  
  // テーブルヘッダー内の日付セルをすべて取得
  const dateHeaderCells = document.querySelectorAll(".date-header-cell");

  // 各日付セルにクリックイベントを設定
  dateHeaderCells.forEach((cell) => {
    cell.addEventListener("click", () => {
      // セルからテキスト（日付文字列）を取得して前後の余白を削除
      const targetDate = cell.textContent.trim();

      // 日付フォーマット（YYYY-MM-DD）の簡易チェック
      if (/^\d{4}-\d{2}-\d{2}$/.test(targetDate)) {
        // input要素の値を更新
        updateDateInput.value = targetDate;
      }
    });
  });
});