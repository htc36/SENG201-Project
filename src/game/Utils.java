package game;

import java.util.Random;

public class Utils {
    /**
     * <<auto generated javadoc comment>>
     */
    public static void printSpaceshipASCII() {
        String spaceship = ""  +
            "      /\\                               \n" +
            "     (  )   vrr                         \n" +
            "     /  \\     rRr                       \n"  +
            "    /|/\\|\\     oOoo                   \n" +
            "   /_||||_\\       oOOmMM               \n" +
            "   ||||||||                               ";

        System.out.println(spaceship);
    }

    /**
     * <<auto generated javadoc comment>>
     * @param unlucky <<Param Desc>>
     */
    public static void printSpaceshipTravelling(boolean unlucky) {
        for (int i = 5; i > 0; i--) {
            System.out.print(i + ".. ");
            try { Thread.sleep(1000); } catch (InterruptedException e) {};
        }
        System.out.println();
        System.out.println("and lift off!");
        try { Thread.sleep(1000); } catch (InterruptedException e) {};
        printSpaceshipASCII();
        for (int i = 0; i < 30; i++) {
            System.out.println("     ###   ");
            if (i % 2 == 0) {
                System.out.println("    #####  ");
            }
            if (i % 5 == 0) {
                System.out.println("o---#####---o");
            }
            try { Thread.sleep(50); } catch (InterruptedException e) {};
        }

        System.out.println("     ##   ");
        System.out.println("     #   ");

        if (unlucky) {
            System.out.println();
            System.out.println();
            System.out.println("***BONK!!!*** ");
        }

    }

    /**
     * <<auto generated javadoc comment>>
     */
    public static void printCrewASCII() {
        String[] flavourText = {"heyy boss", 
            "suup boss",
            "no we're totally not high",
            "has anyone seen Jimmy",
            "I heard it's curry for dinner tonight",
            "You sure you took a shower last night?",
            "psst I've got 5 grams 5 grams",
            "why do we look all the same",
            "I can seeee sound"};

        Random rand = new Random();
        String randomFlavourText = flavourText[rand.nextInt(flavourText.length)];
        String crew = "" +
            "  o  " +
            "  o  " +
            "  o  " +
            "  o  " +
            "  o  " +
            String.format("  o  %s\n", randomFlavourText) +
            " /|\\ " +
            " /|\\ " +
            " /|\\ " +
            " /|\\ " +
            " /|\\ " +
            " /|\\ \n" +
            " /\\  " +
            "  /\\  " +
            "/\\  " +
            "  /\\  " +
            "/\\  " +
            " /\\  \n";
        System.out.println(crew);
    }

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    public static String getDeathMessage() {
        String[] templates = { "Beloved", 
            "Dearly beloved", 
            "Adored", 
            "So loved", 
            "Into the sunshine", 
            "Dearly loved", 
            "Once met, never forgotten.", 
            "Uncompromisingly unique", 
            "Remembered with love", 
            "Love you always", 
            "With a greater thing to do", 
            "Love is enough", 
            "Peace perfect peace", 
            "Deep peace of the quiet earth to you", 
            "Generous of heart, constant of faith", 
            "Love you miss you", 
            "So loved", 
            "In everloving memory of", 
            "Love is enough", 
            "May his memory be eternal", 
            "Always together", 
            "Devoted in love", 
            "Dance on..", 
            "Always loving, always loved", 
            "Your love will light my way", 
            "Asleep in Jesus", 
            "Forever in our hearts", 
            "Until we meet again", 
            "Rest in Peace", 
            "Here lies", 
            "Ever loved", 
            "Adored", 
            "So loved", 
            "An inspiration", 
            "Loved and remembered", 
            "In God's care", 
            "An inspiration to all", 
            "She walked in beauty", 
            "Together again", 
            "Love is waiting", 
            "Once met, never forgotten", 
            "A long life well lived", 
            "Remembered with love", 
            "Great love lives on"};
        Random rand = new Random();
        return templates[rand.nextInt(templates.length)];
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public static void printActionCommitFoodSelectionHeader() {
        typePrint("Index      Type        Name Price Heal Fill Cures_Plague");
        typePrint("--------------------------------------------------------");
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public static void printCrewStatusHeader() {
        String template = "";
        template += "\n";
        template += "*** Crew Members Status ***\n";
        template += "\n";
        template += "        Name       Type   Health   Luck   Plagued   Hunger   Fatique   Actions\n";
        template += "------------------------------------------------------------------------------\n";
        typePrint(template);
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public static void printSpaceshipHeader() {
        String template = "";
        template += "\n";
        template += "*** Spaceship Status ***\n";
        template += "\n";
        template += "        Name  Health\n";
        template += "--------------------\n";
        typePrint(template);
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public static void printOutpostHeader() {
        typePrint();
        typePrint("*** Welcome to the outpost ***", 30);
        typePrint();
        typePrint("Clerk: Don't forget to place your items in the bagging area", 30);
        typePrint("Clerk: Here are the things on sale today :}");
        typePrint();
        typePrint("    Type          Name Price Heal Fill Cures_Plague");
        typePrint("    -----------------------------------------------");
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public static void printActionCenterHeader() {
        String topRows = "";
        topRows += "Index        Name       Type   Health   Luck   Plagued   Hunger   Fatique   Actions";
        topRows += "\n";
        topRows += "-----------------------------------------------------------------------------------";
        typePrint(topRows);

    }

    /**
     * <<auto generated javadoc comment>>
     */
    public static void printActionCenterChoices() {
        typePrint();
        typePrint("[1] Consume food/medical supplys");
        typePrint("[2] Sleep");
        typePrint("[3] Repair ship");
        typePrint("[4] Search planet for ship pieces");
        typePrint("[5] Pilot ship to new planet");    
        typePrint();
        System.out.print("Choice (1-5) ");
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public static void printHomepageHeader() {
        typePrint();
        typePrint("******** COMMAND CENTER ********");
        typePrint("    Welcome back, captain");
        typePrint();
        typePrint("    [1] View crew status");
        typePrint("    [2] View ship status");
        typePrint("    [3] Commit action");
        typePrint("    [4] Visit Outpost");
        typePrint("    [5] Move to next day");
        typePrint();
    }

    /**
     * Prints out String message as if it was typed
     * the delay between each char can be set with param
     * delay (in miliseconds)
     * @param message the message it prints
     * @param delay delay between each chars
     */
    public static void typePrint(String message, int delay) {
        int msgLength = message.length();
        if (msgLength == 0) {
            System.out.println();
            return;
        }

        int i;
        for (i = 0; i < msgLength; i++) {
            System.out.print(message.charAt(i));
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {};
        }

        if (message.charAt(--i) != '\n') {
            System.out.println();
        }
    }

    /**
     * Prints out String message as if it was typed
     * the delay is set to 10 by default
     * @param message the message it prints
     */
    public static void typePrint(String message) {
        int msgLength = message.length();
        if (msgLength == 0) {
            System.out.println();
            return;
        }
        int i;

        for (i = 0; i < msgLength; i++) {
            System.out.print(message.charAt(i));
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {};
        }

        if (message.charAt(--i) != '\n') {
            System.out.println();
        }
    }

    /**
     * Wrapper around System.out.println() for consistencies
     */
    public static void typePrint() {
        System.out.println();
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public static void clearScreen() {
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
    }

    /**
     * <<auto generated javadoc comment>>
     * @param args <<Param Desc>>
     */
    public static void main(String[] args) {
        System.out.println(Utils.getDeathMessage());
    }


}
