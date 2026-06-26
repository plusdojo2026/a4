document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('myForm');
    const cowIdInput = document.getElementById('cow_id');
    const errorMessage = document.getElementById('error_message');

    // 1. 【入力時】リアルタイムに数字以外を弾く
    if (cowIdInput) {
        cowIdInput.addEventListener('input', (e) => {
            // 半角数字以外（全角数字や文字）をその場で消去
            e.target.value = e.target.value.replace(/[^0-9]/g, '');
            
            // 入力されたらエラー表示をクリアする
            if (errorMessage) {
                errorMessage.textContent = '';
            }
        });
    }

    // 2. 【送信時】登録ボタンを押したときの最終チェック
    if (form && cowIdInput && errorMessage) {
        form.addEventListener('submit', (e) => {
            const cowIdValue = cowIdInput.value;

            // 厳格に「半角数字かつ10桁」であるかを判定する正規表現
            const exact10DigitsRegex = /^[0-9]{10}$/;

            if (!exact10DigitsRegex.test(cowIdValue)) {
                // サーバ（Servlet）への送信をストップ
                e.preventDefault();

                // 状況に応じたエラーメッセージの出し分け
                if (cowIdValue.length === 0) {
                    errorMessage.textContent = '❌ ウシIDを入力してください。';
                } else if (cowIdValue.length < 10) {
                    errorMessage.textContent = `❌ 10桁で入力してください（現在 ${cowIdValue.length} 桁）。`;
                } else {
                    errorMessage.textContent = '❌ 半角数字のみで入力してください。';
                }

                // 入力欄に自動でカーソルを合わせる
                cowIdInput.focus();
            } else {
                // 正しい入力の場合はエラー表示を消す
                errorMessage.textContent = '';
            }
        });
    }
});