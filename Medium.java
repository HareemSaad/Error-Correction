public class Medium {
    public static void carry(CodeWord cd) {
        int random = (int)(Math.random()*2);
        if (random == 1) {
            int random_place = (int)(Math.random()*cd.getOverHead().getDataSize());
            //int random_place = 7;
            System.out.println(random_place);
            if(cd.getDataWord().get(random_place) == 0) {
                cd.getDataWord().set(random_place, 1);
            } else {
                cd.getDataWord().set(random_place, 0);
            }
        }
    }
}
