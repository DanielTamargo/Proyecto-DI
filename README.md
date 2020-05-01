# DI: Proyecto Final
Proyecto final de la asignatura Desarrollo de Interfaces.
Basado en Android y Kotlin.

## Descargar el proyecto o la APK
- [**Proyecto: haz clic aquí para descargar únicamente el .rar con el proyecto**](https://github.com/DanielTamargo/DI-Proyecto/raw/master/Proyecto-Assets/GitHub-VistaPrevia/Proyecto.rar)
- [**Enlace a Google Drive para descargar la APK** (en algunos móviles no deja instalarla por 'falta de seguridad')](https://drive.google.com/file/d/1d0AYdyW6uqyRQkbqVuUpjsbs5vGDs4ig/view?usp=sharing)

## Resumen de la tarea
*"El proyecto consiste en crear una aplicación para Android utilizando Kotlin como lenguaje de programación.
La aplicación deberá tener un toolbar con diferentes opciones para el menú y usar un RecyclerView o ViewPager2 para el diseño, o ambos. Además, es posible usar CardViews y TabLayout para mejorar el diseño.
También tendrá que tener una BBDD usando SQLite y Room."*

## Resumen del proyecto
Nombre: **Valorant Info**
Utilidad: Proporcionar información sobre el juego y sus distintos mapas, personajes y armas.

## Elementos usados y motivo

- **ProgressBar**: para mostrar esa barra de progreso de inicio que se ve al iniciar la aplicación.
- **Toolbar**: presente en todas las actividades, dispone de dos opciones en el menú de la propia toolbar.
  * Qué es Valorant: esta opción mostrará una Activity con la información básica que explica en qué consiste el juego.
  * Términos legales: esta opción mostrará un Alert Dialog con la información sobre los términos legales de Riot Games.
- **RecyclerView + CardView**: mezclando estos dos elementos, se muestra una lista con las diferentes opciones, en este caso: 3. Dependiendo en qué celda clique el usuario, se le llevará a una Activity u otra. Se han utilizado CardViews para mejorar la estética de la aplicación.
- **ViewPager2**: se ha utilizado ViewPager2 para unir distintos fragmentos en una sola actividad, de esta manera, el usuario al deslizar el dedo horizontalmente podrá moverse entre distintas "Ventanas" (realmente son fragmentos) y así ver la información. En este caso se puede utilizar este ViewPager2 en la ventana 'Mapas', pudiendo ver la información de los 3 mapas del juego.
- **ViewPager2 + TabLayout + 'RecyclerView casero'**: en este caso se ha vuelto a utilizar ViewPager2, pero además, se le ha implementado TabLayout para poder ver el nombre de las pestañas entre las que el usuario se está moviendo, así, podrá sentir que está filtrando los resultados. Denomino 'RecyclerView casero' a una serie de líneas de código que cargan todos las armas almacenadas en distintos Arrays en distintas celdas, pero todo ese código está hecho a mano y con un final bastante atractivo (el motivo de haberlo hecho a mano no era más que querer aprender a implementar a través de código basado en Kotlin un bucle de creaciones de elementos en cadena). Este código lo podrás encontrar en cualquiera de los 4 fragmentos dentro de la carpeta 'FragmentosArmas'.
- **BBDD**: la base de datos ha sido utilizada para los personajes, donde se dan las siguientes opciones:
  * Ver un listado de los personajes existentes.
  * Crear un nuevo personaje personalizado.
  * Editar un personaje personalizado (no se pueden editar personajes oficiales).
  * Borrar un personaje personalizado (no se pueden borrar personajes oficiales).
  * Además, si es la primera vez que se inicia la aplicación, al cargar la pestaña personajes, la aplicación se dará cuenta e insertará por su propia cuenta los 5 personajes oficiales registrados, de esta manera aunque sea una aplicación recién instalada, no podrá fallar debido a NullPointerException por la falta de datos.
  * En la BBDD se guardan las clases Personaje y Habilidad. Cada personaje tiene 4 habilidades.
  * A la hora de crear o editar, se comprueba que estén todos los datos obligatorios rellenados (existen datos opcionales, que son los links a las imágenes de los personajes y sus habilidades, puesto que si se deja el link vacío, la propia aplicación utilizará unos arrays de links para seleccionar uno aleatorio y de esta manera el personaje tendrá su imagen principal y sus imágenes para las habilidades.
  * Para borrar un personaje habrá que acceder a Editar personaje, si no se accede a través de ese botón, no existirá la opción eliminar (no se puede eliminar algo que no se ha creado). Si se intenta editar un personaje oficial, saltará un Alert Dialog indicando que eso no es posible, además, si estás intentando editar sin haber creado ningún personaje, mostrará un mensaje distinto a cuando intentes editar un personaje oficial pero sí hayas creado ya con anterioridad un personaje personalizado.
- Utilizo numerosos Intent con sus respectivos startActivity para iniciar actividades e incluso en una ocasión utilizo startActivityForResult pasándole datos y recibiendo un boolean que confirma si el personaje ha sido eliminado o no, ya que si ha sido eliminado, será necesario cambiar el personaje a cargar para evitar que la aplicación falle por un NullPointerException intentando cargar un personaje no existente.
- **ScrollView**: la mayoría de Activities y Fragments utilizan ScrollView para que el usuario pueda ver toda la información que contengan.
- **Picasso**: para cargar las imágenes a través de links, aunque los personajes oficiales tienen sus imágenes guardadas como Drawables para utilizarlas como placeHolder en caso de que Picasso llegue a fallar por ejemplo, por un error del servidor o falta de conexión a internet.
- **Muchos Datos**: bastantes datos sobre el juego recavados con la finalidad de hacer la aplicación lo más precisa posible.
- **Easter Eggs**: la aplicación cuenta con 5 easter eggs escondidos, cada uno será una alerta tipo Toast. Están escondidas por toda la aplicación, ¿podrás encontrar las 5? Ojo, la mayoría son simplemente clicar en alguna parte oculta, otra será clicar X veces (tampoco demasiadas) en algún botón o checkbox...


Las imágenes de los personajes, iconos de las habilidades, datos de los mapas/personajes/armas y fuente pertenecen a la empresa Riot Games. Para términos legales consultar el apartado 'Términos Legales' disponible en una de las opciones de la Toolbar dentro de la propia aplicación.


## Vista Previa de la aplicación

![Intro](/Proyecto-Assets/GitHub-VistaPrevia/intro.gif)

![VentanaPrincipal](/Proyecto-Assets/GitHub-VistaPrevia/ventanaprincipal.png)

![VentanaMapas](/Proyecto-Assets/GitHub-VistaPrevia/ventanamapas.png)

![VentanaPersonajes](/Proyecto-Assets/GitHub-VistaPrevia/ventanapersonajes.png)

![VentanaArmas](/Proyecto-Assets/GitHub-VistaPrevia/ventanaarmas.png)

## Código 'RecyclerView casero'

![RecyclerViewCasero](/Proyecto-Assets/GitHub-VistaPrevia/recyclerviewcasero.gif)
