import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        List<Integer> arraylist = new ArrayList<>();
        arraylist =ParseFile.read(args[0]);
        randomGetTimes(arraylist);
        sortedGetTimes(arraylist);
        reverseSortedGetTimes(arraylist);

        int[] inputAxis = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251282};

        // For random data
        double[][] yAxis = new double[4][10];
        yAxis[0] = new double[]{0.00, 0.10,	0.60, 0.80, 1.20, 5.30, 19.70, 69.20, 300.60, 1219.40};
        yAxis[1] = new double[]{0.00,0.00,1.60,3.10,7.80,18.80,59.30,203.10,773.20,3030.60};
        yAxis[2] = new double[]{168.60,177.60,180.40,181.90	,182.60	,184.90	,191.30	,193.40,194.60,197.80};
        yAxis[3] = new double[]{138.40,140.7,141.6,142.3,143.4,144.5,146.4,147.6,148.7,149.8};

        // For sorted data
        double[][] syAxis = new double[4][10];
        syAxis[0] = new double[]{0.00,0.00,0.10,0.10,0.10,0.10,0.10,0.10,0.20,0.50};
        syAxis[1] = new double[]{0.60,1.50,1.50,1.60,6.30,17.20,56.20,203.10,788.80,2939.60};
        syAxis[2] = new double[]{168.60,177.60,180.40,181.90	,182.60	,184.90	,188.30	,191.40,194.60,197.80};
        syAxis[3] = new double[]{138.40,140.7,141.6,142.3,143.4,144.5,146.4,147.6,148.7,149.8};

        // For reverse sorted data
        double[][] ryAxis = new double[4][10];
        ryAxis[0] = new double[]{0.10,0.20,0.40,1.20,2.10,8.90,34.30,139.50,568.20,2134.00};
        ryAxis[1] = new double[]{0.00,0.00,1.60,3.10,6.90,16.30,60.80,209.10,785.8,2958.90};
        ryAxis[2] = new double[]{168.60,177.60,180.40,181.90 ,184.60	,185.90	,191.30	,193.40,194.60,197.80};
        ryAxis[3] = new double[]{138.40,140.7,141.6,142.3,143.4,144.5,146.4,147.6,148.7,149.8};

        // Save the char as .png and show it
        showAndSaveChart("Random Data", inputAxis, yAxis);
        showAndSaveChart("Sorted Data", inputAxis, syAxis);
        showAndSaveChart("Reverse Sorted Data", inputAxis, ryAxis);



    }
    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();
        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        chart.addSeries("Insertion", doubleX, yAxis[0]);
        chart.addSeries("Merge", doubleX, yAxis[1]);
        chart.addSeries("Pigeonhole", doubleX, yAxis[2]);
        chart.addSeries("Counting", doubleX, yAxis[2]);


        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }

    public static void randomGetTimes(List<Integer> inputs) {

        int[] inputSizes = {512,1024,2048,4096,8192,16384,32768,65536,131072,251281};


        System.out.println("Random Data");
        for (int i: inputSizes) {
            int[] arrays = new int[i];
            ArrayList<Integer> mergeArrayList = new ArrayList<>();
            for(int j=0; j < i; j++){
                arrays[j] = inputs.get(j);
                mergeArrayList.add(inputs.get(j));

            }

            double insertionavgTime = getAvgTimes(new InsertionSort() , arrays);
            System.out.println("Insertion Sort Elapsed Time in milli seconds: "+ (new DecimalFormat("#0.0000").format(insertionavgTime)) + " input size: " + i);
            double mergeavgTime = getAvgTimesMerge(mergeArrayList);
            System.out.println("Merge Sort Elapsed Time in milli seconds: "+ (new DecimalFormat("#0.0000").format(mergeavgTime)) + " input size: " + i);
            double pigeonavgTime = getAvgTimes(new PigeonholeSort() , arrays);
            System.out.println("Pigeon Sort Elapsed Time in milli seconds: "+ (new DecimalFormat("#0.0000").format(pigeonavgTime)) + " input size: " + i);
            double countingavgTime = getAvgTimes(new CountingSort() , arrays);
            System.out.println("Counting Sort Elapsed Time in milli seconds: "+ (new DecimalFormat("#0.0000").format(countingavgTime)) + " input size: " + i);
            System.out.println("-----------");

        }

    }
    public static void sortedGetTimes(List<Integer> inputs) {

        int[] inputSizes = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251281};


        System.out.println("Sorted Data");
        for (int i : inputSizes) {
            int[] arrays = new int[i];
            ArrayList<Integer> mergeArrayList = new ArrayList<>();
            for(int j=0; j < i; j++){
                arrays[j] = inputs.get(j);
                mergeArrayList.add(inputs.get(j));

            }
            arrays = new InsertionSort().Sort(arrays);
            double insertionavgTime = getAvgTimes(new InsertionSort() , arrays);
            System.out.println("Sorted Insertion Sort Elapsed Time in milli seconds: "+ (new DecimalFormat("#0.0000").format(insertionavgTime)) + " input size: " + i);
            mergeArrayList = MergeSort.Sort(mergeArrayList);
            double mergeavgTime = getAvgTimesMerge(mergeArrayList);
            System.out.println("Sorted Merge Sort Elapsed Time in milli seconds: "+ (new DecimalFormat("#0.0000").format(mergeavgTime)) + " input size: " + i);
            double pigeonavgTime = getAvgTimes(new PigeonholeSort() , arrays);
            System.out.println("Sorted Insertion Sort Elapsed Time in milli seconds: "+ (new DecimalFormat("#0.0000").format(pigeonavgTime)) + " input size: " + i);
            double countingavgTime = getAvgTimes(new CountingSort() , arrays);
            System.out.println("Sorted Insertion Sort Elapsed Time in milli seconds: "+ (new DecimalFormat("#0.0000").format(countingavgTime)) + " input size: " + i);

        }
    }
    public static void reverseSortedGetTimes(List<Integer> inputs) {

        int[] inputSizes = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251281};


        System.out.println("Reverse Sorted Data");
        for (int i : inputSizes) {
            int[] arrays = new int[i];
            ArrayList<Integer> mergeArrayList = new ArrayList<>();
            for(int j=0; j < i; j++){
                arrays[j] = inputs.get(j);
                mergeArrayList.add(inputs.get(j));

            }

            arrays = new InsertionSort().Sort(arrays);
            arrays = reverse(arrays);
            double insertionavgTime3 = getAvgTimes(new InsertionSort(), arrays);
            System.out.println("Reverse Sorted Insertion Sort Elapsed Time in milli seconds: " + (new DecimalFormat("#0.0000").format(insertionavgTime3)) + " input size: " + i);
            mergeArrayList = MergeSort.Sort(mergeArrayList);
            Collections.reverse(mergeArrayList);
            double mergeavgTime = getAvgTimesMerge(mergeArrayList);
            System.out.println("Reverse Merge Sort Elapsed Time in milli seconds: "+ (new DecimalFormat("#0.0000").format(mergeavgTime)) + " input size: " + i);
            double pigeonavgTime = getAvgTimes(new PigeonholeSort() , arrays);
            System.out.println("Reverse Sorted Insertion Sort Elapsed Time in milli seconds: "+ (new DecimalFormat("#0.0000").format(pigeonavgTime)) + " input size: " + i);
            double countingavgTime = getAvgTimes(new CountingSort() , arrays);
            System.out.println("Reverse Sorted Insertion Sort Elapsed Time in milli seconds: "+ (new DecimalFormat("#0.0000").format(countingavgTime)) + " input size: " + i);
        }
    }


    public static double getAvgTimes(BaseSort sortobject, int[] arrays) {
        double total = 0;
        int i=0;
        while(i<10){
            double start = System.currentTimeMillis();
            sortobject.Sort(arrays);
            double end =  System.currentTimeMillis();
            double duration = end - start;
            total += duration;
            i++;
        }
        double avgTime = total / 10;
        return avgTime;
    }
    public static double getAvgTimesMerge(ArrayList<Integer> arrayList) {
        double total = 0;
        int i=0;
        while(i<10){
            double start = System.currentTimeMillis();
            MergeSort.Sort(arrayList);
            double end =  System.currentTimeMillis();
            double duration = end - start;
            total += duration;
            i++;
        }
        double avgTime = total / 10;
        return avgTime;
    }
    public static int[] reverse(int[] data) {
        for (int left = 0, right = data.length - 1; left < right; left++, right--) {
            // swap the values at the left and right indices
            int temp = data[left];
            data[left]  = data[right];
            data[right] = temp;
        }
        return data;
    }

}
