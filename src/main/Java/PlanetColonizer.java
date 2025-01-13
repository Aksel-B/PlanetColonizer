import extensions.*;

class PlanetColonizer extends Program{

//-------------------------------------------------------------------FONCTIONS POUR TOUT-------------------------------------------------------------------------------------------------------
    
    // Fonction pour trouver le maximum entre deux entiers
    int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    // Fonction pour trouver le minimum entre deux entiers
    int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    // Fonction pour formater une caractéristique
    String formatCharacteristic(String name, int maxLength) {
        return name + repeatChar(' ', maxLength - length(name));
    }

    // Fonction pour répéter un caractère un certain nombre de fois
    String repeatChar(char ch, int count) {
        String result = "";
        for (int i = 0; i < count; i++) {
            result += ch;
        }
        return result;
    }

    int findLastIndex(String[] tab){
        int id=0;
        while (id<length(tab) && !equals(tab[id],"")){
            id++;
        }
        return id;
    }

    boolean TerrainTabIsEmpty(Terrain[] tab){
        boolean isEmpty=false;
        int nullCmpt=0;
        int id=0;
        while(id<length(tab)){
            if(tab[id]==null){
                nullCmpt++;
            }
            id++;
        }
        if (nullCmpt==length(tab)){
            isEmpty=true;
        }
        return isEmpty;
    }

    void tabEmptier(String[] tab){
        for (int i=0;i<length(tab);i++){
            tab[i]="";
        }
    }

    boolean StringTabIsEmpty(String[] tab){
        boolean isEmpty=false;
        int nullCmpt=0;
        int id=0;
        while(id<length(tab)){
            if(equals(tab[id],"")){
                nullCmpt++;
            }
            id++;
        }
        if (nullCmpt==length(tab)){
            isEmpty=true;
        }
        return isEmpty;
    }

    // Convertit un entier en caractère (utile pour l'affichage de la carte)
    char IntToChar(int n) {
        String str = "";
        return charAt(str += n, 0); // Transforme l'entier en chaîne, puis retourne le premier caractère
    }

    boolean stringToBoolean(String bool){
        if (equals(bool,"true")){
            return true;
        }
        return false;
    }

        // Permet à l'utilisateur de saisir un numéro de ligne valide
    int saisirLigne (int nombreLignes){
        int ligne;
        boolean valide=true;
        do {
            ligne = readIntSecurise(ANSI_BOLD + "Saisissez une ligne" + ANSI_RESET + "   (1,2,3,...) : "); // Lire un entier pour la ligne
            if (ligne < 1 || ligne > nombreLignes-1){
                valide=false;
                println(ANSI_PURPLE+"La valeur n'est pas dans l'intervalle !"+ANSI_RESET);
            }
        } while (valide==false); // Valide uniquement si dans l'intervalle
        return ligne;
    }


    int saisirColonne(int nombreColonne) {
        int colonne;
        do {
            // Utiliser la nouvelle méthode sécurisée
            char caractere = readCharMajSecurise(ANSI_BOLD + "Saisissez une colonne" + ANSI_RESET + " (A,B,C,...) : ");
            
            // Convertir le caractère en numéro de colonne
            colonne = caractere - 'A' + 1;
        } while (colonne < 1 || colonne > nombreColonne);
        
        return colonne; // Pour correspondre à l'indexation du tableau
    }

    String spaceEraser(String txt){
        String output="";
        for(int i=0;i<length(txt);i++){
            char car=charAt(txt,i);
            if(car!=' '){
                output+=car;
            }
        }
        return output;
    }

    String readStringSecurise(String prompt) {
        String input;
        boolean inputValide = false;
        
        while (!inputValide) {
            try {
                // Afficher le message de prompt
                print(prompt);
                
                // Lire l'entrée utilisateur
                input = readString();
                
                // Vérifier que l'entrée n'est pas nulle
                if (input == null) {
                    println(ANSI_RED+"Erreur : La saisie ne peut pas être nulle. Veuillez réessayer."+ANSI_RESET);
                    continue;
                }
                
                // Vérifier la longueur de l'entrée après suppression des espaces
                input=spaceEraser(input);
                
                // Vérifier que l'entrée n'est pas vide ou composée uniquement d'espaces
                if (length(input) == 0) {
                    println(ANSI_RED+"Erreur : La saisie ne peut pas être vide ou composée uniquement d'espaces. Veuillez réessayer."+ANSI_RESET);
                    continue;
                }
                
                inputValide = true;
                return input;
                
            } catch (Exception e) {
                // Gestion des exceptions inattendues
                println(ANSI_RED+"Une erreur est survenue lors de la saisie. Veuillez réessayer."+ANSI_RESET);
            }
        }
        
        // Cette ligne ne devrait jamais être atteinte
        return "";
    }

    int readIntSecurise(String prompt) {
        while (true) {
            try {
                // Utiliser readStringSecurise pour obtenir une entrée valide
                String input = readStringSecurise(prompt);
                
                // Convertir la chaîne en entier
                int valeur = stringToInt(input);
                
                return valeur;
                
            } catch (Exception e) {
                println(ANSI_RED+"Erreur : Veuillez saisir un nombre entier valide."+ANSI_RESET);
            }
        }
    }

    char readCharSecurise(String prompt) {
        while (true) {
            try {
                // Afficher le message de prompt
                print(prompt);
                
                // Lire l'entrée utilisateur
                String input = readString();
                
                // Vérifier que l'entrée n'est pas nulle
                if (input == null) {
                    println(ANSI_RED+"Erreur : La saisie ne peut pas être nulle. Veuillez réessayer."+ANSI_RESET);
                    continue;
                }
                
                // Vérifier que l'entrée est exactement un caractère
                if (length(input) != 1) {
                    println(ANSI_RED+"Erreur : Veuillez saisir exactement un caractère."+ANSI_RESET);
                    continue;
                }
                
                // Retourner le premier (et seul) caractère de l'entrée
                return charAt(input, 0);
                
            } catch (Exception e) {
                // Gestion des exceptions inattendues
                println(ANSI_RED+"Une erreur est survenue lors de la saisie. Veuillez réessayer."+ANSI_RESET);
            }
        }
    }

    char readCharMajSecurise(String prompt) {
        while (true) {
            // Utiliser la méthode de lecture sécurisée
            char caractere = readCharSecurise(prompt);
            
            // Convertir en majuscule manuellement
            if (caractere >= 'a' && caractere <= 'z') {
                caractere -= 32;
            }
            
            // Vérifier si le caractère est une lettre
            if (!((caractere >= 'A' && caractere <= 'Z'))) {
                println(ANSI_RED + ANSI_BOLD + "Erreur : Veuillez saisir une lettre." + ANSI_RESET);
                continue;
            }
            
            return caractere;
        }
    }

//-----------------------------------------TEST-------------------------------------------------------------------------------------------

    void testMax() {
        assertEquals(5, max(5, 3)); // Vérifie que max(5, 3) retourne 5
        assertEquals(5, max(3, 5)); // Vérifie que max(3, 5) retourne 5
        assertEquals(-1, max(-1, -5)); // Vérifie que max(-1, -5) retourne -1
    }

    void testMin() {
        assertEquals(3, min(5, 3)); // Vérifie que min(5, 3) retourne 3
        assertEquals(3, min(3, 5)); // Vérifie que min(3, 5) retourne 3
        assertEquals(-5, min(-1, -5)); // Vérifie que min(-1, -5) retourne -5
    }

    void testFormatCharacteristic() {
        assertEquals("Test      ", formatCharacteristic("Test", 10)); // Ajout d'espaces jusqu'à 10 caractères
        assertEquals("LongText", formatCharacteristic("LongText", 5)); // Ne coupe pas les chaînes déjà longues
    }

    void testRepeatChar() {
        assertEquals("*****", repeatChar('*', 5)); // Répète le caractère *
        assertEquals("", repeatChar('a', 0)); // Ne répète rien pour une quantité de 0
    }

    void testFindLastIndex() {
        String[] tab1 = {"a", "b", "", ""};
        String[] tab2 = {"a", "b", "c"};
        assertEquals(2, findLastIndex(tab1)); // Dernier index avant les chaînes vides
        assertEquals(3, findLastIndex(tab2)); // Pas de chaînes vides, longueur totale
    }

    void testTerrainTabIsEmpty() {
        Terrain[] emptyTab = {null, null, null};
        Terrain[] nonEmptyTab = {new Terrain(), null};
        assertTrue(TerrainTabIsEmpty(emptyTab)); // Vérifie que le tableau vide retourne true
        assertFalse(TerrainTabIsEmpty(nonEmptyTab)); // Vérifie qu'un tableau non vide retourne false
    }

    void testTabEmptier() {
        String[] tab = {"a", "b", "c"};
        tabEmptier(tab);
        assertTrue(StringTabIsEmpty(tab)); // Après vidage, le tableau doit être vide
    }

    void testStringTabIsEmpty() {
        String[] emptyTab = {"", "", ""};
        String[] nonEmptyTab = {"a", "", ""};
        assertTrue(StringTabIsEmpty(emptyTab)); // Doit être vide
        assertFalse(StringTabIsEmpty(nonEmptyTab)); // Ne doit pas être vide
    }

    void testIntToChar() {
        assertEquals('1', IntToChar(1)); // Convertit 1 en caractère '1'
        assertEquals('9', IntToChar(9)); // Convertit 9 en caractère '9'
    }

    void testStringToBoolean() {
        assertTrue(stringToBoolean("true")); // "true" doit donner true
        assertFalse(stringToBoolean("false")); // "false" doit donner false
    }

    void testSpaceEraser() {
        assertEquals("abc", spaceEraser(" a b c ")); // Retire les espaces
        assertEquals("", spaceEraser("")); // Une chaîne vide reste vide
    }


//---------------------------------SAUVEGARDE/Chargement----------------------------------------------------------------------------------

    void sauvegarderJeu(EtatJeu etat, String nomFichier) {
        String[][] donneesCSV = new String[compterLignes(etat) + 10][6]; // Toujours 6 colonnes
        int index = 0;

        // 1. Section Colons
        donneesCSV[index++] = new String[] {"#SECTION", "COLONS", "", "", "", ""};
        donneesCSV[index++] = new String[] {"id", "age", "sante", "satisfaction", "energie", "anneesDerniereNaissance"};
        int morts = 0;
        for (int i = 0; i < etat.colons.length; i++) {
            if (etat.colons[i] != null) {
                donneesCSV[index++] = new String[] {
                    "" + etat.colons[i].id,
                    "" + etat.colons[i].age,
                    "" + etat.colons[i].sante,
                    "" + etat.colons[i].satisfaction,
                    "" + etat.colons[i].energie,
                    "" + etat.colons[i].anneesDerniereNaissance
                };
            } else {
                morts++;
            }
        }

        int vivants = compterColonsVivants(etat.colons);
        donneesCSV[index++] = new String[] {"Colons vivants", "" + vivants, "", "", "", ""};
        donneesCSV[index++] = new String[] {"Colons morts", "" + morts, "", "", "", ""};

        // 2. Section Etat du Jeu
        donneesCSV[index++] = new String[] {"#SECTION", "ETAT_JEU", "", "", "", ""};
        donneesCSV[index++] = new String[] {"nomJeu", "score", "tour", "", "", ""};
        donneesCSV[index++] = new String[] {etat.nom, "" + etat.score, "" + etat.tour, "", "", ""};

        // 3. Section Gestion
        donneesCSV[index++] = new String[] {"#SECTION", "GESTION", "", "", "", ""};
        donneesCSV[index++] = new String[] {
            "capaciteTotalPop", "capaciteEntrepot", "vaisseauPlace", "centreDeCommunicationPlace", "nombreVivants", ""
        };
        donneesCSV[index++] = new String[] {
            "" + etat.gestion.capaciteTotalePop,
            "" + etat.gestion.capaciteEntrepot,
            "" + etat.gestion.vaisseauPlace,
            "" + etat.gestion.centreDeCommunicationPlace,
            "" + etat.gestion.nombreVivants,
            ""
        };

        // 4. Section Carte
        donneesCSV[index++] = new String[] {"#SECTION", "CASES_CARTE", "", "", "", ""};
        donneesCSV[index++] = new String[] {"symbole", "quantiteRestante", "ressourceActuelle", "ressourceCaseInit", "exploitee", ""};
        for (CaseCarte[] ligne : etat.planete.carte) {
            for (CaseCarte caseCarte : ligne) {
                donneesCSV[index++] = new String[] {
                    caseCarte.symbole,
                    "" + caseCarte.quantiteRestante,
                    (caseCarte.ressourceActuelle != null) ? caseCarte.ressourceActuelle.nom : "null",
                    (caseCarte.ressourceCaseInit != null) ? caseCarte.ressourceCaseInit.nom : "null",
                    "" + caseCarte.exploitee,
                    ""
                };
            }
        }

        // Sauvegarder dans un fichier CSV
        try {
            saveCSV(donneesCSV, "../save/" + nomFichier);
            println(ANSI_BOLD + "Jeu sauvegardé avec succès" + ANSI_RESET + " dans " + nomFichier);
        } catch (Exception e) {
            println("Erreur lors de la sauvegarde du jeu : " + e.getMessage());
        }
    }

