
import java.util.Comparator;
/**
 *arraylist objektov jedincov zoradujem na zaklade fitnesov najdenych pokladov a instrukcii, celkovy fitnes sa riesi ako maxmalny pocet instrukcii krat (1+pocet pokladov)-pocet instrukcii
 *tento vzorec mi zaruci ze  najlpsi bude jedinec kt. najde najviac pokladov a bude mat co najmenej instrukcii
 * */
public class Compare implements Comparator<Individual>{
    FindTreasure hp=new FindTreasure();
    @Override
    public int compare(Individual j1, Individual j2) {
        int fitnes1=hp.maxinstrukcii*(1+j1.getfitnespoklady())-j1.getfitnesinstrukcia();
        int fitnes2=hp.maxinstrukcii*(1+j2.getfitnespoklady())-j2.getfitnesinstrukcia();
        return (fitnes1>fitnes2 ? -1 :(fitnes1==fitnes2) ? 0: 1);
    }

}
