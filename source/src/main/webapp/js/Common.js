/**
 * 
 */
 /*ログアウト処理*/ 
/*function logout_check(){
    const answer = window.confirm('ログアウトしてよろしいですか？');

    if (answer === true){
        window.location.href = 'login.html';
    }else{
        event.preventDefault();
    }
}
*/
function logout(){
    const form = document.createElement('form');
    form.method = 'get'; 
    form.action = 'LoginServlet';
    document.body.appendChild(form);
    form.submit();
}

const btn = document.getElementById("btn");


btn.addEventListener('click', (e) => {
    e.preventDefault(); 
    
    let result = confirm('ログアウトしますか？');
    if (result) {
        logout();
    }
});