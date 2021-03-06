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
                var destinos = {
                     'Madrid': "Madrid",
                     'Londres': "Londres",
                     'Paris': "Par??s",
                     'Nueva-York': "Nueva York"
                 };
                var hoteles = {
                     'Melia-Madrid-Princesa': "Meli?? Madrid Princesa",
                     'Gran-Melia-Palacio-de-los-Duques': "Gran Meli?? Palacio de los Duques",
                     'Melia-White-House': "Meli?? White House",
                     'ME-London': "ME London",
                     'Innside-Paris-Charles-de-Gaulle': "Innside Paris Charles de Gaulle",
                     'Melia-Paris-La-Defense': "Meli?? Paris La D??fense",
                     'TRYP-New-York-Times-Square': "TRYP New York Times Square",
                     'Innside-New-York-Nomad': "Innside New York Nomad"
                 };
                var habitaciones = {
                      'Premium': "Habitaci??n Premium",
                      'Suite': "Suite The Level",
                      'Grand-Suite-Presidencial': "Grand Suite Presidencial The Level",
                      'Supremme': " Habitaci??n Supremme",
                      'Suite-Deluxe': "Suite Deluxe RedLevel",
                      'Royal-Suite': "Royal Suite RedLevel",
                      'Premium-King': "Habitaci??n Premium King",
                      'Junior-Suite': "Junior Suite The Level",
                      'Marylebone-Suite': "The Marylebone Suite",
                      'Mode': "Habitaci??n Mode",
                      'Suite-Personality': "Suite Personality",
                      'Chic-Penthouse-Suite': "Chic Penthouse Suite",
                      'Premium-Extra': "Habitaci??n Premium Extra",
                      'Loft': "The Loft",
                      'Townhouse-Suite': "The Townhouse Suite",
                      'Premium-Twin': "Habitaci??n Premium Twin",
                      'Suite-Premium': "Suite Premium The Level",
                      'Grand-Suite-Eiffel': "Grand Suite Torre Eiffel The Level",
                      'Queen': "Habitaci??n Queen",
                      'King': "Habitaci??n King",
                      'Junior-Suite-Metro': "Junior Metro Suite",
                      'King-City': "Habitaci??n King City",
                      'Studio': "The Studio with Terrace",
                      'Townhouse-Junior-Suite': "The Townhouse Junior Suite"
                 };
                if(confirm("Reserva localizada.\n\u2022 Identificador de la reserva: " + id + "\n\u2022 NIF empleado para la reserva: " + nif + "\n\u2022 Destino elegido: " + destinos[destino] + "\n\u2022 Hotel de estancia: " + hoteles[hotel] + "\n\u2022 Tipo de habitaci??n reservada: " + habitaciones[reserva.tipo] + "\n\u2022 N??mero hu??spedes: " + reserva.huespedes + "\n\u2022 N??mero de habitaciones: " + reserva.habitaciones + "\n\u2022 Fecha de entrada: " + checkInDef + "\n\u2022 Fecha de salida: " + checkOutDef + "\n??Desea realizar el Check-in online?")){
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

      var checkInDefi = new Date(checkInDef);
      var checkOutDefi = new Date(checkOutDef);

      if(checkIn && checkOut && (checkOutDefi > checkInDefi)){
         if(destino == "Madrid"){
                var madrid1, madrid2;
                
                const address1 = "api/v1/reservas/booking/Melia-Madrid-Princesa/" + habitaciones;
                let request1 = await fetch(address1, {
                    method: 'GET'
                });
                if(request1.ok) {
                   madrid1 = true;
                }else {
                   madrid1 = false;
                }

                const address2 = "api/v1/reservas/booking/Gran-Melia-Palacio-de-los-Duques/" + habitaciones;
                let request2 = await fetch(address2, {
                    method: 'GET'
                });
                if(request2.ok) {
                   madrid2 = true;
                }else {
                   madrid2 = false;
                }

                if(madrid1 == false && madrid2 == false){
                    alert("No hay m??s habitaciones disponibles en ninguno de los hoteles de Madrid. Disculpe las molestias.");
                }else if(madrid1 == true && madrid2 == false){
                    alert("Solo quedan habitaciones disponibles en el hotel Meli?? Madrid Princesa.");
                    document.location.href="habitaciones-madrid-princesa.html";
                }else if(madrid1 == false && madrid2 == true){
                    alert("Solo quedan habitaciones disponibles en el hotel Gran Meli?? Palacio de los Duques.");
                    document.location.href="habitaciones-madrid-duques.html";
                }else{
                    document.location.href="hoteles1.html#madrid-hoteles";
                }

          }else if(destino == "Londres"){
                var londres1, londres2;
                
                const address1 = "api/v1/reservas/booking/Melia-Madrid-Princesa/" + habitaciones;
                let request1 = await fetch(address1, {
                    method: 'GET'
                });
                if(request1.ok) {
                   londres1 = true;
                }else {
                   londres1 = false;
                }

                const address2 = "api/v1/reservas/booking/Gran-Melia-Palacio-de-los-Duques/" + habitaciones;
                let request2 = await fetch(address2, {
                    method: 'GET'
                });
                if(request2.ok) {
                   londres2 = true;
                }else {
                   londres2 = false;
                }

                if(londres1 == false && londres2 == false){
                    alert("No hay m??s habitaciones disponibles en ninguno de los hoteles de Londres. Disculpe las molestias");
                }else if(londres1 == true && londres2 == false){
                    alert("Solo quedan habitaciones disponibles en el hotel Meli?? White House.");
                    document.location.href="habitaciones-londres-wh.html";
                }else if(londres1 == false && londres2 == true){
                    alert("Solo quedan habitaciones disponibles en el hotel ME London.");
                    document.location.href="habitaciones-londres-me.html";
                }else{
                    document.location.href="./hoteles1.html#londres-hoteles";
                }

          }else if(destino == "Paris"){
                var paris1, paris2;
                
                const address1 = "api/v1/reservas/booking/Melia-Madrid-Princesa/" + habitaciones;
                let request1 = await fetch(address1, {
                    method: 'GET'
                });
                if(request1.ok) {
                   paris1 = true;
                }else {
                   paris1 = false;
                }

                const address2 = "api/v1/reservas/booking/Gran-Melia-Palacio-de-los-Duques/" + habitaciones;
                let request2 = await fetch(address2, {
                    method: 'GET'
                });
                if(request2.ok) {
                   paris2 = true;
                }else {
                   paris2 = false;
                }

                if(paris1 == false && paris2 == false){
                    alert("No hay m??s habitaciones disponibles en ninguno de los hoteles de Londres. Disculpe las molestias");
                }else if(paris1 == true && paris2 == false){
                    alert("Solo quedan habitaciones disponibles en el hotel Innside Paris Charles de Gaulle.");
                    document.location.href="habitaciones-paris-cdg.html";
                }else if(paris1 == false && paris2 == true){
                    alert("Solo quedan habitaciones disponibles en el hotel Meli?? Paris La D??fense.");
                    document.location.href="habitaciones-paris-defense.html";
                }else{
                    document.location.href="./hoteles2.html#paris-hoteles";
                }

          }else{
                var nyc1, nyc2;
                
                const address1 = "api/v1/reservas/booking/Melia-Madrid-Princesa/" + habitaciones;
                let request1 = await fetch(address1, {
                    method: 'GET'
                });
                if(request1.ok) {
                   nyc1 = true;
                }else {
                   nyc1 = false;
                }

                const address2 = "api/v1/reservas/booking/Gran-Melia-Palacio-de-los-Duques/" + habitaciones;
                let request2 = await fetch(address2, {
                    method: 'GET'
                });
                if(request2.ok) {
                   nyc2 = true;
                }else {
                   nyc2 = false;
                }

                if(nyc1 == false && nyc2 == false){
                    alert("No hay m??s habitaciones disponibles en ninguno de los hoteles de Londres. Disculpe las molestias");
                }else if(nyc1 == true && nyc2 == false){
                    alert("Solo quedan habitaciones disponibles en el hotel Innside Paris Charles de Gaulle.");
                    document.location.href="habitaciones-nyc-ts.html";
                }else if(nyc1 == false && nyc2 == true){
                    alert("Solo quedan habitaciones disponibles en el hotel Meli?? Paris La D??fense.");
                    document.location.href="habitaciones-nyc-nomad.html";
                }else{
                    document.location.href="./hoteles2.html#nyc-hoteles";
                }

             document.location.href="./hoteles2.html#nyc-hoteles";
          }
      }else{
           alert("No se ha podido comprobar la disponibilidad. Por favor, revise que las fechas introducidas son correctas.");
      }
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