    // Fonction pour compter toutes les lignes nécessaires
    int compterLignes(EtatJeu etat) {
        int lignesColons = etat.colons.length; // Nombre total de colons
        int lignesCarte = etat.planete.carte.length * etat.planete.carte[0].length; // Total des cases de la carte
        return 2 + lignesColons + 2 + 1 + 2 + lignesCarte; // Nombre de lignes nécessaires
    }


     EtatJeu chargerJeu(String nomFichier) {
        EtatJeu etatCharge = new EtatJeu();

        try {
            CSVFile fichierSauvegarde = loadCSV("../save/" + nomFichier);

            // Variables temporaires
            int nombreColons = 0;
            int nombreRessources = 0;
            int largeurCarte = 0;
            int longueurCarte = 0;

            // Phase 1 : Parcourir une première fois pour compter les éléments
            for (int ligne = 0; ligne < rowCount(fichierSauvegarde); ligne++) {
                String cellule0 = getCell(fichierSauvegarde, ligne, 0);

                if (equals(cellule0, "Ressources")) {
                    // Compter le nombre de ressources
                    int i = ligne + 2;
                    while (i < rowCount(fichierSauvegarde) && !equals(getCell(fichierSauvegarde, i, 0), "Planete")) {
                        nombreRessources++;
                        i++;
                    }
                }

                if (equals(cellule0, "Colons")) {
                    // Compter le nombre de colons
                    int i = ligne + 2;
                    while (i < rowCount(fichierSauvegarde) && i < ligne + 100) {
                        nombreColons++;
                        i++;
                    }
                }
            }

            // Initialiser les tableaux avec les tailles détectées
            etatCharge.ressources = new Terrain[nombreRessources];
            etatCharge.colons = new Colon[nombreColons];

            // Phase 2 : Remplir les données
            for (int ligne = 0; ligne < rowCount(fichierSauvegarde); ligne++) {
                String cellule0 = getCell(fichierSauvegarde, ligne, 0);

                // Charger les informations de base
                if (equals(cellule0, "Tour")) {
                    etatCharge.tour = stringToInt(getCell(fichierSauvegarde, ligne, 1));
                }

                if (equals(cellule0, "Score")) {
                    etatCharge.score = stringToDouble(getCell(fichierSauvegarde, ligne, 1));
                }

                if (equals(cellule0, "NomFichier")) {
                    etatCharge.nom = getCell(fichierSauvegarde, ligne, 1);
                }

                // Charger les ressources
                if (equals(cellule0, "Ressources")) {
                    for (int j = 0; j < nombreRessources; j++) {
                        String nom = getCell(fichierSauvegarde, ligne + 2 + j, 0);
                        String symbole = getCell(fichierSauvegarde, ligne + 2 + j, 1);
                        double probabilite = stringToDouble(getCell(fichierSauvegarde, ligne + 2 + j, 2));
                        int quantite = stringToInt(getCell(fichierSauvegarde, ligne + 2 + j, 3));

                        etatCharge.ressources[j] = newRessource(nom, symbole, probabilite, quantite);
                    }
                }

                // Charger les paramètres de Gestion
                if (equals(cellule0, "Gestion")) {
                    etatCharge.gestion.nombreVivants = stringToInt(getCell(fichierSauvegarde, ligne + 1, 0));
                    etatCharge.gestion.capaciteTotalePop = stringToInt(getCell(fichierSauvegarde, ligne + 1, 1));
                    etatCharge.gestion.capaciteEntrepot = stringToInt(getCell(fichierSauvegarde, ligne + 1, 2));
                    etatCharge.gestion.vaisseauPlace = stringToInt(getCell(fichierSauvegarde, ligne + 1, 3));
                    etatCharge.gestion.centreDeCommunicationPlace = stringToInt(getCell(fichierSauvegarde, ligne + 1, 4));
                    etatCharge.gestion.quantiteElecTourPrecedent[0] = stringToInt(getCell(fichierSauvegarde, ligne + 1, 5));
                }

                // Charger les colons
                if (equals(cellule0, "Colons")) {
                    for (int j = 0; j < nombreColons; j++) {
                        Colon colon = new Colon();
                        colon.id = stringToInt(getCell(fichierSauvegarde, ligne + 2 + j, 0));
                        colon.age = stringToInt(getCell(fichierSauvegarde, ligne + 2 + j, 1));
                        colon.sante = stringToDouble(getCell(fichierSauvegarde, ligne + 2 + j, 2));
                        colon.satisfaction = stringToDouble(getCell(fichierSauvegarde, ligne + 2 + j, 3));
                        colon.energie = stringToDouble(getCell(fichierSauvegarde, ligne + 2 + j, 4));
                        colon.anneesDerniereNaissance = stringToInt(getCell(fichierSauvegarde, ligne + 2 + j, 5));
                        etatCharge.colons[j] = colon;
                    }
                }

                // Charger la planète
                if (equals(cellule0, "Planete")) {
                    largeurCarte = stringToInt(getCell(fichierSauvegarde, ligne + 2, 1));
                    longueurCarte = stringToInt(getCell(fichierSauvegarde, ligne + 2, 2));
                    etatCharge.planete = newPlanete(etatCharge.ressources, longueurCarte, largeurCarte);
                }

                // Charger le contenu de la carte
                if (equals(cellule0, "Contenu de la Carte")) {
                    int i = ligne + 1;
                    while (i < rowCount(fichierSauvegarde) && !equals(getCell(fichierSauvegarde, i, 0), "Colons")) {
                        int l = stringToInt(getCell(fichierSauvegarde, i, 0));
                        int c = stringToInt(getCell(fichierSauvegarde, i, 1));
                        String symbole = getCell(fichierSauvegarde, i, 2);
                        int quantiteRestante = stringToInt(getCell(fichierSauvegarde, i, 3));
                        boolean exploitee = stringToBoolean(getCell(fichierSauvegarde, i, 4));

                        String ressourceActuelleNom = getCell(fichierSauvegarde, i, 5);
                        String ressourceCaseInitNom = getCell(fichierSauvegarde, i, 6);

                        Terrain ressourceActuelle = null;
                        for (Terrain terrain : etatCharge.ressources) {
                            if (equals(terrain.nom, ressourceActuelleNom)) {
                                ressourceActuelle = terrain;
                                break;
                            }
                        }

                        Terrain ressourceCaseInit = null;
                        for (Terrain terrain : etatCharge.ressources) {
                            if (equals(terrain.nom, ressourceCaseInitNom)) {
                                ressourceCaseInit = terrain;
                                break;
                            }
                        }

                        etatCharge.planete.carte[l][c] = newCaseCarte(ressourceActuelle, quantiteRestante, ressourceCaseInit, exploitee);
                        i++;
                    }
                }
            }

            println("Jeu chargé avec succès depuis " + nomFichier);
            return etatCharge;

        } catch (Exception e) {
            println("Erreur lors du chargement du jeu : " + e.getMessage());
            return null;
        }
    }

//---------------------------------RESSOURCES-------------------------------------------------------------------------------------

    final Terrain[] RESSOURCESINIT=new Terrain[]{ 
                        //Nom,Symbole,probaApparition,quantite
            newRessource("Terre", " + ", 1.0, 0), // Ressource par défaut
            newRessource("Terre", " + ", 0.200, 0),
            newRessource("Fer", "Fe ", 0.100, 100), 
            newRessource("Cuivre", "Cu ", 0.075, 50),
            newRessource("Carbone", " C ", 0.050, 35),
            newRessource("Sulfure", " S ", 0.025, 10),
            newRessource("Plutonium", "Pu ", 0.0005, 5), // Ressource rare et polluante
            newRessource("💧 Eau", "H2O", 1.0, 100), // Ressources essentielles
            newRessource("💨 Air", "O2", 1.0, 50), 
            newRessource("🍎 Nourriture", "Rations", 1.0, 50),

            newRessource("⚡ Electricité", "Elec",1.0,100)
        };

    // Fonction pour créer une nouvelle ressource avec des attributs spécifiques
    Terrain newRessource(String nom, String symbole, double probaApparition, int quantite) {
        Terrain r = new Terrain();
        r.nom = nom; // Nom de la ressource
        r.symbole = symbole; // Symbole représentant la ressource
        r.probaApparition = probaApparition; // Probabilité d'apparition de la ressource
        r.quantite = quantite; // Quantité initiale de la ressource
        
        return r; // Retourne une instance de la ressource
    }

    void ressourceEmptyVerif(EtatJeu etat){
        for(int i=0;i<length(etat.events.ressourceEstEpuiseeSTR);i++){
            if(etat.ressources[i].quantite>0){
                etat.events.ressourceEstEpuiseeSTR[i]="";
            }
        }
    }

