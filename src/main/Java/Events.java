class Events{
    final String ANSI_RESET = "\033[m";
    final String ANSI_MAGENTA = "\033[35m";
    final String ANSI_GREENKINDOF = "\033[38;5;42m";
    final String ANSI_BROWN = "\033[38;2;139;69;19m";
    final String ANSI_GRAY = "\033[38;2;128;128;128m";
    final String ANSI_BLUE_LIGHT = "\033[38;2;173;216;230m";
    final String ANSI_BLUE= "\033[0;34m"; 
    final String ANSI_GREEN_LIGHT = "\033[38;2;144;238;144m";
    final String ANSI_CYAN_LIGHT = "\033[38;2;224;255;255m";
    final String ANSI_YELLOW_BRIGHT = "\033[38;2;255;255;0m";
    final String ANSI_YELLOW =  "\033[38;5;11m";

    boolean[] entrepotPlein=new boolean[]{false};
    String entrepotPleinSTR="Les Entrepôts sont pleins !";

    boolean[] dortoirsPlein=new boolean[]{false};
    String dortoirsPleinSTR="Les Dortoirs sont pleins !";

    String[] ressourceEstEpuiseeSTR;
    String[] filonEpuise=new String[20];

    String[] naissance=new String[20];
    String[] deces=new String[20];

    boolean[] BatimentsPosed;
    String[][] posedTxt=new String[][]{
                        {"Votre" +ANSI_GREENKINDOF+" vaisseau (V)"+ ANSI_RESET +" s'est posé en toute sécurité sur une nouvelle planète encore inexplorée.",
                        "Dans le cadre du Projet «Fondation» votre mission est de veiller à la survie et au développement de votre colonie.",
                        "Afin d'accomplir cet objectif votre vaisseau et son réacteur à fusion peuvent vous apporter les ressources nécessaire à un bon départ !",
                        "Veuilliez prêter initialement attention aux ressources vitales à la réussite de votre mission tels que la nourriture, l'eau ou encore le cuivre.",
                        "       ",
                        "Bon courage Capitaine !"},

                        {"Afin d'assurer un certain confort et héberger les colons les "+ANSI_BROWN+"Dortoirs (⌂)"+ ANSI_RESET +" sont indispensables.",
                        "Gràce à une solide armature de Fer ce dortoir peut supporter les conditions extrèmes de la planète et ainsi garantir la sécurité.",
                        "Le Cuivre permet à ce dortoir de disposer aussi d'aménagements électriques assurant aux colons du confort"},

                        {"Les rayons de l'"+ANSI_GRAY+"Entrepôt (⌧)"+ ANSI_RESET +" doivent supporter de lourdes charges.",
                        "Le fer est idéal pour créer une structure à la fois robuste et résistante à l’usure."},

                        {"Afin de coordonner leurs activités ou simplement pour communiquer avec leurs proches restés sur Terre, le "+ANSI_MAGENTA+"Centre de communication Terrien (☤)"+ ANSI_RESET +" est indispensables",
                        "La combinaison du fer, du cuivre et du carbone assure un fonctionnement optimal.",
                        "Le fer est essentiel dans la communication par onde grâce à ses propriétés magnétiques, qui permettent de gérer, moduler et confiner les champs électromagnétiques.",
                        "Le Carbone agit comme bouclier contre les interférences électromagnétiques pouvant provenir des vents stellaires, il permet aussi de réduire la dissipation électrique",
                        "Grâce à sa faible résistivité et son excellente conductivité, le cuivre minimise les pertes de signal, ce qui le rend idéal pour le transport des signaux haute fréquence."},

                        {"Avoir un "+ANSI_YELLOW+"Cinéma (◈)"+ ANSI_RESET +" permet d'améliorer la satisfaction des habitants en les divertissants et en leur rappelant leur importante mission.",
                        "En utilisant du fer le cinéma se dote d'une armature solide, nécessaire pour une structure de cette envergure;",
                        "le cuivre permet également de mettre en place tout un système électrique servant à l'audiovisuel.",
                        "Le carbone, lui, permet une isolation accoustique en absorbant les sons indésirables offrant ainsi une meilleure expérience."},
                        
                        {"Le "+ANSI_BLUE_LIGHT + "Capteur d’Humiditée (⌯)"+ANSI_RESET+" joue un rôle primordial dans la survie de la colonie",
                        "Le sulfate de cuivre anhydre, obtenu par la combinaison de cuivre et de soufre étant ensuite déshydraté, est un excellent indicateur d'humiditée qui se révéle vital sur cette planète.",
                        "Le fer, lui, permet au capteur de résister aux conditions climatiques extrèmes de la planète."},

                        {"Se nourrir fait parti des besoins les plus basiques d'un être humain et la "+ANSI_GREEN_LIGHT + "Ferme Hydroponique (✲)"+ANSI_RESET+" y répond.",
                        "En optant pour ce mode de cultivation, de grandes économies d'eau sont réalisées",
                        "et grâce à la photosynthèse en plus d'apporter une nourriture saine, les plantes contribuent à la purification de l'air.",
                        "Le fer , en servant de structure aux racks et aux supports, permet la culture par hydroponie.",
                        "Le Carbone est utilisé pour filtrer et purifier l'eau; il élimine les impuretés, les produits chimiques, et les agents pathogènes, garantissant une eau propre pour les plantes."},

                        {"Les reserves d'oxygène étant limitées il est nindispensable, en utilisant un "+ANSI_CYAN_LIGHT+"Recycleur d’Air (≎)"+ANSI_RESET+", de renoouveler l'air de la colonie.",
                        "En plus de ses capacités antimicrobiennes, le cuivre est utilisé sous forme d'electrodes pour purifier, par électrolyse, l'air de la colonie.",
                        "L'eau est utilisée pour humidifier l'air sec, particulièrement dans cet environnement fermé, ce qui améliore le confort respiratoire et réduit les irritations causées par l'air sec."},

                        {"L'électricité est également une ressource limité sur cette planète; par chance la fine atmosphère de cette dernière nous permet d'utiliser un "+ANSI_YELLOW_BRIGHT+"Panneau Stellaire (☼)"+ANSI_RESET+" à son plein potentiel.",
                        "Ces panneaux etants composés de cellules à couches minces (CIGS) le cuivre sous sa forme Cu⁺ joue un rôle primordial dans les échanges énergétiques qui se déroulent alors.",
                        "Le carbone est intégré les cellules solaires pour améliorer la capture de la lumière et la conductivité électrique. Il réduit également les pertes de chaleur, augmentant ainsi le rendement énergétique."},

                        {"Enfin, la "+ANSI_YELLOW+"Centrale Nucléaire (☢)"+ANSI_RESET+"! La source d'une quantié d'énergie aussi massive pour la colonie.",
                        "Cette centrale a utilise l'uranium comme combustible et créé de l'énergie par la fission de ce dernier.",
                        "Le fer permet de construire une structure solide et blindée pour contenir les radiations.",
                        "L'eau joue un rôle fondamental: elle modère les neutrons produits lors de la fission, permettant ainsi de contrôler la réaction en chaîne,",
                        "elle refroidit également le réacteur et transporte la chaleur produite vers les turbines pour produire de l'électricité,",
                        "enfin, en raison de sa densité et de sa capacité à absorber les radiations, elle sert de barrière de protection contre les radiations."},

                        {"Les ressources extraites par le "+ANSI_BLUE+"Puits de Forage (⍒)"+ANSI_RESET+" sont la base de toute construction sur cette planète.",
                        "Les tiges de forage, utilisées pour transmettre le mouvement de rotation et la force de perçage au trépan, sont faites de fer en raison de sa résistance à l'usure et de sa solidité",
                        "Le cuivre est utilisé pour la mise au point des puissants moteurs électriques qui permettent de faire tourner les tiges de forage.",}};
}