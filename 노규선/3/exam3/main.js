// DomContentLoaded 모든 HTML 문서가 로드된 상태
window.addEventListener("DOMContentLoaded", function () {
  // strict 모드 설정
  (function () {
    'use strict';
  })();

  const loginForm = document.getElementById("login-form");

  const checkingSuccess = document.getElementById("checkingSuccess");
  const checkingFail = document.getElementById("checkingFail");
  checkingSuccess.setAttribute("style", "display:none");
  checkingFail.setAttribute("style", "display:none");

  // login form validation
  // 아이디, 이름, 비밀번호는 공백이면 안 됩니다.
  // 아이디 비밀번호 공백체크 및 focus 처리
  const validateForm = function (form) {
    if (form['userId'].value.trim() == '') {
      alert("userId empty!");
      form['userId'].focus();
      return false;
    }
    if (form['userName'].value.trim() == '') {
      alert("userName empty!");
      form['userId'].focus();
      return false;
    }
    if (form['userPassword1'].value.trim() == '') {
      alert("userPassword1 empty!");
      form['userPassword'].focus();
      return false;
    }
    if (form['userPassword2'].value.trim() == '') {
      alert("userPassword2 empty!");
      form['userPassword'].focus();
      return false;
    }
  }

  // loginForm submit(전송) 이벤트 등록
  loginForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    // loginForm validation 실행
    if (validateForm(event.target) == false) {
      return;
    }

    //회원가입 api 호출
    const userId = event.target['userId'].value;
    const userName = event.target['userName'].value;
    const userPassword1 = event.target['userPassword1'].value;
    const userPassword2 = event.target['userPassword2'].value;

    // 회원가입 처리
    let user = null;
    try {
      user = await signUp(userId, userName, userPassword1, userPassword2);
      console.log(user);

      await checkSignUp(userId)

    } catch (e) {
      alert(e);
    }
  });
});

// 회원 아이디 중복 여부 체크
async function checkSignUp(userId) {

  const url = `http://133.186.144.236:8100/api/users/${userId}/exist`;

  const options = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    }
  }

  const response = await fetch(url, options);
  return response.status == 200;
}

// 회원 비밀번호 일치 여부 체크
async function checkPassword(userPassword1, userPassword2) {
  return userPassword1 === userPassword2;
}

// 회원가입 구현
async function signUp(userId, userName, userPassword1, userPassword2) {
  const checkingSuccess = document.getElementById("checkingSuccess");
  const checkingFail = document.getElementById("checkingFail");

  const url = "http://133.186.144.236:8100/api/users";

  const data = {
    userId: userId,
    userName: userName,
    userPassword1: userPassword1,
    userPassword2: userPassword2
  }

  const options = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  }

  const user = await fetch(url, options).then(response => {
    if (checkSignUp(userId)) {
      checkingFail.setAttribute("style", "display:block");
      throw new Error('sign up duplicated');
    } else if (!checkPassword(userPassword1, userPassword2)) {
      throw new Error('password inconsistency error');
    } else if (!response.ok) {
      throw new Error('login error');
    }

    checkingSuccess.setAttribute("style", "display:block");
    return response.json();
  });

  return user;
}
