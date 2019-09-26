package com.company;
import java.util.Scanner;
import java.util.Random;

public class Main {
    static int aantalSpelers = 0; //Het aantal spelers in het spel
    static Scanner scan = new Scanner(System.in); //Initialiseren van scanner
    static int spelDuratie; //Hoeveel vakjes er wordt gespeeld
    static String[] spelerInfo = new String[10]; //De naam van elke speler wordt hierin opgeslagen
    static int[] spelerPlaats = new int[10]; //Het vakje van elke speler (huidige progressie) wordt hierin opgeslagen
    static Random rand = new Random(); //initialiseren van Random component
    static String winnaar; //Hierin wordt de winnaar opgeslagen als diegene het spel heeft gewonnen
    static int beginner; //Degene die het spel mag beginnen.
    static int aanDeBeurt; //De speler die op dat moment aan de beurt is.

    public static void main(String[] args) { //Wordt 1 keer doorlope om bij de start van het spel de gegevens van elke speler te weten en hoeveel vakjes er gespeeld moet worden.
        System.out.println("Met hoeveel spelers wil je spelen?"); //Initialiseert met hoeveel spelers er gespeeld wordt
        aantalSpelers = scan.nextInt();
        scan.nextLine();
        System.out.println("Hoeveel speelvakken wil je spelen?"); //Initialiseert met hoeveel vakjes er gespeeld wordt
        spelDuratie = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < aantalSpelers; i++) { //Slaat voor elke speler de spelernaam op.
            System.out.println("Hoe heeft speler " + (i + 1) + "?");
            String spelerNaam = scan.nextLine(); //variabele waarin de spelernaam wordt opgeslagen van de zojuist ingevoerde speler.
            spelerInfo[i] = spelerNaam;
            spelerPlaats[i] = 0;
        }
        beginner = rand.nextInt(aantalSpelers); //Kiest een willekeurige speler die mag beginnen
        System.out.println(spelerInfo[beginner] + " mag het spel beginnen!");
        aanDeBeurt = beginner; //Zet de beginnende speler aan de beurt
        beurt(); //laat de beginnende speler beginnen met zijn beurt
    }

    public static void beurt() { //voert de beurten uit voor elke speler.
        int worp = rand.nextInt(6) + 1; //Werpt de dobbelsteen en zet de speler met het corrosponderende aantal vooruit.
        System.out.println(spelerInfo[aanDeBeurt] + " heeft " + worp + " gegooid.");
        spelerPlaats[aanDeBeurt] = spelerPlaats[aanDeBeurt] + worp;
        System.out.println(spelerInfo[aanDeBeurt] + " is beland op vakje " + spelerPlaats[aanDeBeurt]);
        voorbij(); //Kijkt of het spel voorbij is, d.w.z. iemand heeft het aantal van te voren ingevoerde vakjes gehaald.
        if (vraag() == false) { //Gaat naar de functie vraag die een vraag stelt aan de speler.
            if (aanDeBeurt < aantalSpelers - 1) { //als de vraag fout is gaat de functie naar de volgende speler.
                aanDeBeurt++;
                System.out.println(spelerInfo[aanDeBeurt] + " is aan de beurt.");
            } else {
                aanDeBeurt = 0;
                System.out.println(spelerInfo[aanDeBeurt] + " is aan de beurt.");
            }
        }
        beurt(); //herhaalt deze functie opnieuw met de speler die aandebeurt is.
        //dit gaat net zolang door tot het spel is afgelopen en de functie voorbij() het spel afsluit
    }

    public static boolean voorbij() { //checkt of het spel voorbij is en terminate het spel als dat waar is
        for (int i = 0; i < aantalSpelers; i++) { //kijkt per speler of hij/zij boven het benodigde aantal vakjes staat
            if (spelerPlaats[i] > spelDuratie) {
                winnaar = spelerInfo[i]; //slaat de winnaar op die het aantal vakjes heeft gehaald
                System.out.println("Het spel is afgelopen.");
                System.out.println(winnaar + " heeft gewonnen."); //laat zien wie er heeft gewonnen op de monitor.
                System.exit(0); //sluit het spel af
            }
        }
        return false; //als niemand heeft gewonnen returnt hij false en gaat het spel weer verder.
    }

    public static boolean vraag() { //zorgt ervoor dat er voor elke speler een vraag wordt gesteld.
        //hierna returnt hij of deze vraag correct of incorrect is beantwoord.
        if (spelerPlaats[aanDeBeurt] % 6 == 1) { //geeft de juiste categorie afhankelijk van het vakje waar de speler op komt.
            if (rekenen() == true) { //geeft een rekenvraag
                return true;
            }
            else {
                return false;
            }
        }
        else if (spelerPlaats[aanDeBeurt] % 6 == 2) {
            if (taal() == true) { //geeft een taalvraag
                return true;
            }
            else {
                return false;
            }
        }
        else if (spelerPlaats[aanDeBeurt] % 6 == 3) {
            if (levensstijl() == true) { //geeft een levensstijlvraag
                return true;
            }
            else {
                return false;
            }
        }
        else if (spelerPlaats[aanDeBeurt] % 6 == 4) {
            if (geografie() == true) { //geeft een geografievraag
                return true;
            } else {
                return false;
            }
        }
        else if (spelerPlaats[aanDeBeurt] % 6 == 5) {
            int categorie = rand.nextInt(5); //kiest een willekeurige categorie.
            if (categorie == 1) {
                if (rekenen() == true) { //geeft een rekenvraag
                    return true;
                } else {
                    return false;
                }
            } else if (categorie == 2) {
                if (taal() == true) { //geeft een taalvraag
                    return true;
                } else {
                    return false;
                }
            }
            else if (categorie == 3) {
                if (levensstijl() == true) { //geeft een levensstijl vraag
                    return true;
                } else {
                    return false;
                }
            }
            else if (categorie == 4) {
                if (geografie() == true) { //geeft een geografievraag
                    return true;
                } else {
                    return false;
                }
            }
            /*else {
                System.out.println("Error, Categorie not definied");
                System.exit(0);
            }*/
        }
        else { //als er een veelvoud van 6 wordt gegooid
            if (spelerPlaats[aanDeBeurt]%12 == 0){ //als het deelbaar is door 12 mag er nog een keer worden gegooid
                System.out.println("Gefelicteerd, je mag nog een keer gooien!");
                return true;
            }
           else{ //anders is de beurt voorbij.
                System.out.println("Helaas, je beurt is voorbij.");
                return false;
            }
        }
        return true;
    }

    public static boolean rekenen() {
        int rekenAantal = 17; //aantal vragen in de categorie rekenen.
        int vraagGesteld = rand.nextInt(rekenAantal); //de vraagGesteld is een random nummer uit het aantal rekenvragen.
        String[][] rekenVragen = {{"Hoeveel is 5*5?", "25"}, {"Hoeveel is 19-10?", "9"}, {"Hoeveel is 5*6?", "30"},
                {"Hoeveel is 28-19?", "9"}, {"Hoeveel is 72*0?", "0"}, {"Hoeveel is 15+108?", "123"}, {"Hoeveel is 21/3?", "7"},
                {"Hoeveel is 5-20?", "-15"}, {"Hoeveel is 12*2?", "24"}, {"Hoeveel is 7*8?", "56"}, {"Hoeveel is 128+72?", "200"},
                {"Hoeveel is 160-60?", "100"}, {"Hoeveel is 1+2+3+4+5?", "15"}, {"Hoeveel is 87-17?", "70"}, {"Hoeveel is 12+110?", "122"},
                {"Hoeveel is 8*9?", "72"}, {"Hoeveel is 60/3?", "20"}
        };
        System.out.println("Beantwoord de volgende vraag in de categorie rekenen:");
        System.out.println(rekenVragen[vraagGesteld][0]); //geeft de vraag
        String antwoord = scan.nextLine(); //leest het antwoord in
        if (antwoord.equals(rekenVragen[vraagGesteld][1])) {
            System.out.println("Dit antwoord is correct!"); //als correct
            return true;
        } else {
            System.out.println("dit antwoord is incorrect."); //als false
            return false;
        }
    }

    public static boolean taal() {
        int taalAantal = 16;  //aantal vragen in de categorie taal.
        int vraagGesteld = rand.nextInt(taalAantal); //de vraagGesteld is een random nummer uit het aantal vragen.
        String[][] taalVragen = {{"Het kind (spelen) op straat", "speelt"}, {"De jongen (lopen) naar school.", "loopt"},
                {"De mensen (zingen) een lied.", "zingen"}, {"De jongen heeft dat niet (doen)", "gedaan"},
                {"Het meisje heeft het ijsje (eten).", "gegeten"}, {"(lopen) je naar school?", "loop"},
                {"Gisteren (zijn) ik op school.", "was"},{"Ik (maken) een tekening.", "(maak)"},
                {"Wij (hebben) gezwommen.", "hebben"}, {"jullie (zwemmen) in het water.", "zwemmen"},
                {"Vorige week (hebben) ik een boek.", "heb"}, {"Ik zal zo (gaan)", "gaan"},
                {"Jullie zullen morgen (lezen)", "lezen"}, {"Henk (lezen) een boek", "leest"},
                {"Henk (lezen) gisteren een boek", "las"}, {"Henk zal een boek (lezen)", "lezen"}
        };
        System.out.println("Beantwoord de volgende vraag in de categorie Taal:");
        System.out.println(taalVragen[vraagGesteld][0]); //geeft de vraag
        String antwoord = scan.nextLine(); //leest het antwoord in
        if (antwoord.equals(taalVragen[vraagGesteld][1])) {
            System.out.println("Dit antwoord is correct!"); //als correct
            return true;
        } else {
            System.out.println("dit antwoord is incorrect."); //als incorrect
            return false;
        }
    }
    public static boolean levensstijl() {
        int levensstijlAantal = 15;  //aantal vragen in de categorie levensstijl.
        int vraagGesteld = rand.nextInt(levensstijlAantal); //de vraagGesteld is een random nummer uit het aantal vragen.
        String[][] levensstijlVragen = {{"Is een appel een groente of een fruit?", "fruit"},
                {"Is een banaan een groente of een fruit?", "fruit"},
                {"Is een asperge een groente of een fruit?", "groente"},
                {"Welke kleur heeft een rijpe aardbei?", "rood"},
                {"Welke kleur heeft spinazie?", "groen"},
                {"Welke groente bestaat niet? wortel, spruitje, ziade", "ziade"},
                {"Welke fruit bestaat niet? framboos, hooibes, bosbes", "hooibes"},
                {"Welk dier bestaat niet? ramvis, vinvis, walvis", "ramvis"},
                {"Is een koe een zoogdier", "ja"}, {"Is een specht een zoogdier?", "nee"},
                {"Kan een struisvogel vliegen?", "nee"},
                {"Hoe heet een beer die op de Noordpool woont?", "ijsbeer"},
                {"Leven Pinguïns op de Zuidpool of op de Noordpool?", "Noordpool"},
                {"Wat is gezonder? chocola, appel", "appel"},
                {"is een groene banaan al lekker?", "nee"}
        };
        System.out.println("Beantwoord de volgende vraag in de categorie levensstijl:");
        System.out.println(levensstijlVragen[vraagGesteld][0]); //geeft de vraag
        String antwoord = scan.nextLine(); //leest het antwoord in
        if (antwoord.equals(levensstijlVragen[vraagGesteld][1])) {
            System.out.println("Dit antwoord is correct!"); //als correct
            return true;
        } else {
            System.out.println("dit antwoord is incorrect."); // als incorrect
            return false;
        }
    }
    public static boolean geografie() {
        int geografieAantal = 21;  //aantal vragen in de categorie geografie.
        int vraagGesteld = rand.nextInt(geografieAantal); //de vraagGesteld is een random nummer uit het aantal vragen.
        String[][] geografieVragen = {{"Wat is de hoofdstad van Friesland", "Leeuwarden"},
                {"Wat is de hoofdstad van Groningen?", "Groningen"},
                {"Wat is de hoofdstad van Drenthe?", "Assen"},
                {"Wat is de hoofdstad van Overijssel?", "Zwolle"},
                {"Wat is de hoofdstad van Flevoland?", "Lelystad"},
                {"Wat is de hoofdstad van Gelderland?", "Arnhem"},
                {"Wat is de hoofdstad van Utrecht?", "Utrecht"},
                {"Wat is de hoofdstad van Noord-Holland?", "Haarlem"},
                {"Wat is de hoofdstad van Zuid-Holland?", "Den Haag"},
                {"Wat is de hoofdstad van Zeeland?", "Middelburg"},
                {"Wat is de hoofdstad van Noord-Brabant?", "Den Bosch"},
                {"Wat is de hoofdstad van Limburg?", "Maastricht"},
                {"Wat is de hoofdstad van Nederland?", "Amsterdam"},
                {"In welk land ligt Berlijn?", "Duitsland"},
                {"In welk land ligt Parijs?", "Frankrijk"},
                {"In welk land ligt Londen?", "Engeland"},
                {"Hoe heet een inwoner uit België?", "Belg"},
                {"Heeft Nederland meer of minder inwoners dan 20 miljoen?", "minder"},
                {"Welke stad bestaat niet? Madrid, Zapen, Alkmaar.", "Zapen"},
                {"Welke stad bestaat niet? Groningen, Utrecht, Limburg", "Limburg"}

        };
        System.out.println("Beantwoord de volgende vraag in de categorie geografie:");
        System.out.println(geografieVragen[vraagGesteld][0]); //geeft de vraag
        String antwoord = scan.nextLine(); //leest het antwoord in
        if (antwoord.equals(geografieVragen[vraagGesteld][1])) {
            System.out.println("Dit antwoord is correct!"); //als correct
            return true;
        } else {
            System.out.println("dit antwoord is incorrect"); //als incorrect
            return false;
        }
    }

}
