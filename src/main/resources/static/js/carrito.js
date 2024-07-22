window.onload = function() {
    cargarCarrito();
  };
function guardarAlmacenamientoLocal(llave, valor_a_guardar) {
    localStorage.setItem(llave, JSON.stringify(valor_a_guardar))
}
function obtenerAlmacenamientoLocal(llave) {
    const datos = JSON.parse(localStorage.getItem(llave))
    return datos
}

let productos = obtenerAlmacenamientoLocal('productos') || [];
let mensaje = document.getElementById('mensaje')

//Añadir un producto
const añadirProducto = document.getElementById('productoAñadir')
const añadirValor = document.getElementById('valorAñadir')
const añadirExistencia = document.getElementById('existenciaAñadir')
const añadirImagen = document.getElementById('ImagenAñadir')

document.querySelector(".box-container").addEventListener("click", e => {
    if (e.target.classList.contains('btn-agregar-carrito')) {
        let idProducto = e.target.parentElement.parentElement.querySelector(".content > input").value
        
        productos.push(buscarProducto(idProducto))
        guardarAlmacenamientoLocal('productos', productos)
        cargarCarrito()
        

        /*let van = true

        if (productoAñadir == '' || valorAñadir == '' || existenciaAñadir == '' || imagenAñadir == '') {
            mensaje.classList.add('llenarCampos')
            setTimeout(() => { mensaje.classList.remove('llenarCampos') }, 2500)
            van = false
        }
        else {
            for (let i = 0; i < productos.length; i++) {
                if (productos[i].nombre == productoAñadir) {
                    mensaje.classList.add('repetidoError')
                    setTimeout(() => { mensaje.classList.remove('repetidoError') }, 2500)
                    van = false
                }
            }
        }

        if (van == true) {
            productos.push({
                nombre: productoAñadir,
                valor: valorAñadir,
                existencia: existenciaAñadir,
                urlImagen: imagenAñadir
            })
            mensaje.classList.add('realizado')
            setTimeout(() => {
                mensaje.classList.remove('repetidoError')
                window.location.reload()
            }, 1500)
        }
        guardarAlmacenamientoLocal('productos', productos);*/
    }

})

// Editar
const productoEd = document.getElementById('productoEditar')
const atributoEd = document.getElementById('atributoEditar')
const nuevoAtributoEd = document.getElementById('nuevoAtributo')

document.getElementById("botonEditar").addEventListener("click", function (event) {
    event.preventDefault()
    let productoEditar = productoEd.value
    let atributoEditar = atributoEd.value
    let nuevoAtributo = nuevoAtributoEd.value
    let van = false
    if (productoEditar == '' || atributoEditar == '' || nuevoAtributo == '') {
        mensaje.classList.add('llenarCampos')
        setTimeout(() => { mensaje.classList.remove('llenarCampos') }, 2500)
    }
    else {
        for (let i = 0; i < productos.length; i++) {
            if (productos[i].nombre == productoEditar) {
                productos[i][atributoEditar] = nuevoAtributo
                van = true
            }
        }
        if (van == true) {
            mensaje.classList.add('realizado')
            setTimeout(() => {
                mensaje.classList.remove('realizado')
                window.location.reload()
            }, 1500);
        }
        else {
            mensaje.classList.add('noExisteError')
            setTimeout(() => { mensaje.classList.remove('noExisteError') }, 2500);
        }
        guardarAlmacenamientoLocal('productos', productos);
    }
})

// Eliminar
const productoE = document.getElementById('productoEliminar')

document.getElementById("botonEliminar").addEventListener("click", function (event) {
    event.preventDefault()
    let productoEliminar = productoE.value
    let van = false

    for (let i = 0; i < productos.length; i++) {
        if (productos[i].nombre == productoEliminar) {
            productos.splice(i, 1)
            van = true
        }
    }

    if (van == false) {
        mensaje.classList.add('noExsiteError')
        setTimeout(() => { mensaje.classList.remove('noExisteError') }, 2500);
    }
    else {
        mensaje.classList.add('realizado')
        setTimeout(() => {
            mensaje.classList.remove('realizado')
            window.location.reload()
        }, 1500);
    }
    guardarAlmacenamientoLocal('productos', productos);
})

// mostrar productos
window.addEventListener("load", () => {
    const productoEd = document.getElementById('productoEditar')
    const productoEl = document.getElementById('productoEliminar')
    for (let i = 0; i < productos.length; i++) {
        productoEd.innerHTML += `<option>${productos[i].nombre}</option>`
        productoEl.innerHTML += `<option>${productos[i].nombre}</option>`
    }
    Object.keys(productos[0]).forEach(element => {
        atributoEd.innerHTML += `<option>${element}</option>`
    });

    let mostraProductos = document.getElementById('mostrarProductos')
    mostraProductos.innerHTML = ''
    for (let i = 0; i < productos.length; i++) {
        mostraProductos.innerHTML += `<div class="contenedorProductos"><img src="${productos[i].urlImagen}"><div class="informacion"><p>${productos[i].nombre}</p><p class="precio"><span>Precio: ${productos[i].valor}$</span></p> Existencia: ${productos[i].existencia}<p></p></div></div>`
    }
})

function buscarProducto(idProducto){
    return listaProductos.filter((p) => p.id == idProducto)[0] || {}
}

function cargarCarrito(){
    const carrito = document.querySelector(".cart-items-container-c")
    let elementos = "";
    let productosCart = obtenerAlmacenamientoLocal('productos')
    let totalTag = document.querySelector(".precio-total");
    for(let i = 0; i < productosCart.length; i++){
        elementos += '<div class="cart-item">'+
            '<span class="bi bi-x-lg"></span>'+
            '<img src="imgProductos/'+productosCart[i].urlImagen+'" alt="" />'+
            '<div class="content">'+
                '<h3>'+productosCart[i].nombreProducto+'</h3>'+
                '<div class="price">S/.'+productosCart[i].precioVenta+'</div>'+
            '</div>'+
        '</div>'
    }
    carrito.innerHTML = elementos;
    totalTag.textContent=obtenerTotal()

}
function obtenerTotal(){
    let productosCart = obtenerAlmacenamientoLocal('productos')
    let total = 0
    for(let i = 0; i < productosCart.length; i++){
        total += productosCart[0].precioVenta
    }
    return total
}

function limpiarCarrito(){
    localStorage.removeItem("productos")
}

function registrarVenta() {

    let detalleVenta = [];
    let productosCart = obtenerAlmacenamientoLocal('productos')
    for(let i = 0; i < productosCart.length; i++){
        let item = {
            "producto":productosCart[i],
            "cantidad":1,
            "precioUnitario":productosCart[i].precioVenta
        }
        detalleVenta.push(item)
    }
    venta = {
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
    }
    fetch('/api/ventas', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(venta)
    })
    .then(response => {
        if (response.status == 201) {
            alert("guardado en base de datos")
        } else {
            console.error('Error:', response.statusText);
        }
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}
