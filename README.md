# MySocialNetworkCollection
Problema a resolver:

La idea es construir un servicio de recolección de entradas de APIs sociales (por ahora solo Instagram). La idea es que tenés una API para definir tu interés en un hashtag y para dejar de seguir el hash. 

Cuando se registra el interés en un hashtag o user, pasan dos cosas: 
1) Se guarda en algún tipo de base de datos (redis, mongo, mysql, hsqldb o lo que quieras).
2) Un proceso recolector comienza a recibir los mensajes de esa subscripción. Basta con imprimir los datos del mensaje en el log.

Cuando la aplicación arranca, releva todas las subscripciones desde la base de datos y se suscribe a todas.

Estaría bueno tener también un endpoint que te devuelva, ya sea paginando o vía streaming los mensajes que van llegando.  

La idea no es que necesariamente termines todo, sino ver como codificas esos servicios y la integración y ver que cosas te trabaron y como es tu proceso para tratar de resolverlas. El ejercicio es abierto, un montón de detalles quedan de tu lado para que los resuelvas como te parezca. No tengas miedo de cortar el alcance como te parezca.

Hipótesis adoptadas:
* Voy a tomar en cuenta que el proceso recolector busca una serie de datos de las publicaciones. No chequeé en las diferentes APIs de distintas redes sociales
los atributos que pueden presentar. La estructura de los datos que decidí guardar está basada pura y exclusivamente en lo que vi de Instagram. En caso de que
las distintas redes brinden información muy diferente, quizás hubiera debido crear distintos tipos de MediaObject según corresponda por el caso.
* Debido a las políticas de Instagram, como la aplicación se encuentra en modo de desarrollo, necesito que algunos usuarios específicamente me den permisos para
ser reconocidos por la aplicación. Por esa razón no cuento con una extensa base de datos para buscar. Los usuarios (amigos) que me dieron permiso son:
	- lucastarcetti
	- francotesei
	- ines.parnisari
