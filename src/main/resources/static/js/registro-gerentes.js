async function registroGerentes() {
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
        const data1 = {nombre: nombre, apellido1: apellido1, apellido2: apellido2, nif: nif, cumpleanos: cumpleanosDef, correo: correo, contrasena: contrasena, contrasena2: contrasena2};
        const address1 = "api/v1/usuarios/registro-gerente";
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
                const data2 = {"nif": nif, "nombre": nombre, "apellido1": apellido1, "apellido2": apellido2, "cumpleanos": cumpleanosDefi, "correo": correo, "contrasena": contrasena, "rol": "admin"};
                const address2 = "api/v1/usuarios/insert-gerente";
                fetch(address2, {
                    method: 'POST',
                    headers: {
                         'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data2)
                })
                .then(response => response.json())
                .then(data2 => {
                    if(data2.result == "OK") {
                        alert("Credenciales correctos. Se ha registrado con éxito un nuevo administrador en el programa MyMeliáBenefits.");
                    }else{
                        alert(data2.result);
                    }
                });
            }else{
                alert("Credenciales erróneos o no reconocidos. Por favor, revise sus credenciales.\nDebe introducir todos los datos marcados, un NIF vigente, un email válido (@melia.com) y una contraseña alfanúmerica.\nRecuerde que la contraseña debe ser igual en ambos campos.");
            }
       });
    } catch (err) {
        console.error(err.message);
    }
    return false;
}

$('#registroForm').submit(function (e) {
    e.preventDefault();
});