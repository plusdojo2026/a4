/*
  //要素の取得
  const parentSelect   = document.getElementById(".parent");       // 収入・支出セレクト
  const childrenSelect = document.getElementById(".children");     // 理由セレクト
  const amountInput    = document.getElementById("input[name='amount']"); // 金額入力
  const form           = document.getElementById("form");          // フォーム全体
  const submitBtn      = document.getElementById("button[type='submit']"); // 登録ボタン

/*
     金額フィールド：数字以外を即時除去
     replace(/[^0-9]/g, "") 0～9以外の文字を全部（g=global)""(空文字)に置き換える
*/
/*
  amountInput.addEventListener("input", function () {
    this.value = this.value.replace(/[^0-9]/g, "");
  });


/*
     バリデーション関数（入力された値が正しいかどうかをチェックするための関数）
     エラーがあればメッセージ文字列を返す。なければ ""。
*/
/*
  function validate() {
    const moneyVal  = parentSelect.value;
    const reasonVal = childrenSelect.value;
    const amountVal = amountInput.value.trim();

    if (!moneyVal)              return "「収入・支出」を選択してください。";
    if (!reasonVal)             return "「理由」を選択してください。";
    if (amountVal === "")       return "「金額」を入力してください。";
    if (Number(amountVal) <= 0) return "「金額」は1以上の数値を入力してください。";
    return "";
  }
*/


  /* -----------------------------------------------
     要素の取得
  ----------------------------------------------- */
  const parentSelect   = document.querySelector(".parent");       // 収入・支出セレクト
  const childrenSelect = document.querySelector(".children");     // 理由セレクト
  const amountInput    = document.querySelector("input[name='amount']"); // 金額入力
  const form           = document.querySelector("form");          // フォーム全体
  const submitBtn      = document.querySelector("button[type='submit']"); // 登録ボタン

  // 金額にプレースホルダーを設定
  amountInput.setAttribute("placeholder", "例: 10000");

/*
     親セレクト変更イベント（収入・支出の切り替え）
     選択値に合わない data-val を持つ option を削除する
*/
  parentSelect.addEventListener("change", function () {
    const selectedVal = this.value; // "income" or "expend" or ""

    // 子セレクトを初期状態にリセット
    childrenSelect.innerHTML = originalChildren;

    if (selectedVal === "") {
      // 未選択 → 子セレクトを無効化
      childrenSelect.value = "";
      childrenSelect.setAttribute("disabled", "disabled");
    } else {
      // 不要な option を削除（先頭の「項目の選択」は残す）
      const options = childrenSelect.querySelectorAll("option[data-val]");
      options.forEach(function (opt) {
        if (opt.dataset.val !== selectedVal) {
          opt.remove();
        }
      });
      // 子セレクトを有効化・先頭に戻す
      childrenSelect.value = "";
      childrenSelect.removeAttribute("disabled");
    }
  });


/*
     金額フィールド：数字以外を即時除去
*/
  amountInput.addEventListener("input", function () {
    this.value = this.value.replace(/[^0-9]/g, "");
  });


/*
     バリデーション関数(入力された値が正しいかどうかをチェックするための関数）
     エラーがあればメッセージ文字列を返す。なければ ""。
*/
  function validate() {
    const moneyVal  = parentSelect.value;
    const reasonVal = childrenSelect.value;
    const amountVal = amountInput.value.trim();

    if (!moneyVal)              return "「収入・支出」を選択してください。";
    if (!reasonVal)             return "「理由」を選択してください。";
    if (amountVal === "")       return "「金額」を入力してください。";
    if (Number(amountVal) <= 0) return "「金額」は1以上の数値を入力してください。";
    return "";
  }


/*
     フォーム送信イベント
     バリデーション → 確認ダイアログ → 送信 or キャンセル
*/
  form.addEventListener("submit", function (e) {
    const errorMsg = validate();
    if (errorMsg) {
      e.preventDefault();
      alert(errorMsg);
      return;
    }

    // 確認ダイアログ
    const moneyLabel  = parentSelect.options[parentSelect.selectedIndex].text;
    const reasonLabel = childrenSelect.options[childrenSelect.selectedIndex].text;
    const amount      = Number(amountInput.value).toLocaleString();
    const confirmed   = window.confirm(
      "以下の内容で登録します。よろしいですか？\n\n" +
      "種別 : " + moneyLabel + "\n" +
      "理由 : " + reasonLabel + "\n" +
      "金額 : " + amount + " 円"
    );

    if (!confirmed) {
      e.preventDefault();
    }
  });




