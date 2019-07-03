import java.util.Random;

public class Game {
    int numbers[][] = new int[4][4];//二位数组存游戏的16个数字，空白为0
    int before_numbers[][] = new int[4][4];//用于存此次操作前的数组
    Random random = new Random();

    Game(){
        Ini_numbers();//初始化二位数组都为0
        Create_2();
        Create_2();
        before_numbers = numbers;
    }
    //随机一个空白格赋值为2
    void Create_2(){
        int blanks = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(numbers[i][j] == 0){
                    blanks++;
                }
            }
        }
        if(blanks > 0){
            int n = random.nextInt(blanks) + 1;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if(numbers[i][j] == 0 && n != 0){
                        n--;
                    }
                    if(n == 0){
                        numbers[i][j] = 2;
                        return;
                    }
                }
            }
        }
    }
    //向左移动
    void Left(){
        for(int i = 0;i < 4;i++){
            int t = 0;
            for(int j = 0;j < 4;j++){
                if (numbers[i][j] != 0) {
                    if(t != j){
                        numbers[i][t] = numbers[i][j];
                        numbers[i][j] = 0;
                        t++;
                    } else {
                        t++;
                    }
                }
            }
        }
        for(int i = 0;i < 4;i++){
            if(numbers[i][0] == 0) continue;
            for(int j = 0;j < 3;j++){
                if(numbers[i][j] == numbers[i][j + 1] && numbers[i][j] != 0){
                    numbers[i][j] *= 2;
                    numbers[i][j + 1] = 0;
                    int k = j + 1;
                    while (k < 3){
                        numbers[i][k] = numbers[i][k + 1];
                        k++;
                    }
                    numbers[i][k] = 0;
                }
            }
        }
        Create_2();
    }
    //向上移动
    void Up(){
        for (int i = 0; i < 4; i++) {
            int t = 0;
            for (int j = 0; j < 4; j++) {
                if(numbers[j][i] != 0){
                    if(t != j){
                        numbers[t][i] = numbers[j][i];
                        numbers[j][i] = 0;
                        t++;
                    } else {
                        t++;
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            if(numbers[0][i] == 0) continue;
            for (int j = 0; j < 3; j++) {
                if(numbers[j][i] == numbers[j + 1][i] && numbers[j][i] != 0){
                    numbers[j][i] *= 2;
                    numbers[j + 1][i] =0;
                    int k = j + 1;
                    while (k < 3){
                        numbers[k][i] = numbers[k + 1][i];
                        k++;
                    }
                    numbers[k][i] = 0;
                }
            }
        }
        Create_2();
    }
    //向右移动
    void Right(){
        for (int i = 0; i < 4; i++) {
            int t = 3;
            for (int j = 3; j >= 0; j--) {
                if(numbers[i][j] != 0){
                    if(t != j){
                        numbers[i][t] = numbers[i][j];
                        numbers[i][j] = 0;
                        t--;
                    } else {
                        t--;
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            if(numbers[i][3] == 0) continue;
            for (int j = 3; j > 0; j--) {
                if(numbers[i][j] == numbers[i][j - 1] && numbers[i][j] != 0){
                    numbers[i][j] *= 2;
                    numbers[i][j - 1] = 0;
                    int k = j - 1;
                    while (k > 0){
                        numbers[i][k] = numbers[i][k - 1];
                        k--;
                    }
                    numbers[i][k] = 0;
                }
            }
        }
        Create_2();
    }
    //向右移动
    void Down(){
        for (int i = 0; i < 4; i++) {
            int t = 3;
            for (int j = 3; j >= 0; j--) {
                if(numbers[j][i] != 0){
                    if(t != j){
                        numbers[t][i] = numbers[j][i];
                        numbers[j][i] = 0;
                        t--;
                    }else {
                        t--;
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            if(numbers[3][i] == 0) continue;
            for (int j = 3; j > 0; j--) {
                if(numbers[j][i] == numbers[j - 1][i] && numbers[j][i] != 0){
                    numbers[j][i] *= 2;
                    numbers[j - 1][i] = 0;
                    int k = j - 1;
                    while (k > 0){
                        numbers[k][i] = numbers[k - 1][i];
                        k--;
                    }
                    numbers[k][i] = 0;
                }
            }
        }
        Create_2();
    }
    //初始化数组
    void Ini_numbers(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                numbers[i][j] = 0;
            }
        }
    }
    //判断游戏是否结束，返回true结束，返回false未结束
    boolean isGameOver(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if(numbers[i][j] == 0 || numbers[i][j + 1] == 0 ||numbers[i][j] == numbers[i][j + 1] || numbers[j][i] == numbers[j + 1][i]){
                    return false;
                }
            }
        }
        System.out.println("Game Over!");
        return true;
    }
    //打印数组
    void Print_arr(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(numbers[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}