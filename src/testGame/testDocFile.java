package testGame;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class testDocFile {
    public static void main(String[] args) throws IOException {
        file();
    }

    public static void file () throws IOException {
        String urlInput = "res\\levels\\Level1.txt";

        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(urlInput));
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }
        int level = scanner.nextInt();
        int rowMap = scanner.nextInt();
        int columnMap = scanner.nextInt();
        scanner.nextLine();
        char[][] map = new char[rowMap][columnMap];

        for (int r = 0; r <rowMap; r++) {
            String s = scanner.nextLine();
            for (int cl = 0; cl < columnMap; cl++) {
                if (s.length() > 0)
                    map[r][cl] = s.charAt(cl);
            }
        }

        System.out.println("level = " + level + " row = " + rowMap + "  column =  " + columnMap);
        for (int r = 0; r <rowMap; r++) {
            for (int cl = 0; cl < columnMap; cl++) {
                System.out.print(map[r][cl]);
            }
            System.out.println();
        }
        scanner.close();
    }
}
