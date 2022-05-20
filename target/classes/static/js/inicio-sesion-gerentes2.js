async function getInformacionInnsideCharlesDeGaulle(nombreHotel){
     try {
       const address0 = "api/v1/hoteles/nombre/" + nombreHotel;
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
           var hotel = await request0.json();
           document.getElementById("room-total-cdg").innerHTML = hotel.habitacionesTotales;
           document.getElementById("room-ocupadas-cdg").innerHTML = hotel.habitacionesOcupadas;
           document.getElementById("capacidad-cdg").innerHTML = hotel.capacidad;
           document.getElementById("ocupacion-cdg").innerHTML = hotel.ocupacion;
           const address1 = "api/v1/reservas/hotel/" + nombreHotel;
           let request1 = await fetch(address1, {
               method: 'GET'
           });
           if(request1.ok){
               var reservas = await request1.json();
               document.getElementById("reservas-cdg").innerHTML = reservas.length;
           }
       }
     }catch (err){
       console.error(err.message);
     }
     return false;
}

async function getInformacionMeliaParisDefense(nombreHotel){
     try {
       const address0 = "api/v1/hoteles/nombre/" + nombreHotel;
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
           var hotel = await request0.json();
           document.getElementById("room-total-defense").innerHTML = hotel.habitacionesTotales;
           document.getElementById("room-ocupadas-defense").innerHTML = hotel.habitacionesOcupadas;
           document.getElementById("capacidad-defense").innerHTML = hotel.capacidad;
           document.getElementById("ocupacion-defense").innerHTML = hotel.ocupacion;
           const address1 = "api/v1/reservas/hotel/" + nombreHotel;
           let request1 = await fetch(address1, {
               method: 'GET'
           });
           if(request1.ok){
               var reservas = await request1.json();
               document.getElementById("reservas-defense").innerHTML = reservas.length;
           }
       }
     }catch (err){
       console.error(err.message);
     }
     return false;
}

async function getInformacionTRYPTimesSquare(nombreHotel){
     try {
       const address0 = "api/v1/hoteles/nombre/" + nombreHotel;
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
           var hotel = await request0.json();
           document.getElementById("room-total-ts").innerHTML = hotel.habitacionesTotales;
           document.getElementById("room-ocupadas-ts").innerHTML = hotel.habitacionesOcupadas;
           document.getElementById("capacidad-ts").innerHTML = hotel.capacidad;
           document.getElementById("ocupacion-ts").innerHTML = hotel.ocupacion;
           const address1 = "api/v1/reservas/hotel/" + nombreHotel;
           let request1 = await fetch(address1, {
               method: 'GET'
           });
           if(request1.ok){
               var reservas = await request1.json();
               document.getElementById("reservas-ts").innerHTML = reservas.length;
           }
       }
     }catch (err){
       console.error(err.message);
     }
     return false;
}

async function getInformacionInnsideNewYorkNomad(nombreHotel){
     try {
       const address0 = "api/v1/hoteles/nombre/" + nombreHotel;
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
           var hotel = await request0.json();
           document.getElementById("room-total-nomad").innerHTML = hotel.habitacionesTotales;
           document.getElementById("room-ocupadas-nomad").innerHTML = hotel.habitacionesOcupadas;
           document.getElementById("capacidad-nomad").innerHTML = hotel.capacidad;
           document.getElementById("ocupacion-nomad").innerHTML = hotel.ocupacion;
           const address1 = "api/v1/reservas/hotel/" + nombreHotel;
           let request1 = await fetch(address1, {
               method: 'GET'
           });
           if(request1.ok){
               var reservas = await request1.json();
               document.getElementById("reservas-nomad").innerHTML = reservas.length;
           }
       }
     }catch (err){
       console.error(err.message);
     }
     return false;
}

async function abrirHotel(nombreHotel){
     try {
       const address0 = "api/v1/hoteles/nombre/" + nombreHotel;
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
           var hotel = await request0.json();
           var hoteles = {
             'Innside-Paris-Charles-de-Gaulle': "Innside Paris Charles de Gaulle",
             'Melia-Paris-La-Defense': "Meliá Paris La Défense",
             'TRYP-New-York-Times-Square': "TRYP New York Times Square",
             'Innside-New-York-Nomad': "Innside New York Nomad"
           };
           if(hotel.estado === true){
                alert('El hotel ' + hoteles[nombreHotel] + ' ya está abierto al público.');
           }else{
                const address1 = "api/v1/hoteles/update/estado/" + nombreHotel + "/true";
                let request1 = await fetch(address1, {
                    method: 'PUT'
                });
                if(request1.ok){
                    alert('El hotel ' + hoteles[nombreHotel] + ' se ha abierto al público y está preparado para recibir nuevos clientes.');
                }
           }
       }
     }catch (err){
       console.error(err.message);
     }
     return false;
}

