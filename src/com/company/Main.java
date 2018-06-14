package com.company;

import java.util.ArrayList;
import java.util.List;
import org.math.plot.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        final int A = (int)Math.pow(7,5);
        final int M = Integer.MAX_VALUE;
        final int seed = 10;
        Double value;
        List<Integer> listaUtilizados = new ArrayList<Integer>();
        List<Double> listaAleatorios = new ArrayList<Double>();

        value = calculaNumAleatorio (A,M,seed);
        listaUtilizados.add(value.intValue());
        listaAleatorios.add(value/M);

        for (int i = 1; i < 10000; i++) {
            //pego sempre o valor anterior
            value = calculaNumAleatorio(A,M,listaUtilizados.get(i-1).intValue());
            listaUtilizados.add(value.intValue());
            listaAleatorios.add(value/M);
        }

        plotHistogram(listaAleatorios);
    }


    /**
     * Calcula um numero aleatorio baseado na semente
     * @param a
     * @param m
     * @param seed
     * @return
     */
    private static double calculaNumAleatorio (int a, int m, int seed){
        return (a*seed)%m;
    }

    private static void plotHistogram(List<Double> valores){
        double [] primitivo = new double[10000];
        int i =0;

        for (Double d: valores) {
            primitivo[i++] = d;
        }

        // create your PlotPanel (you can use it as a JPanel)
        Plot2DPanel plot = new Plot2DPanel();

        // add the histogram (50 slices) of x to the PlotPanel
        plot.addHistogramPlot("Log Normal population", primitivo, 50);

        // put the PlotPanel in a JFrame like a JPanel
        JFrame frame = new JFrame("a plot panel");
        frame.setSize(600, 600);
        frame.setContentPane(plot);
        frame.setVisible(true);

    }


}
