
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
  amountInput.addEventListener("input", function () {
    this.value = this.value.replace(/[^0-9]/g, "");
  });


/*
     バリデーション関数（入力された値が正しいかどうかをチェックするための関数）
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
     二重送信防止
     送信ボタンを押したら「登録中...」に変えて無効化する
*/
  submitBtn.addEventListener("click", function () {
    const self = this;
    setTimeout(function () {
      self.disabled = true;
      self.textContent = "登録中...";
    }, 200);
  });

