Mars Photos Viewer

Descripción:
Mars Photos Viewer es una aplicación Java Swing que
permite a los usuarios explorar y visualizar
fotos capturadas por los rovers de Marte de la NASA.
La aplicación interactúa con la API de Fotos de Rovers de Marte de la NASA
para obtener y mostrar fotos basadas en criterios
seleccionados por el usuario, como el rover,
el sol marciano (día), el tipo de cámara y
el número de fotos.

Características:
Buscar Fotos: Los usuarios pueden filtrar fotos
seleccionando un rover (Curiosity, Opportunity, Spirit),
especificando un sol marciano (día en Marte), eligiendo un tipo de
cámara y estableciendo el número de fotos a recuperar.

Ver Detalles de la Foto:
Cada foto mostrada incluye detalles como su ID,
sol marciano, fecha terrestre en que se tomó la foto,
nombre del rover y cámara utilizada.

Abrir Foto en el Navegador Web:
Los usuarios pueden hacer clic en un botón asociado con cada foto
para verla directamente en la web a través del enlace de origen de
imágenes de la NASA.

Actualizaciones de Estado:
La aplicación proporciona actualizaciones
de estado en una barra de estado, informando a los usuarios sobre
la operación actual (por ejemplo, recuperación de fotos, errores).

Tecnologías Utilizadas:
Java (JDK21): Lenguaje de programación principal utilizado para la
lógica de la aplicación y el desarrollo de la interfaz
gráfica de usuario.

Java Swing: Juego de herramientas GUI utilizado para construir
la interfaz gráfica de usuario.

Análisis JSON: Se utilizó la biblioteca Gson para analizar los
datos JSON recibidos de la API de Fotos de Rovers de Marte de la NASA.

Conexión HTTP: HttpURLConnection para realizar solicitudes
HTTP GET al punto final de la API de la NASA.

Programación Funcional: Se utilizaron lambdas y streams para
operaciones de estilo funcional donde correspondiera,
mejorando la legibilidad y concisión del código.

Uso:
1) Al iniciar la aplicación, se abre la ventana principal (MAIN)
con opciones para seleccionar un rover, sol marciano,
tipo de cámara y número de fotos a recuperar.

2) Haz clic en el botón Filtrar para iniciar la búsqueda y
mostrar fotos que coincidan con los criterios seleccionados.

3) Las fotos se muestran en un panel desplazable donde cada foto
incluye información detallada y un botón para verla en la web.
