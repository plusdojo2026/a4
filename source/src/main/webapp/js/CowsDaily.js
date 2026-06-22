'use strict';

const now = new Date();

const year = now.getFullYear();
const month = now.getMonth();
const date = now.getDate();

const output = `🐄今日は${year}年${month + 1}月${date}日です🐄。`;
document.getElementById('time').textContent = output;


//フォームの制御(まるぱくりしました)
const form = document.getElementById("form");

const button = document.getElementById("button");

form.addEventListener("input", update);
form.addEventListener("change", update);


function update() {
  const isRequired = form.checkValidity();

  if (isRequired) {
    button.disabled = false;
    return;
  }
}

const form2 = document.getElementById("form2");
const button2 = document.getElementById("button2");

form2.addEventListener("input", update2);
form2.addEventListener("change", update2);

function update2() {
  const isRequired = form2.checkValidity();

  if (isRequired) {
    button2.disabled = false;
    return;
  }
  button2.disabled = true;
}
