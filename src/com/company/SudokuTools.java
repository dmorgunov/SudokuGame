package com.company;

import java.util.Arrays;

public class SudokuTools {

        public static int[] getRow(int[][] grid, int row){
//            int size = grid.length;
//            int[] result = new int[size];
//            for (int n = 0; n<size; n++){
//                result[n] = grid[row][n];
//            }
//            return result;
            return grid[row];
        }
        public static int[] getColumn(int[][] grid, int column){
            int size = grid.length;
            int[] result = new int[size];
            for (int n = 0; n<size; n++){
                result[n] = grid[n][column];
            }
            return result;
        }
        public static int[] getArea(int[][] grid, int row, int column){
            int size = grid.length;
            int area = (int)Math.sqrt(grid.length);
            int[] res = new int[size];
            if (area*row > size || area*column > size) return res;
            for (int m=0; m<area; m++){
                for (int n=0;n<area;n++){
                    res[m*area+n] =grid[area*row+m][area*column+n];
                }
            }
            return res;
        }

        public static boolean isSolved(int[][] grid){
            for (int i = 0; i<grid.length; i++){
                int[] temp = SudokuTools.getColumn(grid,i);
                for (int k = 0; k< grid.length; k++){
                    //if (!Arrays.toString(temp).contains(Integer.toString(k+1))) System.out.println(k+" in " +Arrays.toString(temp));
                    if (!Arrays.toString(temp).contains(Integer.toString(k+1))) return false;
                }
            }

            for (int i = 0; i<grid.length; i++){
                int[] temp = SudokuTools.getRow(grid,i);
                for (int k = 0; k< grid.length; k++){
                    //if (!Arrays.toString(temp).contains(Integer.toString(k+1))) System.out.println(k+" in " +Arrays.toString(temp));
                    if (!Arrays.toString(temp).contains(Integer.toString(k+1))) return false;
                }
            }

            for (int i = 0; i<3; i++){
                for (int j = 0; j<3; j++){
                int[] temp = SudokuTools.getArea(grid,i, j);
                for (int k = 0; k< grid.length; k++){
                    //if (!Arrays.toString(temp).contains(Integer.toString(k+1))) System.out.println(k+" in " +Arrays.toString(temp));
                    if (!Arrays.toString(temp).contains(Integer.toString(k+1))) return false;
                }
            }}

            return true;
        }
    }

