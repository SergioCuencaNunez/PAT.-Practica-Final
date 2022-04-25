async function contactar(){
    try {
        var correo = await document.getElementById("correo").value;
        var nombre = await document.getElementById("nombre").value;
        var mensaje = await document.getElementById("mensaje").value;

        var data = {"numero": null,
                    "correo": correo,
                    "nombre":nombre,
                    "mensaje": mensaje,
                    };
        const address = "api/v1/contactos/insert-mensaje";
        fetch(address, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body:JSON.stringify(data)
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if(data.result == "OK") {
                    alert("Mensaje enviado correctamente. Le enviaremos, con la mayor brevedad posible, la respuesta a " + correo);
                }else{
                    alert(data.result);
                }
            });

    } catch (err) {
        console.error(err.message);
    }
    return false;
}

async function boletinSuscripcion(){
    try {
        var correo = await document.getElementById("correoSuscripcion").value;

        var data = {"numero": null,
                    "correo": correo,
                    "nombre": "",
                    "mensaje": "Suscripción al boletín de ofertas y descuentos",
                    };
        const address = "api/v1/contactos/insert-suscripcion";
        fetch(address, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body:JSON.stringify(data)
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if(data.result == "OK") {
                    alert("Gracias por suscribirte al boletín de noticias de Meliá Hotels International. Pronto recibirás nuevas ofertas y descuentos.");
                }else{
                    alert(data.result);
                }
            });

    } catch (err) {
        console.error(err.message);
    }
    return false;
}