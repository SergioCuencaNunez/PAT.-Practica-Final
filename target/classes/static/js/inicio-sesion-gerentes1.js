async function getInformacionMeliaPrincesa(nombreHotel){
     try {
       const address0 = "api/v1/hoteles/nombre/" + nombreHotel;
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
           var hotel = await request0.json();
           document.getElementById("room-total-princesa").innerHTML = hotel.habitacionesTotales;
           document.getElementById("room-ocupadas-princesa").innerHTML = hotel.habitacionesOcupadas;
           document.getElementById("capacidad-princesa").innerHTML = hotel.capacidad;
           document.getElementById("ocupacion-princesa").innerHTML = hotel.ocupacion;
           const address1 = "api/v1/reservas/hotel/" + nombreHotel;
           let request1 = await fetch(address1, {
               method: 'GET'
           });
           if(request1.ok){
               var reservas = await request1.json();
               document.getElementById("reservas-princesa").innerHTML = reservas.length;
           }
       }
     }catch (err){
       console.error(err.message);
     }
     return false;
}

async function getInformacionGranMeliaDuques(nombreHotel){
     try {
       const address0 = "api/v1/hoteles/nombre/" + nombreHotel;
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
           var hotel = await request0.json();
           document.getElementById("room-total-duques").innerHTML = hotel.habitacionesTotales;
           document.getElementById("room-ocupadas-duques").innerHTML = hotel.habitacionesOcupadas;
           document.getElementById("capacidad-duques").innerHTML = hotel.capacidad;
           document.getElementById("ocupacion-duques").innerHTML = hotel.ocupacion;
           const address1 = "api/v1/reservas/hotel/" + nombreHotel;
           let request1 = await fetch(address1, {
               method: 'GET'
           });
           if(request1.ok){
               var reservas = await request1.json();
               document.getElementById("reservas-duques").innerHTML = reservas.length;
           }
       }
     }catch (err){
       console.error(err.message);
     }
     return false;
}

async function getInformacionMeliaWhiteHouse(nombreHotel){
     try {
       const address0 = "api/v1/hoteles/nombre/" + nombreHotel;
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
           var hotel = await request0.json();
           document.getElementById("room-total-wh").innerHTML = hotel.habitacionesTotales;
           document.getElementById("room-ocupadas-wh").innerHTML = hotel.habitacionesOcupadas;
           document.getElementById("capacidad-wh").innerHTML = hotel.capacidad;
           document.getElementById("ocupacion-wh").innerHTML = hotel.ocupacion;
           const address1 = "api/v1/reservas/hotel/" + nombreHotel;
           let request1 = await fetch(address1, {
               method: 'GET'
           });
           if(request1.ok){
               var reservas = await request1.json();
               document.getElementById("reservas-wh").innerHTML = reservas.length;
           }
       }
     }catch (err){
       console.error(err.message);
     }
     return false;
}

async function getInformacionMELondon(nombreHotel){
     try {
       const address0 = "api/v1/hoteles/nombre/" + nombreHotel;
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
           var hotel = await request0.json();
           document.getElementById("room-total-me").innerHTML = hotel.habitacionesTotales;
           document.getElementById("room-ocupadas-me").innerHTML = hotel.habitacionesOcupadas;
           document.getElementById("capacidad-me").innerHTML = hotel.capacidad;
           document.getElementById("ocupacion-me").innerHTML = hotel.ocupacion;
           const address1 = "api/v1/reservas/hotel/" + nombreHotel;
           let request1 = await fetch(address1, {
               method: 'GET'
           });
           if(request1.ok){
               var reservas = await request1.json();
               document.getElementById("reservas-me").innerHTML = reservas.length;
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
             'Melia-Madrid-Princesa': "Meliá Madrid Princesa",
             'Gran-Melia-Palacio-de-los-Duques': "Gran Meliá Palacio de los Duques",
             'Melia-White-House': "Meliá White House",
             'ME-London': "ME London"
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
             'Melia-Madrid-Princesa': "Meliá Madrid Princesa",
             'Gran-Melia-Palacio-de-los-Duques': "Gran Meliá Palacio de los Duques",
             'Melia-White-House': "Meliá White House",
             'ME-London': "ME London"
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

getInformacionMeliaPrincesa('Melia-Madrid-Princesa');
getInformacionGranMeliaDuques('Gran-Melia-Palacio-de-los-Duques');
getInformacionMeliaWhiteHouse('Melia-White-House');
getInformacionMELondon('ME-London');