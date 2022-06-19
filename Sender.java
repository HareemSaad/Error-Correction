import java.util.ArrayList;

public class Sender{
   
    static void calculateParity(CodeWord cd) {
        int cbs = cd.getOverHead().getCorrectionBitsSize();
        int cmp = 0, power = 0;
        int size = cd.getOverHead().getDataSize() + cbs;
        ArrayList<Integer> arr = cd.getDataWord();
        //insert empty places in dataword
        for (int i = 0; i < size; i++) {
            if(i+1 == Math.pow(2, power)) {
                arr.add(i, -1);
                //----------------
                power++;
                cmp++;
                if (cmp == cbs) {
                    break;
                }
            }
        }
        //calculate parity
        // for (int i = 0; i < size; i++) {
        //     System.out.print(arr.get(i) + "\t");
        // }
        System.out.println();
        int pow,x,count;
        for (int p = 0; p < cbs; p++) {
            pow = (int) Math.pow(2, p) ;
            count = 0;
            for (int i = pow - 1; i < size; i+=pow){
                x = pow;
                while(x!=0 && i < size) {
                    if(arr.get(i) == 1) {
                        count++;
                    }
                    i++;
                    x--;
                }
            }
            if(count % 2 == 0) {
                arr.set(pow-1, 0);
                cd.getOverHead().getCorrectionBits().add(0);
            } else {
                arr.set(pow-1, 1);
                cd.getOverHead().getCorrectionBits().add(1);
            }
        }
        // for (int i = 0; i < size; i++) {
        //     System.out.print(arr.get(i) + "\t");
        // }

        //System.out.println(cd.getOverHead().getCorrectionBits());
        
        return;
    }

    static void send(CodeWord cd) {
        System.out.println("Orignal Data\n" + cd.getDataWord());
        calculateParity(cd);
        System.out.println("sent data:\n" + cd.getDataWord());
        System.out.println("correction bits:\n" + cd.getOverHead().getCorrectionBits());
        Medium.carry(cd);
        System.out.println("received data:\n" + cd.getDataWord());
        Receiver.receive(cd);
    }
    public static void main(String[] args) {
        CodeWord cd = new CodeWord();
        //calculateParity(cd);
        send(cd);
    }

}