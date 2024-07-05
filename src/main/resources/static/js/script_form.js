const formOpenBtn = document.querySelector("#user-btn"),
  home = document.querySelector(".home"),
  formContainer = document.querySelector(".form_container"),
  formCloseBtn = document.querySelector(".form_close"),
  signupBtn = document.querySelector("#signup"),
  loginBtn = document.querySelector("#login");

const forgotPwLink = document.querySelector(".forgot_pw"),
  backToLoginLink = document.querySelector("#back_to_login"),
  backToSignupLink = document.querySelector("#back_to_signup"),
  forgotPwForm = document.querySelector(".forgot_password_form"),
  loginForm = document.querySelector(".login_form"),
  signupForm = document.querySelector(".signup_form");

function showLoginForm() {
  loginForm.style.display = "block";
  signupForm.style.display = "none";
  forgotPwForm.style.display = "none";
  formContainer.classList.remove("active");
}

function showSignupForm() {
  loginForm.style.display = "none";
  signupForm.style.display = "block";
  forgotPwForm.style.display = "none";
  formContainer.classList.add("active");
}

function showForgotPwForm() {
  loginForm.style.display = "none";
  signupForm.style.display = "none";
  forgotPwForm.style.display = "block";
  formContainer.classList.remove("active");
}

formOpenBtn.addEventListener("click", () => home.classList.add("show"));

formCloseBtn.addEventListener("click", () => home.classList.remove("show"));

loginBtn.addEventListener("click", (e) => {
  e.preventDefault();
  showLoginForm();
});

signupBtn.addEventListener("click", (e) => {
  e.preventDefault();
  showSignupForm();
});

forgotPwLink.addEventListener("click", (e) => {
  e.preventDefault();
  showForgotPwForm();
});

backToLoginLink.addEventListener("click", (e) => {
  e.preventDefault();
  showLoginForm();
});

backToSignupLink.addEventListener("click", (e) => {
  e.preventDefault();
  showSignupForm();
});
