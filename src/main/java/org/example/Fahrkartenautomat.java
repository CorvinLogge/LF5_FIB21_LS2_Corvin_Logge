package org.example;

import java.util.Locale;
import java.util.Scanner;

class Fahrkartenautomat {

    public static double[] ticketPreise;
    public static String[] ticketArten;
    public static double zuZahlenderBetrag;
    public static double eingezahlterGesamtbetrag;
    public static double eingeworfeneMuenze;
    public static double rueckgabebetrag;
    public static double nochZuZahlen;
    public static double ticketPreis;
    public static int anzahlTickets;
    public static int ticketArt;
    public static Scanner tastatur;
    public static void main(String[] args) {

        fahrkartenArtenKonfigurieren();

        tastatur = new Scanner(System.in).useLocale(Locale.US);


        // Auswahl der Ticketart (4.3)
        displayTicketArten();


        fahrkartenbestellungErfassen();


        // Geldeinwurf
        geldeinwurf();


        // Fahrscheinausgabe
        System.out.println("\nFahrschein wird ausgegeben");


        fakeLoadingSequence();


        // Rückgeldberechnung und -ausgabe
        rueckgeldAusgabe();


        System.out.println("""
                Vergessen Sie nicht, den Fahrschein
                vor Fahrtantritt entwerten zu lassen!
                Wir wünschen Ihnen eine gute Fahrt.""");

        tastatur.close();
    }

    private static void displayTicketArten() {
        System.out.println("Wählen Sie ihre Wunschfahrkarte für Berlin AB aus:");

        for (int i = 0; i < ticketArten.length; i++) {
            System.out.println("  " + ticketArten[i] + " [" + ticketPreise[i] + "] (" + (i + 1) + ")");
        }
        System.out.println("Bezahlen (14)");
    }

    private static void fahrkartenArtenKonfigurieren() {
        ticketPreise = new double[13];
        ticketPreise[0] = 3.00;
        ticketPreise[1] = 3.50;
        ticketPreise[2] = 3.80;
        ticketPreise[3] = 2.00;
        ticketPreise[4] = 8.60;
        ticketPreise[5] = 9.20;
        ticketPreise[6] = 10.00;
        ticketPreise[7] = 9.40;
        ticketPreise[8] = 12.60;
        ticketPreise[9] = 13.80;
        ticketPreise[10] = 25.50;
        ticketPreise[11] = 26.00;
        ticketPreise[12] = 26.50;

        ticketArten = new String[13];
        ticketArten[0] = "Einzelfahrschein AB";
        ticketArten[1] = "Einzelfahrschein BC";
        ticketArten[2] = "Einzelfahrschein ABC";
        ticketArten[3] = "Kurzstrecke AB";
        ticketArten[4] = "Tageskarte AB";
        ticketArten[5] = "Tageskarte BC";
        ticketArten[6] = "Tageskarte ABC";
        ticketArten[7] = "4-Fahrten-Karte AB";
        ticketArten[8] = "4-Fahrten-Karte BC";
        ticketArten[9] = "4-Fahrten-Karte ABC";
        ticketArten[10] = "Kleingruppen-Tageskarte AB";
        ticketArten[11] = "Kleingruppen-Tageskarte BC";
        ticketArten[12] = "Kleingruppen-Tageskarte ABC";

    }

    private static void fahrkartenbestellungErfassen() {
        zuZahlenderBetrag = 0;
        ticketArt = 0;

        while (ticketArt != 14) {                                                                                        //4.4
            System.out.print("Ticketart: ");
            ticketArt = tastatur.nextInt();
            while ((ticketArt < 1 || ticketArt > 13) && ticketArt != 14) {                                                //4.4 (teilweise)
                System.out.print("Bitte wählen sie eine gültige Ticketart: ");
                ticketArt = tastatur.nextInt();
            }


            // Nachfrage nach Ticketanzahl (4.2)
            if (ticketArt != 14) {
                ticketPreis = ticketPreise[ticketArt - 1];
                System.out.print("Anzahl der Tickets (1 bis 10): ");
                anzahlTickets = tastatur.nextInt();
                while (anzahlTickets < 1 || anzahlTickets > 10) {
                    System.out.print("Bitte geben sie eine Zahl zwischen 1 und 10 ein \n Anzahl der Tickets: ");
                    anzahlTickets = tastatur.nextInt();
                }
                zuZahlenderBetrag += ticketPreis * anzahlTickets;                                                       //4.4
            }
            System.out.println("Zwischensumme: " + zuZahlenderBetrag + "€");
        }
    }

    private static void geldeinwurf() {
        eingezahlterGesamtbetrag = 0.0;
        while (eingezahlterGesamtbetrag < zuZahlenderBetrag ) {
            nochZuZahlen = zuZahlenderBetrag - eingezahlterGesamtbetrag;
            System.out.printf("Noch zu zahlen: %4.2f Euro\n", nochZuZahlen);

            System.out.print("Eingabe (mind. 5 Cent, höchstens 2 Euro): ");
            eingeworfeneMuenze = tastatur.nextDouble();


            while (eingeworfeneMuenze != 0.05 &&
                    eingeworfeneMuenze != 0.10 &&
                    eingeworfeneMuenze != 0.20 &&
                    eingeworfeneMuenze != 0.50 &&
                    eingeworfeneMuenze != 1.00 &&
                    eingeworfeneMuenze != 2.00 &&
                    eingeworfeneMuenze != 5.00 &&
                    eingeworfeneMuenze != 10.00 &&
                    eingeworfeneMuenze != 20.00) {
                System.out.print("Bitte geben Sie einen gültigen Münz- oder Scheinbetrag ein: ");
                eingeworfeneMuenze = tastatur.nextDouble();
            }
            eingezahlterGesamtbetrag = eingezahlterGesamtbetrag + eingeworfeneMuenze;
        }
    }

    private static void fakeLoadingSequence() {

        for (int i = 0; i < 8; i++) {
            System.out.print("=");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n\n");
    }

    private static void rueckgeldAusgabe() {
        rueckgabebetrag = eingezahlterGesamtbetrag - zuZahlenderBetrag;
        if (rueckgabebetrag > 0.0) {
            System.out.format("Der Rückgabebetrag in Höhe von %4.2f Euro \n", rueckgabebetrag);
            System.out.println("wird in folgenden Münzen ausgezahlt:");

            while (rueckgabebetrag >= 2.0) { // 2-Euro-Münzen
                System.out.println("2 Euro");
                rueckgabebetrag = rueckgabebetrag - 2.0;
            }
            while (rueckgabebetrag >= 1.0) { // 1-Euro-Münzen
                System.out.println("1 Euro");
                rueckgabebetrag = rueckgabebetrag - 1.0;
            }
            while (rueckgabebetrag >= 0.5) { // 50-Cent-Münzen
                System.out.println("50 Cent");
                rueckgabebetrag = rueckgabebetrag - 0.5;
            }
            while (rueckgabebetrag >= 0.2) { // 20-Cent-Münzen
                System.out.println("20 Cent");
                rueckgabebetrag = rueckgabebetrag - 0.2;
            }
            while (rueckgabebetrag >= 0.1) { // 10-Cent-Münzen
                System.out.println("10 Cent");
                rueckgabebetrag = rueckgabebetrag - 0.1;
            }
            while (rueckgabebetrag >= 0.05) { // 5-Cent-Münzen
                System.out.println("5 Cent");
                rueckgabebetrag = rueckgabebetrag - 0.05;
            }
        }
    }
}