const getCorreo = () => {
    return localStorage.getItem("correo");
}

async function setNombre(){
     try {
       const address = "api/v1/usuarios/nombre/" + getCorreo();
       let request = await fetch(address, {
           method: 'GET'
       });
       if(request.ok){
           var usuario = await request.json();
           console.log(usuario.nombre);
           document.getElementById("cuenta").innerHTML = "Reservas de " + usuario.nombre;
       }
     }catch (err){
       console.error(err.message);
     }
     return false;
}

async function cerrarSesion(){
    localStorage.removeItem("access_token");
    console.log(localStorage.getItem("access_token"));
    document.location.href="/inicio-sesion.html";
}

/*const setCorreo = (correo) => {
    document.getElementById("correo").innerHTML = correo;
}*/

setNombre();
//setCorreo(getCorreo());