    String getResourceColor(String ressource,CaseCarte caseC) {
        if(caseC.ressourceActuelle.fonctionne[0]==true){
            switch (ressource) {
                // Ressources initiales
                case " + ":
                    return ANSI_WHITE + ANSI_DARK_BG;
                case "Fe ":
                    return ANSI_RED + ANSI_DARK_BG;
                case "Cu ":
                    return ANSI_ORANGE + ANSI_DARK_BG;
                case " C ":
                    return ANSI_GREEN + ANSI_DARK_BG;
                case " S ":
                    return ANSI_BLUE + ANSI_DARK_BG;
                case "Pu ":
                    return ANSI_PURPLE + ANSI_DARK_BG;
                
                // Bâtiments
                case " V ":  // Vaisseau
                    return ANSI_GREENKINDOF + ANSI_DARK_BG;
                case " ⌂ ":  // Dortoir
                    return ANSI_BROWN + ANSI_DARK_BG;
                case " ⌧ ":  // Entrepôt
                    return ANSI_GRAY + ANSI_DARK_BG;
                case " ☤ ":  // Centre de Communication Terrien
                    return ANSI_MAGENTA + ANSI_DARK_BG;
                case " ◈ ":  // Cinema
                    return ANSI_YELLOW + ANSI_DARK_BG;
                case " ⌯ ":  // Capteur d'Humidité
                    return ANSI_BLUE_LIGHT + ANSI_DARK_BG;
                case " ✲ ":  // Ferme hydroponique
                    return ANSI_GREEN_LIGHT + ANSI_DARK_BG;
                case " ≎ ":  // Recycleur d'Air
                    return ANSI_CYAN_LIGHT + ANSI_DARK_BG;
                case " ☼ ":  // Panneau Stellaire
                    return ANSI_YELLOW_BRIGHT + ANSI_DARK_BG;
                case " ☢ ":  // Centrale nucléaire
                    return ANSI_YELLOW + ANSI_DARK_BG;
                case " ⍒ ":  // Puit de Forage
                    return getResourceColor(caseC.ressourceCaseInit.symbole,caseC);
                
                default:
                    return ANSI_RESET;
            }
        }else{
            switch (ressource) {
                // Bâtiments
                case " V ":  // Vaisseau
                    return ANSI_RED_BRIGHT + ANSI_DARK_BG;
                case " ⌂ ":  // Dortoir
                    return ANSI_RED_BRIGHT + ANSI_DARK_BG;
                case " ⌧ ":  // Entrepôt
                    return ANSI_RED_BRIGHT + ANSI_DARK_BG;
                case " ☤ ":  // Centre de Communication Terrien
                    return ANSI_RED_BRIGHT + ANSI_DARK_BG;
                case " ◈ ":  // Cinema
                    return ANSI_RED_BRIGHT + ANSI_DARK_BG;
                case " ⌯ ":  // Capteur d'Humidité
                    return ANSI_RED_BRIGHT + ANSI_DARK_BG;
                case " ✲ ":  // Ferme hydroponique
                    return ANSI_RED_BRIGHT + ANSI_DARK_BG;
                case " ≎ ":  // Recycleur d'Air
                    return ANSI_RED_BRIGHT + ANSI_DARK_BG;
                case " ☼ ":  // Panneau Stellaire
                    return ANSI_RED_BRIGHT + ANSI_DARK_BG;
                case " ☢ ":  // Centrale nucléaire
                    return ANSI_RED_BRIGHT + ANSI_DARK_BG;
                case " ⍒ ":  // Puit de Forage
                    return ANSI_RED_BRIGHT + ANSI_DARK_BG;
                
                default:
                    return ANSI_RESET;
            }
        }
    }

    final String ANSI_MAGENTA = "\033[35m";
    final String ANSI_ORANGE = "\033[38;2;255;165;0m";
    final String ANSI_GREENKINDOF = "\033[38;5;42m";
    final String ANSI_DARK_BG = "\033[48;2;52;40;61m";
    final String ANSI_BROWN = "\033[38;2;139;69;19m";
    final String ANSI_GRAY = "\033[38;2;128;128;128m";
    final String ANSI_BLUE_LIGHT = "\033[38;2;173;216;230m";
    final String ANSI_GREEN_LIGHT = "\033[38;2;144;238;144m";
    final String ANSI_CYAN_LIGHT = "\033[38;2;224;255;255m";
    final String ANSI_YELLOW_BRIGHT = "\033[38;2;255;255;0m";
    final String ANSI_YELLOW =  "\033[38;5;11m";
    final String ANSI_ORANGE_DARK = "\033[38;2;255;140;0m";
    final String ANSI_RED_BRIGHT = "\033[38;2;255;0;0m";

//----------------------------------------BATIMENTS------------------------------------------------------------------------------------------------------------------------
    
    final Terrain[] LISTEBATIMENTSPOSSIBLES=new Terrain[]{
                  //Recette ResNecessaire, String nom,String symbole,,double pollutionGeneree,int[] ressourcesConso, int[] quantiteResConso,int[] ressourcesGeneree, int[] quantiteResGeneree
        newBatiment(newRecette(),"Vaisseau"," V ",0.0001,new int[]{10},new int[]{100},new int[]{10},new int[]{250}),

        newBatiment(newRecette(new int[]{2,3},new int[]{5,10}),"Dortoir"," ⌂ ",0.0005,new int[]{10},new int[]{10},new int[]{-1},new int[]{0}),
        newBatiment(newRecette(new int[]{2},new int[]{15}),"Entrepôt"," ⌧ ",0.0002,new int[]{10},new int[]{5},new int[]{-1},new int[]{0}),
        newBatiment(newRecette(new int[]{2,3,4},new int[]{125,35,30}),"Centre de Communication Terrien"," ☤ ",0.0007,new int[]{10},new int[]{35},new int[]{-1},new int[]{0}),
        newBatiment(newRecette(new int[]{2,3,4},new int[]{75,25,10}),"Cinema"," ◈ ",0.0007,new int[]{10},new int[]{50},new int[]{-1},new int[]{0}),
        
        newBatiment(newRecette(new int[]{2,3,5},new int[]{5,10,5}),"Capteur d'Humidité"," ⌯ ",0.0001,new int[]{10}, new int[]{20},new int[]{7}, new int[]{10}),
        newBatiment(newRecette(new int[]{2,4,7},new int[]{5,5,5}),"Ferme hydroponique"," ✲ ",-0.0005,new int[]{7,10},new int[]{10,20},new int[]{8,9},new int[]{5,10}), //10 rations 5 O2
        newBatiment(newRecette(new int[]{3,7},new int[]{5,5}),"Recycleur d'Air"," ≎ ",0.0001,new int[]{10}, new int[]{20},new int[]{8}, new int[]{10}),

        newBatiment(newRecette(new int[]{3,5},new int[]{5,10}),"Panneau Stellaire"," ☼ ",0.0001,new int[]{10}, new int[]{0},new int[]{10},new int[]{20}),
        newBatiment(newRecette(new int[]{2,6,7},new int[]{100,5,30}),"Centrale nucléaire"," ☢ ",0.0001,new int[]{6,7,10},new int[]{2,10,100},new int[]{10},new int[]{400}),//Conso 5 Pu 10 H2O

        newBatiment(newRecette(new int[]{2,3,4},new int[]{10,5,5}),"Puit de Forage"," ⍒ ",0.005,new int[]{0,10},new int[]{0,25},new int[1],new int[1]) //Prod/Conso variable selon la ressource
    };

    // Si on veut faire des ressources plus complexes et modifier les recettes en conséquence
    //newBatiment(newRecette(new Terrain[]{RESSOURCESINIT[2],RESSOURCESINIT[3],RESSOURCESINIT[4]},new int[]{75,25,10}),"Usine de Transformation"," ⌬ ",0.001,25,new int[1],new int[1]), //Prod/Conso variable selon la ressource


    Recette newRecette(){
        Recette r=new Recette();
        r.coutDeConstruction=new int[0];
        return r;
    }
    
    Recette newRecette(int[] ressources,int[] quantiteNecessaire){
        Recette r=new Recette ();
        r.coutDeConstruction=ressources;
        r.quantiteNecessaire=quantiteNecessaire;
        return r;
    }
    
    Terrain newBatiment(Recette ResNecessaire, String nom,String symbole,double pollutionGeneree,int[] ressourcesConso, int[] quantiteResConso,int[] ressourcesGeneree, int[] quantiteResGeneree){
        Terrain b=new Terrain();
        b.ResNecessaire=ResNecessaire;
        b.ressourcesGeneree=ressourcesGeneree;
        b.quantiteResGeneree=quantiteResGeneree;
        b.ressourcesConso=ressourcesConso;
        b.quantiteResConso=quantiteResConso;
        b.pollutionGeneree=pollutionGeneree;

        b.nom=nom;
        b.symbole=symbole;
        return b;
    }

    int placerBatiment(EtatJeu etat, Terrain[] LISTEBATIMENTSPOSSIBLES,int lig,int col,Terrain batiment){
        etat.gestion.posBat[countLastPos(etat.gestion.posBat)]=new int[]{lig,col};
        int id=0;
        while(id<length(LISTEBATIMENTSPOSSIBLES) && batiment!=LISTEBATIMENTSPOSSIBLES[id]){
            id++;
        }
        if(batiment==LISTEBATIMENTSPOSSIBLES[0]){// Vaisseau
            etat.planete.carte[lig][col]=newCaseCarte(batiment,-1,batiment,true); //-1 car ressource inépuisable
            etat.gestion.vaisseauPlace=1;
            return etat.gestion.vaisseauPlace;

        }else if(batiment==LISTEBATIMENTSPOSSIBLES[1]){ //Dortoirs
            etat.planete.carte[lig][col]=newCaseCarte(batiment,-1,batiment,true);
            etat.gestion.capaciteTotalePop+=15;
            return etat.gestion.capaciteTotalePop;

        }else if(batiment==LISTEBATIMENTSPOSSIBLES[2]){ //Entrepots
            etat.planete.carte[lig][col]=newCaseCarte(batiment,-1,batiment,true);
            etat.gestion.capaciteEntrepot+=150;
            return etat.gestion.capaciteEntrepot;

        }else if(batiment==LISTEBATIMENTSPOSSIBLES[3]){ //Centre de Communication Terrien
            etat.planete.carte[lig][col]=newCaseCarte(batiment,-1,batiment,true);
            etat.gestion.centreDeCommunicationPlace=1;
            return etat.gestion.centreDeCommunicationPlace;

        }else if(id < 10){
            etat.planete.carte[lig][col]=newCaseCarte(batiment,-1,batiment,true);
        }else{
            if(etat.planete.carte[lig][col].ressourceCaseInit.nom==etat.ressources[2].nom){ //Fer
                etat.planete.carte[lig][col]=newCaseCarte(batiment,500,etat.planete.carte[lig][col].ressourceCaseInit,true);
                etat.planete.carte[lig][col].ressourceActuelle.ressourcesConso[0]=2;
                etat.planete.carte[lig][col].ressourceActuelle.quantiteResConso[0]=10;
                etat.planete.carte[lig][col].ressourceActuelle.ressourcesGeneree[0]=2;
                etat.planete.carte[lig][col].ressourceActuelle.quantiteResGeneree[0]=10; 

            }else if(etat.planete.carte[lig][col].ressourceCaseInit.nom==etat.ressources[3].nom){ //Cuivre
                etat.planete.carte[lig][col]=newCaseCarte(batiment,500,etat.planete.carte[lig][col].ressourceCaseInit,true);
                etat.planete.carte[lig][col].ressourceActuelle.ressourcesConso[0]=3;
                etat.planete.carte[lig][col].ressourceActuelle.quantiteResConso[0]=12;
                etat.planete.carte[lig][col].ressourceActuelle.ressourcesGeneree[0]=3;
                etat.planete.carte[lig][col].ressourceActuelle.quantiteResGeneree[0]=7;

            }else if(etat.planete.carte[lig][col].ressourceCaseInit.nom==etat.ressources[4].nom){ //Carbone
                etat.planete.carte[lig][col]=newCaseCarte(batiment,400,etat.planete.carte[lig][col].ressourceCaseInit,true);
                etat.planete.carte[lig][col].ressourceActuelle.ressourcesConso[0]=4;
                etat.planete.carte[lig][col].ressourceActuelle.quantiteResConso[0]=15;
                etat.planete.carte[lig][col].ressourceActuelle.ressourcesGeneree[0]=4;
                etat.planete.carte[lig][col].ressourceActuelle.quantiteResGeneree[0]=5;

            }else if(etat.planete.carte[lig][col].ressourceCaseInit.nom==etat.ressources[5].nom){ //Sulfure
                etat.planete.carte[lig][col]=newCaseCarte(batiment,300,etat.planete.carte[lig][col].ressourceCaseInit,true);
                etat.planete.carte[lig][col].ressourceActuelle.ressourcesConso[0]=5;
                etat.planete.carte[lig][col].ressourceActuelle.quantiteResConso[0]=15;
                etat.planete.carte[lig][col].ressourceActuelle.ressourcesGeneree[0]=5;
                etat.planete.carte[lig][col].ressourceActuelle.quantiteResGeneree[0]=5;

            }else if(etat.planete.carte[lig][col].ressourceCaseInit.nom==etat.ressources[6].nom){ //Plutonium
                etat.planete.carte[lig][col]=newCaseCarte(batiment,200,etat.planete.carte[lig][col].ressourceCaseInit,true);
                etat.planete.carte[lig][col].ressourceActuelle.ressourcesConso[0]=6;
                etat.planete.carte[lig][col].ressourceActuelle.quantiteResConso[0]=15;
                etat.planete.carte[lig][col].ressourceActuelle.ressourcesGeneree[0]=6;
                etat.planete.carte[lig][col].ressourceActuelle.quantiteResGeneree[0]=1;
            }
        }
        for (int i=0;i<length(batiment.ResNecessaire.coutDeConstruction);i++){
            int idRes=batiment.ResNecessaire.coutDeConstruction[i];
            etat.ressources[idRes].quantite-=batiment.ResNecessaire.quantiteNecessaire[i];
            etat.gestion.variationRessources[idRes]-=batiment.ResNecessaire.quantiteNecessaire[i];
        }
        return id;
    }

