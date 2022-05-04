async function checkIn(){
    try {
        var nif = await document.getElementById("nif").value;
        var id = await document.getElementById("id").value;
        var destino = await document.getElementById("destino").value;
        var hotel = await document.getElementById("hotel").value;

        const address = "api/v1/reservas/id/" + id;
        response = await fetch(address, {
            method: 'GET'
        });
        if(response.ok){
            var reserva = await response.json();
            console.log(reserva);
            alert("Reserva en " + reserva.id);
        }else{
            alert("Reserva no");
       }
    } catch (err) {
        console.error(err.message);
    }
    return false;
}

$('#checkInForm').submit(function (e) {
    e.preventDefault();
});