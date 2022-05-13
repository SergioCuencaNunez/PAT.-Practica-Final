const getCorreo = () => {
    return localStorage.getItem("correo");
}

const getCorreoPerfil = () => {
    return localStorage.getItem("correoPerfil");
}

async function getInformacionPerfil(){
     try {
       var nombre = document.getElementById("nombre");
       var apellido1 = document.getElementById("apellido1");
       var apellido2 = document.getElementById("apellido2");
       var nif = document.getElementById("nif");
       var cumpleanos = document.getElementById("cumpleanos");
       var correo = document.getElementById("correo");

       const address = "api/v1/usuarios/correo/" + getCorreo();
       let request = await fetch(address, {
           method: 'GET'
       });
       if(request.ok){
           var usuario = await request.json();
           nombre.value = usuario.nombre;
           apellido1.value = usuario.apellido1;
           apellido2.value = usuario.apellido2;
           nif.value = usuario.nif;
           var cumpleanosDia = usuario.cumpleanos.substr(8,9);
           var cumpleanosMesAnt = usuario.cumpleanos.substr(5,2);
           var cumpleanosAno = usuario.cumpleanos.substr(0,4);
           var meses = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
           var cumpleanosMes = meses[parseInt(cumpleanosMesAnt) - 1]
           cumpleanos.value = cumpleanosDia + " " + cumpleanosMes + ", " + cumpleanosAno;
           correo.value = usuario.correo;
           localStorage.setItem("correoPerfil", usuario.correo);
       }
     }catch (err){
       console.error(err.message);
     }
     return false;
}

async function cambiarInformacionPerfil() {
    try {
        var nombre = document.getElementById("nombre").value;
        var apellido1 = document.getElementById("apellido1").value;
        var apellido2 = document.getElementById("apellido2").value;
        var nif = document.getElementById("nif").value;
        var cumpleanos = document.getElementById("cumpleanos").value;
        var correo = document.getElementById("correo").value;
        var contrasena = document.getElementById("contrasena").value;
        var contrasena2 = document.getElementById("contrasena2").value;
        var cumpleanosDia = cumpleanos.substr(0,2);
        var cumpleanosMes = cumpleanos.substr(3,(cumpleanos.indexOf(",")) - 3);
        var cumpleanosAno = cumpleanos.substr((cumpleanos.indexOf(",") + 2));
        var numeroMes = ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"].indexOf(cumpleanosMes.toLowerCase()) + 1;
        if (numeroMes < 10) {
          numeroMes = "0" + numeroMes;
        }
        var cumpleanosDef = cumpleanosAno + "-" + numeroMes + "-" + cumpleanosDia;

        if(contrasena == ""){
            const address0 = "api/v1/usuarios/correo/" + getCorreoPerfil();
            let request = await fetch(address0, {
               method: 'GET'
            });
            if(request.ok){
               var usuario = await request.json();
               contrasena = usuario.contrasena;
            }
        }
        const data1 = {nombre: nombre, apellido1: apellido1, apellido2: apellido2, nif: nif, cumpleanos: cumpleanosDef, correo: correo, contrasena: contrasena, contrasena2: contrasena};
        const address1 = "api/v1/usuarios/registro";
        fetch(address1, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data1)
        })
        .then(response => response.json())
        .then(data1 => {
            console.log(data1);
            if(data1.result == "OK") {
                console.log("Authenticated");
                var cumpleanosDefi = new Date(cumpleanosDef);
                const data2 = {"nif": nif, "nombre": nombre, "apellido1": apellido1, "apellido2": apellido2, "cumpleanos": cumpleanosDefi, "correo": correo, "contrasena": contrasena, "rol": getCorreoPerfil()};
                const address2 = "api/v1/usuarios/update";
                fetch(address2, {
                    method: 'PUT',
                    headers: {
                         'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data2)
                })
                .then(response => response.json())
                .then(data2 => {
                    if(data2.result == "OK"){
                        localStorage.setItem("correoPerfil", correo);
                        alert("Credenciales modificados correctamente.");
                    }else{
                        alert(data2.result);
                    }
                });
            }else{
                alert("Credenciales erróneos o no reconocidos. Por favor, revise sus credenciales para poder modificarlos.\nDebe introducir todos los datos marcados, el NIF adjunto en el momento del registro, un email válido y una contraseña alfanúmerica.\nRecuerde que la contraseña debe ser igual en ambos campos.");
            }
       });
    } catch (err) {
        console.error(err.message);
    }
    return false;
}

/*const setCorreo = (correo) => {
    document.getElementById("correo").innerHTML = correo;
}*/

async function cerrarSesion(){
    localStorage.removeItem("access_token");
    console.log(localStorage.getItem("access_token"));
    if(confirm("¿Realmente desea cerrar su sesión?")){
        document.location.href="/inicio-sesion.html";
    }
}

getInformacionPerfil();

$('#perfilForm').submit(function (e) {
    e.preventDefault();
});

//setCorreo(getCorreo());
