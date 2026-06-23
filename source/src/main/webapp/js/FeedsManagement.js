/*
     数値入力バリデーション
     - 0以下の数値は送信させない
     - 未入力のまま送信させない
     JSPにフォームが2つあるが、どちらも同じ name="money" のため
     querySelectorAll でまとめて取得する
 */

  // ページ内の全フォームを取得（panel1・panel2 それぞれにフォームがある）
  const forms = document.querySelectorAll("form");

  forms.forEach(function (form) {
    form.addEventListener("submit", function (e) {

      // 数値入力フィールドを取得
      const moneyInput = form.querySelector('input[name="money"]');
      const value = moneyInput.value.trim();

      // 未入力チェック
      if (value === "" || value === null) {
        e.preventDefault(); // フォーム送信をキャンセル
        alert("数値を入力してください。");
        moneyInput.focus(); // 入力欄にフォーカスを戻す
        return;
      }

      // 数値変換
      const numValue = Number(value);

      // 0以下チェック
      if (numValue <= 0) {
        e.preventDefault();
        alert("1以上の数値を入力してください。");
        moneyInput.focus();
        return;
      }

      // 小数チェック（エサの量は整数のみ想定）
      if (!Number.isInteger(numValue)) {
        e.preventDefault();
        alert("整数を入力してください。");
        moneyInput.focus();
        return;
      }

      // バリデーション通過 → 送信確認ダイアログ
      // どちらのタブ（増量/減量）かを hidden フィールドから判定
      const record = form.querySelector('input[name="record"]').value;
      const confirmed = window.confirm(
        record + " として " + numValue + " を登録します。よろしいですか？"
      );

      if (!confirmed) {
        e.preventDefault(); // キャンセル時は送信しない
      }
    });
  });


/*
     数値入力フィールド：負の値・小数点入力を即時制限
     input イベントで入力中にリアルタイムチェックする
*/
  const numberInputs = document.querySelectorAll('input[type="number"]');

  numberInputs.forEach(function (input) {

    // 最小値を1に設定（HTMLレベルで負の値を防ぐ）
    input.setAttribute("min", "1");
    input.setAttribute("step", "1");       // 整数ステップ
    input.setAttribute("placeholder", "例: 10"); // 入力例を表示

    // 入力中のリアルタイムチェック
    input.addEventListener("input", function () {
      // 小数点以下を切り捨て（整数のみ許可）
      if (this.value.includes(".")) {
        this.value = Math.floor(Number(this.value));
      }
      // 0以下なら空にする
      if (Number(this.value) < 0) {
        this.value = "";
      }
    });
  });


/*
     送信ボタン：二重送信防止
     一度クリックされたらボタンを無効化する
*/
  const submitButtons = document.querySelectorAll('input[type="submit"]');

  submitButtons.forEach(function (btn) {
    btn.addEventListener("click", function () {
      // 少し遅延させて、バリデーションがキャンセルした場合は無効化しない
      setTimeout(function () {
        // フォームにエラーがなく送信が進んでいる場合のみ無効化
        btn.disabled = true;
        btn.value = "送信中...";
      }, 100);
    });
  });

});
/* DOMContentLoaded ここまで */