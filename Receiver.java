import java.util.ArrayList;

public class Receiver{
   
    static void receive (CodeWord cd) {
        int x = detection(cd);
        if(x != 0) {
            correction(cd, x);
            x = detection(cd);
            if(x != 0) {
                System.out.println("ask for retransmission");
            }
        }
    }

    static int detection(CodeWord cd) {
        //cd.getDataWord().set(11, 0);
        ArrayList<Integer> arrCheck = new ArrayList<>();
        int cbs = cd.getOverHead().getCorrectionBitsSize();
        int size = cd.getOverHead().getDataSize() + cbs;
        ArrayList<Integer> arr = cd.getDataWord();
        //calculate parity
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
                arrCheck.add(0);
            } else {
                arrCheck.add(1);
            }
        }
        // System.out.println("read this backwards"); ;
        // System.out.println(arrCheck);
        // System.out.println(cd.getOverHead().getCorrectionBits());
        System.out.println("new correction bits\n" + arrCheck);

        count = 0;
        for (int i = 0; i < cbs; i++) {
            if(arrCheck.get(i) == 1) {
                count += (int) Math.pow(2, i);
            }
        }
        System.out.println("error at: " + count);
        if(count==0) {
            System.out.println("Data Packet is error free");
        }else{
            System.out.println("Data Packet is not error free");
        }

        return count;
        
        // if(count != 0) {
        //     System.out.println("Data Packet is not error free");
        //     correction(cd, count);
        // }

        // System.out.println("Data Packet is error free");
        // return 0;
    }

    static private void correction (CodeWord cd, int toFix) {
        toFix--;
        if(cd.getDataWord().get(toFix)==1){
            cd.getDataWord().set(toFix, 0);
        } else {
            cd.getDataWord().set(toFix, 1);
        }
        System.out.println(cd.getDataWord());
    }
}