    // Repère la dernière position enregistrée d'un batiment
    int countLastPos(int[][] posBat){      
        int id=0;
        while(id < length(posBat) && posBat[id][0]!=0){
            id++;
        }
        return id;
    }

    boolean batimentPosable(EtatJeu etat, Terrain[] LISTEBATIMENTSPOSSIBLES,int lig, int col, Terrain batiment){
        boolean posableTerrain=true;
        boolean posableRessource=true;

        if((etat.planete.carte[lig][col].ressourceCaseInit==etat.ressources[0] || etat.planete.carte[lig][col].ressourceCaseInit==etat.ressources[1]) && equals(batiment.nom,LISTEBATIMENTSPOSSIBLES[10].nom)){
            posableTerrain=false;
        }
        int id=0;
        while(id<length(etat.ressources) && etat.planete.carte[lig][col].ressourceCaseInit!=etat.ressources[id]){
            id++;
        }
        if(!equals(batiment.nom,LISTEBATIMENTSPOSSIBLES[10].nom) && (id>=2 && id<7)){
            posableTerrain=false;
        }
        if((batiment==LISTEBATIMENTSPOSSIBLES[3] && etat.gestion.centreDeCommunicationPlace==1) || (batiment==LISTEBATIMENTSPOSSIBLES[0] && etat.gestion.vaisseauPlace==1)){
            posableTerrain=false;
        }
        id=0;
        while(id<length(batiment.ResNecessaire.coutDeConstruction) && posableRessource){
            int idRes=batiment.ResNecessaire.coutDeConstruction[id];
            if(batiment.ResNecessaire.quantiteNecessaire[id]>etat.ressources[idRes].quantite){
                posableRessource=false;
            }
            id++;
        }

        return (posableTerrain==true && posableRessource==true && etat.planete.carte[lig][col].exploitee==false) ? true:false;
    }

    int calcCapacitéEntrepôt(EtatJeu etat){
        int capaciteEntreposee=0;
        for(int i=0;i<length(etat.ressources)-1;i++){       // L'electricite n'est pas comprise dans le stockage
            capaciteEntreposee+=etat.ressources[i].quantite;
        }
        return capaciteEntreposee;
    }

    // Nouvelle méthode pour vérifier si on peut ajouter des ressources
    boolean peutAjouterDansEntrepot(EtatJeu etat, int quantite) {
        int capaciteActuelle = calcCapacitéEntrepôt(etat);
        return (capaciteActuelle + quantite) <= etat.gestion.capaciteEntrepot;
    }

    // Modification de la méthode verifCapacitéEntrepot existante
    void verifCapacitéEntrepot(EtatJeu etat) {
        int capaciteActuelle = calcCapacitéEntrepôt(etat);
        
        // Vérifie si on dépasse la capacité
        if (capaciteActuelle > etat.gestion.capaciteEntrepot) {
            // Ramène les ressources au maximum autorisé
            int excedent = capaciteActuelle - etat.gestion.capaciteEntrepot;
            // Réduit les ressources les plus récemment ajoutées pour respecter la limite
            for (int i = length(etat.ressources) - 2; i >= 0 && excedent > 0; i--) {
                if (etat.ressources[i].quantite > 0) {
                    int reduction = min(excedent, etat.ressources[i].quantite);
                    etat.ressources[i].quantite -= reduction;
                    excedent -= reduction;
                }
            }
            etat.events.entrepotPlein[0] = true;
        } else if (capaciteActuelle == etat.gestion.capaciteEntrepot) {
            etat.events.entrepotPlein[0] = true;
        } else {
            etat.events.entrepotPlein[0] = false;
        }
    }

    void mettreAJourBatiment(EtatJeu etat, Terrain[] LISTEBATIMENTSPOSSIBLES) {
        int capaciteEntreposee = calcCapacitéEntrepôt(etat);
        for (int i = 0; i < countLastPos(etat.gestion.posBat); i++) {
            CaseCarte batiment = etat.planete.carte[etat.gestion.posBat[i][0]][etat.gestion.posBat[i][1]];

            if (batiment.ressourceActuelle.fonctionne[0] == false) {
                if (etat.events.entrepotPlein[0] == false) {
                    int peutConsommerRes = 0;
                    int cmpt = 0;
                    int idRes = 0;
                    while (cmpt < length(batiment.ressourceActuelle.ressourcesConso)) {
                        idRes = batiment.ressourceActuelle.ressourcesConso[cmpt];
                        if (((etat.ressources[idRes].quantite) - batiment.ressourceActuelle.quantiteResConso[cmpt]) >= 0) {
                            peutConsommerRes++;
                        }
                    }
                    if (peutConsommerRes == length(batiment.ressourceActuelle.ressourcesConso)) {
                        marcheArret(LISTEBATIMENTSPOSSIBLES, etat.planete.carte, etat.gestion.posBat[i][0], etat.gestion.posBat[i][1]);
                    }
                }
            }

            if (batiment.ressourceActuelle.fonctionne[0] == true) {
                if (equals(batiment.ressourceActuelle.nom, LISTEBATIMENTSPOSSIBLES[10].nom)) {
                    mettreAJourPuitDeForage(etat, LISTEBATIMENTSPOSSIBLES, etat.gestion.posBat[i][0], etat.gestion.posBat[i][1], capaciteEntreposee);
                } else {
                    int id = 0;
                    while (id < length(LISTEBATIMENTSPOSSIBLES) && !equals(batiment.ressourceActuelle.nom, LISTEBATIMENTSPOSSIBLES[id].nom)) {
                        id++;
                    }
                    if (batiment.ressourceActuelle.ressourcesGeneree[0] != -1) { // Si le batiment genere quelque chose
                        boolean peutConsommerRes = true;
                        int cmpt = 0;
                        int idRes = 0;
                        while (cmpt < length(batiment.ressourceActuelle.ressourcesConso) && peutConsommerRes != false) {
                            idRes = batiment.ressourceActuelle.ressourcesConso[cmpt];
                            if (((etat.ressources[idRes].quantite) - batiment.ressourceActuelle.quantiteResConso[cmpt]) < 0) {
                                peutConsommerRes = false;
                                marcheArret(LISTEBATIMENTSPOSSIBLES, etat.planete.carte, etat.gestion.posBat[i][0], etat.gestion.posBat[i][1]);
                                etat.events.ressourceEstEpuiseeSTR[idRes] = ANSI_RED + "La ressource: <" + etat.ressources[idRes].nom + "> n'est pas en quantité suffisante !" + ANSI_RESET;
                            }
                            cmpt++;
                        }
                        if (peutConsommerRes) {
                            etat.gestion.tabMoyennepollution[id] += batiment.ressourceActuelle.pollutionGeneree;

                            consommer(etat, etat.gestion.posBat[i][0], etat.gestion.posBat[i][1]);
                            generer(etat, LISTEBATIMENTSPOSSIBLES, etat.gestion.posBat[i][0], etat.gestion.posBat[i][1], capaciteEntreposee);
                            verifCapacitéEntrepot(etat);
                        }
                    }
                }
            }
        }
    }


    void mettreAJourPuitDeForage(EtatJeu etat, Terrain[] LISTEBATIMENTSPOSSIBLES, int lig, int col, int capaciteEntreposee) {
        CaseCarte batiment = etat.planete.carte[lig][col];
        int id = 0;
        while (id < length(etat.ressources) && batiment.ressourceCaseInit != etat.ressources[id]) {
            id++;
        }

    if(batiment.quantiteRestante <= 0) {
        marcheArret(LISTEBATIMENTSPOSSIBLES, etat.planete.carte, lig, col);
        String ligSTR = "";
        if (lig > 9) {
            ligSTR += lig;
        } else {
            ligSTR += " " + lig;
        }
        etat.events.filonEpuise[findLastIndex(etat.events.filonEpuise)] = ANSI_RED + "Le filon du secteur " + ligSTR + ":" + (char)(64+col) + " est épuisé !" + ANSI_RESET;

    } else if(!etat.events.entrepotPlein[0]) {
        // Vérifie si on peut ajouter les ressources
        if(peutAjouterDansEntrepot(etat, batiment.ressourceActuelle.quantiteResGeneree[0])) {
            // On peut ajouter toute la quantité
            batiment.quantiteRestante -= batiment.ressourceActuelle.quantiteResConso[0];
            etat.ressources[id].quantite += batiment.ressourceActuelle.quantiteResGeneree[0];
            
            etat.ressources[10].quantite -= batiment.ressourceActuelle.quantiteResConso[1];
            etat.gestion.variationRessources[10] -= batiment.ressourceActuelle.quantiteResConso[1];

            etat.gestion.variationRessources[id] += batiment.ressourceActuelle.quantiteResGeneree[0];
            etat.gestion.tabMoyennepollution[10] += batiment.ressourceActuelle.pollutionGeneree;
        } else {
            // Calcule l'espace restant et n'ajoute que ce qui peut rentrer
            int placeRestante = etat.gestion.capaciteEntrepot - calcCapacitéEntrepôt(etat);
            if(placeRestante > 0) {
                batiment.quantiteRestante -= (placeRestante * batiment.ressourceActuelle.quantiteResConso[0]) / batiment.ressourceActuelle.quantiteResGeneree[0];
                etat.ressources[id].quantite += placeRestante;
                
                etat.ressources[10].quantite -= batiment.ressourceActuelle.quantiteResConso[1];
                etat.gestion.variationRessources[10] -= batiment.ressourceActuelle.quantiteResConso[1];

                etat.gestion.variationRessources[id] += placeRestante;
                etat.gestion.tabMoyennepollution[10] += batiment.ressourceActuelle.pollutionGeneree;
            }
            marcheArret(LISTEBATIMENTSPOSSIBLES, etat.planete.carte, lig, col);
            etat.events.entrepotPlein[0] = true;
        }
    } else {
        marcheArret(LISTEBATIMENTSPOSSIBLES, etat.planete.carte, lig, col);
    }
    }

    void consommer(EtatJeu etat,int lig, int col){
        CaseCarte batiment=etat.planete.carte[lig][col];
        for(int e=0;e<length(batiment.ressourceActuelle.quantiteResConso);e++){
            int idRes=batiment.ressourceActuelle.ressourcesConso[e];
            etat.ressources[idRes].quantite-=batiment.ressourceActuelle.quantiteResConso[e];
            etat.gestion.variationRessources[idRes]-=batiment.ressourceActuelle.quantiteResConso[e];
        }
    }

