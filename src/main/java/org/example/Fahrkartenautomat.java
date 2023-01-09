package org.example;

import java.util.Locale;
import java.util.Scanner;

class Fahrkartenautomat {
    public static void main(String[] args) {

        Scanner tastatur = new Scanner(System.in).useLocale(Locale.US);

        double zuZahlenderBetrag;
        double eingezahlterGesamtbetrag;
        double eingeworfeneMuenze;
        double rueckgabebetrag;
        double nochZuZahlen;
        double ticketPreis;
        int anzahlTickets;
        int ticketArt;
        //1


        // Geldbetrag eingeben
        /*System.out.print("Ticketpreis (Euro): ");
        ticketPreis = tastatur.nextDouble();

        if (ticketPreis <= 0) { // neu
            System.out.println("Fehlerhafte Eingabe"); // neu
            ticketPreis = 1; // neu
        }*/ // neu


        // Auswahl der Ticketart (4.3)
        System.out.print("""
                Wählen Sie ihre Wunschfahrkarte für Berlin AB aus:
                  Kurzstrecke AB [2,00 EUR] (1)
                  Einzelfahrschein AB [3,00 EUR] (2)
                  Tageskarte AB [8,80 EUR] (3)
                  4-Fahrten-Karte AB [9,40 EUR] (4)
                  Bezahlen (9)
                """);

        zuZahlenderBetrag = 0;
        ticketArt = 0;

        while (ticketArt != 9) {                                                                                         //4.4
            System.out.print("Ticketart: ");
            ticketArt = tastatur.nextInt();
            while ((ticketArt < 1 || ticketArt > 4) && ticketArt != 9) {                                                //4.4 (teilweise)
                System.out.print("Bitte wählen sie eine gültige Ticketart: ");
                ticketArt = tastatur.nextInt();
            }


            switch (ticketArt) {
                case 1 -> ticketPreis = 2;
                case 2 -> ticketPreis = 3;
                case 3 -> ticketPreis = 8.80;
                case 4 -> ticketPreis = 9.40;
                default -> ticketPreis = 0;
            }


            // Nachfrage nach Ticketanzahl (4.2)
            if (ticketArt != 9) {                                                                                        //4.4
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

        // Geldeinwurf
        eingezahlterGesamtbetrag = 0.0;
        nochZuZahlen = 0.0;
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


        // Fahrscheinausgabe
        System.out.println("\nFahrschein wird ausgegeben");
        for (int i = 0; i < 8; i++) {
            System.out.print("=");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n\n");

        // Rückgeldberechnung und -ausgabe
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

        System.out.println("""

                Vergessen Sie nicht, den Fahrschein
                vor Fahrtantritt entwerten zu lassen!
                Wir wünschen Ihnen eine gute Fahrt.""");

        tastatur.close();
    }
}