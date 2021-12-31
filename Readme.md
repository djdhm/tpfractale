# TP Fractale POO
### Compiler le projet 
<p>Pour compiler le projet il suffit d'avoir <italic> Maven </italic> installé </p>
<p>lancez la commande : </p>
<code>mvn clean install</code>
La compilation generera un dossier <italic>target</italic> qui contient un jar <bold>fractale-1.0.0-shaded.jar</bold>

### Lancer le programme 
<p>Pour le lancement utilisez la commande </p>
<code>java -jar target/fractale-1.0.0-shaded.jar</code>

### Arguments de programme 

<p>
<code>
-cx,--cx <arg>        Valeur reel de la constante
                       Valeur par défaut : 0
</code>

<p>
<code>
-cy,--cy <arg>        Valeur imaginaire de la constante
                       Valeur par défaut : -0.75
</code>
</p>
<p>
<code>
-g,--graphic          Lancer en mode graphique
                       Valeur par défaut : false
</code>
</p>

<p>
<code>
-h,--height <arg>     Hauteur de l'image
                       Valeur par défaut :1000
</code>
</p>

<p>
<code>
 -i,--max_iter <arg>   Nombre maximal d'iteration pour determiner la
                       convergence
                       Valeur par défaut :500
</code>
</p>

<p>
<code>
-m,--manual           Afficher le manuel d'utilisation
</code>
</p>

<p>
<code>
 -o,--output <arg>     Fichier de sortie
                       Valeur par défaut :fractale
</code>
</p>

<p>
<code>
 -p,--polynome <arg>   Polynome utilisé pour le calcul
                       Valeur par défaut : 1X^2
</code>
</p>

<p>
<code>
 -x,--minX <arg>       X min
                       Valeur par défaut : -1
</code>
</p>

<p>
<code>
 -X,--maxX <arg>        X max
                       Valeur par défaut :1
</code>
</p>

<p>
<code>
 -y,--minY <arg>       Y min
                       Valeur par défaut :-1
</code>
</p>

<p>
<code>
 -Y,--maxY <arg>       Y max
                       Valeur par défaut :1

</code>

</p>

<p>
<code>
 -z,--palette <arg>    Choisir la palette de couleur (0-3)
                       Valeur par défaut : 2
</code>
</p>

<p>
<code>
-s,--set <arg>        Type de l'ensemble  : Julia/Mandelbrot.
                       Valeur par défaut : Julia
</code>
</p>


<p>
<code>
 -t,--thread <arg>     Nombre de Thread de calcul (GUI seulement)
                       Valeur par défaut : 1
</code>
</p>
<p>
<code>
-w,--width <arg>       Largeur de l'image
                       Valeur par défaut : 1000
</code>
</p>

### Exemples 

<p>
Lancement dans le mode CLI : 
<code>
java -jar target/fractale-1.0.0-shaded.jar --cx 0.35 --cy 0.35 --width 1000 --height 1000 --palette 3 --output test
</code>
</p>
<p>
Lancement dans le mode GUI : 
<code>
java -jar target/fractale-1.0.0-shaded.jar -g --output exemple
</code>
</p>



 




