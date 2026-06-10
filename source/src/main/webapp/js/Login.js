/**
 * 
 */
 document.querySelector('form').onsubmit = function(event){
	
    let id = document.querySelector('input[name="id"]').id.value;
    let pw = document.querySelector('input[name="pw"]').pw.value;
    
    if(id === '' || pw === ''){
        document.getElementById('errorArea').textContent = 'IDとPWを入力してください!';
        event.preventDefault();
    }
}

