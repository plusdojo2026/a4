/**
 * 
 */
 /*ログアウト処理*/ 
function logout_check(){
    const answer = window.confirm('ログアウトしてよろしいですか？');

    if (answer === true){
        window.location.href = 'login.html';
    }else{
        event.preventDefault();
    }
}