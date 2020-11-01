package com.company;


public class SudokuGenerator {
    private int[][] grid = new int[9][9];

    public SudokuGenerator() {
        for (int i=0; i<9; i++){
            grid[0][i] = i+1;
        }
        for (int i = 1; i<9; i++){
            for (int j = 0; j<9; j++){
                grid[i][j] = ((i*3 + i/3 + j) % (3*3) + 1);
            }
        }
    }
    public int[][] getSudoku(){
        someSwaps();
        delSells(0);
        return grid;
    }

    private void delSells(int complexity){
        int spaces = 20;//обычно считают лёгким когда на поле есть 30-35 «подсказок», средним — 25-30, и сложным — 20-25.
        switch (complexity){
            case 0: spaces = 49; break;
            case 1: spaces = 55; break;
            case 2: spaces = 58; break;
            default: spaces = 50;
        }
        for (int i = spaces; i>0; i--){
            int m = (int)(Math.random()*9);
            int n = (int)(Math.random()*9);
            if (grid[m][n] == 0){
                i++;
                continue;
            }
            grid[m][n] = 0;
        }

    }
    private void someSwaps(){
        for (int i = 0; i<3; i++) {
            swapRowSmall((int) (Math.random() * 3)+(i*3), (int) (Math.random() * 3)+(i*3));
            swapColumnSmall((int) (Math.random() * 3)+(i*3), (int) (Math.random() * 3)+(i*3));
        }
    }

    public void swapRowSmall(int rowIndex1, int rowIndex2){
        int[] temp;
        temp = SudokuTools.getRow(grid,rowIndex1);
        grid[rowIndex1] = grid[rowIndex2];
        grid[rowIndex2] = temp;
    }
    public void swapColumnSmall(int columnIndex1, int columnIndex2){
//        if (columnIndex1 > 9 || columnIndex2>9 || columnIndex1<0 || columnIndex2<0 || (columnIndex1<3 && columnIndex2>3)
//                || (columnIndex1>=3 && columnIndex1<6 && (columnIndex2>6 || columnIndex2<3))
//                || (columnIndex1>=6 && columnIndex2<6)) return;
        int temp[] = SudokuTools.getColumn(grid,columnIndex1);
        for (int i = 0; i<9; i++){
            grid[i][columnIndex1] = grid[i][columnIndex2];
            grid[i][columnIndex2] = temp[i];
        }
    }
    public void swapRowArea(int rowIndex1, int rowIndex2){
        if (rowIndex1>=3 || rowIndex2>=3) return;
        for (int i=0; i<3; i++){
            swapRowSmall(3*rowIndex1+i, 3*rowIndex2+i);
        }
    }
    public void swapColumnArea(int columnIndex1, int columnIndex2){
        if (columnIndex1>=3 || columnIndex2>=3) return;
        for (int i=0; i<3; i++){
            swapColumnSmall(3*columnIndex1+i, 3*columnIndex2+i);
        }
    }



    public void show(){
        System.out.println("===================================================");
        for (int i = 0; i<grid.length; i++){
            for (int j = 0; j<grid.length; j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }

}
