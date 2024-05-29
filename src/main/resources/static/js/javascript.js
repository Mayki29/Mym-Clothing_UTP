let navbar = document.querySelector(".navbar"); /* Selecciona el primer elemento en el documento HTML que tiene la clase "navbar" y lo asigna a la variable `navbar`. */

document.querySelector("#opciones-btn").onclick = () => { 
  navbar.classList.toggle("active"); /* Alterna la clase "active" en el elemento `navbar`. */
  cartItem.classList.remove("active"); /* Elimina la clase "active" del elemento `cartItem`. */
};

let cartItem = document.querySelector(".cart-items-container");

document.querySelector("#cart-btn").onclick = () => { /* Agrega un evento de clic al elemento con el ID "cart-btn". Cuando se hace clic en este elemento, se ejecuta una función de flecha que alterna la clase "active" en el elemento `cartItem` y elimina la clase "active" del elemento `navbar`. */
  cartItem.classList.toggle("active"); /* Alterna la clase "active" en el elemento `cartItem`. */
  navbar.classList.remove("active"); /* Elimina la clase "active" del elemento `navbar`. */
};

window.onscroll = () => { /* Agrega un evento de desplazamiento de la ventana (`window`). Cuando se desplaza la ventana, se ejecuta una función de flecha que elimina la clase "active" de los elementos `navbar` y `cartItem`, lo que probablemente esté destinado a ocultar ambos elementos cuando el usuario está desplazándose por la página. */
  navbar.classList.remove("active"); /* Elimina la clase "active" del elemento `navbar`. */
  cartItem.classList.remove("active"); /* Elimina la clase "active" del elemento `cartItem`. */
};
