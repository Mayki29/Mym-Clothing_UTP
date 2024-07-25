window.onload = function () {
    cargarCarrito();
};

function guardarAlmacenamientoLocal(producto) {
    let productosCarrito = JSON.parse(localStorage.getItem('productos')) || [];
    productosCarrito.push(producto);
    localStorage.setItem('productos', JSON.stringify(productosCarrito));
}

function obtenerAlmacenamientoLocal(llave) {
    const datos = JSON.parse(localStorage.getItem(llave));
    return datos;
}

let mensaje = document.getElementById('mensaje');

//añadir carrito
document.querySelector(".box-container").addEventListener("click", e => {
    if (e.target.classList.contains('btn-agregar-carrito')) {
        let idProducto = e.target.parentElement.parentElement.querySelector(".content > input").value
        if (!isInCart(idProducto)) {
            let product = buscarProducto(idProducto)

            let itemCarrito = {
                "producto": product,
                "precioUnitario": product.precioVenta,
                "cantidad": 1
            }
            guardarAlmacenamientoLocal(itemCarrito)
            cargarCarrito()
        }

    }

})

// Función para actualizar el icono del carrito
function actualizarIconoCarrito() {
    let cart = obtenerAlmacenamientoLocal('productos');
    const iconoCarrito = document.querySelector(".bi-cart3");
    let totalProductos = 0;
    if(cart == null){
        return
    }
    totalProductos = cart.length;
    iconoCarrito.setAttribute("cart-count", totalProductos);
}

// Función para buscar producto por ID
function buscarProducto(idProducto) {
    return listaProductos.filter((p) => p.id == idProducto)[0] || {};
}

// Función para cargar el carrito desde localStorage
function cargarCarrito() {
    const carrito = document.querySelector(".cart-items-container-c");
    let elementos = "";
    let productosCart = obtenerAlmacenamientoLocal('productos');
    console.log(productosCart)
    let totalTag = document.querySelector(".precio-total");
    if(productosCart == null){
        return
    }
    for (let i = 0; i < productosCart.length; i++) {
        elementos += `
            <div class="cart-item">
                <span class="bi bi-x-lg" onclick="eliminarProducto(${productosCart[i].producto.id})"></span>
                <img src="imgProductos/${productosCart[i].producto.urlImagen}" alt="" />
                <div class="content">
                    <h3>${productosCart[i].producto.nombreProducto}</h3>
                    <div class="row">
                        <div class="price col-4">S/.${productosCart[i].precioUnitario}</div>
                        <div class="quantity ml-2 p-0 col-8 row g-0">
                            <button class="col-4 btn-cantidad-carrito" onclick="cambiarCantidad(${productosCart[i].producto.id}, -1)">-</button>
                            <input class="col-4 cantidad border-0" type="number" value="${productosCart[i].cantidad}" readonly>
                            <button class="col-4 btn-cantidad-carrito" onclick="cambiarCantidad(${productosCart[i].producto.id}, 1)">+</button>
                        </div>
                    </div>
                    
                </div>
            </div>`;
    }

    carrito.innerHTML = elementos;
    totalTag.textContent = obtenerTotal();
    actualizarIconoCarrito();
}

function obtenerTotal() {
    let productosCart = obtenerAlmacenamientoLocal('productos');
    let total = productosCart.reduce((sum, prod) => sum + prod.precioUnitario * prod.cantidad, 0);
    return total;
}

function eliminarProducto(idProducto) {
    let productosCart = obtenerAlmacenamientoLocal('productos');
    let productosUpdated = productosCart.filter(prod => prod.producto.id !== idProducto);
    localStorage.setItem('productos', JSON.stringify(productosUpdated));
    cargarCarrito();
}

function cambiarCantidad(idProducto, cambio) {
    let productosCart = obtenerAlmacenamientoLocal('productos');
    let producto = productosCart.find(prod => prod.producto.id === idProducto);
    let index = productosCart.findIndex(prod => prod.producto.id === idProducto);
    if (producto) {
        producto.cantidad += cambio;
        if (producto.cantidad <= 0) {
            eliminarProducto(idProducto);
        } else {
            productosCart[index] = producto
            localStorage.setItem('productos', JSON.stringify(productosCart));
            cargarCarrito();
        }
    }
}

function isInCart(id) {
    let productosCart = obtenerAlmacenamientoLocal('productos');
    if(productosCart != null){
        let isIn = productosCart.some(e => e.producto.id == id);
        return isIn;
    }
    return false

}

function limpiarCarrito() {
    localStorage.removeItem("productos");
    cargarCarrito();
}

function registrarVenta() {
    let detalleVenta = obtenerAlmacenamientoLocal('productos');
    let venta = {
        "detallesVenta": detalleVenta,
        "usuario": sesion
    };
    fetch('/api/ventas', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(venta)
    })
        .then(response => {
            
            if (response.status == 201) {
                alert("Venta registrada correctamente");
            } else {
                console.error('Error:', response.statusText);
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

// Integración de Stripe

/*document.addEventListener('DOMContentLoaded', function () {
    const stripe = Stripe('pk_test_51PbtkaLx8S7JHdc9C7CRcbe6CXxzxUe20ghUsWNS6jh0pNqGwNApFJZfYeCvw7J51n9dOF6F4uXh1y9QjCoCoRmD00uxsTQV5S'); // Clave pública de Stripe

    document.getElementById("stripe-button").addEventListener("click", function () {
        let productosCart = obtenerAlmacenamientoLocal('productos');
        fetch('http://localhost:3000/create-checkout-session', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                items: productosCart.map(prod => ({
                    id: prod.id,
                    name: prod.nombreProducto,
                    quantity: prod.cantidad,
                    price: prod.precioVenta
                }))
            })
        })
            .then(response => response.json())
            .then(session => {
                return stripe.redirectToCheckout({ sessionId: session.id });
            })
            .then(result => {
                if (result.error) {
                    alert(result.error.message);
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
});*/


