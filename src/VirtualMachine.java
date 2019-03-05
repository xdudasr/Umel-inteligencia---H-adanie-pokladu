public class VirtualMachine {
    /**
     * metoda ktora na zaklade chromozomu, vyhodnoti jeho instrukcie, a vola metoda posunu
     */
    public Individual virtualmachine(int maxinstrukcii , int[][] mapka, Individual jedinec){
        EvolutionAlgorithm el = new EvolutionAlgorithm();
        CrossingMap mriezka=new CrossingMap();
        mriezka.prechadzaniemapky(mapka);
        int adresa=0;
        int adresainc=0;
        int instrukcia=0;
        int stav=0;
        byte [] gen =jedinec.getchromozom();
        for(int i=0;i<maxinstrukcii;i++){

            //zvysim fitnes o 1 ked spravim instrukciu
            jedinec.setfitnesinstrukcie(jedinec.getfitnesinstrukcia()+1);
            instrukcia=((gen[adresa] & 0xFF)>>6);
            adresainc=((gen[adresa] & 0xFF)&63);
            //inc
            if(instrukcia==0){
                //ak je adresa 111111 tak potom 000000
                if(pocjednotiek(gen[adresainc])==6){
                    gen[adresainc]=0;
                }
                else gen[adresainc]++;
            }
            //dec
            if(instrukcia==1){
                //ak je adresa 000000 tak potom 111111
                if((gen[adresainc])==1){
                    gen[adresainc]=63;
                }
                else gen[adresainc]--;
            }
            //skok
            if(instrukcia==2){
                adresa=adresainc;
                continue;
            }
            //vypis
            if(instrukcia==3){
                stav=0;
                if(pocjednotiek(gen[adresainc])==1 || pocjednotiek(gen[adresainc])==2){
                    jedinec.setposun(jedinec.getposun()+'U');
                    stav=mriezka.hore(jedinec);
                    if(stav==2) break;
                    if(stav==1){
                        el.koniec=true;
                        break;
                    }
                }
                if(pocjednotiek(gen[adresainc])==3 || pocjednotiek(gen[adresainc])==4){
                    jedinec.setposun(jedinec.getposun()+'D');
                    stav=mriezka.dole(jedinec);
                    if(stav==2) break;
                    if(stav==1){
                        el.koniec=true;
                        break;
                    }
                }
                if(pocjednotiek(gen[adresainc])==5 || pocjednotiek(gen[adresainc])==6){
                    jedinec.setposun(jedinec.getposun()+'R');
                    stav=mriezka.pravo(jedinec);
                    if(stav==2) break;
                    if(stav==1){
                        el.koniec=true;
                        break;
                    }
                }
                if(pocjednotiek(gen[adresainc])==7 || pocjednotiek(gen[adresainc])==8){
                    jedinec.setposun(jedinec.getposun()+'L');
                    stav=mriezka.vlavo(jedinec);
                    if(stav==2) break;
                    if(stav==1){
                        el.koniec=true;
                        break;
                    }
                }
            }
            if(adresa>62) adresa=-1;
            adresa++;

        }

        return jedinec;
    }
    //metoda na zistenie pocet jednotiek v gene
    public int pocjednotiek(byte gen){
        int pocjed=0;
        for (int i = 0; i < 8; i++)
        {
            if ((gen & 1) == 1)
            {
                pocjed++;
            }
            gen = (byte)(gen >>> 1);
        }
        return pocjed;
    }

}