    void generer(EtatJeu etat, Terrain[] LISTEBATIMENTSPOSSIBLES, int lig, int col, int capaciteEntreposee) {
        CaseCarte batiment = etat.planete.carte[lig][col];
        if (etat.events.entrepotPlein[0]==false){
            for (int f = 0; f < length(batiment.ressourceActuelle.quantiteResGeneree); f++) {
                int idRes = batiment.ressourceActuelle.ressourcesGeneree[f];
                if(((batiment.ressourceActuelle.quantiteResGeneree[f]+capaciteEntreposee)<=etat.gestion.capaciteEntrepot) || batiment.ressourceActuelle.ressourcesGeneree[f]==10){// L'electricite n'est pas comprise dans le stockage
                    etat.ressources[idRes].quantite+=batiment.ressourceActuelle.quantiteResGeneree[f];
                    etat.gestion.variationRessources[idRes]+=batiment.ressourceActuelle.quantiteResGeneree[f];
                }else{
                    etat.ressources[idRes].quantite+=etat.gestion.capaciteEntrepot-capaciteEntreposee;
                    etat.gestion.variationRessources[idRes]+=etat.gestion.capaciteEntrepot-capaciteEntreposee;
                    marcheArret(LISTEBATIMENTSPOSSIBLES,etat.planete.carte,lig,col);
                    etat.events.entrepotPlein[0]=true;
                }
            }
        }else{
            marcheArret(LISTEBATIMENTSPOSSIBLES,etat.planete.carte,lig,col);
        }
    }

    void marcheArret(Terrain[] LISTEBATIMENTSPOSSIBLES,CaseCarte[][] carte,int lig, int col){
        int[]quantiteRessourceConsoPuitDF=new int[]{10,12,15,15,15};
        int[]quantiteRessourceGenereePuitDF=new int[]{10,7,5,5,1};

        CaseCarte batiment=carte[lig][col];
        int id=0;
        while(id < length(LISTEBATIMENTSPOSSIBLES) && !equals(batiment.ressourceActuelle.nom,LISTEBATIMENTSPOSSIBLES[id].nom)){
            id++;
        }
        if (batiment.ressourceActuelle.fonctionne[0]==true){
            batiment.ressourceActuelle.quantiteResConso=new int[length(LISTEBATIMENTSPOSSIBLES[id].quantiteResConso)];
            batiment.ressourceActuelle.quantiteResGeneree=new int[length(LISTEBATIMENTSPOSSIBLES[id].quantiteResGeneree)];
            batiment.ressourceActuelle.fonctionne[0]=false;
        }else if(id==10 && batiment.ressourceActuelle.fonctionne[0]==true){
            batiment.ressourceActuelle.quantiteResConso=new int[]{quantiteRessourceConsoPuitDF[batiment.ressourceActuelle.ressourcesConso[0]-2],25};
            batiment.ressourceActuelle.quantiteResConso=new int[]{quantiteRessourceGenereePuitDF[batiment.ressourceActuelle.ressourcesGeneree[0]-2]};
            batiment.ressourceActuelle.fonctionne[0]=true;
        }else{
            batiment.ressourceActuelle.quantiteResConso=LISTEBATIMENTSPOSSIBLES[id].quantiteResConso;
            batiment.ressourceActuelle.quantiteResGeneree=LISTEBATIMENTSPOSSIBLES[id].quantiteResGeneree;
            batiment.ressourceActuelle.fonctionne[0]=true;
        }
    }


//-------------------------------------------------PLANETE---------------------------------------------------------------------------------------

    // Fonction pour créer une nouvelle planète avec une carte et des ressources
    Planete newPlanete(Terrain[] ressources, int longueur, int largeur) {
        Planete planete = new Planete();
        planete.pollution = 0.0; // Initialisation du niveau de pollution à 0

        // Création de la carte de la planète avec des dimensions spécifiques
        planete.carte = new CaseCarte[largeur][longueur];
        init(planete, ressources); // Initialisation de la carte avec les ressources

        return planete; // Retourne l'objet planète
    }

    CaseCarte newCaseCarte(Terrain ressourceActuelle, int quantiteRestante, Terrain ressourceCaseInit,boolean exploitee){
        CaseCarte c = new CaseCarte();
        c.ressourceActuelle=ressourceActuelle;
        c.ressourceCaseInit=ressourceCaseInit;
        c.exploitee=exploitee;

        c.symbole=ressourceActuelle.symbole;
        c.quantiteRestante=quantiteRestante;
        return c;
    }

    // Fonction pour trouver l'indice de la ressource correspondant à une probabilité donnée
    int encadrement(Terrain[] ressources, double random) {
        int randomAbs = -1; 
        int i = 0;

        // Parcourt les ressources pour trouver l'intervalle correspondant
        while (randomAbs == -1) {
            if (ressources[i].probaApparition <= random) {
                randomAbs = i; // Indice trouvé
            }
            i += 1;
        }

        // Calcul de la différence pour ajuster l'indice si nécessaire
        double diffSup = (ressources[randomAbs - 1].probaApparition - random) ;
        double diffInf = random - ressources[randomAbs].probaApparition;

        if (randomAbs > 0 && diffSup < diffInf) {
            randomAbs -=1;
        }

        return randomAbs; // Retourne l'indice correspondant
    }

    // Initialise la carte de la planète avec des ressources naturelles
    void init(Planete planete, Terrain[] RESSOURCESINIT) {
        for (int l = 0; l < length(planete.carte, 1); l++) {
            for (int c = 0; c < length(planete.carte, 2); c++) {
                double random = (double)(int)(random() * 1000) / 1000; // Génération d'une probabilité aléatoire
                if (random == 0.0000) {
                    random += 0.0005; // Correction pour éviter une valeur nulle
                }
                // Assignation d'une ressource à la case en fonction de la probabilité
                int IDRessource=encadrement(RESSOURCESINIT, random);
                planete.carte[l][c] = newCaseCarte(RESSOURCESINIT[IDRessource],500,RESSOURCESINIT[IDRessource],false);
            }
        }
    }

    double pollutionTotale(EtatJeu etat){
        double pollutionTotale=0.0;
        for (int i=0;i<length(etat.gestion.tabMoyennepollution);i++){
            pollutionTotale+=etat.gestion.tabMoyennepollution[i];
        }
        int polluant=0;
        for (double e:etat.gestion.tabMoyennepollution){
            if (e!=0.0){
                polluant++;
            }
        }
        etat.gestion.tabMoyennepollution=new double[length(LISTEBATIMENTSPOSSIBLES)];
        return (double)(int)(((pollutionTotale/polluant)+etat.planete.pollution)*1000000)/1000000;
    }

//-------------------------------------------COLONS----------------------------------------------------------------------------------

    final int AGE_REPRODUCTION_MIN = 20; // Âge minimum pour la reproduction
    final int AGE_REPRODUCTION_MAX = 50; // Âge maximum pour la reproduction
    final double PROBABILITE_REPRODUCTION = 0.1; // Probabilité qu'un colon puisse se reproduire

    // Fonction pour créer un nouveau colon avec des caractéristiques aléatoires
    Colon newColon(int age, int id) {
        Colon colon = new Colon();
        
        colon.age = age; // Âge du colon
        colon.id = id; // Identifiant unique du colon
        colon.sante=1.0;
        colon.satisfaction = 1.0; // Satisfaction initiale du colon
        colon.energie = 1.0; // Énergie initiale du colon
        colon.anneesDerniereNaissance = 0; // Années écoulées depuis la dernière naissance

        return colon; // Retourne une instance de colon
    }

    Colon[] genererColons(int nombreColons) {
        Colon[] colons = new Colon[nombreColons];
        
        for (int i = 0; i < nombreColons; i++) {
            // Générer un âge aléatoire entre 20 et 40 ans
            int age = 20 + (int)(random() * 21);
            
            // Créer un nouveau colon avec un âge et un identifiant unique
            colons[i] = newColon(age, i + 1);
        }
        
        return colons;
    }

    // Fonction pour permettre aux colons de se reproduire
    Colon[] reproduire(Colon[] colons,EtatJeu etat) {
        // Trouver deux colons éligibles pour la reproduction
        Colon[] parents;
        if (etat.gestion.capaciteTotalePop>etat.gestion.nombreVivants){
            parents = trouverParentsEligibles(colons,etat.gestion);
        }else{
            etat.events.dortoirsPlein[0]=true;
            return colons;
        }
        if (parents[0]==null || parents[1]==null){
            return colons;
        }else{
            // Générer un nouvel enfant à partir des deux parents
            Colon nouvelEnfant = newColon(0, trouverProchainId(colons));// genère un enfant
        
            // Ajouter l'enfant à la liste des colons
            colons[etat.gestion.nombreVivants] = nouvelEnfant; // Ajouter l'enfant à l'emplacement disponible
            etat.events.naissance[findLastIndex(etat.events.naissance)]="Colon N°" + nouvelEnfant.id +" (Enfant de N°" + parents[0].id + " et N°" + parents[1].id + ")";
        
            // Réinitialiser le compteur d'années depuis la dernière naissance pour les parents
            parents[0].anneesDerniereNaissance = 0;
            parents[1].anneesDerniereNaissance = 0;
        
            return colons; // Retourner la liste des colons mise à jour
        }
    }

    // Fonction pour trouver deux colons éligibles pour la reproduction
    Colon[] trouverParentsEligibles(Colon[] colons,Gestion gestion) {
        Colon[] parents = new Colon[2];
        boolean mereTrouvee=false,pereTrouve=false;
        
        int cmpt=0;
        while(cmpt < length(colons) && (!mereTrouvee || !pereTrouve) && colons[cmpt]!=null) { 
            // Vérifier les conditions de reproduction
            boolean conditionsReproduction = (
                colons[cmpt].age >= AGE_REPRODUCTION_MIN && 
                colons[cmpt].age <= AGE_REPRODUCTION_MAX &&
                colons[cmpt].sante > 0.5 &&
                colons[cmpt].anneesDerniereNaissance >= 2
            );
            
            // Assigner le colon comme mâle ou femelle s'il remplit les conditions
            if (conditionsReproduction) {
                if (colons[cmpt].id%2==1 && mereTrouvee == false) {
                    parents[0] = colons[cmpt];
                    mereTrouvee=true;
                } else if (colons[cmpt].id%2==0 && pereTrouve == false) {
                    parents[1] = colons[cmpt];
                    pereTrouve=true;
                }
            }
            
            cmpt++;
        }
        return parents; // Retourner le couple de parents éligibles
    }

    // Fonction pour trouver le prochain identifiant unique pour un colon
    int trouverProchainId(Colon[] colons) {
        int cmpt=0;
        while(colons[cmpt]!=null && cmpt<length(colons)){
            cmpt++;
        } //
        return colons[cmpt-1].id+1; // Retourner l'ID suivant
    }

