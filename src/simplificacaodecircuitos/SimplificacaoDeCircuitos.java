/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplificacaodecircuitos;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mfernandes
 */
public class SimplificacaoDeCircuitos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String str
                = "A;B;C;D;a;b;c;d;e;f;g\n"
                + "0;0;0;0;1;1;1;1;1;1;0\n"
                + "0;0;0;1;0;1;1;0;0;0;0\n"
                + "0;0;1;0;1;1;0;1;1;0;1\n"
                + "0;0;1;1;1;1;1;1;0;0;1\n"
                + "0;1;0;0;0;1;1;0;0;1;1\n"
                + "0;1;0;1;1;0;1;1;0;1;1\n"
                + "0;1;1;0;1;0;1;1;1;1;1\n"
                + "0;1;1;1;1;1;1;0;0;0;0\n"
                + "1;0;0;0;1;1;1;1;1;1;1\n"
                + "1;0;0;1;1;1;1;1;0;1;1\n"
                + "1;0;1;0;1;1;1;0;1;1;1\n"
                + "1;0;1;1;0;0;1;1;1;1;1\n"
                + "1;1;0;0;1;0;0;1;1;1;0\n"
                + "1;1;0;1;0;1;1;1;1;0;1\n"
                + "1;1;1;0;1;0;0;1;1;1;1\n"
                + "1;1;1;1;1;0;0;0;1;1;1";

        ArrayList<String[]> linhas = new ArrayList();
        boolean cabecalho = true;
        String[] cab = null;
        for (String string : str.split("\n")) {
            System.out.println(string);
            if (cabecalho) {
                cab = string.split(";");
                cabecalho = false;
                continue;
            }
            linhas.add(string.split(";"));
        }

        int entradas = 0;
        for (String string : cab) {
            if (string.matches("[A-Z]")) {
                entradas++;
            }
        }

        int saidas = cab.length - entradas;

        System.out.println("entradas " + entradas + " saidas " + saidas);

        StringBuilder sb2 = new StringBuilder();

        for (int i = 0; i < saidas; i++) {
            StringBuilder sb = new StringBuilder();
            int pos = entradas + i;

            sb.append(cab[pos]).append(" = ");

            boolean tem = false;
            for (String[] linha : linhas) {

                if ("1".equals(linha[pos])) {
                    if (tem) {
                        sb.append(" + ");
                    }
                    tem = true;
                    for (int j = 0; j < entradas; j++) {
                        sb.append(("1".equals(linha[j]) ? cab[j] : cab[j].toLowerCase()));
                    }

                }
            }
            sb2.append(sb).append(";");
            System.out.println(sb);

        }

        SimpFrame sf = new SimpFrame(sb2.toString().split(";"));
        sf.setVisible(true);

        ArrayList<String> simplificados = sf.getSimplificados();

        System.out.println("resultados:");

        for (String simplificado : simplificados) {
            System.out.println(simplificado);
        }

        // new Mostrar(sb2.toString().split(";")).setVisible(true);
        new Mostrar(simplificados.toArray(new String[]{}), sb2.toString().split(";")).setVisible(true);
    }

}
