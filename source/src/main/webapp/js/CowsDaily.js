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
}

function update() {
  const isRequired = form.checkValidity();

  if (isRequired) {
    button.disabled = false;
    return;
  }
}


	
