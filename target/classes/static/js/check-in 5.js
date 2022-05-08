async function checkIn(){
    try {
        var nif = await document.getElementById("nif").value;
        var id = await document.getElementById("id").value;
        var destino = await document.getElementById("destino").value;
        var hotel = await document.getElementById("hotel").value;
        var checkIn = await document.getElementById("date-in").value;
        var checkOut = await document.getElementById("date-out").value;
        var checkInDia = checkIn.substr(0,2);
        var checkOutDia = checkOut.substr(0,2);
        var checkInMes = checkIn.substr(3,(checkIn.indexOf(",")) - 3);
        var checkOutMes = checkOut.substr(3,(checkOut.indexOf(",")) - 3);
        var checkInAno = checkIn.substr((checkIn.indexOf(",") + 2));
        var checkOutAno = checkOut.substr((checkOut.indexOf(",") + 2));
        var checkInNumeroMes = ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"].indexOf(checkInMes.toLowerCase()) + 1;
        var checkOutNumeroMes = ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"].indexOf(checkOutMes.toLowerCase()) + 1;

        if (checkInNumeroMes < 10 || checkOutNumeroMes < 10) {
          checkInNumeroMes = "0" + checkInNumeroMes;
          checkOutNumeroMes = "0" + checkOutNumeroMes;
        }
        var checkInDef = checkInAno + "-" + checkInNumeroMes + "-" + checkInDia;
        var checkOutDef = checkOutAno + "-" + checkOutNumeroMes + "-" + checkOutDia;

        const address = "api/v1/reservas/id/" + id;
        request = await fetch(address, {
            method: 'GET'
        });
        if(request.ok){
            var reserva = await request.json();
            var checkInDefi = new Date(checkInDef);
            var checkOutDefi = new Date(checkOutDef);
            const data1 = {"id": id, "nif":nif, "hotel": hotel, "destino":destino, "fechaEntrada": checkInDefi, "fechaSalida": checkOutDefi};
                    const address1 = "api/v1/reservas/check-in";
                    fetch(address1, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(data1)
                    })
                    .then(response => response.json())
                    .then(data1 => {
                    if(data1.result == "OK") {
                        if(confirm("Reserva localizada.\n\u2022 Identificador de la reserva: " + id + "\n\u2022 NIF empleado para la reserva: " + nif + "\n\u2022 Destino elegido: " + destino + "\n\u2022 Hotel de estancia: " + hotel + "\n\u2022 Tipo de habitación reservada: " + reserva.tipo + "\n\u2022 Número huéspedes: " + reserva.huespedes + "\n\u2022 Número de habitaciones: " + reserva.habitaciones + "\n\u2022 Fecha de entrada: " + checkInDef + "\n\u2022 Fecha de salida: " + checkOutDef + "\n¿Desea realizar el Check-in online?")){
                            const data2 = {"numero": null,
                                           "correo": nif,
                                           "nombre": id,
                                           "mensaje": "Check-in online de la reserva con identificador " + id + " y NIF " + nif,
                            };
                            const address2 = "api/v1/contactos/insert-checkIn";
                                    fetch(address2, {
                                        method: 'POST',
                                        headers: {
                                            'Content-Type': 'application/json'
                                        },
                                        body:JSON.stringify(data2)
                                        })
                                        .then(response => response.json())
                                        .then(data2 => {
                                            if(data2.result == "OK") {
                                                alert("Check-in online realizado correctamente. Ya puede disfrutar y aprovechar de todas nuestras ventajas exclusivas.");
                                            }else{
                                                alert(data2.result);
                                            }
                                        });
                        }else{
                            alert("Check-in online no realizado.");
                        }
                    }else{
                        alert(data1.result);
                    }
                    });
        }else{
            alert("Reserva no localizada. Por favor, revise el identificador introducido");
       }
    } catch (err) {
        console.error(err.message);
    }
    return false;
}

async function booking(){
    try {
      var destino = await document.getElementById("destino").value;
      var habitaciones = await document.getElementById("room").value;
      var huespedes = await document.getElementById("guest").value;
      var checkIn = await document.getElementById("date-in").value;
      var checkOut = await document.getElementById("date-out").value;
      var checkInDia = checkIn.substr(0,2);
      var checkOutDia = checkOut.substr(0,2);
      var checkInMes = checkIn.substr(3,(checkIn.indexOf(",")) - 3);
      var checkOutMes = checkOut.substr(3,(checkOut.indexOf(",")) - 3);
      var checkInAno = checkIn.substr((checkIn.indexOf(",") + 2));
      var checkOutAno = checkOut.substr((checkOut.indexOf(",") + 2));
      var checkInNumeroMes = ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"].indexOf(checkInMes.toLowerCase()) + 1;
      var checkOutNumeroMes = ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"].indexOf(checkOutMes.toLowerCase()) + 1;

      if (checkInNumeroMes < 10 || checkOutNumeroMes < 10) {
          checkInNumeroMes = "0" + checkInNumeroMes;
          checkOutNumeroMes = "0" + checkOutNumeroMes;
      }
      var checkInDef = checkInAno + "-" + checkInNumeroMes + "-" + checkInDia;
      var checkOutDef = checkOutAno + "-" + checkOutNumeroMes + "-" + checkOutDia;

      const data0 = {nif: "", checkIn: checkInDef, checkOut: checkOutDef, huespedes: huespedes, habitaciones: habitaciones};
                    const address0 = "api/v1/reservas/booking";
                    fetch(address0, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(data0)
                    })
                    .then(response => response.json())
                    .then(data0 => {
                    if(data0.result == "OK") {


                    }else{
                        alert("No se ha podido comprobar la disponibilidad. Por favor, revise que todos los campos introducidos son correctos.\nDebe introducir un destino, fechas de entrada y de salida coherentes, número de huéspedes y número de habitaciones.");
                    }
                    });
    }catch (err){
        console.error(err.message);
    }
    return false;
}

$('#checkInForm').submit(function (e) {
    e.preventDefault();
});

$('#bookingForm').submit(function (e) {
    e.preventDefault();
});