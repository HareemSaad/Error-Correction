import java.util.ArrayList;
import java.util.Scanner;

public class CodeWord {
    class OverHead {
        private int dataSize;
        private int correctionBitsSize;
        private ArrayList<Integer> correctionBits;

        private OverHead(int size) {
            dataSize = size;
            correctionBits = new ArrayList<Integer>();
            correctionBitsSize = calculateCorrectionBitSize(size);
        }

        public int getDataSize() {
            return dataSize;
        }

        public void setDataSize(int dataSize) {
            this.dataSize = dataSize;
        }

        public ArrayList<Integer> getCorrectionBits() {
            return correctionBits;
        }

        public void setCorrectionBits(ArrayList<Integer> correctionBits) {
            this.correctionBits = correctionBits;
        }

        public int getCorrectionBitsSize() {
            return correctionBitsSize;
        }

        private int calculateCorrectionBitSize(int m) {
            for(int r = 0; r < m; r++) {
                if(Math.pow(2,r) >= (m+r+1)) {
                    return r;
                }
            }
            return 0;
        }
    }
    
    private ArrayList<Integer> dataWord = new ArrayList<Integer>();
    private OverHead overHead;

    public CodeWord() {
        makeCodeWord();
    }

    private void makeCodeWord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter bit size: ");
        int size = sc.nextInt();

        //add data
        for (int i = 0; i < size; i++){
            System.out.print("Enter new bit: ");
            dataWord.add(sc.nextInt());
        }
        overHead = new OverHead(dataWord.size());
        sc.close();
    }

    

    public ArrayList<Integer> getDataWord() {
        return dataWord;
    }

    public OverHead getOverHead() {
        return overHead;
    }

    // public static void main(String[] args) {
    //     CodeWord cd1 = new CodeWord();
    // }
}
