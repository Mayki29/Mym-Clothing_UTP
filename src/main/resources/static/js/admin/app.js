const form = document.getElementById("myForm");
const imgInput = document.querySelector(".img");
const fileInput = document.getElementById("imgInput");
const nameInput = document.getElementById("name");
const categoryInput = document.getElementById("category");
const sizeInput = document.getElementById("size");
const colorInput = document.getElementById("color");
const priceInput = document.getElementById("price");
const stockInput = document.getElementById("stock");
const brandInput = document.getElementById("brand");
const dateInput = document.getElementById("date");
const descriptionInput = document.getElementById("description");
const submitBtn = document.querySelector(".submit");
const userInfo = document.getElementById("data");
const modal = document.getElementById("userForm");
const modalTitle = document.querySelector("#userForm .modal-title");
const newUserBtn = document.querySelector(".newUser");
const idInput = document.getElementById("id");

let getData = localStorage.getItem("userProfile")
  ? JSON.parse(localStorage.getItem("userProfile"))
  : [];

let isEdit = false;
let editId;

function showInfo() {
  document.querySelectorAll(".productDetails").forEach((info) => info.remove());

  getData.forEach((element, index) => {
    const row = document.createElement("tr");
    row.classList.add("productDetails");

    row.innerHTML = `
          <td>${element.id}</td>
          <td><img src="${element.image}" alt="" width="50" height="50"></td>
          <td>${element.name}</td>
          <td>${element.category}</td>
          <td>${element.size}</td>
          <td>${element.color}</td>
          <td>${element.price}</td>
          <td>${element.stock}</td>
          <td>${element.brand}</td>
          <td>${element.date}</td>
          <td>${element.description}</td>
          <td>
              <button class="btn btn-success" onclick="readInfo('${element.id}', '${element.image}', '${element.name}', '${element.category}', '${element.size}', '${element.color}', '${element.price}', '${element.stock}', '${element.brand}', '${element.date}', '${element.description}')" data-bs-toggle="modal" data-bs-target="#readData"><i class="bi bi-eye"></i></button>
              <button class="btn btn-primary" onclick="editInfo('${element.id}', '${element.image}', '${element.name}', '${element.category}', '${element.size}', '${element.color}', '${element.price}', '${element.stock}', '${element.brand}', '${element.date}', '${element.description}')" data-bs-toggle="modal" data-bs-target="#userForm"><i class="bi bi-pencil-square"></i></button>
              <button class="btn btn-danger" onclick="deleteInfo('${element.id}')"><i class="bi bi-trash"></i></button>
          </td>
      `;

    userInfo.appendChild(row);
  });
}

function getCurrentDate() {
  const today = new Date();
  const year = today.getFullYear();
  const month = String(today.getMonth() + 1).padStart(2, "0"); // Los meses se indexan desde 0
  const day = String(today.getDate()).padStart(2, "0");
  return `${year}-${month}-${day}`;
}

showInfo();

newUserBtn.addEventListener("click", () => {
  submitBtn.innerText = "Submit";
  modalTitle.innerText = "Fill the Form";
  isEdit = false;
  imgInput.src = "image/icon-default.png";
  form.reset();
});

fileInput.addEventListener("change", function () {
  if (fileInput.files[0].size < 1000000) {
    // 1MB = 1000000
    const fileReader = new FileReader();

    fileReader.onload = function (e) {
      imgInput.src = e.target.result;
    };

    fileReader.readAsDataURL(fileInput.files[0]);
  } else {
    alert("This file is too large!");
  }
});

function readInfo(id) {
  const prod = buscarProducto(id);
  console.log(prod)

  document.querySelector(".showImg").src = '/imgProductos/'+prod.urlImagen;
  document.getElementById("showName").value = prod.nombreProducto;
  document.getElementById("showCategory").value = prod.categoria.id;
  document.getElementById("showSize").value=prod.talla;
  document.getElementById("showPrice").value = prod.precioVenta;
  document.getElementById("showStock").value = prod.stock;
  document.getElementById("showDescription").value = prod.descripcion;
}

function editInfo(
  id,
  image,
  name,
  category,
  sizes,
  color,
  price,
  stock,
  brand,
  date,
  description
) {
  isEdit = true;
  editId = id;
  imgInput.src = image;
  nameInput.value = name;
  categoryInput.value = category;
  Array.from(sizeInput.options).forEach((option) => {
    option.selected = sizes.includes(option.value);
  });
  colorInput.value = color;
  priceInput.value = price;
  stockInput.value = stock;
  brandInput.value = brand;
  dateInput.value = date;
  descriptionInput.value = description;

  submitBtn.innerText = "Update";
  modalTitle.innerText = "Edit Item";
}

function deleteInfo(id) {
  if (confirm("¿Estás seguro de eliminar este producto?")) {
    getData = getData.filter((item) => item.id !== id);
    localStorage.setItem("userProfile", JSON.stringify(getData));
    showInfo();
  }
}
function generateUniqueId(length = 5) {
  const characters =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  let result = "ID-";
  const charactersLength = characters.length;
  for (let i = 0; i < length; i++) {
    result += characters.charAt(Math.floor(Math.random() * charactersLength));
  }
  return result;
}

newUserBtn.addEventListener("click", () => {
  submitBtn.innerText = "Submit";
  modalTitle.innerText = "Nueva Prenda";
  isEdit = false;
  imgInput.src = "image/icon-default.png";
  form.reset();

  // Establecer la fecha actual en el campo de fecha
  dateInput.value = getCurrentDate();
});

// form.addEventListener("submit", (e) => {
//   e.preventDefault();

//   const sizeOptions = Array.from(sizeInput.selectedOptions).map(
//     (option) => option.value
//   );

//   const information = {
//     id: isEdit ? editId : generateUniqueId(), // Genera un nuevo ID si no está en modo edición
//     image: imgInput.src || "image/icon-default.png",
//     name: nameInput.value,
//     category: categoryInput.value,
//     size: sizeOptions,
//     color: colorInput.value,
//     price: priceInput.value,
//     stock: stockInput.value,
//     brand: brandInput.value,
//     date: dateInput.value || getCurrentDate(),
//     description: descriptionInput.value,
//   };

//   if (!isEdit) {
//     getData.push(information);
//   } else {
//     isEdit = false;
//     getData = getData.map((item) => (item.id === editId ? information : item));
//   }

//   localStorage.setItem("userProfile", JSON.stringify(getData));

//   submitBtn.innerText = "Submit";
//   modalTitle.innerHTML = "New Item";

//   showInfo();

//   form.reset();

//   imgInput.src = "image/icon-default.png";
// });

function buscarProducto(idProducto) {
  return listaProductos.filter((p) => p.id == idProducto)[0] || {};
}

// document.getElementById('btnSave').addEventListener('click',function(){
//   document.getElementById('myForm').submit();
// })
