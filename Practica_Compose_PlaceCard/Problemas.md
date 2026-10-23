**PROBLEMAS DURANTE LA PRACTICA**

#One Drive#

    - Intentando ejecutar la aplicacion el ide me ha dado un error de build y todos han sido relacionados con la ruta donde tengo el proyecto
    me he informado sobre esta situacion y tuve que sacar el proyecto fuera de la carpeta de OneDrive y eliminar app/build 
    - El error era AccessDeniedException, OneDrive bloqueaba los archivos de la carpeta build mientras los sincronizaba y gradle no podia borrarlos

#ESTRUCTURACION#

    - Al principio ha habido dudas de como estructurar el projecto y como orientarlo a POO
    - Al utilizar los composables no sabia como pasarle bien los parametros desde fuera al PlaceCard (de ahi a crear la data class Place)
    - Me costaba entender porque el modifier iba en casi todos los componentes
    - Al crear la carpeta model me decia que el nombre no era valido, el problema era que estaba creando un directory y no un package
    - Tenia dudas de si crear una clase para los componentes, pero los composables son funciones sueltas dentro de un archivo, no hace falta clase
    - Tenia dudas de donde crear el objeto de seattle, al final lo he puesto en PlaceRepository dentro de model y no en el MainActivity

#MODIFIER#

    - Al principio no sabia si el modifier tenia que ir en cada funcion o solo en PlaceCard, al final va en cada composable que pinta algo
    y se aplica solo al elemento raiz de la funcion (los hijos usan Modifier con mayuscula)
    - Me daba error al poner align dentro de un composable, porque align solo existe dentro de un Box, asi que la posicion se pone en PlaceCard 
    cuando llamo al composable y no dentro de el
    - Me confundia entre modifier y Modifier en las llamadas, si usas el de minuscula arrastras al hijo lo que le pasen al PlaceCard

#IMAGEN#

    - He tenido complicaciones a la hora de aplicar los estilos a la imagen, el orden de los estilos se ha visto incluido
    - En la data class Place puse la imagen como Painter y no me dejaba crearla en el repositorio, porque painterResource solo se puede 
    llamar dentro de un composable. Lo he cambiado a Int (el id del recurso) y el painter se crea en PlaceImage

#Icono#

    - He tenido complicaciones a la hora de aplicarle la imagen al icono
    - Al importar Icons me salia en rojo, porque faltaba la dependencia de material icons. Como no funcionaba he creado los iconos con
    Vector Asset en res/drawable y los uso con painterResource

#BOTONES#

    - No sabia que poner en el onClick porque es obligatorio, no vale null, se pasa una lambda vacia {}
    - Queria que el fondo de un boton fuera igual al de la app, y al final he usado OutlinedButton con fondo transparente
    - Para el borde y el color de la letra he usado BorderStroke dentro del propio boton y el color teal_700
    - Para que los dos botones ocupen lo mismo puse el weight en el Row y no en los botones, el weight(1f) va en cada boton

#DIFICULTAD (CANVAS)#

    - Costaba entender como hacer los 5 circulos segun un numero, la idea es que el circulo i esta relleno si i < nivel
    - Habia que calcular la posicion x de cada circulo y el ancho del canvas, si no el ultimo circulo se corta
    - El color del tema hay que sacarlo fuera del Canvas porque dentro no se puede leer MaterialTheme
    - Tuve que cambiar la dificultad de String a Int para poder compararla

#TIPOGRAFIA#

    - Intente cambiar la fuente con un link de google fonts y poniendo implementaciones, pero no funcionaba nada
    - Al final he descargado los archivos .ttf, he creado la carpeta res/font y he definido la FontFamily en el archivo de Type
    - Los nombres de los archivos tienen que ir en minuscula y con guion bajo

#QUE PARTE ME HA COSTADO MAS#

     - La parte que mas me ha costado ha sido la estructuracion del codigo, tambien es en la que mas tiempo le he dedicado.
    Me gusta ser organizado y limpio y con el codigo igual, entonces para mi es fundamental que este lo mejor posible
    orientado a objetos (POO)
    - Lo que mas me ha costado ha sido pasar de Java a Kotlin y Android Studio, por ejemplo como pasarle los datos a PlaceCard
    (de ahi la data class Place) o entender porque el modifier va solo en el elemento raiz de cada composable
    - Por lo demas ha sido investigar/buscar y la experiencia previa del anterior curso