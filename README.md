# :school: mongoDBProject :school:

## Base de données MongoDB avec implémentation Java Maven

| Propriétés   | Valeur |
|-------|--------------|
|Langage   | Java  |       
|Base de données| MongoDB | 
|patterns     |DAO & Singleton  |
|Framework |  Maven|
|Dependencies | MongoDB, Jakarta, Jaxb |
|Paramétrage | config.properties  |
|IDE | IntelliJ |




 ***
 
 ## Diagramme UML du programme
 <img src="Images/UML mongoDB.png" width="694" height="432">
 
 ## Fonctionnement
 
 ### Introduction
 
Le sujet posé s’intéresse à la gestion des Etablissements : ses avions et son 
personnel. L’idée est d’analyser le sujet, de le modéliser sous SQL et de pouvoir 
automatiser sa gestion avec JAVA sur Eclipse. 

### Maven 
Pour la gestion du projet nous avons utilisé Maven qui permet de gérer les dépendances dans un projet Java.
Le projet est découpé en quatre packages : main, dao, model, GUI. Le package « main » correspond aux classes qui vont permettre d’exécuter le projet. Les packages « dao » et « model » correspondent aux classes utilisées dans le pattern Dao. Puis le package GUI contient des classes de Jframe nécessaire à l’affichage graphique. Le fichier porn.xml gère les dépendances liées aux librairies Jakarta et MongoDB. Le fichier config.properties regroupe les paramètres essentiels pour se connecter à la base de données.

### Configuration
Arrivé dans Intellij il faut importer un projet Maven existant. Dans le fichier config.properties il faut modifier les chemins d’accès en fonction du serveur NoSql-MongoDB de l’utilisateur.
```{java}
url=mongodb://localhost:27017
database=Projet2022
```

### Pattern Singleton

Nous avons utilisé le pattern Singleton pour établir le lien entre notre base et l’implémentation Java, il sert à mettre en place la connexion entre les deux. L’idée est de créer un objet Connection contenant la connexion déclaré en private. Ce pattern est mis en place dans la classe « MongoDBConnection », cette classe récupère les informations contenues dans le fichier config.properties.

###Pattern DAO et pattern Factory

Pour mettre en place le pattern DAO nous avons créé une classe pour chacun des objets, ces classes sont composées des propriétés correspondantes au champs des tables et des getters et setters (le package model). Pour chacune de ces classes on a créé une classe qui réalise les manipulations dans la base de données (AdresseDAO, EtablissementDAO, EtudiantDAO, FormationDAO). Ces dernières permettent de réaliser six opérations sur les données
- create(T obj) : boolean , pour créer un nouveau document dans une collection
- update(T obj) : boolean, pour mettre à jour un document dans une collection, - 
- find(id) : T, pour rechercher un document dans une collection 
- delete(T obj) : boolean, pour supprimer un document dans une collection 
- findAll() : ArrayList, pour afficher tous les documents d’une collection

Ces classes sont une extension de la superclasse « Dao » pour gérer correctement les connexions avec la base.

Le pattern Dao est accompagné du pattern Factory. Celui-ci permet de créer les objets Dao dans notre classe AppP.java.

### Exécution
Dans la classe « App , Il suffit d’appeler le constructeur des DAO pour pouvoir l’utiliser, notre code de la classe DAO présente les fonctionnalités complètes.
