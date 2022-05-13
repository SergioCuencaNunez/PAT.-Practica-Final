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
           document.getElementById("cuenta").innerHTML = "Perfil de " + usuario.nombre;
       }
     }catch (err){
       console.error(err.message);
     }
     return false;
}

/*const setCorreo = (correo) => {
    document.getElementById("correo").innerHTML = correo;
}*/

setNombre();
//setCorreo(getCorreo());
