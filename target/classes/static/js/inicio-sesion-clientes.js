const getCorreo = () => {
    return localStorage.getItem("correo");
}

const getReservas = async () => {

   const address = "api/v1/clientes/correo/" + correo;
   fetch(address, {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json'
            },
  })
  .then(response => response.json())

  console.log(response);
}

const setCorreo = (correo) => {
    document.getElementById("correo").innerHTML = correo;
}

setCorreo(getCorreo());
