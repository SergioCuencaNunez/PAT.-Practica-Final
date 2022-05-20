const getCorreo = () => {
    return localStorage.getItem("correo");
}

async function setNombre(){
     try {
       const address = "api/v1/usuarios/correo/" + getCorreo();
       let request = await fetch(address, {
           method: 'GET'
       });
       if(request.ok){
           var usuario = await request.json();
           document.getElementById("cuenta").innerHTML = "Reservas de " + usuario.nombre;
       }
     }catch (err){
       console.error(err.message);
     }
     return false;
}

setNombre();