async function getInformacionInnsideCharlesDeGaulle(){
     try {
       const address0 = "api/v1/hoteles/nombre/Innside-Paris-Charles-de-Gaulle";
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
           var hotel = await request0.json();
           document.getElementById("room-total-cdg").innerHTML = hotel.habitacionesTotales;
           document.getElementById("room-ocupadas-cdg").innerHTML = hotel.habitacionesOcupadas;
           document.getElementById("capacidad-cdg").innerHTML = hotel.capacidad;
           document.getElementById("ocupacion-cdg").innerHTML = hotel.ocupacion;
           const address1 = "api/v1/reservas/hotel/Innside-Paris-Charles-de-Gaulle";
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

async function getInformacionMeliaParisDefense(){
     try {
       const address0 = "api/v1/hoteles/nombre/Melia-Paris-La-Defense";
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
           var hotel = await request0.json();
           document.getElementById("room-total-defense").innerHTML = hotel.habitacionesTotales;
           document.getElementById("room-ocupadas-defense").innerHTML = hotel.habitacionesOcupadas;
           document.getElementById("capacidad-defense").innerHTML = hotel.capacidad;
           document.getElementById("ocupacion-defense").innerHTML = hotel.ocupacion;
           const address1 = "api/v1/reservas/hotel/Melia-Paris-La-Defense";
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

async function getInformacionTRYPTimesSquare(){
     try {
       const address0 = "api/v1/hoteles/nombre/TRYP-New-York-Times-Square";
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
           var hotel = await request0.json();
           document.getElementById("room-total-ts").innerHTML = hotel.habitacionesTotales;
           document.getElementById("room-ocupadas-ts").innerHTML = hotel.habitacionesOcupadas;
           document.getElementById("capacidad-ts").innerHTML = hotel.capacidad;
           document.getElementById("ocupacion-ts").innerHTML = hotel.ocupacion;
           const address1 = "api/v1/reservas/hotel/TRYP-New-York-Times-Square";
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

async function getInformacionInnsideNewYorkNomad(){
     try {
       const address0 = "api/v1/hoteles/nombre/Innside-New-York-Nomad";
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
           var hotel = await request0.json();
           document.getElementById("room-total-nomad").innerHTML = hotel.habitacionesTotales;
           document.getElementById("room-ocupadas-nomad").innerHTML = hotel.habitacionesOcupadas;
           document.getElementById("capacidad-nomad").innerHTML = hotel.capacidad;
           document.getElementById("ocupacion-nomad").innerHTML = hotel.ocupacion;
           const address1 = "api/v1/reservas/hotel/Innside-New-York-Nomad";
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

getInformacionInnsideCharlesDeGaulle();
getInformacionMeliaParisDefense();
getInformacionTRYPTimesSquare();
getInformacionInnsideNewYorkNomad();
