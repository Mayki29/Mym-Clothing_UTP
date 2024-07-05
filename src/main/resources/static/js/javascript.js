let navbar = document.querySelector(".navbar");
document.querySelector("#opciones-btn").onclick = () => {
  navbar.classList.toggle("active");
  cartItem.classList.remove("active");
};

let cartItem = document.querySelector(".cart-items-container");

document.querySelector("#cart-btn").onclick = () => {
  cartItem.classList.toggle("active");
  navbar.classList.remove("active");
};

window.onscroll = () => {
  navbar.classList.remove("active");
  cartItem.classList.remove("active");
};

/* Profile */

let subMenu = document.getElementById("subMenu");

function toggleMenu() {
  subMenu.classList.toggle("open-menu");
}

function redireccionar() {
  window.location.href = "index_profile.html";
}

function redireccionar2() {
  window.location.href = "index.html";
}

/* Close Profile */
