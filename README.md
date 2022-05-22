# Práctica Final

## Objetivo de la práctica
El objetivo de la práctica final es realizar una aplicación web utilizando Spring Boot basándose en la compañía de hoteles Meliá Hotels International. Dicha aplicación contiene todas las funcionalidades que se esperan de una página web de una cadena hotelera, ya que se incluye una interfaz habilitada para el propio usuario y una interfaz específica para los administradores y los gestores de los hoteles.
Entre estas funcionalidades se incluyen: 
- Registro de usuarios e inicio de sesión
- Modificación de datos del perfil individual
- Reservas en diferentes hoteles
- Modificación y cancelación de reservas
- Check-in online
- Buscar disponibilidad
- Newsletter/suscripción a boletín de noticias
- Contacto
- Gestión y modificación de hoteles
- Gestión de reservas realizadas y de usuarios registrados

## Funcionamiento
Para ello se han partido de 5 tablas SQL, en las que están definidas todos los atributos y las que se encargan de guardar la información que se maneja en el sitio web. Se ha incluido una tabla para reservas, otra para todos los hoteles, otra para los tipos de habitación en cada hotel, otra para los mensajes recibidos y, por último, la de usuarios.
Partiendo de esta base de tablas, se ha organizado todo el sistema que permite realizar las llamadas operaciones CRUD (create, read, update and delete) y que contiene todos los métodos necesarios que han sido posteriormente validados y probados gracias al Testing realizado.

Todos los formularios que se incluyen están estrechamente relacionados con estos métodos y permiten al usuario y al gerente interaccionar con la página y poder realizar las peticiones deseadas. Se puede navegar por el sitio web y por varias páginas sin necesidad de registrarse e incluso realizar check-in online, pero para poder acceder a las reservas es necesario registrarse o iniciar sesión.

## Diseño
El sitio web consta de una compleja red de páginas web que se encuentran vinculadas por enlaces, botones y formularios. Sin necesidad de registrarse, es posible comprobar la disponibilidad de habitaciones en los hoteles gracias al formulario inicial; ver toda la oferta de hoteles y habitaciones disponibles a través de la sección ``Hoteles``, dónde si se selecciona un hotel se puede realizar una reserva; registrarse al boletín de noticias encontrado en el ``Footer``, realizar check-in online y localizar cualquier reserva realizada en la sección ``Check-In Online`` y mandar cualquier mensaje de atención al cliente en la sección ``Contacto``. 

Si se inicia sesión como usuario, se tendrá acceso a la página de ``Perfil``, donde se pueden modificar todos los datos, excepto el NIF registrado, se cierra la sesión y dónde se elimina el usuario. La contraseña no aparece visible por motivos de seguridad, pero es posible cambiarla y en caso de no querer modificarla, no hace falta escribir nada en esos campos. El usuario luego tiene acceso a su página de reservas, en la sección ``Mi Cuenta > Reservas`` dónde aparecen todos los detalles de cada una de las reservas realizadas y desde donde se podrán cancelar o modificar gracias a los botones situados en la parte de abajo.

En caso de ser administrador, se tendrá total control a todos los aspectos comentados. También dispone de un perfil, al cuál se llega al iniciar sesión, dónde poder modificar los datos personales y, además, crear otros perfiles de gerentes. Por motivos de seguridad, las cuentas de los nuevos administradores se crean dentro de la cuenta de otro. Desde la sección ``Gestión empresarial`` se podrá acceder a:
- Usuarios: se muestran todos los ususarios junto con su información relevante.
- Reservas: se muestra para cada hotel, las reservas realizadas, con posibilidad de poder cancelarlas pero no modificarlas, que corre a cargo del usuario.
- Hoteles: se muestra el control de cada hotel, desde el cuál se pueden ampliar o reducir habitaciones, y cerrar o abrir el hotel. También se pueden acceder a las reservas de cada hotel a través de esta página. 
- Contacto: se pueden observar todos los mensajes recibidos (check-in online, suscripciones y mensajes de los clientes).

## Software Back-End
Se ha dado especial importancia a que los métodos implementados en la gestión de los endpoints REST sean coherentes y reflejen de manera correcta toda la información que se maneja en el sitio web. Para ello, todos los formularios están correctamente validados y no permiten realizar acciones si los datos que se introducen son incorrectos mostrando siempre en cada caso, el error por el cuál se produce o en su caso, la confirmación de que los datos se extraen, se modifican o eliminan de manera correcta.

De esta manera, la información que se muestra siempre en todas las páginas web esta actualizada y se puede verificar. Por ejemplo, al realizar una reserva y su correspondiente check-in online, el usuario recibirá sendas confirmaciones y, podrá acceder y modificar su reserva en su página individual. Por otro lado, el administrador recibirá el mensaje del check-in online, podrá observar la reserva realizada y verá como el hotel tiene más ocupación y habitaciones ocupadas.

Todos los endpoints han sido comprobados con el testing que se implementa y que verifican que los métodos utilizados no tienen fallos y que la información procesada es correcta.

## Información adicional
- Los usuarios (clientes y gerentes) podrán navegar por toda la web una vez registrados, ya que su sesión permanecerá abierta hasta que ellos deseen cerrarla.
- La página de inicio de sesión determina si el usuario es administrador o cliente para poder redirigirlo a su página correspondiente.
- Los usuarios no pueden registrarse con correos que contienen la extensión del correo de la empresa (@melia) y no se pueden crear nuevas cuentas de administradores si el correo no incluye la extensión mencionada.
- La aplicación web está adaptada para poder ser utilizada en cualquier dispositivo (ordenador, tablet, móvil) al ser completamente responsiva.
- Hay una gran variedad de datos e información almacenada en las tablas inicialmente. Dos ejemplos de usuarios con los que puede navegar con la página para probar su funcionamiento son:

  - Sergio Cuenca Núñez. Correo: scuenca@melia.com, contraseña: MeliaHotelsInternational2022, rol: admin.
  - Elena Conderana Medem. Correo: econderana@gmail.com, contraseña: ConderanaMedem2804, rol: cliente.
