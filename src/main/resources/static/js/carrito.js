window.onload = function() {
    cargarCarrito();
};

function guardarAlmacenamientoLocal(llave, valor_a_guardar) {
    localStorage.setItem(llave, JSON.stringify(valor_a_guardar));
}

function obtenerAlmacenamientoLocal(llave) {
    const datos = JSON.parse(localStorage.getItem(llave));
    return datos;
}

let productos = obtenerAlmacenamientoLocal('productos') || [];
let mensaje = document.getElementById('mensaje');

// Función para actualizar el icono del carrito
function actualizarIconoCarrito() {
    const iconoCarrito = document.querySelector(".bi-cart3");
    const totalProductos = productos.reduce((sum, prod) => sum + prod.cantidad, 0);
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
    let totalTag = document.querySelector(".precio-total");
    
    for (let i = 0; i < productosCart.length; i++) {
        elementos += `
            <div class="cart-item">
                <span class="bi bi-x-lg" onclick="eliminarProducto(${productosCart[i].id})"></span>
                <img src="imgProductos/${productosCart[i].urlImagen}" alt="" />
                <div class="content">
                    <h3>${productosCart[i].nombreProducto}</h3>
                    <div class="price">S/.${productosCart[i].precioVenta}</div>
                    <div class="quantity">
                        <button class="btn btn-sm btn-secondary" onclick="cambiarCantidad(${productosCart[i].id}, -1)">-</button>
                        <span>${productosCart[i].cantidad}</span>
                        <button class="btn btn-sm btn-secondary" onclick="cambiarCantidad(${productosCart[i].id}, 1)">+</button>
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
    let total = productosCart.reduce((sum, prod) => sum + prod.precioVenta * prod.cantidad, 0);
    return total;
}

function eliminarProducto(idProducto) {
    productos = productos.filter(prod => prod.id !== idProducto);
    guardarAlmacenamientoLocal('productos', productos);
    cargarCarrito();
}

function cambiarCantidad(idProducto, cambio) {
    let producto = productos.find(prod => prod.id === idProducto);
    if (producto) {
        producto.cantidad += cambio;
        if (producto.cantidad <= 0) {
            eliminarProducto(idProducto);
        } else {
            guardarAlmacenamientoLocal('productos', productos);
            cargarCarrito();
        }
    }
}

function limpiarCarrito() {
    localStorage.removeItem("productos");
    cargarCarrito();
}

function registrarVenta() {
    let detalleVenta = [];
    let productosCart = obtenerAlmacenamientoLocal('productos');
    for (let i = 0; i < productosCart.length; i++) {
        let item = {
            "producto": productosCart[i],
            "cantidad": productosCart[i].cantidad,
            "precioUnitario": productosCart[i].precioVenta
        };
        detalleVenta.push(item);
    }
    let venta = {
        "detallesVenta": detalleVenta,
        "usuario": {
            "id": 1,
            "nombres": "Cesar Luis",
            "apellidos": "Vera Bendezú",
            "direccion": "Los viñedos H-13",
            "dni": "73242253",
            "email": "zicmayki@gmail.com",
            "edad": "18"
        }
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
            alert("guardado en base de datos");
        } else {
            console.error('Error:', response.statusText);
        }
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

// Integración de Stripe

document.getElementById("stripe-button").addEventListener("click", function () {
    let productosCart = obtenerAlmacenamientoLocal('productos');
    fetch('http://localhost:4242/create-checkout-session', {
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


