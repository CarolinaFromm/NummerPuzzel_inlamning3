import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class NummerSpel_inlämning3 extends JFrame implements ActionListener {

    // Här har jag byggt upp hela spelplanen
    // Skapat upp knapparna
    JPanel panel = new JPanel();
    JPanel spelPlan = new JPanel();
    JPanel knappPanel = new JPanel();

    JButton startaOm = new JButton("Blanda och starta om spelet");
    JButton rättOrdning = new JButton("Starta om i rätt ordning");

    JButton b1 = new JButton("1");
    JButton b2 = new JButton("2");
    JButton b3 = new JButton("3");
    JButton b4 = new JButton("4");
    JButton b5 = new JButton("5");
    JButton b6 = new JButton("6");
    JButton b7 = new JButton("7");
    JButton b8 = new JButton("8");
    JButton b9 = new JButton("9");
    JButton b10 = new JButton("10");
    JButton b11 = new JButton("11");
    JButton b12 = new JButton("12");
    JButton b13 = new JButton("13");
    JButton b14 = new JButton("14");
    JButton b15 = new JButton("15");
    JButton b16 = new JButton(" ");

    // Array list med knapparna
    List<JButton> allaKnappar = new ArrayList<>();

    JButton tomKnapp = b16;

    NummerSpel_inlämning3() {

        allaKnappar.add(b1);
        allaKnappar.add(b2);
        allaKnappar.add(b3);
        allaKnappar.add(b4);
        allaKnappar.add(b5);
        allaKnappar.add(b6);
        allaKnappar.add(b7);
        allaKnappar.add(b8);
        allaKnappar.add(b9);
        allaKnappar.add(b10);
        allaKnappar.add(b11);
        allaKnappar.add(b12);
        allaKnappar.add(b13);
        allaKnappar.add(b14);
        allaKnappar.add(b15);
        allaKnappar.add(b16);

        // Skapar upp spelplanen och bygger legot
        this.add(panel);
        panel.setLayout(new BorderLayout());
        panel.add(spelPlan, BorderLayout.CENTER);
        panel.add(knappPanel, BorderLayout.SOUTH);

        // Knapp-panelen längst ner i en flow
        knappPanel.setLayout(new FlowLayout());
        knappPanel.add(startaOm);
        knappPanel.add(rättOrdning);

        // Grid för att få ett rutmönster på spelplanen med 4x4
        spelPlan.setLayout(new GridLayout(4,4));

        // for loop och en actionlistener på knapparna
        for (JButton ak : allaKnappar){
            spelPlan.add(ak);
            ak.addActionListener(this);
        }

        //Actionlistener på knapparna i flow
        startaOm.addActionListener(this);
        rättOrdning.addActionListener(this);

        // Rutan inkl. att den ska vara i mitten(LocationRelative)
        setTitle("Nummer Puzzel");
        setVisible(true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // Metod för att planda knapparna
    public void blandaSpelet (){

        Collections.shuffle(allaKnappar);
        spelPlan.removeAll();
        for (JButton ak : allaKnappar) {
            spelPlan.add(ak);
        }
        spelPlan.revalidate();
        spelPlan.repaint();

    }
    // Metod för att återställa knapparna i rätt ordning
    // Sorterar knapparna i deras ursprungliga ordning
    public void sättRättOrdning() {

        allaKnappar.clear();
        allaKnappar.add(b1);
        allaKnappar.add(b2);
        allaKnappar.add(b3);
        allaKnappar.add(b4);
        allaKnappar.add(b5);
        allaKnappar.add(b6);
        allaKnappar.add(b7);
        allaKnappar.add(b8);
        allaKnappar.add(b9);
        allaKnappar.add(b10);
        allaKnappar.add(b11);
        allaKnappar.add(b12);
        allaKnappar.add(b13);
        allaKnappar.add(b14);
        allaKnappar.add(b15);
        allaKnappar.add(b16);


        // tar bort och lägger till
        spelPlan.removeAll();
        for (JButton ak : allaKnappar) {
            spelPlan.add(ak);
        }
        spelPlan.revalidate();
        spelPlan.repaint();
    }

    // En bool för att kontrollera om knapparna är på rätt plats
    // Först går igenom alla knappar utom sista som är tom
    // Jämför varje knapp-"text" med vad den borde vara
    // Retunerar true om de är i rätt ordning
    // Retunerar false om de är i oordninig
    public boolean ärKnapparRätt() {
        for (int i = 0; i < allaKnappar.size() - 1; i++) {
            if (!allaKnappar.get(i).getText().equals(String.valueOf(i + 1))) {
                return false;
            }
        }
        return true;
    }
    // Metod som flyttar knapparna
    // En parameter av JButton knapp som refererar till knappen användaren klickat på
    // knappIndex ger indexposisionen för knapp i listan "allaKnappar"
    // allaKnappar är listan som har alla knappar i ordning, genom indexOf(knapp) vet man var just denna knapp är
    // tomIndex gör att man vet var den tomma knappen är
    // Math.abs är "fel" spelet kan flytta från höger ner ett steg på vänster sida
    public void flyttaKnapp(JButton knapp) {
        int knappIndex = allaKnappar.indexOf(knapp);
        int tomIntex = allaKnappar.indexOf(tomKnapp);

        if (Math.abs(knappIndex - tomIntex) == 1  || Math.abs(knappIndex - tomIntex) == 4) {
            Collections.swap(allaKnappar, knappIndex, tomIntex);
            uppdateraSpelPlan();

            // Om knapparna är rätt skickas det ut en JOpane som säger att spelet är vunnet
            if (ärKnapparRätt()){
                JOptionPane.showMessageDialog(this,"Grattis du har vunnit spelet!");
            }
        }
    }

    // Uppdaterar spelplanen när en knapp flyttats
    public void uppdateraSpelPlan() {
        spelPlan.removeAll();
        for (JButton ak : allaKnappar) {
            spelPlan.add(ak);
        }
        spelPlan.revalidate();
        spelPlan.repaint();

    }

    // Action event
    // Om knappen "startaOm" klickas på anropas "blandaSpelet"
    // Om knappen "rättOrdning" klickas på anropas metoden "sättRättOrdning"
    // Om eventet inte kom från de knapparna anropas metoden "flyttaKnapp"
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startaOm) {
            blandaSpelet();
        } else if (e.getSource() == rättOrdning) {
            sättRättOrdning();
        } else { JButton knapp = (JButton) e.getSource();
            flyttaKnapp(knapp);

        }
    }
    public static void main(String[] args) {
        new NummerSpel_inlämning3();
    }

}
