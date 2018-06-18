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
        final int N = 100000; //quantidade de execuções
        final double LAMBDA = 5.0d;
        Double value;
        List<Integer> listaUtilizados = new ArrayList<>();
        List<Double> listaAleatorios = new ArrayList<>();
        List<Double> listaExponencial = new ArrayList<>();

        value = calculaNumAleatorioLinear (A,M,seed); //x0 da solução
        listaUtilizados.add(value.intValue());
        listaAleatorios.add(value/M);


        for (int i = 1; i < N; i++) {
            //pego sempre o valor anterior
            value = calculaNumAleatorioLinear(A,M,listaUtilizados.get(i-1).intValue());
            listaUtilizados.add(value.intValue());
            listaAleatorios.add(value/M);
        }

        for (int i = 0; i < listaUtilizados.size(); i++) {
            listaExponencial.add(calculaNumAleatorioExponencial(listaUtilizados.get(i),LAMBDA));
        }

       // plotHistogram(listaAleatorios);
        plotHistogram(listaExponencial);
    }


    /**
     * Calcula um numero aleatorio baseado na semente
     * @param a
     * @param m
     * @param seed
     * @return
     */
    private static double calculaNumAleatorioLinear (int a, int m, int seed){
        return (a*seed)%m;
    }

    private static double calculaNumAleatorioExponencial (int u,double lambda){
        double logResult = Math.log(u);
        double partialLambda = (-1/lambda);
        if (u > 0)
            return (partialLambda *logResult);
        else
            return 0;
    }

    private static void plotHistogram(List<Double> valores){
        double [] primitivo = new double[valores.size()];
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
