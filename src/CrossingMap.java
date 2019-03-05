
public class CrossingMap {
    int y=0;
    int x=0;
    int pocetpokladov=0;
    int velkostx=7;
    int velkosty=7;
    int[][]mapka;
    int poklad=0;

    //metoda najde poziciu pokladov a start
    public void prechadzaniemapky(int [][]mapa){
        mapka=prepismapy(mapa);
        /**
         * hladam zaciatok mapky ktory je znaceny 1 a pocet pokladov co predstavuju 2
         */
        for (int i = 0; i < velkosty ; i++){
            for (int l = 0; l < velkostx ; l++){
                if(mapka[i][l]==1){
                    y=i;
                    x=l;
                }
                if (mapka[i][l]==2) pocetpokladov++;
            }
        }
    }

    /**
     * posun po mapke,,,slovo obsahuje NAPR. PPPHHDDLL
     */
    //stav==0 hladac nenasiel vsetky poklady vycerpal vsetky instrukcie
    //stav==1 hladac nasiel vsetky poklady
    //stav==2 hladac vysiel mimo mapy

    //ak hladac vide s mapy konci virtual machine a ide dalsi jedinec
    int stav=0;
    public int hore(Individual jedinec){
        if(y>0){
            y--;
            if(hladaniepokladov(jedinec)==true) stav=1;
        }
        else stav=2;
        return stav;
    }

    public int dole(Individual jedinec){
        if(y<velkosty-1){
            y++;
            if(hladaniepokladov(jedinec)==true) stav=1;
        }
        else stav=2;
        return stav;
    }
    public int pravo(Individual jedinec){
        if(x<velkostx-1){
            x++;
            if(hladaniepokladov(jedinec)==true) stav=1;
        }
        else stav=2;
        return stav;
    }
    public int vlavo(Individual jedinec){
        if(x>0){
            x--;
            if(hladaniepokladov(jedinec)==true) stav=1;
        }
        else stav=2;
        return stav;
    }

    //nastavuje jedincovi fitnes pokladov o jeden pri vzdy novo najdenom poklade a prepise v mapke poziciu na 0
    public boolean hladaniepokladov(Individual jedinec){
        boolean vsetkynajdene=false;
        if (mapka[y][x]==2) {
            mapka[y][x]=0;
            poklad++;
            jedinec.setfitnespoklady(jedinec.getfitnespoklady()+1);
        }
        if (pocetpokladov==poklad){
            System.out.println("Hladac nasiel vsetky poklady jeho putovanie po mapke bolo nasledovne:");
            for(int i=0;i<jedinec.getposun().length(); i++){
                System.out.print(jedinec.getposun().charAt(i)+" , ");
            }
            System.out.println();
            System.out.println("Jedinec generacii: "+ jedinec.getgeneracia());
            jedinec.getkopiaprogram();
            //jedinec.getprogram();
            vsetkynajdene=true;

        }
        return vsetkynajdene;
    }
    public int[][]prepismapy(int[][]mapka){
        int[][]mapa=new int[velkosty][velkostx];
        for (int i = 0; i < velkosty ; i++){
            for (int l = 0; l < velkostx ; l++){
                mapa[i][l]=mapka[i][l];
            }
        }
        return mapa;
    }

}


