import java.util.Random;

public class CreatingNewIndividual {

    //nahodne vygenerovany chromozon jedinca vsetkych 64 genov od -128 po 127.
    public Individual vytvoreniejedincanahodne(int generacia){
        Individual jedinec =new Individual();
        byte [] gen = new byte[64];
        for (int i=0;i<32;i++){
            gen[i] = (byte) nahoda(256);
        }
        for (int i=32;i<64;i++){
            gen[i] = (byte) 0;
        }
        jedinec.setgeneracia(generacia);
        jedinec.setkopiachrom(prepisgen(gen));
        jedinec.setchromozom((gen));
        jedinec.setposun("");
        jedinec.setfitnesinstrukcie(0);
        jedinec.setfitnespoklady(0);
        return jedinec;
    }
    //krizenie ktore striedavo bere geny a vytvori chromozon noveho jedinca  pri krizeni moze nastat mutovanie s istou pravdepodobnostou
    public Individual krizeniejedincanastriedacku(byte [] matka,byte [] otec,int generacia){
        Individual jedinec =new Individual();
        byte [] gen = new byte[64];
        for (int i=0;i<64;i++){
            if((i%2)==0){
                gen[i]=matka[i];
            }
            else gen[i]=otec[i];
        }
        jedinec.setgeneracia(generacia);
        jedinec.setkopiachrom(prepisgen(gen));
        jedinec.setchromozom((gen));
        jedinec.setposun("");
        jedinec.setfitnesinstrukcie(0);
        jedinec.setfitnespoklady(0);
        return mutovanieskrizenim(jedinec);
    }
    //prvych 32 genov zobere od matky dalsich od otca  pri krizeni moze nastat mutovanie s istou pravdepodobnostou
    public Individual krizeniejedincanapol(byte [] matka,byte [] otec,int generacia){
        Individual jedinec =new Individual();
        byte [] gen = new byte[64];
        for (int i=0;i<64;i++){
            if(i<32){
                gen[i]=matka[i];
            }
            else gen[i]=otec[i];
        }
        jedinec.setgeneracia(generacia);
        jedinec.setkopiachrom(prepisgen(gen));
        jedinec.setchromozom((gen));
        jedinec.setposun("");
        jedinec.setfitnesinstrukcie(0);
        jedinec.setfitnespoklady(0);
        return mutovanieskrizenim(jedinec);
    }
    //nahodne zobere od matky a nahodne od otca pri krizeni moze nastat mutovanie s istou pravdepodobnostou
    public Individual krizeniejedincanahoda(byte [] matka,byte [] otec,int generacia){
        Individual jedinec =new Individual();
        byte [] gen = new byte[64];
        int nahoda=0;
        for (int i=0;i<64;i++){
            nahoda=nahoda(2);
            if(nahoda==0){
                gen[i]=matka[i];
            }
            else gen[i]=otec[i];
        }
        jedinec.setgeneracia(generacia);
        jedinec.setkopiachrom(prepisgen(gen));
        jedinec.setchromozom((gen));
        jedinec.setposun("");
        jedinec.setfitnesinstrukcie(0);
        jedinec.setfitnespoklady(0);
        return mutovanieskrizenim(jedinec);

    }
    //toto mutovanie nastava pri postupe elitneho jedinca to novej populacii
    public Individual mutovanie(Individual jedinec,int generacia){
        byte[] gen=prepisgen((jedinec.getkopiachrom()));
        int nahodnemutovanie=nahoda(3);
        int nahodnybajt=nahoda(64);
        if(nahodnemutovanie==0){
            gen[nahodnybajt]=0;
        }
        if(nahodnemutovanie==1){
            gen[nahodnybajt]=(byte) nahoda(256);
        }
        if(nahodnemutovanie==2){
            for(int i=0;i<8;i++){
                if (((gen[nahodnybajt]>>i) &1)==1){
                    gen[nahodnybajt] &= ~( 1<< i); // nastavi bit na 0
                }
                else{
                    gen[nahodnybajt] |= (1 << i); //nastavi bit na 1
                }
            }
        }
        jedinec.setgeneracia(generacia);
        jedinec.setkopiachrom(prepisgen(gen));
        jedinec.setchromozom((gen));
        jedinec.setposun("");
        jedinec.setfitnesinstrukcie(0);
        jedinec.setfitnespoklady(0);
        return jedinec;
    }
    //toto mutovanie nastava pri novom krizeni a jedinec s istou pravdepodobnostou mutuje
    public Individual mutovanieskrizenim(Individual jedinec){
        FindTreasure hp=new FindTreasure();
        byte[] gen=jedinec.getchromozom();
        for (int i=0;i<64;i++){
            if (Math.random()<=hp.percentomutovaniakrizenim){
                int nahodnybit=nahoda(8);
                if (((gen[i]>>nahodnybit) &1)==1){
                    gen[i] &= ~( 1<< nahodnybit); // nastavi bit na 0
                }
                else{
                    gen[i] |= (1 << nahodnybit); //nastavi bit na 1
                }
            }
        }
        jedinec.setkopiachrom(prepisgen(gen));
        jedinec.setchromozom(prepisgen(gen));
        jedinec.setposun("");
        jedinec.setfitnesinstrukcie(0);
        jedinec.setfitnespoklady(0);
        return jedinec;
    }

    Random generator = new Random();
    public  int nahoda(int hranica){
        return generator.nextInt(hranica);
    }
    public byte [] prepisgen(byte [] gen){
        byte[] genn=new byte[64];
        for (int i = 0; i < 64 ; i++){
            genn[i]=gen[i];

        }
        return genn;
    }
}
