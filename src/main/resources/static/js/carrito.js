function actualizarIconoCarrito() {
    let productosCart = obtenerAlmacenamientoLocal('productos');
    let totalCantidad = productosCart.reduce((sum, producto) => sum + producto.cantidad, 0);
    document.querySelector("#cart-btn .badge").textContent = totalCantidad;
}

// Llama a esta función dentro de las funciones de cambio de cantidad, eliminación y carga del carrito.

window.onload = function() {
    cargarCarrito();
    actualizarIconoCarrito();
};

function guardarAlmacenamientoLocal(llave, valor_a_guardar) {
    localStorage.setItem(llave, JSON.stringify(valor_a_guardar));
}

function obtenerAlmacenamientoLocal(llave) {
    const datos = JSON.parse(localStorage.getItem(llave));
    return datos;
}

function buscarProducto(idProducto) {
    return listaProductos.filter((p) => p.id == idProducto)[0] || {};
}

function cargarCarrito() {
    const carrito = document.querySelector(".cart-items-container-c");
    let elementos = "";
    let productosCart = obtenerAlmacenamientoLocal('productos');
    let totalTag = document.querySelector(".precio-total");

    productosCart.forEach((producto, index) => {
        elementos += `<div class="cart-item">
            <span class="bi bi-x-lg" onclick="eliminarProducto(${index})"></span>
            <img src="imgProductos/${producto.urlImagen}" alt="" />
            <div class="content">
                <h3>${producto.nombreProducto}</h3>
                <div class="price">S/.${producto.precioVenta}</div>
                <button class="btn btn-primary" data-toggle="modal" data-target="#productoModal${index}">Ver detalles</button>
                <div class="quantity-container">
                    <button onclick="cambiarCantidad(${index}, -1)">-</button>
                    <span class="quantity">${producto.cantidad}</span>
                    <button onclick="cambiarCantidad(${index}, 1)">+</button>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="productoModal${index}" tabindex="-1" role="dialog" aria-labelledby="productoModalLabel${index}" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="productoModalLabel${index}">${producto.nombreProducto}</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Talla: ${producto.talla || 'Única'}</p>
                        <p>Color: ${producto.color || 'No especificado'}</p>
                        <p>Precio: S/.${producto.precioVenta}</p>
                        <p>Descripción: ${producto.descripcion || 'No disponible'}</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>`;
    });

    carrito.innerHTML = elementos;
    totalTag.textContent = obtenerTotal();
    actualizarIconoCarrito();
}

function cambiarCantidad(index, change) {
    let productosCart = obtenerAlmacenamientoLocal('productos');
    if (productosCart[index].cantidad + change > 0) {
        productosCart[index].cantidad += change;
        guardarAlmacenamientoLocal('productos', productosCart);
        cargarCarrito();
    }
}

function eliminarProducto(index) {
    let productosCart = obtenerAlmacenamientoLocal('productos');
    productosCart.splice(index, 1);
    guardarAlmacenamientoLocal('productos', productosCart);
    cargarCarrito();
}

function obtenerTotal() {
    let productosCart = obtenerAlmacenamientoLocal('productos');
    let total = 0;
    for (let i = 0; i < productosCart.length; i++) {
        total += productosCart[i].precioVenta * productosCart[i].cantidad;
    }
    return total;
}

function limpiarCarrito() {
    localStorage.removeItem("productos");
    actualizarIconoCarrito();
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
            "edad": "18",
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
        if (response.status === 201) {
            alert("Guardado en base de datos");
            limpiarCarrito();
        } else {
            console.error('Error:', response.statusText);
        }
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}


