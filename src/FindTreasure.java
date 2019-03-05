
public class FindTreasure {
    int[][]mapka =
            {
                    {0,0,0,0,0,0,0},
                    {0,0,0,0,2,0,0},
                    {0,0,2,0,0,0,0},
                    {0,0,0,0,0,0,2},
                    {0,2,0,0,0,0,0},
                    {0,0,0,0,2,0,0},
                    {0,0,0,1,0,0,0}

            };
    int maxinstrukcii=500;
    //percento najlepsej populacii prenasanou do novej genaracii bez zmeny
    static double percentoelity=0.25;
    //percento novej populacii ktora vznikne krizenim a mutovanim
    static double percentokrizenia=0.7;
    //percento novej populacii ktora vznikne len mutovanim
    static double percentomutovania=0.05;
    //mutovanie s krizenim pri vytvoreni noveho jedinca moze nastat mutovanie s pravdepodobnostou
    static double percentomutovaniakrizenim=0.1;
    int velkostpopulacie=50;
    int maxpocetgeneracii =5000;
    /*
     * 0==geny sa nahodne beru od otca aj od matky
     * 1==kazdy parny gen je od matky a neparny od otca
     * 2==prvych 32 genov je od matky ostatok od otca
     */
    static int typkrizenia=0;


    public static void main(String [ ] args){
        if((percentoelity+percentokrizenia+percentomutovania)>1){throw new IllegalArgumentException();}
        EvolutionAlgorithm el = new EvolutionAlgorithm();
        el.evolucnyalgoritmus();
    }

}