    // Fonction pour vieillir un colon
    void vieillir(EtatJeu etat,int id) {
        boolean estMort=false;
        etat.colons[id].age++; // Augmenter l'âge
 
        if(etat.colons[id].age<=20){ // Diminuer la santé
            etat.colons[id].sante-=0.02;
        }else if (etat.colons[id].age<=45){
            etat.colons[id].sante -= 0.05;
        }else{
            etat.colons[id].sante-=0.10;
        }
        // Si la santé tombe à 0 ou moins, le colon meurt
        if (etat.colons[id].sante <= 0.0) {
            etat.events.deces[findLastIndex(etat.events.deces)]="☠ Le colon N°" + etat.colons[id].id + " est décédé à l'âge de " + etat.colons[id].age + ".";
            etat.colons[id]=null;
            estMort=true;
        }else{
            if (etat.ressources[8].quantite==0){ //Si Air=0 alors le colon meurt
                etat.colons[id].sante-=1.0;
            }else{
                etat.ressources[8].quantite-=1;
                etat.gestion.variationRessources[8]-=1;
            }

            if (etat.ressources[7].quantite==0 || etat.ressources[9].quantite==0){
                etat.colons[id].sante-=0.20;
            }
            if(etat.ressources[7].quantite!=0){
                etat.ressources[7].quantite-=1;
                etat.gestion.variationRessources[7]-=1;
            }
            if(etat.ressources[9].quantite!=0){
                etat.ressources[9].quantite-=1;
                etat.gestion.variationRessources[9]-=1;
            }
        }
        // Si la santé tombe à 0 ou moins, le colon meurt
        if (!estMort){
            if (etat.colons[id].sante <= 0.0) {
                etat.events.deces[findLastIndex(etat.events.deces)]="☠ Le colon N°" + etat.colons[id].id + " est décédé à l'âge de " + etat.colons[id].age + ".";
                etat.colons[id]=null;
            }else{
                etat.colons[id].anneesDerniereNaissance++; // Incrémenter les années depuis la dernière naissance
            }
        }
    }


        // Met à jour les colons en les faisant vieillir et en affichant leur état
    void mettreAJourColons(EtatJeu etat) {
        for (int i = 0; i < length(etat.colons); i++) {
            if (etat.colons[i] !=null && etat.colons[i].sante !=0.0) {
                // Faire vieillir le colon et mettre à jour son état
                vieillir(etat,i);
                //satisfactionColon(colon[i]);
            }
        }
        triTableau(etat.colons);
    }


    void triTableau(Colon[] colons){
        for (int i = 1; i < length(colons); i++) {
            Colon current = colons[i];
            if (colons != null) {
                int j = i - 1;

                // Déplace les éléments `null` ou les cases déjà remplies plus loin
                while (j >= 0 && colons[j] == null) {
                    colons[j + 1] = colons[j];
                    j-=1;
                }

                // Place l'élément courant à la bonne position
                colons[j + 1] = current;
            }
        }
    }

    Colon[] incrementationTailleTableau(Colon[] colons,int nombreVivants){
        Colon[] colonsNewTab=new Colon[nombreVivants*2]; //double la taille du tableau pour parer à toute éventualité
        for (int i=0;i<nombreVivants;i++){
            colonsNewTab[i]=colons[i];
        }
        return colonsNewTab;
    }

    // Fonction pour compter le nombre de colons vivants
    int compterColonsVivants(Colon[] colons) {
        int nombreVivants = 0;
        while(nombreVivants<length(colons) && colons[nombreVivants]!=null){
            nombreVivants++;
        }
        return nombreVivants; // Retourner le nombre de colons vivants
    }

//----------------------------------------PARTIE------------------------------------------------------------------------------------------------------------------

    void placerVaisseau(Terrain[] LISTEBATIMENTSPOSSIBLES,EtatJeu etat){
        Terrain vaisseau=LISTEBATIMENTSPOSSIBLES[0];
        println("\n" +ANSI_BOLD + "Choisissez une Position de départ " + ANSI_RESET + "où atterir: ");
        int col=saisirColonne(length(etat.planete.carte,2));
        int lig=saisirLigne(length(etat.planete.carte,1));
        
        placerBatiment(etat,LISTEBATIMENTSPOSSIBLES,lig,col,vaisseau);
    }
    
    // Affiche l'état de la planète, y compris la pollution et les ressources
    String[][] afficherEtatCreerTab(EtatJeu etat) {
        String[][] afficherTab=new String[8][];

        afficherTab[1]=creerTabCarteAffic(etat);
        
        if(etat.tour>0){
            afficherTab[2]=creerSeparateur();
            afficherTab[3]=creerTabResAffic(etat);
            afficherTab[4]=creerSeparateur();
            afficherTab[5]=creerTabEvents(etat);
            afficherTab[6]=creerSeparateur();
            afficherTab[7]=creeTabEtatPlanete(etat);
        }else{
            for (int v=2;v<length(afficherTab,1);v++){
                afficherTab[v]=new String[27];
                for (int k=0;k<length(afficherTab[2]);k++){
                    afficherTab[v][k]="";
                }
            }
        }


        if(etat.tour==0){
            afficherTab[0]=new String[]{ANSI_BOLD + "Carte de la Planète" + ANSI_RESET+"\n"};
        }else{
            afficherTab[0]=new String[]{ANSI_BOLD + formatCharacteristic("Carte de la Planète",length(afficherTab[1][0])+3)+formatCharacteristic("Stockage",length(afficherTab[3][26])+3)+formatCharacteristic("État de la Colonie et de la planète",length(afficherTab[5][26]))+"\n"+ANSI_RESET};
        }
        return afficherTab;
    }
    
    void formatEmptyLine(String[] tab){
        int maxLine=length(tab[0]);

        for(int c=1;c<length(tab);c++){
            if (length(tab[c])>maxLine){
                maxLine=length(tab[c]);
            }
        }

        for(int y=0;y<length(tab);y++){
            if(charAt(tab[y],2)=='1'){
                tab[y]=formatCharacteristic(tab[y],maxLine+8);

            }else if(charAt(tab[y],2)=='3'){
                tab[y]=formatCharacteristic(tab[y],maxLine+9);

            }else{
                tab[y]=formatCharacteristic(tab[y],maxLine);
            }  
        }
    }

    String[] creerTabCarteAffic(EtatJeu etat){
        String[] carteTabAffic=new String[27];
        for(int i=0;i<length(carteTabAffic);i++){
            carteTabAffic[i]="   ";
        }

        int nbDecimales = length("" + length(etat.planete.carte, 1)); // Pour aligner les numéros de lignes

        // En-tête des colonnes (A, B, C, ...)
        String carteTabAffic0="";
        carteTabAffic0+="  ";
        for (int c = 1; c < length(etat.planete.carte, 2); c++) {
            if (c == 1) {
                String espace = "";
                for (int i = 0; i < nbDecimales; i++) {
                    espace += " ";
                }
                carteTabAffic0+=espace + 'A' + " ";
            } else {
                carteTabAffic0+=" " + (char) (64 + c) + " ";
            }
        }
        carteTabAffic[0]=carteTabAffic0;

        // Afficher chaque ligne de la carte avec les ressources et des informations à côté
        for (int l = 1; l < length(etat.planete.carte, 1); l++) {
            String affichageL = "" + l;
            while (length(affichageL) < nbDecimales) {
                affichageL = "0" + affichageL;
            }
            affichageL = substring(affichageL, 0, nbDecimales);
            
            carteTabAffic[l]=affichageL + " "; // Numéro de ligne
            for (int c = 1; c < length(etat.planete.carte, 2); c++) {
                String ressource = etat.planete.carte[l][c].symbole;
                String colorCode = getResourceColor(ressource,etat.planete.carte[l][c]);
                carteTabAffic[l]+=(colorCode + ressource + ANSI_RESET); // Ressources sous forme de grille
            }
        }
        return carteTabAffic;
    }

    String[] creerTabResAffic(EtatJeu etat){
        String[] resTabAffic=new String[27];
        for(int i=0;i<length(resTabAffic);i++){
            resTabAffic[i]="   ";
        }

        resTabAffic[0]=ANSI_BOLD + "Ressources Naturelles:"+ ANSI_RESET;
            
        // Ajouter des informations supplémentaires sur la même ligne
        int max=length(etat.ressources[2].nom);
        for(int a=3;a<length(etat.ressources);a++){
            if (length(etat.ressources[a].nom)>max){
                max=length(etat.ressources[a].nom);
            }
        }
        max++;

        int maxQuant=length(""+etat.ressources[2].quantite);
        for(int b=3;b<length(etat.ressources);b++){
            if (length(""+etat.ressources[b].quantite)>maxQuant){
                maxQuant=length(""+etat.ressources[b].quantite);
            }
        }
        int maxVar=length(""+etat.gestion.variationRessources[2]);
        for(int c=3;c<length(etat.gestion.variationRessources);c++){
            if (length(""+etat.gestion.variationRessources[c])>maxVar){
                maxVar=length(""+etat.gestion.variationRessources[c]);
            }
        }

        for(int p =0;p<length(etat.planete.carte, 1);p++){
            if (p>=1 && p <= 5) {
                String var=var(etat,p+1);
                resTabAffic[p]=formatCharacteristic(etat.ressources[p+1].nom,max) + " : " + formatCharacteristic(""+etat.ressources[p+1].quantite,maxQuant)+formatCharacteristic(var,maxVar);
            }else if(p>=8 && p<11){
                String var=var(etat,p-1);
                resTabAffic[p]=formatCharacteristic(etat.ressources[p-1].nom,max) + " : " + formatCharacteristic(""+etat.ressources[p-1].quantite,maxQuant)+formatCharacteristic(var,maxVar);
            }else if(p==12){
                String var=varElec(etat,p-2);
                resTabAffic[p]=formatCharacteristic(etat.ressources[p-2].nom,max) + ": " + formatCharacteristic(""+etat.ressources[p-2].quantite,maxQuant)+formatCharacteristic(var,maxVar);
            }
        }
        resTabAffic[7]=ANSI_BOLD + "Ressources Vitales:"+ ANSI_RESET;
        resTabAffic[14]=ANSI_BOLD + "Capacité de l'entrepôt"+ ANSI_RESET+": "+calcCapacitéEntrepôt(etat)+"/"+etat.gestion.capaciteEntrepot;
        
        int idDortoirs=16;
        if(etat.events.entrepotPlein[0]){
            idDortoirs++;
            resTabAffic[15]=ANSI_RED+etat.events.entrepotPleinSTR+ANSI_RESET;
            resTabAffic[idDortoirs]=ANSI_BOLD + "Lits disponibles"+ ANSI_RESET+": "+etat.gestion.nombreVivants+"/"+etat.gestion.capaciteEntrepot;
        }else{
            resTabAffic[idDortoirs]=ANSI_BOLD + "Lits disponibles"+ ANSI_RESET+": "+etat.gestion.nombreVivants+"/"+etat.gestion.capaciteTotalePop;
        }
        if(etat.events.dortoirsPlein[0]){
            resTabAffic[idDortoirs+1]=ANSI_RED+etat.events.dortoirsPleinSTR+ANSI_RESET;
        }

        formatEmptyLine(resTabAffic);
        resTabAffic[12]=substring(resTabAffic[12],0,length(resTabAffic[12])-1); //Pour une raison étrange l'électricité prend une case en trop (taille de l'émoji ?)

        return resTabAffic;
    }

    String var(EtatJeu etat,int ind){
        String var="";
        if(etat.gestion.variationRessources[ind]>0){
            var=" (+"+etat.gestion.variationRessources[ind]+")";
        }else if(etat.gestion.variationRessources[ind]<0){
            var=" ("+etat.gestion.variationRessources[ind]+")";
        }
        return var;
    }

    String varElec(EtatJeu etat,int ind){
        String var="";

        if (etat.gestion.variationRessources[ind]+100==etat.gestion.quantiteElecTourPrecedent[0]){
            return var;
        }else{
            if((etat.gestion.variationRessources[ind]+100)-etat.gestion.quantiteElecTourPrecedent[0]>0){
                var=" (+"+((etat.gestion.variationRessources[ind]+100)-etat.gestion.quantiteElecTourPrecedent[0])+")";
            }else{
                var=" (-"+(etat.gestion.quantiteElecTourPrecedent[0]-(etat.gestion.variationRessources[ind]+100))+")";
            }
            return var;
        }
    }

