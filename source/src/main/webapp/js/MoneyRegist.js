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

document.addEventListener("DOMContentLoaded", function () {

  const parentSel    = document.querySelector(".parent");
  const childrenSel  = document.querySelector(".children");
  const amountInput  = document.querySelector("input[name='amount']");
  const submitBtn    = document.querySelector("button[type='submit']");
  const origChildren = childrenSel.innerHTML;

  /* エラーメッセージの表示・非表示 */
  function showErr(id, show) {
    document.getElementById(id).classList.toggle("show", show);
  }

  /* 登録ボタンの活性制御 */
  function updateBtn() {
    const ok = parentSel.value !== ""
            && childrenSel.value !== ""
            && amountInput.value.trim() !== ""
            && Number(amountInput.value) > 0;
    submitBtn.disabled = !ok;
  }

  /* 初期状態: ボタン無効 */
  submitBtn.disabled = true;

  /* 収入・支出セレクト変更 */
  parentSel.addEventListener("change", function () {
    const val = this.value;
    childrenSel.innerHTML = origChildren;

    if (val === "") {
      childrenSel.value    = "";
      childrenSel.disabled = true;
    } else {
      childrenSel.querySelectorAll("option[data-val]").forEach(function (opt) {
        if (opt.dataset.val !== val) opt.remove();
      });
      childrenSel.value    = "";
      childrenSel.disabled = false;
    }

    showErr("err-parent",   val === "");
    showErr("err-children", false);
    updateBtn();
  });

  /* 理由セレクト変更 */
  childrenSel.addEventListener("change", function () {
    showErr("err-children", this.value === "");
    updateBtn();
  });

  /* 金額入力（数字以外を除去＋エラー表示） */
  amountInput.addEventListener("input", function () {
    this.value = this.value.replace(/[^0-9]/g, "");
    const empty = this.value.trim() === "" || Number(this.value) <= 0;
    showErr("err-amount", empty);
    updateBtn();
  });

  /* フォーム送信 */
  document.querySelector("form").addEventListener("submit", function (e) {
    if (submitBtn.disabled) { e.preventDefault(); return; }

    const moneyLabel  = parentSel.options[parentSel.selectedIndex].text;
    const reasonLabel = childrenSel.options[childrenSel.selectedIndex].text;
    const amount      = Number(amountInput.value).toLocaleString();
    const confirmed   = window.confirm(
      "以下の内容で登録します。よろしいですか？\n\n" +
      "種別 : " + moneyLabel + "\n" +
      "理由 : " + reasonLabel + "\n" +
      "金額 : " + amount + " 円"
    );
    if (!confirmed) e.preventDefault();
  });

})


