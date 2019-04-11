package game;

import java.util.Random;

public class Utils {
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

    public static void printActionCommitFoodSelectionHeader() {
        typePrint("Index   Quantity   Type           Name Price Heal Fill Cures_Plague");
        typePrint("-------------------------------------------------------------------");
    }

    public static void printCrewStatusHeader() {
        String template = "";
        template += "\n";
        template += "*** Crew Members Status ***\n";
        template += "\n";
        template += "        Name       Type   Health   Luck   Plagued   Hunger   Fatique   Actions\n";
        template += "------------------------------------------------------------------------------\n";
        typePrint(template);
    }

    public static void printSpaceshipHeader() {
        String template = "";
        template += "\n";
        template += "*** Spaceship Status ***\n";
        template += "\n";
        template += "        Name  Health\n";
        template += "--------------------\n";
        typePrint(template);
    }

    public static void printOutpostHeader() {
        typePrint();
        typePrint("*** Welcome to the outpost ***", 50);
        typePrint();
        typePrint("Clerk: Don't forget to place your items in the bagging area", 30);
        typePrint("Clerk: Here are the things on sale today :}");
        typePrint();
        typePrint("    Type           Name Price Heal Fill Cures_Plague");
        typePrint("    ------------------------------------------------");
    }

    public static void printActionCenterHeader() {
        printCrewASCII();
        String topRows = "";
        topRows += "Index        Name       Type   Health   Luck   Plagued   Hunger   Fatique   Actions";
        topRows += "\n";
        topRows += "-----------------------------------------------------------------------------------";
        typePrint(topRows);

    }

    public static void printActionCenterChoices() {
        typePrint();
        typePrint("Press 1 to comsume food/medical supplys");
        typePrint("Press 2 to sleep");
        typePrint("Press 3 to repair ship");
        typePrint("Press 4 to search planet");
        typePrint("Press 5 to pilot ship to new planet");    
        typePrint();
        System.out.print("Choice (1-5) ");
    }

    public static void printHomepageHeader() {
        typePrint("Welcome to the homepage");
        typePrint("Press 1 to view crew status");
        typePrint("Press 2 to view ship status");
        typePrint("Press 3 commit action");
        typePrint("Press 4 to visit Outpost");
        typePrint("Press 5 to move to next day");
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

}