    String[] creerTabEvents(EtatJeu etat){
        String[] eventsTabAffic=new String[27];
        for(int i=0;i<length(eventsTabAffic);i++){
            eventsTabAffic[i]="   ";
        }

        eventsTabAffic[0]=ANSI_BOLD + "Événements:"+ ANSI_RESET;
        if (StringTabIsEmpty(etat.events.naissance) && StringTabIsEmpty(etat.events.deces) && StringTabIsEmpty(etat.events.filonEpuise) && StringTabIsEmpty(etat.events.ressourceEstEpuiseeSTR)){
            eventsTabAffic[2]="Il n'y à pas eu d'événements cette année";
        }else{
            String[] tabFusion=new String[length(etat.events.naissance)+length(etat.events.deces)+length(etat.events.filonEpuise)+length(etat.events.ressourceEstEpuiseeSTR)];
            
            int h=0;
            for(int r=0;r<length(etat.events.naissance);r++){
                tabFusion[h]=etat.events.naissance[r];
                h++;
            }
            for(int b=0;b<length(etat.events.deces);b++){
                tabFusion[h]=etat.events.deces[b];
                h++;
            }
            for(int w=0;w<length(etat.events.filonEpuise);w++){
                tabFusion[h]=etat.events.filonEpuise[w];
                h++;
            }            
            for(int w=0;w<length(etat.events.ressourceEstEpuiseeSTR);w++){
                tabFusion[h]=etat.events.ressourceEstEpuiseeSTR[w];
                h++;
            }

            int maxFusion=length(tabFusion[0]);
            for(int g=0;g<length(tabFusion);g++){
                if (length(tabFusion[g])>maxFusion){
                    maxFusion=length(tabFusion[g]);
                }
            }

            int id=1; 
            if (!StringTabIsEmpty(etat.events.naissance) || !StringTabIsEmpty(etat.events.deces)){
                int last=0;
                boolean tooMuch=false;
                eventsTabAffic[id]=ANSI_BOLD +"Colonie:"+ANSI_RESET;
                id+=2;

                if(!StringTabIsEmpty(etat.events.naissance)){
                    last=findLastIndex(etat.events.naissance);
                    if(last==1){
                        eventsTabAffic[id]=ANSI_BOLD + "Nouvelle naissance ! :" +ANSI_RESET;
                    }else{
                        eventsTabAffic[id]=ANSI_BOLD + "Nouvelles naissances ! :" +ANSI_RESET;
                    }
                    id++;
                    if(last>5){
                        last=5;
                        tooMuch=true;
                    }

                    for (int i=0;i<last;i++){
                        eventsTabAffic[id]=formatCharacteristic(etat.events.naissance[i],maxFusion);
                        id++;
                    }
                    if (tooMuch){
                        eventsTabAffic[id]="+"+(findLastIndex(etat.events.naissance)-5+" ");
                        tooMuch=false;
                        id++;
                    }
                    etat.events.naissance=new String[20];
                    tabEmptier(etat.events.naissance);
                    id++;
                }
                if(!StringTabIsEmpty(etat.events.deces)){
                    last=findLastIndex(etat.events.deces);
                    tooMuch=false;
                    if(last==1){
                        eventsTabAffic[id]=ANSI_BOLD + "Nouveau décès ! :" +ANSI_RESET;
                    }else{
                        eventsTabAffic[id]=ANSI_BOLD + "Nouveaux décès ! :" +ANSI_RESET;
                    }
                    id++;
                    if(last>5){
                        last=5;
                        tooMuch=true;
                    }
                    for (int m=0;m<last;m++){
                        eventsTabAffic[id]=formatCharacteristic(etat.events.deces[m],maxFusion);
                        id++;
                    }
                    if (tooMuch){
                        eventsTabAffic[id]="+"+(findLastIndex(etat.events.deces)-5+" ");
                        tooMuch=false;
                        id++;
                    }
                    etat.events.deces=new String[20];
                    tabEmptier(etat.events.deces);
                    id++;
                }
            }

            if(!StringTabIsEmpty(etat.events.filonEpuise) || !StringTabIsEmpty(etat.events.ressourceEstEpuiseeSTR)){
                String separator="";
                for (int s=0;s<maxFusion;s++){
                    separator=repeatChar('-',maxFusion);
                }
                if (id-1!=0){
                    eventsTabAffic[id]=separator;
                    id++;
                }
                eventsTabAffic[id]="Gestion des Ressources: ";
                id++;

                if(!StringTabIsEmpty(etat.events.ressourceEstEpuiseeSTR)){
                    for (int q=2;q<length(etat.ressources);q++){
                        if(!equals(etat.events.ressourceEstEpuiseeSTR[q],"")){
                            eventsTabAffic[id]=etat.events.ressourceEstEpuiseeSTR[q];
                            id++;
                        }
                    }
                    id++;
                }
                if(!StringTabIsEmpty(etat.events.filonEpuise)){
                    for (int a=0;a<findLastIndex(etat.events.filonEpuise);a++){
                        eventsTabAffic[id]=etat.events.filonEpuise[a];
                        id++;
                    }
                    etat.events.filonEpuise=new String[20];
                    tabEmptier(etat.events.filonEpuise);
                    id++;
                }
            }
        }

        formatEmptyLine(eventsTabAffic);

        return eventsTabAffic;
    }

    String[] creeTabEtatPlanete(EtatJeu etat){
        String[] planeteTabAffic=new String[27];
        for(int i=0;i<length(planeteTabAffic);i++){
            planeteTabAffic[i]="   ";
        }
        
        planeteTabAffic[0]=ANSI_BOLD + "État de la Planète: "+ ANSI_RESET;
        if(etat.planete.pollution>0){
            planeteTabAffic[2]=ANSI_BOLD + "Pollution: "+ ANSI_RESET+": "+String.format("%.4f", etat.planete.pollution);
        }else{
            planeteTabAffic[2]=ANSI_BOLD + "Pollution: "+ ANSI_RESET+": 0.0";
        }

        formatEmptyLine(planeteTabAffic);

        return planeteTabAffic;
    }

    String[] creerSeparateur(){
        String[] separAffic=new String[27];
        for(int e=0;e<length(separAffic);e++){
            separAffic[e]=" | ";
        }
        return separAffic;
    }

    void afficherEtat(EtatJeu etat,boolean afficherNbVivNecessaire,boolean optionInvalide){
        if(etat.tour>0){
            clearScreen(); 
            println("\n=== Année " + etat.tour + " ===\n");
        }

        String[][] affichage=afficherEtatCreerTab(etat);
        String affiche="";

        println(affichage[0][0]);
        for (int c=0;c<length(affichage[1]);c++){
            for (int l=1;l<length(affichage,1);l++){
                affiche+=affichage[l][c];
            }
            println(affiche);
            affiche="";
        }
        println();
        if(afficherNbVivNecessaire){
            println("\n" + ANSI_BOLD + "Nombre de colons survivants : "+ ANSI_RESET + etat.gestion.nombreVivants);
        }

        // CaseCarte batiment=etat.planete.carte[etat.gestion.posBat[countLastPos(etat.gestion.posBat)-1][0]][etat.gestion.posBat[countLastPos(etat.gestion.posBat)-1][1]];
        // int id=0;
        // while(id < length(LISTEBATIMENTSPOSSIBLES) && !equals(batiment.ressourceActuelle.nom,LISTEBATIMENTSPOSSIBLES[id].nom)){
        //     id++;
        // }
        // if(etat.events[id]==false){
        // int max=length(etat.events[id][0]);
        // for(int a=1;a<length(etat.events[id]);a++){
        //     if (length(etat.events[id][a])>max){
        //         max=length(etat.events[id][a]);
        //     }
        // }
        // String cadre="";
        // for (int i=0;i<max+5;i++){
        //     cadre+="-";
        // }
        // println(cadre);
        // for(int e=0;e<etat.events[id];e++){
        //     println("|"+formatCharacteristic(etat.events[id][e],max+5)+"|");
        // }
        // println(cadre);
        // println();
        // }

        if(optionInvalide){
            println(ANSI_RED + "\nOption invalide. Veuillez réessayer." + ANSI_RESET);
        }
    }

    Events newEvents(EtatJeu etat,Terrain[] LISTEBATIMENTSPOSSIBLES){
        Events e=new Events();
        e.ressourceEstEpuiseeSTR=new String[length(etat.ressources)];
        for (int a=0;a<length(etat.ressources);a++){
            e.ressourceEstEpuiseeSTR[a]="";
        }
        for (int i=0;i<length(e.filonEpuise);i++){
            e.filonEpuise[i]="";
            e.naissance[i]="";
            e.deces[i]="";
        }
        e.BatimentsPosed=new boolean[length(LISTEBATIMENTSPOSSIBLES)];
        return e;
    }

    Gestion newGestion(Terrain[] RESSOURCESINIT,Terrain[] LISTEBATIMENTSPOSSIBLES){
        Gestion g=new Gestion();
        g.tabMoyennepollution=new double[length(LISTEBATIMENTSPOSSIBLES)];
        return g;
    }

    EtatJeu initialiserNouvellePartie(Terrain[] RESSOURCESINIT,Terrain[] LISTEBATIMENTSPOSSIBLES) {
        // Créer un nouvel état de jeu
        EtatJeu nouvelEtat = new EtatJeu();
        
        //INitialisation du nom de la colonie (fichier de sauvegarde)
        nouvelEtat.nom=readStringSecurise(ANSI_BOLD + "Entrez le nom " + ANSI_RESET + "de votre nouvelle colonie : ") + ".csv";

        // Initialisation du tour à 0
        nouvelEtat.tour = 0;
        
        // Initialisation du score à 0
        nouvelEtat.score = 0.0;

        // Initialisation des ressources disponibles
        nouvelEtat.ressources = RESSOURCESINIT;

        // Création de la planète 
        int largeur = 27;
        int longueur = 27;
        nouvelEtat.planete = newPlanete(nouvelEtat.ressources, longueur, largeur);
        
        // Générer les colons initiaux
        nouvelEtat.colons = genererColons(6); // 6 colons de départ

        // Générer les paramètres initiaux
        nouvelEtat.gestion=newGestion(RESSOURCESINIT,LISTEBATIMENTSPOSSIBLES);

        nouvelEtat.events=newEvents(nouvelEtat,LISTEBATIMENTSPOSSIBLES);

        println();
        afficherEtat(nouvelEtat,false,false);
        placerVaisseau(LISTEBATIMENTSPOSSIBLES,nouvelEtat);   
        return nouvelEtat;
    }

//---------------------------------------------------------------MENUS-------------------------------------------------------------------------------------------------------------------------

    void afficherMenuJeu(EtatJeu etat,boolean invalide) {
        afficherEtat(etat,true,invalide);
        println("\n=== ACTIONS POSSIBLES ===");
        println("1. " + ANSI_BOLD + "Construire" + ANSI_RESET + " un Bâtiment");
        println("2. " + ANSI_BOLD + "Passer" + ANSI_RESET + " cette année");
        println("3. " + ANSI_BOLD + "Sauvegarder" + ANSI_RESET + " la partie");
        println("4. " + ANSI_BOLD + "Quitter" + ANSI_RESET);
        println("-------------------------");
    }

