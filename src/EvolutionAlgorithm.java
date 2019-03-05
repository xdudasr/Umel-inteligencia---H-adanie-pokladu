
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class EvolutionAlgorithm {
    static ArrayList<Individual> populacia =new ArrayList<Individual> ();
    Compare por=new Compare();
    Individual jedinec=new Individual();
    static FindTreasure hp=new FindTreasure();
    static boolean menu=false;
    static boolean koniec=false;
    public  Scanner sc = new Scanner(System.in);
    ArrayList<Integer> mutanti = new ArrayList<>();


    public void evolucnyalgoritmus(){
        VirtualMachine vm=new VirtualMachine();
        CreatingNewIndividual vytjed=new CreatingNewIndividual();
        while(menu==false){
            populacia.clear();
            koniec=false;
            for (int i = 0; (i < hp.velkostpopulacie); i++){
                populacia.add(vm.virtualmachine(hp.maxinstrukcii,hp.mapka,vytjed.vytvoreniejedincanahodne(0)));
                if(koniec==true) break;
            }
            for(int i=1;i<hp.maxpocetgeneracii;i++){
                ArrayList<Individual> novapopulacia =new ArrayList<Individual> ();
                //zoradenie fitnes
                Collections.sort(populacia,por);
                //elitarstvo najlepsi preziju
                for(int l=0;l <(int) Math.round(hp.percentoelity*populacia.size()) ; l++ ){
                    novapopulacia.add(populacia.get(l));
                    if(koniec==true) break;
                }
                if(koniec==true) break;
                //mutvanie a vytvorenie noveho potomka
                int nahodnecislo=0;
                mutanti.clear();
                for(int z=0;z <(int) Math.round(hp.percentomutovania*populacia.size()) ; z++ ){
                    nahodnecislo=vytjed.nahoda(populacia.size());
                    if (mutanti.isEmpty()==true){
                        mutanti.add(nahodnecislo);
                        novapopulacia.add(vm.virtualmachine(hp.maxinstrukcii,hp.mapka,	vytjed.mutovanie(populacia.get(nahodnecislo),i)));
                        if(koniec==true) break;
                    }
                    else {
                        if(mutanti.contains(nahodnecislo)==false){
                            mutanti.add(nahodnecislo);
                            novapopulacia.add(vm.virtualmachine(hp.maxinstrukcii,hp.mapka,	vytjed.mutovanie(populacia.get(nahodnecislo),i)));
                            if(koniec==true) break;
                        }
                        else {
                            z--;
                        }
                    }
                }
                if(koniec==true) break;
                //vyberu sa dvojice na vytvorenie novych jedincov krizenim a mutovanim
                int[] novyjedinec=ruleta((int) Math.round(hp.percentokrizenia*populacia.size()));
                if(hp.typkrizenia==0){
                    //krizenim sa vytvoria novy jedinci
                    for(int l=0;l <(int) Math.round(hp.percentokrizenia*populacia.size()) ; l++ ){
                        novapopulacia.add(vm.virtualmachine(hp.maxinstrukcii,hp.mapka,
                                vytjed.krizeniejedincanahoda(populacia.get(novyjedinec[l]).getkopiachrom(),populacia.get(novyjedinec[l+1]).getkopiachrom(),i)));
                        if(koniec==true) break;
                    }
                }
                if(koniec==true) break;
                if(hp.typkrizenia==1){
                    //krizenim sa vytvoria novy jedinci
                    for(int l=0;l <(int) Math.round(hp.percentokrizenia*populacia.size()) ; l++ ){
                        novapopulacia.add(vm.virtualmachine(hp.maxinstrukcii,hp.mapka,
                                vytjed.krizeniejedincanastriedacku(populacia.get(novyjedinec[l]).getkopiachrom(),populacia.get(novyjedinec[l+1]).getkopiachrom(),i)));
                        if(koniec==true) break;
                    }
                }
                if(koniec==true) break;
                if(hp.typkrizenia==2){
                    //krizenim sa vytvoria novy jedinci
                    for(int l=0;l <(int) Math.round(hp.percentokrizenia*populacia.size()) ; l++ ){
                        novapopulacia.add(vm.virtualmachine(hp.maxinstrukcii,hp.mapka,
                                vytjed.krizeniejedincanapol(populacia.get(novyjedinec[l]).getkopiachrom(),populacia.get(novyjedinec[l+1]).getkopiachrom(),i)));
                        if(koniec==true) break;
                    }
                }
                if(koniec==true) break;
                //zvysok sa doplni nahodne vygenerovanymi jedincami
                for(int l=0;l <(int) Math.round((1-(hp.percentokrizenia+hp.percentoelity+hp.percentomutovania))*populacia.size()) ; l++ ){
                    novapopulacia.add(vm.virtualmachine(hp.maxinstrukcii,hp.mapka,vytjed.vytvoreniejedincanahodne(i)));
                    if(koniec==true) break;
                }
                if(koniec==true) break;
                populacia.clear();
                populacia=novapopulacia;
            }
            if(koniec==false){
                Collections.sort(populacia,por);
                System.out.println("Bol vycerpany pocet genracii a najlepsie doteraz najdene riesenie je nasledovne:");
                for(int i=0;i<populacia.get(0).getposun().length(); i++){
                    System.out.print(populacia.get(0).getposun().charAt(i)+" , ");
                }
            }
            System.out.println();
            int bl=zadavanieint("Prajete si zacat znova? Ano=1/Nie=0   ",sc, 0,1);
            System.out.println();
            if(bl==0) { menu= true;}
        }
    }
    public static  int[] ruleta(int pocetnovychjedincov){
        float[] segment = new float[populacia.size()];
        int[] novyjedinec = new int [pocetnovychjedincov*2];
        float interval=0;
        float sucetfitnes=0;
        float strela=0;
        for(int i=0;i<populacia.size();i++){
            sucetfitnes +=(1+(hp.maxinstrukcii*(1+(populacia.get(i).getfitnespoklady()))-populacia.get(i).getfitnesinstrukcia()));
        }
        //kazdemu jedincovy podla svojho fitnes vymedzim segment
        for(int i=0;i<populacia.size();i++){
            segment[i]=(float) (((1+(hp.maxinstrukcii*(1+populacia.get(i).getfitnespoklady())-populacia.get(i).getfitnesinstrukcia()))/sucetfitnes) +interval);
            interval=segment[i];
        }
        int l=0;
        while(l<pocetnovychjedincov*2){
            strela=(float) Math.random();
            if ((0<strela)&&(strela<segment[0])){
                if ((l==0)||((l!=0)&&(novyjedinec[l-1]!=0))){
                    novyjedinec[l]=0;
                    l++;
                }
            }
            for(int i=0;i<segment.length-1;i++){
                if((segment[i]<strela) && (strela < segment[i+1])){
                    if((l==0)||((l!=0)&&(novyjedinec[l-1]!=i))){
                        novyjedinec[l]=i;
                        l++;
                    }
                }
            }
        }
        return novyjedinec;
    }


    public static int zadavanieint(String slovo,Scanner sc, int dolhranica,int horhranica) {
        boolean pom = false;
        String riadok = "";
        while (pom != true) {
            System.out.printf("%s",slovo);
            riadok = sc.nextLine();
            if (osetrovanievstupu(riadok)
                    && (Integer.parseInt(riadok) >= dolhranica)
                    && (Integer.parseInt(riadok) <= horhranica))
                pom = true;
            else
                System.out.printf("Bola zadana nekorektna hodnota ocakavam hodnotu z intervalu od %d do %d%n",
                        dolhranica, horhranica);
        }
        return Integer.parseInt(riadok);
    }

    public static boolean osetrovanievstupu(String str) {
        try {
            String stri = (str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