async function cerrarHotel(nombreHotel){
     try {
       const address0 = "api/v1/hoteles/nombre/" + nombreHotel;
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
           var hotel = await request0.json();
           var hoteles = {
             'Innside-Paris-Charles-de-Gaulle': "Innside Paris Charles de Gaulle",
             'Melia-Paris-La-Defense': "Meliá Paris La Défense",
             'TRYP-New-York-Times-Square': "TRYP New York Times Square",
             'Innside-New-York-Nomad': "Innside New York Nomad"
           };
           if(hotel.estado === false){
                alert('El hotel ' + hoteles[nombreHotel] + ' ya está cerrado al público y no admite clientes.');
           }else{
                const address1 = "api/v1/reservas/hotel/" + nombreHotel;
                let request1 = await fetch(address1, {
                    method: 'GET'
                });
                if(request1.ok){
                    var reservas = await request1.json();
                    if(reservas.length == 0 && hotel.habitacionesOcupadas == 0 && hotel.ocupacion == 0){
                        const address2 = "api/v1/hoteles/update/estado/" + nombreHotel + "/false";
                        let request2 = await fetch(address2, {
                            method: 'PUT'
                        });
                        if(request2.ok){
                           alert('El hotel ' + hoteles[nombreHotel] + ' se ha cerrado al público.');
                        }else{
                           alert('El hotel ' + hoteles[nombreHotel] + ' no se ha podido cerrar.');
                        }
                    }else{
                        alert('El hotel ' + hoteles[nombreHotel] + ' no se ha podido cerrar. Asegúrese de que no hay reservas realizadas ni clientes hospedándose actualmente en el hotel.');
                    }
                }
           }
       }
     }catch (err){
       console.error(err.message);
     }
     return false;
}

async function ampliarHabitaciones(nombreHotel){
     try {
       const address0 = "api/v1/hoteles/nombre/" + nombreHotel;
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
           var hotel = await request0.json();
           var hoteles = {
             'Melia-Madrid-Princesa': "Meliá Madrid Princesa",
             'Gran-Melia-Palacio-de-los-Duques': "Gran Meliá Palacio de los Duques",
             'Melia-White-House': "Meliá White House",
             'ME-London': "ME London"
           };
           if(hotel.estado === false){
                let nuevasHabitaciones = prompt("Introduzca el número de habitaciones para ampliar:", "");
                const address1 = "api/v1/hoteles/update/ampliar/habitacionesTotales/" + nombreHotel + "/" + nuevasHabitaciones;
                let request1 = await fetch(address1, {
                    method: 'PUT'
                });
                if(request1.ok){
                    alert('Se han ampliado correctamente las habitaciones del hotel ' + hoteles[nombreHotel] + '.');
                    location.reload();
                }else{
                    alert('No se han podido ampliar las habitaciones del hotel ' + hoteles[nombreHotel] + '.');
                }
           }else{
               alert('Para ampliar habitaciones el hotel debe estar cerrado.');
           }
       }
     }catch (err){
       console.error(err.message);
     }
     return false;
}

async function reducirHabitaciones(nombreHotel){
     try {
       const address0 = "api/v1/hoteles/nombre/" + nombreHotel;
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
           var hotel = await request0.json();
           var hoteles = {
             'Melia-Madrid-Princesa': "Meliá Madrid Princesa",
             'Gran-Melia-Palacio-de-los-Duques': "Gran Meliá Palacio de los Duques",
             'Melia-White-House': "Meliá White House",
             'ME-London': "ME London"
           };
           if(hotel.estado === false){
                let nuevasHabitaciones = prompt("Introduzca el número de habitaciones para reducir:", "");
                const address1 = "api/v1/hoteles/update/reducir/habitacionesTotales/" + nombreHotel + "/" + nuevasHabitaciones;
                let request1 = await fetch(address1, {
                    method: 'PUT'
                });
                if(request1.ok){
                    alert('Se han reducido correctamente las habitaciones del hotel ' + hoteles[nombreHotel] + '.');
                    location.reload();
                }else{
                    alert('No se han podido reducir las habitaciones del hotel ' + hoteles[nombreHotel] + '.');
                }
           }else{
               alert('Para reducir habitaciones el hotel debe estar cerrado.');
           }
       }
     }catch (err){
       console.error(err.message);
     }
     return false;
}

getInformacionInnsideCharlesDeGaulle('Innside-Paris-Charles-de-Gaulle');
getInformacionMeliaParisDefense('Melia-Paris-La-Defense');
getInformacionTRYPTimesSquare('TRYP-New-York-Times-Square');
getInformacionInnsideNewYorkNomad('Innside-New-York-Nomad');