    void menuPlacerBatiment(Terrain[] LISTEBATIMENTSPOSSIBLES, EtatJeu etat,boolean recursif){
        afficherEtat(etat,true,false);
        if (recursif){
            println("\nVous ne pouvez placer aucun batiment sur cette case !\nVeuillez en saisir une autre.");
        }
        println("\n===== CONSTRUIRE =====\n");

        Terrain[] listeBatimentsPosable=new Terrain[length(LISTEBATIMENTSPOSSIBLES)];

        int col=saisirColonne(length(etat.planete.carte,2));
        int lig=saisirLigne(length(etat.planete.carte,1));

        int id=0;
        int cmpt=0;
        while(cmpt<length(LISTEBATIMENTSPOSSIBLES)){
            if(batimentPosable(etat,LISTEBATIMENTSPOSSIBLES,lig,col,LISTEBATIMENTSPOSSIBLES[cmpt])){
                listeBatimentsPosable[id]=LISTEBATIMENTSPOSSIBLES[cmpt];
                id++;
            }
            cmpt++;
        }

        id=1;
        if (TerrainTabIsEmpty(listeBatimentsPosable)){
            menuPlacerBatiment(LISTEBATIMENTSPOSSIBLES,etat,true);
            return;
        }else{
            println("\nQuel batiment voulez-vous construire sur cette case ?: \n");
            int maxLenRes=0;
            
            for(int c=0;c<length(listeBatimentsPosable);c++){
                if (listeBatimentsPosable[c] != null && length(listeBatimentsPosable[c].nom)>maxLenRes){
                    maxLenRes=length(listeBatimentsPosable[c].nom);
                }
            }

            for(int e=0;e<length(listeBatimentsPosable);e++){
                if(listeBatimentsPosable[e]!=null){
                    String recette=" ( ";
                    for (int j=0;j<length(listeBatimentsPosable[e].ResNecessaire.coutDeConstruction);j++){
                        recette+=""+listeBatimentsPosable[e].ResNecessaire.quantiteNecessaire[j]+" "+etat.ressources[listeBatimentsPosable[e].ResNecessaire.coutDeConstruction[j]].nom+", ";
                    }
                    recette=substring(recette,0,length(recette)-2)+" )";

                    println(id+" - "+ listeBatimentsPosable[e].symbole + " " +formatCharacteristic(listeBatimentsPosable[e].nom,maxLenRes+1)+recette);
                    id++;
                }
            }
            println("\n"+(id)+" - Ne pas poser de batiment");
        }

        int choix=0;
        do{
            choix = readIntSecurise(ANSI_BOLD + "Choisissez une action" + ANSI_RESET + " (1-"+(id)+") : ");
            if (choix < 1 || choix > id){
                println(ANSI_RED + "Option invalide. Veuillez réessayer." + ANSI_RESET);
            }
        }while(choix < 1 || choix > id);
        int place=0;
        if (choix==id){
            place=1;
        }
        switch (place) {
                case 0:
                    placerBatiment(etat, LISTEBATIMENTSPOSSIBLES,lig,col,listeBatimentsPosable[choix-1]);
                    return;
                case 1:
                    gestionMenuJeu(etat,LISTEBATIMENTSPOSSIBLES);
                    return;
                default:
                    println(ANSI_RED + "Option invalide. Veuillez réessayer." + ANSI_RESET);
        }
    }

    boolean gestionMenuJeu(EtatJeu etatJeu,Terrain[] LISTEBATIMENTSPOSSIBLES) {
        boolean invalide=false;
        while (true) {
            afficherMenuJeu(etatJeu,invalide);
            int choix = readIntSecurise(ANSI_BOLD + "Choisissez une action" + ANSI_RESET + " (1-4) : ");
            
            switch (choix) {
                case 1:
                    menuPlacerBatiment(LISTEBATIMENTSPOSSIBLES,etatJeu,false);
                    return true;
                case 2:
                    return true; // Passer l'année
                case 3:
                    gestionSauvegarde(etatJeu);
                    return true;
                case 4:
                    return gestionQuitter(etatJeu);
                default:
                    println(ANSI_RED + "Option invalide. Veuillez réessayer." + ANSI_RESET);
                    invalide=true;
            }
        }
    }

    void gestionSauvegarde(EtatJeu etatJeu) {
        afficherEtat(etatJeu,false,false);
        String nomFichier=etatJeu.nom;
        println("\n=== OPTIONS DE SAUVEGARDE ===");
        println("1. "+ ANSI_BOLD + "Sauvegarder " + ANSI_RESET + "et continuer");
        println("2. "+ ANSI_BOLD + "Sauvegarder et quitter" + ANSI_RESET);
        println("3. "+ ANSI_BOLD+ "Annuler" + ANSI_RESET);
        
        int choix = readIntSecurise(ANSI_BOLD + "Votre choix : " + ANSI_RESET);
        
        switch (choix) {
            case 1:
                sauvegarderJeu(etatJeu, nomFichier);
                break;
            case 2:
                sauvegarderJeu(etatJeu, nomFichier);
                System.exit(0);
                break;
            case 3:
                return;
            default:
                println(ANSI_RED + "Option invalide." + ANSI_RESET);
        }
    }

    boolean gestionQuitter(EtatJeu etatJeu) {
        afficherEtat(etatJeu,false,false);
        String nomFichier=etatJeu.nom;
        println("\n=== QUITTER ===");
        println("1. " + ANSI_BOLD + "Continuer" + ANSI_RESET + " la partie");
        println("2. " + ANSI_BOLD + "Quitter" + ANSI_RESET + " sans sauvegarder");
        println("3. " + ANSI_BOLD + "Sauvegarder et quitter" + ANSI_RESET);
        
        int choix = readIntSecurise(ANSI_BOLD + "Votre choix : " + ANSI_RESET);
        
        switch (choix) {
            case 1:
                return true; // Continuer la partie
            case 2:
                return false; // Terminer la partie sans sauvegarder
            case 3:;
                sauvegarderJeu(etatJeu, nomFichier);
                return false; // Terminer la partie après sauvegarde
            default:
                println(ANSI_RED + "Option invalide." + ANSI_RESET);
                return true;
        }
    }

    EtatJeu gestionMenuPrincipal() {
        while (true) {
                println("                                                               ");
                println("========== MENU PRINCIPAL ==========");
                println("1. Commencer une " + ANSI_BOLD + "nouvelle partie" + ANSI_RESET);
                println("2. "+ ANSI_BOLD + "Charger " + ANSI_RESET + "une ancienne sauvegarde                                            ");
                println("3. "+ ANSI_BOLD + "Quitter                                              " + ANSI_RESET);
                println("------------------------------------");
                
            int option = readIntSecurise(ANSI_BOLD + "Choisissez une option" + ANSI_RESET + " (1-3) : ");

            switch (option) {
                case 1:
                    clearScreen();
                    return initialiserNouvellePartie(RESSOURCESINIT,LISTEBATIMENTSPOSSIBLES);
                case 2:
                    clearScreen();

                    // Lister les fichiers disponibles dans le répertoire ../save/
                    String[] fichiersDisponibles = getAllFilesFromDirectory("../save/");
                    if (length(fichiersDisponibles) == 0) {
                        println(ANSI_BOLD + "Aucune sauvegarde disponible." + ANSI_RESET + " Démarrage d'une nouvelle partie.");
                        return initialiserNouvellePartie(RESSOURCESINIT, LISTEBATIMENTSPOSSIBLES);
                    }

                    // Afficher les fichiers disponibles
                    println(ANSI_BOLD + "Sauvegardes disponibles :" + ANSI_RESET);
                    for (int i = 0; i < length(fichiersDisponibles); i++) {
                        println((i + 1) + ". " + substring(fichiersDisponibles[i],0,length(fichiersDisponibles[i])-4));
                    }

                    // Demander à l'utilisateur de choisir un fichier
                    int choix = -1;
                    do {
                        choix = readIntSecurise(ANSI_BOLD + "Entrez le numéro de la sauvegarde à charger (ou 0 pour annuler) : " + ANSI_RESET);
                    } while (choix < 0 || choix > length(fichiersDisponibles));

                    if (choix == 0) {
                        clearScreen();
                        gestionMenuPrincipal();
                    }

                    // Charger le fichier sélectionné
                    String nomFichier = fichiersDisponibles[choix - 1];
                    EtatJeu etatCharge = chargerJeu(nomFichier);
                    if (etatCharge != null) {
                        return etatCharge;
                    }

                    println(ANSI_BOLD + "Chargement échoué." + ANSI_RESET + " Démarrage d'une nouvelle partie.");
                    return initialiserNouvellePartie(RESSOURCESINIT, LISTEBATIMENTSPOSSIBLES);

                case 3:
                    println("\n"+ANSI_BOLD + "Au revoir!" + ANSI_RESET+"\n");
                    System.exit(0);
                default:
                    println(ANSI_BOLD + ANSI_RED+"\nOption invalide." + ANSI_RESET + " Veuillez réessayer.");
            }
        }
    }

    void afficherResultatFinal(EtatJeu etatJeu) {
        clearScreen(); 
        if(etatJeu.gestion.nombreVivants==0){
            println(ANSI_RED_BRIGHT+"Votre colonie est éteinte. Vous avez échoué."+ANSI_RESET);
        }
        println("\n=== FIN DE LA PARTIE ===");
        println(ANSI_BOLD + "Nombre total" + ANSI_RESET + " de tours : " + etatJeu.tour);
        println(ANSI_BOLD + "Nombre final" + ANSI_RESET + " de colons : " + etatJeu.gestion.nombreVivants);
    }

//-------------------------------------------------------VOID ALGORITHM()-----------------------------------------------------------------------------------------
    // Fonction principale de l'algorithme du jeu
    void algorithm() {
        final String FILENAME = "../../../ressources/CSV-TXT/ASCII-art.txt";
        extensions.File f = newFile(FILENAME);
        int nbLines = 0;
        while(ready(f)){
            String currentLine = readLine(f);
            nbLines++;
            println(currentLine);
        }
        
        EtatJeu etatJeu = gestionMenuPrincipal();

        boolean partieTerminee = false;
        
        while (!partieTerminee) {
            // Incrémentation du tour
            etatJeu.tour++;

            if(etatJeu.tour>1){
                etatJeu.gestion.quantiteElecTourPrecedent[0]=etatJeu.ressources[10].quantite;
            }

            etatJeu.ressources[10]=newRessource("⚡ Electricité", "Elec",1.0,100);
            etatJeu.gestion.variationRessources=new int[length(etatJeu.ressources)];

            ressourceEmptyVerif(etatJeu);
            verifCapacitéEntrepot(etatJeu);
            mettreAJourBatiment(etatJeu,LISTEBATIMENTSPOSSIBLES);
            etatJeu.planete.pollution= pollutionTotale(etatJeu);
            mettreAJourColons(etatJeu);

            // Gestion de la population
            etatJeu.gestion.nombreVivants = compterColonsVivants(etatJeu.colons);
            
            // Expansion du tableau de colons si population stable
            if ((double) etatJeu.gestion.nombreVivants / (double) length(etatJeu.colons) > 0.70) {
                etatJeu.colons = incrementationTailleTableau(etatJeu.colons, etatJeu.gestion.nombreVivants);
            }

            // Reproduction des colons
            for(int i = 0; i < etatJeu.gestion.nombreVivants; i++){
                if (random() < PROBABILITE_REPRODUCTION) {
                    etatJeu.colons = reproduire(etatJeu.colons, etatJeu);
                    etatJeu.gestion.nombreVivants = compterColonsVivants(etatJeu.colons);
                }
            }

                        // Vérification des conditions de fin de partie
            if (etatJeu.gestion.nombreVivants <= 0) {
                partieTerminee = true;
            }else{
                etatJeu.gestion.nombreVivants = compterColonsVivants(etatJeu.colons);

                // etatJeu.score = calculerScore(nombreVivants, etatJeu.planete, etatJeu.ressources);
    
                // Menu de jeu
                boolean continuerTour = gestionMenuJeu(etatJeu,LISTEBATIMENTSPOSSIBLES);
                if (!continuerTour) {
                    partieTerminee=true;
                }
    
                println("\n-----------------------\n");
            }
        }
        afficherResultatFinal(etatJeu);
    }
}