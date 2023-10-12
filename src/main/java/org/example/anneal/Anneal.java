package org.example.anneal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Anneal {
    // 测试数据
    static double[][] workerTasksTime = new double[][]{
            {0.98, 1.21, 0.45, 1.78, 0.62, 1.33, 0.89, 1.15, 0.52, 1.94, 0.75, 1.62, 0.88, 1.27, 0.56, 1.05, 0.41, 1.83, 0.69, 1.42},
            {0.72, 1.56, 0.94, 1.07, 0.83, 1.29, 0.62, 1.88, 0.47, 1.75, 0.53, 1.67, 0.36, 1.96, 0.78, 1.12, 0.68, 1.45, 0.99, 1.25},
            {0.61, 1.44, 0.82, 1.72, 0.98, 1.31, 0.74, 1.17, 0.52, 1.63, 0.91, 1.28, 0.67, 1.49, 0.38, 1.76, 0.85, 1.22, 0.58, 1.94},
            {0.77, 1.09, 0.48, 1.35, 0.69, 1.22, 0.56, 1.66, 0.92, 1.11, 0.64, 1.84, 0.87, 1.05, 0.78, 1.19, 0.43, 1.53, 0.39, 1.98},
            {0.49, 1.71, 0.83, 1.12, 0.57, 1.36, 0.74, 1.64, 0.92, 1.27, 0.61, 1.05, 0.37, 1.88, 0.76, 1.44, 0.48, 1.19, 0.98, 1.33},
            {0.68, 1.14, 0.52, 1.86, 0.89, 1.31, 0.45, 1.77, 0.73, 1.23, 0.65, 1.41, 0.94, 1.09, 0.82, 1.38, 0.58, 1.67, 0.37, 1.96},
            {0.82, 1.49, 0.73, 1.32, 0.57, 1.65, 0.98, 1.07, 0.45, 1.83, 0.64, 1.28, 0.51, 1.96, 0.69, 1.12, 0.37, 1.74, 0.88, 1.19},
            {0.54, 1.28, 0.79, 1.61, 0.92, 1.45, 0.68, 1.33, 0.41, 1.72, 0.63, 1.09, 0.47, 1.84, 0.56, 1.19, 0.74, 1.36, 0.82, 1.25},
            {0.73, 1.06, 0.42, 1.57, 0.98, 1.22, 0.54, 1.31, 0.66, 1.48, 0.79, 1.15, 0.57, 1.67, 0.35, 1.82, 0.88, 1.27, 0.61, 1.42},
            {0.87, 1.34, 0.56, 1.29, 0.72, 1.95, 0.49, 1.16, 0.64, 1.21, 0.83, 1.37, 0.78, 1.02, 0.41, 1.64, 0.92, 1.47, 0.52, 1.76},
            {0.58, 1.23, 0.92, 1.04, 0.68, 1.56, 0.45, 1.81, 0.77, 1.36, 0.51, 1.67, 0.83, 1.14, 0.39, 1.29, 0.97, 1.12, 0.73, 1.42},
            {0.76, 1.05, 0.64, 1.84, 0.48, 1.37, 0.57, 1.23, 0.88, 1.32, 0.69, 1.45, 0.82, 1.11, 0.37, 1.67, 0.53, 1.29, 0.91, 1.19},
            {0.64, 1.18, 0.42, 1.71, 0.95, 1.24, 0.71, 1.38, 0.53, 1.62, 0.86, 1.05, 0.79, 1.31, 0.61, 1.08, 0.47, 1.93, 0.75, 1.27},
            {0.72, 1.33, 0.56, 1.16, 0.81, 1.48, 0.69, 1.29, 0.62, 1.91, 0.77, 1.04, 0.44, 1.62, 0.98, 1.13, 0.51, 1.38, 0.75, 1.26},
            {0.87, 1.17, 0.53, 1.26, 0.68, 1.44, 0.46, 1.59, 0.71, 1.13, 0.65, 1.71, 0.82, 1.06, 0.39, 1.33, 0.94, 1.28, 0.76, 1.22},
            {0.59, 1.78, 0.92, 1.24, 0.77, 1.05, 0.44, 1.51, 0.61, 1.39, 0.48, 1.64, 0.73, 1.11, 0.82, 1.28, 0.69, 1.17, 0.55, 1.96},
            {0.66, 1.42, 0.75, 1.21, 0.84, 1.07, 0.58, 1.35, 0.49, 1.69, 0.91, 1.19, 0.63, 1.24, 0.37, 1.53, 0.72, 1.04, 0.79, 1.16},
            {0.78, 1.13, 0.43, 1.56, 0.61, 1.85, 0.67, 1.29, 0.52, 1.47, 0.94, 1.06, 0.75, 1.22, 0.84, 1.36, 0.46, 1.63, 0.71, 1.19},
            {0.88, 1.25, 0.68, 1.42, 0.53, 1.14, 0.45, 1.31, 0.72, 1.09, 0.59, 1.76, 0.82, 1.05, 0.36, 1.49, 0.97, 1.27, 0.64, 1.38},
            {1.12, 0.87, 1.65, 0.58, 1.33, 0.74, 1.09, 0.52, 1.76, 0.98, 1.44, 0.68, 1.21, 0.45, 1.89, 0.61, 1.07, 0.36, 1.55, 0.83}
    };
    // task数目
    static Integer taskNum = 20;
    // 初始温度和α系数
    static double temperature = 100;
    static double alpha = 0.99;
    // 内循环迭代次数
    static double iteration = 15;

    // 最佳指派任务队列
    static ArrayList<Integer> bestSol = new ArrayList<>();

    static Random random = new Random();

    // 获取cost
    public static double getCost(ArrayList<Integer> sol){
        double cost = 0;
        for(int i=0;i<20;i++){
            cost += workerTasksTime[i][sol.get(i)];
        }
        return cost;
    }

    // 扰动新解
    public static ArrayList<Integer> localSearch(ArrayList<Integer> sol){
        int position1 = random.nextInt(sol.size()-1);
        int position2 = random.nextInt(sol.size()-1);
        Collections.swap(sol,position1,position2);
        return sol;
    }

    // 内循环
    public static ArrayList<Integer> innerIteration(ArrayList<Integer> sol){
        for(int i=0;i<iteration;i++){
            ArrayList<Integer> tempSol = new ArrayList<>();
            tempSol.addAll(sol);
            tempSol = localSearch(tempSol);
            if(getCost(tempSol) < getCost(sol)){
                if(getCost(tempSol) < getCost(bestSol)){
                    for(int j = 0;j<taskNum;j++){
                        bestSol.set(j,tempSol.get(j));
                    }
                }
                sol = tempSol;
            }else{
                if(Math.random()<Math.exp((getCost(tempSol)-getCost(sol))/temperature)){
                    sol = tempSol;
                }
            }
        }
        return sol;
    }

    public static void main(String[] args) {
        // 解决方案初值
        ArrayList<Integer> sol = new ArrayList<>();
        for(int i=0;i<20;i++){
            sol.add(i);
        };
        bestSol.addAll(sol);
        // 存储cost的变化情况
        ArrayList<Double> costList = new ArrayList<>();
        // 开始外循环
        for(int i=0;i<500;i++){
            sol = innerIteration(sol);
            costList.add(getCost(sol));
            temperature*=alpha;
        }

        System.out.println(costList);
        System.out.println(bestSol);
        System.out.println(getCost(bestSol));
    }
}
